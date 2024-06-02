package com.Jacob.jacobsmod.block;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.custom.SoundBlock;
import com.Jacob.jacobsmod.block.custom.TomatoCropBlock;
import com.Jacob.jacobsmod.item.ModItems;
import com.Jacob.jacobsmod.util.ModTags;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, JacobsMod.MODID);

    // all the different omnium blocks
    public static final RegistryObject<Block> OMNIUM_BLOCK = registerBlock("omnium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE)));

    public static final RegistryObject<Block> RAW_OMNIUM_BLOCK = registerBlock("raw_omnium_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.STONE)));

    public static final RegistryObject<Block> OMNIUM_ORE = registerBlock("omnium_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_OMNIUM_ORE = registerBlock("deepslate_omnium_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));

    public static final RegistryObject<Block> NETHER_OMNIUM_ORE = registerBlock("nether_omnium_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));

    public static final RegistryObject<Block> END_STONE_OMNIUM_ORE = registerBlock("end_stone_omnium_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(3,7)));
    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)));

    // Custom blocks / stairs / slabs / fences etc
    public static final RegistryObject<Block> OMNIUM_STAIRS = registerBlock("omnium_stairs",
            () -> new StairBlock(() -> ModBlocks.OMNIUM_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties
                            .copy(Blocks.STONE)
                            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> OMNIUM_SLAB = registerBlock("omnium_slab",
            () -> new SlabBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> OMNIUM_BUTTON = registerBlock("omnium_button",
            () -> new ButtonBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE_BUTTON)
                    .sound(SoundType.STONE),
                    BlockSetType.STONE, 10, true));
    public static final RegistryObject<Block> OMNIUM_PRESSURE_PLATE = registerBlock("omnium_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties
                    .copy(Blocks.STONE)
                    .sound(SoundType.STONE),
                    BlockSetType.STONE));

    public static final RegistryObject<Block> OMNIUM_FENCE = registerBlock("omnium_fence",
            () -> new FenceBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE)
                    .sound(SoundType.STONE)));
    public static final RegistryObject<Block> OMNIUM_FENCE_GATE = registerBlock("omnium_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE)
                    .sound(SoundType.STONE), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> OMNIUM_WALL = registerBlock("omnium_wall",
            () -> new WallBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<Block> OMNIUM_DOOR = registerBlock("omnium_door",
            () -> new DoorBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE).noOcclusion()
                    .sound(SoundType.STONE), BlockSetType.STONE));
    public static final RegistryObject<Block> OMNIUM_TRAPDOOR = registerBlock("omnium_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties
                    .copy(Blocks.STONE).noOcclusion()
                    .sound(SoundType.STONE), BlockSetType.STONE));

    public static final RegistryObject<Block> TOMATO_CROP = BLOCKS.register("tomato_crop",
            () -> new TomatoCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
