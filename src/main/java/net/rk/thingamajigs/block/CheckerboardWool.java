package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class CheckerboardWool extends Block {
    public CheckerboardWool(Properties p) {
        super(p.strength(0.8F).sound(SoundType.WOOL));
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public float getJumpFactor() {
        return 1.2F;
    }

    @Override
    public float getSpeedFactor() {
        return 1.1F;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.checkerboard_wool.desc"));
    }

    // we want the checkerboard wool when stepped on to give entities (who can) obtain helpful effects for fast sneaking (without enchanted boots)
    @Override
    public void stepOn(Level lvl, BlockPos bp, BlockState bs, Entity ent1) {
        if(ent1 instanceof LivingEntity){
            if(((LivingEntity) ent1).isAffectedByPotions() && !ent1.isSteppingCarefully()){
                ((LivingEntity) ent1).addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        10,
                        2,
                        true,
                        false,
                        false));
                // end of step code
            }
        }
    }
}
