package net.rk.thingamajigs.mix;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.level.ItemLike;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Debug(export = true)
@Mixin(Painting.class)
public abstract class ThingamajigsMixins {
    @Shadow public abstract Holder<PaintingVariant> getVariant();

    @ModifyArg(method = "dropItem", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/Painting;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/entity/item/ItemEntity;"))
    public ItemLike thingamajigs_dropPainting(ItemLike originalItem){
        boolean wasPainting = false;
        if(this.getVariant().is(ThingamajigsTags.THINGAMAJIGS_PAINTING)) { // is this a thingamajigs painting?
            wasPainting = true;
        }

        if(wasPainting){
            return ThingamajigsItems.THINGAMAJIGS_PAINTING_ITEM.get();
        }
        return originalItem;
    }
}
