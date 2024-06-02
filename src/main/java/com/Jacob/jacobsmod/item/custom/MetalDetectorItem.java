package com.Jacob.jacobsmod.item.custom;

import com.Jacob.jacobsmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//custom tool
public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        if (context.getLevel().isClientSide()) {
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= pos.getY() + 64; i++) {
                BlockState state = context.getLevel().getBlockState(pos.below(i));

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(pos.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No valuables found!"));
            }
        }

        context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
                player -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {
        components.add(Component.translatable("tooltip.jacobsmod.metal_detector.tooltip"));
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + "="));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
