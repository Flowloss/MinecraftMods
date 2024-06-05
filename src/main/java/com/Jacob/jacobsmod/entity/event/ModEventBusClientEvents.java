package com.Jacob.jacobsmod.entity.event;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.entity.model.CropDusterModel;
import com.Jacob.jacobsmod.entity.model.TractorModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JacobsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(CropDusterModel.LAYER_LOCATION, CropDusterModel::createBodyLayer);
        event.registerLayerDefinition(TractorModel.LAYER_LOCATION, TractorModel::createBodyLayer);
    }
}