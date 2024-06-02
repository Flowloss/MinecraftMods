package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BoatItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, JacobsMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.OMNIUM_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.OMNIUM_BLOCK.get(),
                        ModBlocks.RAW_OMNIUM_BLOCK.get(),
                        ModBlocks.OMNIUM_ORE.get(),
                        ModBlocks.DEEPSLATE_OMNIUM_ORE.get(),
                        ModBlocks.END_STONE_OMNIUM_ORE.get(),
                        ModBlocks.NETHER_OMNIUM_ORE.get(),
                        ModBlocks.SOUND_BLOCK.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.OMNIUM_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.RAW_OMNIUM_BLOCK.get());

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.NETHER_OMNIUM_ORE.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.END_STONE_OMNIUM_ORE.get());

        //adding the blocks to the specific tags
        this.tag(BlockTags.FENCES)
                .add(ModBlocks.OMNIUM_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.OMNIUM_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
                .add(ModBlocks.OMNIUM_WALL.get());
    }
}
