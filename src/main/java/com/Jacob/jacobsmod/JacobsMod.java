package com.Jacob.jacobsmod;

import com.Jacob.jacobsmod.block.ModBlocks;
<<<<<<< HEAD
import com.Jacob.jacobsmod.item.ModCreativeModTabs;
import com.Jacob.jacobsmod.item.ModItems;
import com.mojang.logging.LogUtils;
=======
import com.Jacob.jacobsmod.entity.ModEntities;
import com.Jacob.jacobsmod.entity.client.CropDusterEntityRenderer;
import com.Jacob.jacobsmod.item.ModCreativeModTabs;
import com.Jacob.jacobsmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRenderers;
>>>>>>> 6682f49 (hej)
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(JacobsMod.MODID)
public class JacobsMod {
    public static final String MODID = "jacobsmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public JacobsMod(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);
<<<<<<< HEAD

        bus.addListener(this::commonSetup);
        ModCreativeModTabs.register(bus);


=======
        ModEntities.register(bus);
        
        bus.addListener(this::commonSetup);
        ModCreativeModTabs.register(bus);

>>>>>>> 6682f49 (hej)
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.OMNIUM);
            event.accept(ModItems.RAW_OMNIUM);
        }
<<<<<<< HEAD
=======
        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            // TODO: Move tab
            event.accept(ModItems.CROP_DUSTER);
        }
>>>>>>> 6682f49 (hej)
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
<<<<<<< HEAD

=======
            
            EntityRenderers.register(ModEntities.CROP_DUSTER.get(), CropDusterEntityRenderer::new);
>>>>>>> 6682f49 (hej)
        }
    }
}