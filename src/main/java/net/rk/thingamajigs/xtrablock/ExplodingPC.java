package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

public class ExplodingPC extends ThingamajigsDecorativeBlock {
    // recommended use case: never!
    public ExplodingPC(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player pl, InteractionHand ih, BlockHitResult bhr) {
        if(!lvl.isClientSide){
            float explosionPower = (float)lvl.random.nextFloat() + 2.0F;
            lvl.explode(null,bp.getX(),bp.getY(),bp.getZ(),explosionPower,Level.ExplosionInteraction.TNT);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
