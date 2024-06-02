package com.Jacob.jacobsmod.datagen.loot;

import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    public ModBlockLootTables(){
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.OMNIUM_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_OMNIUM_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.dropSelf(ModBlocks.OMNIUM_FENCE.get());
        this.dropSelf(ModBlocks.OMNIUM_WALL.get());
        this.dropSelf(ModBlocks.OMNIUM_STAIRS.get());
        this.dropSelf(ModBlocks.OMNIUM_BUTTON.get());
        this.dropSelf(ModBlocks.OMNIUM_TRAPDOOR.get());
        this.dropSelf(ModBlocks.OMNIUM_FENCE_GATE.get());
        this.dropSelf(ModBlocks.OMNIUM_PRESSURE_PLATE.get());

        this.add(ModBlocks.OMNIUM_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.OMNIUM_SLAB.get()));
        this.add(ModBlocks.OMNIUM_DOOR.get(),
                block -> createDoorTable(ModBlocks.OMNIUM_DOOR.get()));

        this.add(ModBlocks.OMNIUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.OMNIUM_ORE.get(), ModItems.RAW_OMNIUM.get()));
        this.add(ModBlocks.DEEPSLATE_OMNIUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_OMNIUM_ORE.get(), ModItems.RAW_OMNIUM.get()));
        this.add(ModBlocks.NETHER_OMNIUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NETHER_OMNIUM_ORE.get(), ModItems.RAW_OMNIUM.get()));
        this.add(ModBlocks.END_STONE_OMNIUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.END_STONE_OMNIUM_ORE.get(), ModItems.RAW_OMNIUM.get()));
    }


    protected LootTable.Builder createCopperLikeOreDrops(Block block, Item item) {
        return createSilkTouchDispatchTable(block,
                this.applyExplosionDecay(block,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f, 5.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
