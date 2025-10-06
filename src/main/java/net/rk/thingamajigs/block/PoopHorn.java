package net.rk.thingamajigs.block;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

import javax.annotation.Nullable;
import java.util.List;

public class PoopHorn extends Item {
    public PoopHorn(Properties p) {
        super(p);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level l, Player p, InteractionHand h) {
        ItemStack itemstack = p.getItemInHand(h);
        if(!l.isClientSide()){
            l.playSound(null,p.getX(),p.getY(),p.getZ(), ThingamajigsSoundEvents.LARGE_POOP.get(), SoundSource.PLAYERS,1.0F,1.0F);
        }
        // client side particles
        for(int i = 0; i < 15; i++){
            double d0 = (double)p.getX() + 0.5D;
            double d1 = (double)p.getY() + 1.0D;
            double d2 = (double)p.getZ() + 0.5D;
            l.addParticle(ParticleTypes.SQUID_INK, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
        // client side stuff
        p.startUsingItem(h);
        p.getCooldowns().addCooldown(this, 140);
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack itmStk) {
        return 140;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itStk) {
        return UseAnim.TOOT_HORN;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(Component.translatable("item.thingamajigs.poop_horn.desc"));
    }
}
