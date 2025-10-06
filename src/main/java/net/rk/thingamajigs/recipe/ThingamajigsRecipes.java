package net.rk.thingamajigs.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;

public class ThingamajigsRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Thingamajigs.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ThingamajigsCrafterRecipe>> THINGAMAJIGS_CRAFTER_RECIPE =
            SERIALIZERS.register("thingamajigs_crafting", () -> ThingamajigsCrafterRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
