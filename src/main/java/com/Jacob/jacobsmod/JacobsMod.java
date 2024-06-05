package com.Jacob.jacobsmod;

import com.Jacob.jacobsmod.block.ModBlocks;

import com.Jacob.jacobsmod.entity.client.TractorEntityRenderer;
import com.Jacob.jacobsmod.entity.custom.Vehicle;
import com.Jacob.jacobsmod.item.ModCreativeModTabs;
import com.Jacob.jacobsmod.item.ModItems;
import com.mojang.logging.LogUtils;
import com.Jacob.jacobsmod.entity.ModEntities;
import com.Jacob.jacobsmod.entity.client.CropDusterEntityRenderer;
import com.Jacob.jacobsmod.item.ModCreativeModTabs;
import com.Jacob.jacobsmod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.player.KeyboardInput;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.Console;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Mod(JacobsMod.MODID)
public class JacobsMod {
    public static final String MODID = "jacobsmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static Vehicle RidingVehicle;
        
    public JacobsMod(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(bus);
        ModBlocks.register(bus);
        
        ModCreativeModTabs.register(bus);

        ModEntities.register(bus);
        
        bus.addListener(this::commonSetup);
        ModCreativeModTabs.register(bus);

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

        else if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            // TODO: Move tab
            event.accept(ModItems.CROP_DUSTER);
            event.accept(ModItems.TRACTOR);
        }

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntities.CROP_DUSTER.get(), CropDusterEntityRenderer::new);
            EntityRenderers.register(ModEntities.TRACTOR.get(), TractorEntityRenderer::new);
        }
    }

//    @SubscribeEvent
//    public void mouseEvents(TickEvent event) {
//        var mouseYVelocity =  Minecraft.getInstance().mouseHandler.
//        var inputMouseUp = mouseYVelocity < -0.1f;
//        var inputMouseDown = mouseYVelocity > 0.1f;
//
//        System.out.println(mouseYVelocity);
//        
//        if (inputMouseUp) {
//            System.out.println("Mouse up");
//        }
//        else if (inputMouseDown) {
//            System.out.println("Mouse down");
//        }
//    }

    @SubscribeEvent
    public void inputEvents(InputEvent.Key event) {
        if (RidingVehicle != null) {
            var instance = Minecraft.getInstance();

            var inputUp = instance.options.keyUp.isDown();
            var inputDown = instance.options.keyDown.isDown();
            var inputLeft = instance.options.keyLeft.isDown();
            var inputRight = instance.options.keyRight.isDown();

            RidingVehicle.setInput(inputLeft, inputRight, inputUp, inputDown);
        }
    }

    @SubscribeEvent
    public void mountEvents(EntityMountEvent event) {
        var entity = event.getEntity();

        if (entity instanceof LocalPlayer localPlayer) {
            if (event.isMounting()) {
                var ent = event.getEntityBeingMounted();

                if (ent instanceof Vehicle vehicle) {
                    RidingVehicle = vehicle;
                }
            }
            else if (event.isDismounting()) {
                if (RidingVehicle != null) {
                    RidingVehicle = null;
                }
            }
        }
    }
    
}