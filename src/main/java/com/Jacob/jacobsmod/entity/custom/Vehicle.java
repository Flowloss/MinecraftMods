package com.Jacob.jacobsmod.entity.custom;

import com.Jacob.jacobsmod.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.List;

public class Vehicle extends Entity {
//    public static final EntityType<Vehicle> VEHICLE = register("boat", EntityType.Builder.of(Boat::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10));
    private final static EntityDataAccessor<Integer> DATA_ID_HURT;
    private final static EntityDataAccessor<Integer> DATA_ID_HURTDIR;
    private final static EntityDataAccessor<Float> DATA_ID_DAMAGE;
    private final static EntityDataAccessor<Integer> DATA_ID_TYPE;

    private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYRot;
    private double lerpXRot;

    private float invFriction;
    private float outOfControlTicks;
    private float deltaRotation;

    private double waterLevel;

    private boolean inputLeft;
    private boolean inputRight;
    private boolean inputUp;
    private boolean inputDown;
    private boolean inputMouseUp;
    private boolean inputMouseDown;

    // Change this! :)
    private float landFriction;
    
    // Setter or in constructor
    private float maxSpeed = 0.24f;
    private float currentSpeed;
    private float throttle;

    private Vehicle.Status status;
    private Vehicle.Status oldStatus;
    private double lastYd;

    public static enum Status {
        IN_WATER,
        UNDER_WATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR;

        private Status() {
        }
    }
    
    public Vehicle(EntityType<? extends Vehicle> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public Vehicle(Level level, double xo, double yo, double zo) {
        this(ModEntities.CROP_DUSTER.get(), level);
        this.setPos(xo, yo, zo);
        this.xo = xo;
        this.yo = yo;
        this.zo = zo;
    }

    protected float getSinglePassengerXOffset() {
        return 0.0F;
    }

    protected void clampRotation(Entity p_38322_) {
        p_38322_.setYBodyRot(this.getYRot());
        float f = Mth.wrapDegrees(p_38322_.getYRot() - this.getYRot());
        float f1 = Mth.clamp(f, -105.0F, 105.0F);
        p_38322_.yRotO += f1 - f;
        p_38322_.setYRot(p_38322_.getYRot() + f1 - f);
        p_38322_.setYHeadRot(p_38322_.getYRot());
    }

    @Override
    public double getMyRidingOffset() {
        return -1.0f;
    }

    protected void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
//         vec3 = (new Vec3((double)0f, 0.0, 0.0)).yRot(-this.getYRot() * 0.017453292F - 1.5707964F);
//        moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + (double)-1f, this.getZ() + vec3.z);
//        
        if (this.hasPassenger(entity)) {
            float xOffset = this.getSinglePassengerXOffset();
            float yOffset = (float)((this.isRemoved() ? 0.009999999776482582 : this.getPassengersRidingOffset()) + entity.getMyRidingOffset());
            if (this.getPassengers().size() > 1) {
                int i = this.getPassengers().indexOf(entity);
                if (i == 0) {
                    xOffset = 0.2F;
                } else {
                    xOffset = -0.6F;
                }
            }

            Vec3 vec3 = (new Vec3((double)xOffset, 0.0, 0.0)).yRot(-this.getYRot() * 0.017453292F - 1.5707964F);
            moveFunction.accept(entity, this.getX() + vec3.x, this.getY() + (double)yOffset, this.getZ() + vec3.z);
            entity.setYRot(entity.getYRot() + this.deltaRotation);
            entity.setYHeadRot(entity.getYHeadRot() + this.deltaRotation);
            this.clampRotation(entity);
            if (entity instanceof Animal && this.getPassengers().size() == this.getMaxPassengers()) {
                int j = entity.getId() % 2 == 0 ? 90 : 270;
                entity.setYBodyRot(((Animal)entity).yBodyRot + (float)j);
                entity.setYHeadRot(entity.getYHeadRot() + (float)j);
            }
        }

    }

    public void setInput(boolean p_38343_, boolean p_38344_, boolean p_38345_, boolean p_38346_) {
        this.inputLeft = p_38343_;
        this.inputRight = p_38344_;
        this.inputUp = p_38345_;
        this.inputDown = p_38346_;
    }

    public InteractionResult interact(Player p_38330_, InteractionHand p_38331_) {
        if (p_38330_.isSecondaryUseActive()) {
            return InteractionResult.PASS;
        } else if (this.outOfControlTicks < 60.0F) {
            if (!this.level().isClientSide) {
                return p_38330_.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
            } else {
                return InteractionResult.SUCCESS;
            }
        } else {
            return InteractionResult.PASS;
        }
    }
    
    protected float getEyeHeight(Pose pose, EntityDimensions entityDimensions) {
        return entityDimensions.height;
    }

    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.EVENTS;
    }

    public boolean canCollideWith(Entity p_38376_) {
        return canVehicleCollide(this, p_38376_);
    }

    public static boolean canVehicleCollide(Entity p_38324_, Entity p_38325_) {
        return (p_38325_.canBeCollidedWith() || p_38325_.isPushable()) && !p_38324_.isPassengerOfSameVehicle(p_38325_);
    }
    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean isPushable() {
        return true;
    }

    public void push(Entity p_38373_) {
        if (p_38373_ instanceof Boat) {
            if (p_38373_.getBoundingBox().minY < this.getBoundingBox().maxY) {
                super.push(p_38373_);
            }
        } else if (p_38373_.getBoundingBox().minY <= this.getBoundingBox().minY) {
            super.push(p_38373_);
        }

    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    public void lerpTo(double p_38299_, double p_38300_, double p_38301_, float p_38302_, float p_38303_, int p_38304_, boolean p_38305_) {
        this.lerpX = p_38299_;
        this.lerpY = p_38300_;
        this.lerpZ = p_38301_;
        this.lerpYRot = (double)p_38302_;
        this.lerpXRot = (double)p_38303_;
        this.lerpSteps = 10;
    }

    @Nullable
    private Vehicle.Status isUnderwater() {
        AABB aabb = this.getBoundingBox();
        double d0 = aabb.maxY + 0.001;
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.maxY);
        int l = Mth.ceil(d0);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutableblockpos.set(k1, l1, i2);
                    FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
                    if (true && d0 < (double)((float)blockpos$mutableblockpos.getY() + fluidstate.getHeight(this.level(), blockpos$mutableblockpos))) {
                        if (!fluidstate.isSource()) {
                            return Vehicle.Status.UNDER_FLOWING_WATER;
                        }

                        flag = true;
                    }
                }
            }
        }

        return flag ? Vehicle.Status.UNDER_WATER : null;
    }
    
    private Vehicle.Status getStatus() {
        Vehicle.Status vehicle$status = this.isUnderwater();
        if (vehicle$status != null) {
            this.waterLevel = this.getBoundingBox().maxY;
            return vehicle$status;
        } else if (this.checkInWater()) {
            return Vehicle.Status.IN_WATER;
        } else {
            float f = this.getGroundFriction();
            if (f > 0.0F) {
                this.landFriction = f;
                return Vehicle.Status.ON_LAND;
            } else {
                return Vehicle.Status.IN_AIR;
            }
        }
    }

    private boolean checkInWater() {
        AABB aabb = this.getBoundingBox();
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.minY);
        int l = Mth.ceil(aabb.minY + 0.001);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        boolean flag = false;
        this.waterLevel = -1.7976931348623157E308;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int k1 = i; k1 < j; ++k1) {
            for(int l1 = k; l1 < l; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutableblockpos.set(k1, l1, i2);
                    FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
//                    if (this.canBoatInFluid(fluidstate)) {
                    if (true) {
                        float f = (float)l1 + fluidstate.getHeight(this.level(), blockpos$mutableblockpos);
                        this.waterLevel = Math.max((double)f, this.waterLevel);
                        flag |= aabb.minY < (double)f;
                    }
                }
            }
        }

        return flag;
    }

    // THIS
    public float getGroundFriction() {
        AABB aabb = this.getBoundingBox();
        AABB aabb1 = new AABB(aabb.minX, aabb.minY - 0.001, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
        int i = Mth.floor(aabb1.minX) - 1;
        int j = Mth.ceil(aabb1.maxX) + 1;
        int k = Mth.floor(aabb1.minY) - 1;
        int l = Mth.ceil(aabb1.maxY) + 1;
        int i1 = Mth.floor(aabb1.minZ) - 1;
        int j1 = Mth.ceil(aabb1.maxZ) + 1;
        VoxelShape voxelshape = Shapes.create(aabb1);
        float f = 0.0F;
        int k1 = 0;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(int l1 = i; l1 < j; ++l1) {
            for(int i2 = i1; i2 < j1; ++i2) {
                int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
                if (j2 != 2) {
                    for(int k2 = k; k2 < l; ++k2) {
                        if (j2 <= 0 || k2 != k && k2 != l - 1) {
                            blockpos$mutableblockpos.set(l1, k2, i2);
                            BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
                            if (!(blockstate.getBlock() instanceof WaterlilyBlock) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level(), blockpos$mutableblockpos).move((double)l1, (double)k2, (double)i2), voxelshape, BooleanOp.AND)) {
                                f += blockstate.getFriction(this.level(), blockpos$mutableblockpos, this);
                                ++k1;
                            }
                        }
                    }
                }
            }
        }

        return f / (float)k1;
    }

    private void tickLerp() {
        if (this.isControlledByLocalInstance()) {
            this.lerpSteps = 0;
            this.syncPacketPositionCodec(this.getX(), this.getY(), this.getZ());
        }

        if (this.lerpSteps > 0) {
            double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
            double d1 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
            double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
            double d3 = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
            this.setYRot(this.getYRot() + (float)d3 / (float)this.lerpSteps);
            this.setXRot(this.getXRot() + (float)(this.lerpXRot - (double)this.getXRot()) / (float)this.lerpSteps);
            --this.lerpSteps;
            this.setPos(d0, d1, d2);
            this.setRot(this.getYRot(), this.getXRot());
        }

    }

    public float getWaterLevelAbove() {
        AABB aabb = this.getBoundingBox();
        int i = Mth.floor(aabb.minX);
        int j = Mth.ceil(aabb.maxX);
        int k = Mth.floor(aabb.maxY);
        int l = Mth.ceil(aabb.maxY - this.lastYd);
        int i1 = Mth.floor(aabb.minZ);
        int j1 = Mth.ceil(aabb.maxZ);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        label39:
        for(int k1 = k; k1 < l; ++k1) {
            float f = 0.0F;

            for(int l1 = i; l1 < j; ++l1) {
                for(int i2 = i1; i2 < j1; ++i2) {
                    blockpos$mutableblockpos.set(l1, k1, i2);
                    FluidState fluidstate = this.level().getFluidState(blockpos$mutableblockpos);
//                    if (this.canBoatInFluid(fluidstate)) {
                    if (true) {
                        f = Math.max(f, fluidstate.getHeight(this.level(), blockpos$mutableblockpos));
                    }

                    if (f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if (f < 1.0F) {
                return (float)blockpos$mutableblockpos.getY() + f;
            }
        }

        return (float)(l + 1);
    }

    private void floatVehicle() {
        double d0 = -0.03999999910593033;
        double d1 = this.isNoGravity() ? 0.0 : -0.03999999910593033;
        double d2 = 0.0;
        this.invFriction = 0.05F;
        if (this.oldStatus == Vehicle.Status.IN_AIR && this.status != Vehicle.Status.IN_AIR && this.status != Vehicle.Status.ON_LAND) {
            this.waterLevel = this.getY(1.0);
            this.setPos(this.getX(), (double)(this.getWaterLevelAbove() - this.getBbHeight()) + 0.101, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0, 0.0, 1.0));
            this.lastYd = 0.0;
            this.status = Vehicle.Status.IN_WATER;
        } else {
            if (this.status == Vehicle.Status.IN_WATER) {
                d2 = (this.waterLevel - this.getY()) / (double)this.getBbHeight();
                this.invFriction = 0.9F;
            } else if (this.status == Vehicle.Status.UNDER_FLOWING_WATER) {
                d1 = -7.0E-4;
                this.invFriction = 0.9F;
            } else if (this.status == Vehicle.Status.UNDER_WATER) {
                d2 = 0.009999999776482582;
                this.invFriction = 0.45F;
            } else if (this.status == Vehicle.Status.IN_AIR) {
                this.invFriction = 0.9F;
            } else if (this.status == Vehicle.Status.ON_LAND) {
                this.invFriction = this.landFriction;
                if (this.getControllingPassenger() instanceof Player) {
                    this.landFriction /= 2.0F;
                }
            }

            Vec3 vec3 = this.getDeltaMovement();
            this.setDeltaMovement(vec3.x * (double)this.invFriction, vec3.y + d1, vec3.z * (double)this.invFriction);
            this.deltaRotation *= this.invFriction;
            if (d2 > 0.0) {
                Vec3 vec31 = this.getDeltaMovement();
                this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973) * 0.75, vec31.z);
            }
        }

    }


    public Item getDropItem() {
        return null;
    }

    private void controlVehicle() {
        if (this.isVehicle()) {
//            float f = 0.0F;
            if (this.inputLeft) {
                --this.deltaRotation;
            }

            if (this.inputRight) {
                ++this.deltaRotation;
            }
            
            if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
//                    this.throttle += 0.005F;
            }

            this.setYRot(this.getYRot() + this.deltaRotation);
            
            if (this.throttle < maxSpeed) {

                if (this.inputUp) {
                    this.throttle += 0.01F;
                }
            }

            if (this.throttle > 0f) {
                if (this.inputDown) {
                    this.throttle -= 0.005F;
                }
            }

            this.setDeltaMovement(this.getDeltaMovement().add((double)(Mth.sin(-this.getYRot() * 0.017453292F) * this.throttle), 0.0, (double)(Mth.cos(this.getYRot() * 0.017453292F) * this.throttle)));
        }

    }

    protected int getMaxPassengers() {
        return 1;
    }

    public boolean hasEnoughSpaceFor(Entity p_273171_) {
        return p_273171_.getBbWidth() < this.getBbWidth();
    }

    public Direction getMotionDirection() {
        return this.getDirection().getClockWise();
    }

    @Nullable
    public LivingEntity getControllingPassenger() {
        Entity entity = this.getFirstPassenger();
        LivingEntity livingentity1;
        if (entity instanceof LivingEntity livingentity) {
            livingentity1 = livingentity;
        } else {
            livingentity1 = null;
        }

        return livingentity1;
    }
    
    public void tick() {
        this.oldStatus = this.status;
        this.status = this.getStatus();
        if (this.status != Vehicle.Status.UNDER_WATER && this.status != Vehicle.Status.UNDER_FLOWING_WATER) {
            this.outOfControlTicks = 0.0F;
        } else {
            ++this.outOfControlTicks;
        }

        if (!this.level().isClientSide && this.outOfControlTicks >= 60.0F) {
            this.ejectPassengers();
        }

        if (this.getHurtTime() > 0) {
            this.setHurtTime(this.getHurtTime() - 1);
        }

        if (this.getDamage() > 0.0F) {
            this.setDamage(this.getDamage() - 1.0F);
        }

        super.tick();
        this.tickLerp();
        if (this.isControlledByLocalInstance()) {
            if (!(this.getFirstPassenger() instanceof Player)) {
//                this.setPaddleState(false, false);
            }

            this.floatVehicle();
            if (this.level().isClientSide) {
                this.controlVehicle();
//                this.level().sendPacketToServer(new ServerboundPaddleBoatPacket(this.getPaddleState(0), this.getPaddleState(1)));
            }

            this.move(MoverType.SELF, this.getDeltaMovement());
        } else {
            this.setDeltaMovement(Vec3.ZERO);
        }

        this.checkInsideBlocks();
        List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate(0.20000000298023224, -0.009999999776482582, 0.20000000298023224), EntitySelector.pushableBy(this));
        if (!list.isEmpty()) {
            boolean flag = !this.level().isClientSide && !(this.getControllingPassenger() instanceof Player);

            for(int j = 0; j < list.size(); ++j) {
                Entity entity = (Entity)list.get(j);
                if (!entity.hasPassenger(this)) {
                    if (flag && this.getPassengers().size() < this.getMaxPassengers() && !entity.isPassenger() && this.hasEnoughSpaceFor(entity) && entity instanceof LivingEntity && !(entity instanceof WaterAnimal) && !(entity instanceof Player)) {
                        entity.startRiding(this);
                    } else {
                        this.push(entity);
                    }
                }
            }
        }

    }

    static {
        DATA_ID_HURT = SynchedEntityData.defineId(Vehicle.class, EntityDataSerializers.INT);
        DATA_ID_HURTDIR = SynchedEntityData.defineId(Vehicle.class, EntityDataSerializers.INT);
        DATA_ID_DAMAGE = SynchedEntityData.defineId(Vehicle.class, EntityDataSerializers.FLOAT);
        DATA_ID_TYPE = SynchedEntityData.defineId(Vehicle.class, EntityDataSerializers.INT);
    }
    
    @Override
    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0);
        this.entityData.define(DATA_ID_HURTDIR, 1);
        this.entityData.define(DATA_ID_DAMAGE, 0.0F);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
//        if (compoundTag.contains("Type", 8)) {
//            this.setVariant(Boat.Type.byName(compoundTag.getString("Type")));
//        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
//        compoundTag.putString("Type", this.getVariant().getSerializedName());
    }

    public void setDamage(float p_38312_) {
        this.entityData.set(DATA_ID_DAMAGE, p_38312_);
    }

    public float getDamage() {
        return (Float)this.entityData.get(DATA_ID_DAMAGE);
    }

    public void setHurtTime(int p_38355_) {
        this.entityData.set(DATA_ID_HURT, p_38355_);
    }

    public int getHurtTime() {
        return (Integer)this.entityData.get(DATA_ID_HURT);
    }
    
    public void setHurtDir(int p_38363_) {
        this.entityData.set(DATA_ID_HURTDIR, p_38363_);
    }

    public int getHurtDir() {
        return (Integer)this.entityData.get(DATA_ID_HURTDIR);
    }
}
