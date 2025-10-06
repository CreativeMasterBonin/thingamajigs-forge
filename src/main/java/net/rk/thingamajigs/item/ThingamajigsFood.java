package net.rk.thingamajigs.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ThingamajigsFood {
    // may use older methods that the vanilla game uses
    public static final FoodProperties GLOB_SANDWICH_PROPERTIES = (new FoodProperties.Builder()
            .nutrition(10)
            .fast()
            .saturationMod(2.5F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 5, true, false,false), 1.0F)
            .effect(new MobEffectInstance(MobEffects.LUCK, 750, 5,true, false,false), 1.0F)
            .build());
}
