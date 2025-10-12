package net.rk.thingamajigs.datagen.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Predicate;

public class ItemTouchesBlockTrigger extends SimpleCriterionTrigger<ItemTouchesBlockTrigger.TriggerInstance> {
    // we will not be working on this for a while
    final ResourceLocation id;
    public ItemTouchesBlockTrigger(ResourceLocation location){
        this.id = location;
    }
    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    protected ItemTouchesBlockTrigger.TriggerInstance createInstance(JsonObject json, ContextAwarePredicate contextAwarePredicate1, DeserializationContext deserializationContext1) {
        return null;
    }

    @Override
    protected void trigger(ServerPlayer serverPlayer, Predicate<TriggerInstance> predicate) {
        super.trigger(serverPlayer, predicate);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance{
        public TriggerInstance(ResourceLocation loc,ContextAwarePredicate contextAwarePredicate) {
            super(loc,contextAwarePredicate);
        }
    }
}
