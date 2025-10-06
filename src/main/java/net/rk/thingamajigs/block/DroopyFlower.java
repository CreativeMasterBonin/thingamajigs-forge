package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import java.util.List;

public class DroopyFlower extends FlowerBlock {
    public DroopyFlower(MobEffect effect, int duration, Properties p) {
        super(effect, duration, p);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(!player.isCreative()){
            level.playSound(null,pos, ThingamajigsSoundEvents.POOP.get(), SoundSource.BLOCKS,2F, 0.95F);
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 275, 0, false ,false));
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    // Droopy likes ricochet! translatable string append
    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.droopy_flower.desc"));
    }
}
