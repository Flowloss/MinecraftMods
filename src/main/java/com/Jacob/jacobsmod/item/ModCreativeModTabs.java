package com.Jacob.jacobsmod.item;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, JacobsMod.MODID);

    public static final RegistryObject<CreativeModeTab> JACOBSMOD_TAB = CREATIVE_MODE_TAB.register("jacobsmod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OMNIUM.get()))
                    .title(Component.translatable("creativetab.jacobsmod_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.OMNIUM.get());
                        pOutput.accept(ModItems.RAW_OMNIUM.get());

                        pOutput.accept(ModItems.TOMATO.get());
                        pOutput.accept(ModItems.PINE_CONE.get());
                        pOutput.accept(ModItems.CUCUMBER.get());


                        pOutput.accept(ModItems.CROP_DUSTER.get());


                        pOutput.accept(ModBlocks.OMNIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_OMNIUM_BLOCK.get());


                        pOutput.accept(ModItems.METAL_DETECTOR.get());

                        pOutput.accept(ModBlocks.OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_OMNIUM_ORE.get());

                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());

                        pOutput.accept(ModItems.OMNIUM_SWORD.get());
                        pOutput.accept(ModItems.OMNIUM_PICKAXE.get());
                        pOutput.accept(ModItems.OMNIUM_AXE.get());
                        pOutput.accept(ModItems.OMNIUM_SHOVEL.get());
                        pOutput.accept(ModItems.OMNIUM_HOE.get());

                        pOutput.accept(ModItems.TOMATO_SEEDS.get());
                        pOutput.accept(ModItems.CUCUMBER_SEEDS.get());

                        pOutput.accept(ModBlocks.OMNIUM_STAIRS.get());
                        pOutput.accept(ModBlocks.OMNIUM_SLAB.get());
                        pOutput.accept(ModBlocks.OMNIUM_BUTTON.get());
                        pOutput.accept(ModBlocks.OMNIUM_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.OMNIUM_FENCE.get());
                        pOutput.accept(ModBlocks.OMNIUM_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.OMNIUM_WALL.get());

                        pOutput.accept(ModBlocks.OMNIUM_DOOR.get());
                        pOutput.accept(ModBlocks.OMNIUM_TRAPDOOR.get());

                        pOutput.accept(ModItems.OMNIUM_STAFF.get());

                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
