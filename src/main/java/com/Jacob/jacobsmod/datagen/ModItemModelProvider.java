package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, JacobsMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.OMNIUM);
        simpleItem(ModItems.RAW_OMNIUM);

        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PINE_CONE);
        simpleItem(ModItems.TOMATO);

        simpleBlockItem(ModBlocks.OMNIUM_DOOR);

        fenceItem(ModBlocks.OMNIUM_FENCE, ModBlocks.OMNIUM_BLOCK);
        buttonItem(ModBlocks.OMNIUM_BUTTON, ModBlocks.OMNIUM_BLOCK);
        wallItem(ModBlocks.OMNIUM_WALL, ModBlocks.OMNIUM_BLOCK);

        evenSimplerBlockitem(ModBlocks.OMNIUM_PRESSURE_PLATE);
        evenSimplerBlockitem(ModBlocks.OMNIUM_STAIRS);
        evenSimplerBlockitem(ModBlocks.OMNIUM_FENCE_GATE);
        evenSimplerBlockitem(ModBlocks.OMNIUM_SLAB);

        trapdoorItem(ModBlocks.OMNIUM_TRAPDOOR);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item ) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(JacobsMod.MODID, "item/" + item.getId().getPath()));
    }


    public void evenSimplerBlockitem(RegistryObject<Block> block){
        this.withExistingParent(JacobsMod.MODID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
        modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }


    //custom methods for wall, fence and buttons. forge doesnt have methods to add these models
    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(JacobsMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(JacobsMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(JacobsMod.MODID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    //method like simpleItem to add blockitems much easier
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(JacobsMod.MODID, "item/" + item.getId().getPath()));
    }
}
