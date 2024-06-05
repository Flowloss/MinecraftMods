package com.Jacob.jacobsmod.entity;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.custom.CropDusterEntity;
import com.Jacob.jacobsmod.entity.custom.TractorEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, JacobsMod.MODID);

    public static final RegistryObject<EntityType<CropDusterEntity>> CROP_DUSTER = ENTITY_TYPES.register("crop_duster", 
            () -> EntityType.Builder.<CropDusterEntity>of(CropDusterEntity::new, MobCategory.MISC).sized(1f, 1f).build("crop_duster"));

    public static final RegistryObject<EntityType<TractorEntity>> TRACTOR = ENTITY_TYPES.register("tractor",
            () -> EntityType.Builder.<TractorEntity>of(TractorEntity::new, MobCategory.MISC).sized(1f, 1f).build("tractor"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
