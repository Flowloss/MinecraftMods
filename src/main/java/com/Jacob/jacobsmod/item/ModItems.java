package com.Jacob.jacobsmod.item;

import com.Jacob.jacobsmod.JacobsMod;
<<<<<<< HEAD
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.item.custom.FuelItem;
import com.Jacob.jacobsmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.*;
=======
import com.Jacob.jacobsmod.item.custom.ModCropDusterItem;
import net.minecraft.world.item.Item;
>>>>>>> 6682f49 (hej)
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, JacobsMod.MODID);

    public static final RegistryObject<Item> OMNIUM = ITEMS.register("omnium",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_OMNIUM = ITEMS.register("raw_omnium",
            ()-> new Item(new Item.Properties()));
<<<<<<< HEAD
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            ()-> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            ()-> new Item(new Item.Properties().food(ModFoods.TOMATO)));
    public static final RegistryObject<Item> CUCUMBER = ITEMS.register("cucumber",
            ()-> new Item(new Item.Properties().food(ModFoods.CUCUMBER)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            ()-> new FuelItem(new Item.Properties(), 400));
    public static final RegistryObject<Item> OMNIUM_STAFF = ITEMS.register("omnium_staff",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> OMNIUM_SWORD = ITEMS.register("omnium_sword",
            ()-> new SwordItem(ModToolTiers.OMNIUM, 6, 2, new Item.Properties()));
    public static final RegistryObject<Item> OMNIUM_PICKAXE = ITEMS.register("omnium_pickaxe",
            ()-> new PickaxeItem(ModToolTiers.OMNIUM, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> OMNIUM_AXE = ITEMS.register("omnium_axe",
            ()-> new AxeItem(ModToolTiers.OMNIUM, 7, 1, new Item.Properties()));
    public static final RegistryObject<Item> OMNIUM_SHOVEL = ITEMS.register("omnium_shovel",
            ()-> new ShovelItem(ModToolTiers.OMNIUM, 4, 2, new Item.Properties()));
    public static final RegistryObject<Item> OMNIUM_HOE = ITEMS.register("omnium_hoe",
            ()-> new HoeItem(ModToolTiers.OMNIUM, 4, 2, new Item.Properties()));

    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.TOMATO_CROP.get(), new Item.Properties()));
    public static final RegistryObject<Item> CUCUMBER_SEEDS = ITEMS.register("cucumber_seeds",
            ()-> new ItemNameBlockItem(ModBlocks.CUCUMBER_CROP.get(), new Item.Properties()));
=======

    public static final RegistryObject<Item> CROP_DUSTER = ITEMS.register("crop_duster",
            () -> new ModCropDusterItem(new Item.Properties()));
>>>>>>> 6682f49 (hej)

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
