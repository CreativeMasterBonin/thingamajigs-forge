package net.rk.thingamajigs.xtrablock;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CajonInstrument extends ThingamajigsDecorativeBlock {
    public CajonInstrument(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> lc, TooltipFlag p_49819_) {
        lc.add(Component.translatable("block.thingamajigs.cajon.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand hand, BlockHitResult bhr) {
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            boolean hitASide = false;
            if(ply.getItemInHand(hand).isEmpty()){
                if(bs.getValue(FACING) == Direction.SOUTH){
                    if(bhr.getDirection() == Direction.SOUTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.WEST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.NORTH){
                    if(bhr.getDirection() == Direction.NORTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.EAST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.WEST){
                    if(bhr.getDirection() == Direction.WEST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.NORTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
                else if(bs.getValue(FACING) == Direction.EAST){
                    if(bhr.getDirection() == Direction.EAST){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_HAT.get(), SoundSource.BLOCKS,1.0f,0.75f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_SNARE.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        hitASide = true;
                    }
                    else if(bhr.getDirection() == Direction.SOUTH){
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_BASEDRUM.get(), SoundSource.BLOCKS,1.0f,0.5f);
                        lvl.playSound(null,bp, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS,0.75f,0.1f);
                        hitASide = true;
                    }
                }
            }
            if(hitASide){
                ply.swing(hand);
                return InteractionResult.CONSUME;
            }
            return InteractionResult.PASS;
        }
    }
}
