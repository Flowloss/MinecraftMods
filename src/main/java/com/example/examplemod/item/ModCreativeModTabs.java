package com.example.examplemod.item;

import com.example.examplemod.JacobsMod;
import com.example.examplemod.block.ModBlocks;
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

                        pOutput.accept(ModBlocks.OMNIUM_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_OMNIUM_BLOCK.get());

                        pOutput.accept(ModBlocks.OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_OMNIUM_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_OMNIUM_ORE.get());
    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
