package com.Jacob.jacobsmod.item;

import com.Jacob.jacobsmod.JacobsMod;
import net.minecraft.world.item.Item;
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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
