package net.rk.thingamajigs.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.rk.thingamajigs.fluid.ThingamajigsFluids;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Logger;

public class FryingOilBucket extends BucketItem {
    public FryingOilBucket() {
        super(ThingamajigsFluids.FRYING_OIL, new Item.Properties()
                .craftRemainder(Items.BUCKET)
                .stacksTo(1)
                .rarity(Rarity.COMMON));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level l, Player p, InteractionHand ih) {
        String s = p.level().dimension().location().toString();

        boolean isCustomMCDimOrVanilla = s.contains("minecraft");
        boolean isThingamajigsDim = s.contains("thingamajigs");
        boolean isSnackimadoodlesDim = s.contains("snackimadoodles");
        boolean isPropamajooglesDim = s.contains("propamajoogles");
        boolean isRFToolsDim = s.contains("rftools");
        boolean isAetherDim = s.contains("aether");
        boolean isTFDim = s.contains("twilight_forest");
        boolean isTCDim = s.contains("tropicraft");

        boolean isMystcraftDim = s.contains("mystcraft"); // legacy support for potential future updates
        boolean isThaumDim = s.contains("thaumcraft"); // legacy support for potential future updates

        boolean isAdAstraDim = s.contains("ad_astra");

        boolean isAdAstraDimold = s.contains("adastra");

        if(isAdAstraDimold){
            isAdAstraDim = true;
        }

        if(!isAdAstraDim && !isThaumDim && !isTCDim && !isTFDim && !isAetherDim && !isCustomMCDimOrVanilla && !isRFToolsDim && !isMystcraftDim && !isPropamajooglesDim && !isThingamajigsDim && !isSnackimadoodlesDim){
            return InteractionResultHolder.consume(p.getItemInHand(ih));
        }
        else{
            try{
                l.gameEvent(p, GameEvent.ITEM_INTERACT_FINISH,new BlockPos(p.getBlockX(),p.getBlockY(),p.getBlockZ()));
                return super.use(l, p, ih);
            }
            catch(Exception e){
                Logger.getAnonymousLogger().info("Frying Oil bucket item cannot place fluid contained.");
                return InteractionResultHolder.fail(p.getItemInHand(ih));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level lvl, List<Component> componentList, TooltipFlag tf) {
        componentList.add(Component.translatable("tooltip.thingamajigs.frying_oil_bucket")
                .withStyle(ChatFormatting.GRAY));
        super.appendHoverText(itemStack, lvl, componentList, tf);
    }
}
