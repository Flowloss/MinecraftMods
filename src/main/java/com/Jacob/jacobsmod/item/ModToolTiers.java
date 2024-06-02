package com.Jacob.jacobsmod.item;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier OMNIUM = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25,
                    ModTags.Blocks.NEEDS_OMNIUM_TOOL, () -> Ingredient.of(ModItems.OMNIUM.get())),
            new ResourceLocation(JacobsMod.MODID, "omnium"), List.of(Tiers.NETHERITE), List.of());

}
