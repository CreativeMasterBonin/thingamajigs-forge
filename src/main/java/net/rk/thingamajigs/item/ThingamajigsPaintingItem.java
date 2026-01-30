package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ThingamajigsPaintingItem extends HangingEntityItem {
    public ThingamajigsPaintingItem(Properties p) {
        super(EntityType.PAINTING,p.stacksTo(16).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flag){
        list.add(Component.translatable("item.thingamajigs.thingamajig_painting.desc").withStyle(ChatFormatting.GRAY));
    }

    // FIRST TRY WORKED - GANG! POG!
    // exclusively places THINGAMAJIGS_PAINTING tagged paintings
    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos relativePosition = blockpos.relative(direction);
        Player player = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();

        if(player == null){
            return InteractionResult.FAIL;
        }
        Level level = player.level();

        Painting painting;

        boolean canPlacePainting = this.mayPlace(player, direction, itemstack, relativePosition);
        if(canPlacePainting){
            Optional<Painting> optional = Painting.create(level,relativePosition,direction);
            if(optional.isEmpty()){
                player.playSound(ThingamajigsSoundEvents.POP.get(),1.0f,ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.0f));
                return InteractionResult.CONSUME;
            }
            painting = optional.get();

            CompoundTag variantTag = itemstack.getTag();
            if(variantTag != null){
                EntityType.updateCustomEntityTag(level, player, painting, variantTag);
            }

            if(painting.survives()){
                if(!level.isClientSide){
                    // only can be placed here for now as if placed anywhere before the paintings become inconsistently placed
                    List<Holder<PaintingVariant>> list = new ArrayList<>();
                    BuiltInRegistries.PAINTING_VARIANT.getTagOrEmpty(ThingamajigsTags.THINGAMAJIGS_PAINTING).forEach(list::add);
                    Optional<Holder<PaintingVariant>> variantOptional = Util.getRandomSafe(list,level.getRandom());
                    variantOptional.ifPresent(painting::setVariant);

                    painting.playPlacementSound();
                    level.gameEvent(player, GameEvent.ENTITY_PLACE, painting.position());
                    level.addFreshEntity(painting);
                }

                itemstack.shrink(1);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            else{
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
}
