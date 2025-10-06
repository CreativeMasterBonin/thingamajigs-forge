package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.Sign;

public class BigSign extends Sign {
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,1);

    public BigSign(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false)
                .setValue(TYPE,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(TYPE);
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand ih, BlockHitResult bhr) {
        if(lvl.isClientSide){
            return InteractionResult.SUCCESS;
        }
        else{
            if(ply.getItemInHand(ih).isEmpty()){
                lvl.setBlock(bp,bs.cycle(TYPE),3);
                lvl.playSound(null,bp,
                        SoundEvents.ITEM_FRAME_ROTATE_ITEM,
                        SoundSource.BLOCKS,1F,1F);
                ply.swing(ih);
                return InteractionResult.CONSUME;
            }
            return InteractionResult.PASS;
        }
    }
}
