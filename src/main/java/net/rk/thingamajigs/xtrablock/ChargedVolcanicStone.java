package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ChargedVolcanicStone extends Block {
    public ChargedVolcanicStone(Properties p) {
        super(p.strength(2F,100F).sound(SoundType.ANCIENT_DEBRIS));
    }

    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity ent1) {
        if(ent1 instanceof LivingEntity){
            ent1.hurt(lvl.damageSources().hotFloor(),10.0F);
            ((LivingEntity) ent1).addEffect(new MobEffectInstance(
                    MobEffects.BLINDNESS,
                    7,
                    0,
                    true,
                    false,
                    false)
            );
            ((LivingEntity) ent1).addEffect(new MobEffectInstance(
                    MobEffects.DIG_SLOWDOWN,
                    7,
                    5,
                    true,
                    false,
                    false)
            );
            ((LivingEntity) ent1).addEffect(new MobEffectInstance(
                    MobEffects.MOVEMENT_SLOWDOWN,
                    7,
                    3,
                    true,
                    false,
                    false)
            );
            ent1.setSecondsOnFire(5);
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.charged_volcanic_stone.desc"));
    }
}
