package com.Jacob.jacobsmod.entity.event;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.ModEntities;
import com.Jacob.jacobsmod.entity.custom.CropDusterEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JacobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        
    }
}
