package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class BlueydlaptopBlock extends ThingamajigsDecorativeBlock {
    public static final IntegerProperty VERSION = IntegerProperty.create("version",0,1);
    public BlueydlaptopBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(VERSION);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(!level.isClientSide()){
            if(player.getItemInHand(hand).is(Items.AIR)){
                // It is not just a Linux Distro, it IS a Linux Distro.
                level.setBlock(pos,state.cycle(VERSION),3);
                return InteractionResult.CONSUME;
            }
        }
        else {
            if (player.getItemInHand(hand).is(Items.AIR)) {
                player.playSound(SoundEvents.CAKE_ADD_CANDLE,1f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.98f,1.05f));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
