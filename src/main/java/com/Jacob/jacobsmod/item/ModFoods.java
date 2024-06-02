package com.Jacob.jacobsmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.2f).effect(()-> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.5f).build();
}
