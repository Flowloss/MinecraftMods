package com.Jacob.jacobsmod.entity.custom;

import com.Jacob.jacobsmod.entity.ModEntities;
import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class CropDusterEntity extends Boat {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Boat.class, EntityDataSerializers.INT);

    public CropDusterEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public CropDusterEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.CROP_DUSTER.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }
    
    @Override
    public Item getDropItem() {
        return ModItems.CROP_DUSTER.get();
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    // Wtf does this even do
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_ID_TYPE, 0);
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }
    
}
