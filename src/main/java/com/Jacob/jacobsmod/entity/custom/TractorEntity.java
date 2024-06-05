package com.Jacob.jacobsmod.entity.custom;

import com.Jacob.jacobsmod.entity.ModEntities;
import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class TractorEntity extends Vehicle {
    public static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(Vehicle.class, EntityDataSerializers.INT);

    public TractorEntity(EntityType<? extends Vehicle> entityType, Level level) {
        super(entityType, level);
    }

    public TractorEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntities.TRACTOR.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public Item getDropItem() {
        return ModItems.TRACTOR.get();
    }
    
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }
}
