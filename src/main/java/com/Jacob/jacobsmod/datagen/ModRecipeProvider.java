package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public static final List<ItemLike> OMNIUM_SMELTABLES = List.of(ModItems.RAW_OMNIUM.get(),
            ModBlocks.OMNIUM_ORE.get(), ModBlocks.NETHER_OMNIUM_ORE.get(),
            ModBlocks.END_STONE_OMNIUM_ORE.get(), ModBlocks.DEEPSLATE_OMNIUM_ORE.get());
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        oreSmelting(writer, OMNIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OMNIUM.get(), 0.25f, 200, "omnium");
        oreBlasting(writer, OMNIUM_SMELTABLES, RecipeCategory.MISC, ModItems.OMNIUM.get(), 0.25f, 100, "omnium");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OMNIUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.OMNIUM.get())
                .unlockedBy(getHasName(ModBlocks.OMNIUM_BLOCK.get()), has(ModBlocks.OMNIUM_BLOCK.get()))
                .save(writer);
    }
    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> p_248775_, List<ItemLike> p_251504_, RecipeCategory p_248846_, ItemLike p_249735_, float p_248783_, int p_250303_, String p_251984_) {
        oreCooking(p_248775_, RecipeSerializer.BLASTING_RECIPE, p_251504_, p_248846_, p_249735_, p_248783_, p_250303_, p_251984_, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_)
                    .group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(p_250791_, JacobsMod.MODID + ":" + getItemName(p_250066_) + p_249236_ + "_" + getItemName(itemlike));
        }

    }
}
