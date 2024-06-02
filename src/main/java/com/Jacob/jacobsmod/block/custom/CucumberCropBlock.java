package com.Jacob.jacobsmod.block.custom;

import com.Jacob.jacobsmod.item.ModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CucumberCropBlock extends CropBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;

    public CucumberCropBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }


    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.CUCUMBER_SEEDS.get();
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
