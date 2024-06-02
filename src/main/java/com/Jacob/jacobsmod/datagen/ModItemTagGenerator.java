package com.Jacob.jacobsmod.datagen;

import com.Jacob.jacobsmod.JacobsMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> holderlookup,
                               CompletableFuture<TagLookup<Block>> taglookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, holderlookup, taglookup, JacobsMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

    }
}
