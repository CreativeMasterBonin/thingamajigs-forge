package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.config.ThingamajigsConfigs;
import net.rk.thingamajigs.config.ThingamajigsServerConfigs;
import net.rk.thingamajigs.item.ThingamajigsItems;

@SuppressWarnings("deprecated")
public class ChangeMachine extends ThingamajigsDecorativeBlock {
    public ChangeMachine(Properties p) {
        super(p.sound(SoundType.METAL).mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.1f,10f));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand ih, BlockHitResult bhr) {
        if(!ThingamajigsServerConfigs.COMMON.moneyExchangeEnabled.get()){
            return InteractionResult.PASS;
        }
        // can't use a machine underwater
        if(bs.getValue(WATERLOGGED)){
            return InteractionResult.PASS;
        }

        ItemStack itsm = p.getItemInHand(ih);
        boolean gem = itsm.is(Items.EMERALD);
        boolean money = itsm.is(ThingamajigsItems.MONEY.get());
        boolean didMakeTransaction = false;

        if(money){
            itsm.shrink(1);
            if(itsm.isEmpty()) {
                p.setItemInHand(ih, new ItemStack(ThingamajigsItems.COIN.get(),4));
            }
            else if(!p.getInventory().add(new ItemStack(ThingamajigsItems.COIN.get(),4))) {
                p.drop(new ItemStack(ThingamajigsItems.COIN.get(),4), false);
            }

            lvl.playSound(p,p.getX(),p.getY(),p.getZ(),
                    SoundEvents.LAVA_POP, SoundSource.BLOCKS,
                    1.0F, 1.0F);
            didMakeTransaction = true;
            return InteractionResult.SUCCESS;
        }

        if(gem){
            itsm.shrink(1);
            lvl.playSound(p,p.getX(),p.getY(),p.getZ(),
                    SoundEvents.LEVER_CLICK,SoundSource.BLOCKS,
                    1.0F, 1.0F);
            // if we have money add more to the stack
            if(itsm.isEmpty()) {
                p.setItemInHand(ih, new ItemStack(ThingamajigsItems.MONEY.get()));
            }
            else if(!p.getInventory().add(new ItemStack(ThingamajigsItems.MONEY.get()))) {
                p.drop(new ItemStack(ThingamajigsItems.MONEY.get()), false);
            }
            didMakeTransaction = true;
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
