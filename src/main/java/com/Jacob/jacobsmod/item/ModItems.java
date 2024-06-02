package com.Jacob.jacobsmod.item;

import com.Jacob.jacobsmod.JacobsMod;
import com.Jacob.jacobsmod.block.ModBlocks;
import com.Jacob.jacobsmod.item.custom.FuelItem;
import com.Jacob.jacobsmod.item.custom.MetalDetectorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
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
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            ()-> new MetalDetectorItem(new Item.Properties().durability(100)));
    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            ()-> new Item(new Item.Properties().food(ModFoods.TOMATO)));
    public static final RegistryObject<Item> PINE_CONE = ITEMS.register("pine_cone",
            ()-> new FuelItem(new Item.Properties(), 400));
    public static final RegistryObject<Item> OMNIUM_STAFF = ITEMS.register("omnium_staff",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
