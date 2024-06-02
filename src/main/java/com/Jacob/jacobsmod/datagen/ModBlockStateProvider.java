package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.block.custom.TomatoCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.checkerframework.checker.units.qual.C;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, JacobsMod.MODID, exFileHelper);
    }

    //first step in adding data
    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.OMNIUM_BLOCK);
        blockWithItem(ModBlocks.RAW_OMNIUM_BLOCK);

        blockWithItem(ModBlocks.OMNIUM_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_OMNIUM_ORE);
        blockWithItem(ModBlocks.END_STONE_OMNIUM_ORE);
        blockWithItem(ModBlocks.NETHER_OMNIUM_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.OMNIUM_STAIRS.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.OMNIUM_SLAB.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.OMNIUM_BUTTON.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.OMNIUM_PRESSURE_PLATE.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.OMNIUM_FENCE.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.OMNIUM_FENCE_GATE.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.OMNIUM_WALL.get()), blockTexture(ModBlocks.OMNIUM_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.OMNIUM_DOOR.get()), modLoc("block/omnium_door_bottom"), modLoc("block/omnium_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.OMNIUM_TRAPDOOR.get()), modLoc("block/omnium_trapdoor"), true, "cutout");

        makeTomatoCrop((CropBlock) ModBlocks.TOMATO_CROP.get(), "tomato_stage", "tomato_stage");
    }

    public void makeTomatoCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> tomatoStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] tomatoStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoCropBlock) block).getAgeProperty()),
                new ResourceLocation(JacobsMod.MODID, "block/" + textureName + state.getValue(((TomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
