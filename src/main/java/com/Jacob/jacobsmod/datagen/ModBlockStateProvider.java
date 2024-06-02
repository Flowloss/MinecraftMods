package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JacobsMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.OMNIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_OMNIUM_BLOCK);

        blockWithItem(ModBlocks.OMNIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_OMNIUM_ORE);
        blockWithItem(ModBlocks.END_STONE_OMNIUM_ORE);
        blockWithItem(ModBlocks.NETHER_OMNIUM_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
