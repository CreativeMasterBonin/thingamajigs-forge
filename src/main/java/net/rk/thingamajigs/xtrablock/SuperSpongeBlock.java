package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.MapColor;

// super sponge uses the sponge as a base, but expands upon it making it easier to remove water, but slightly more laggy...
// recommended to be used in map-making only, hence no crafting recipe and OPs only...
public class SuperSpongeBlock extends Block {
    public static final int MAX_DEPTH = 64; // extend depth of water block search
    public static final int MAX_COUNT = 2048; // more blocks are allowed to be removed in one go
    private static final Direction[] ALL_DIRECTIONS = Direction.values();

    public SuperSpongeBlock(Properties p) {
        super(p.mapColor(MapColor.COLOR_YELLOW).strength(0.65F,10F).sound(SoundType.GRASS));
    }

    @SuppressWarnings("deprecated")
    @Override
    public void onPlace(BlockState bs, Level lvl1, BlockPos bp1, BlockState bs1, boolean bl) {
        if (!bs1.is(bs.getBlock())) {
            this.tryAbsorbWater(lvl1, bp1);
        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public void neighborChanged(BlockState bs1, Level lvl1, BlockPos bp1, Block b1, BlockPos bp2, boolean bl2) {
        this.tryAbsorbWater(lvl1, bp1);
        super.neighborChanged(bs1, lvl1, bp1, b1, bp2, bl2);
    }

    protected void tryAbsorbWater(Level trLvl, BlockPos trbp) {
        if (this.removeWaterBreadthFirstSearch(trLvl, trbp)) {
            trLvl.setBlock(trbp, Blocks.AIR.defaultBlockState(), 2); // since this isn't craftable, we don't want another block to be placed
            trLvl.levelEvent(2001, trbp, Block.getId(Blocks.WATER.defaultBlockState()));
        }
    }

    // derived from sponge code (some attributes changed to reflect new block's purpose (such as expandable range/depth)
    private boolean removeWaterBreadthFirstSearch(Level lvl, BlockPos bp) {
        int conditionalFake = 0; // we will NEVER use the 'turn this to a wet block because it can be' of the original sponge, so it was removed
        // the breadth function is built in, why change it? for depth-first? nope.
        return BlockPos.breadthFirstTraversal(bp, MAX_DEPTH - 1, MAX_COUNT - 1, (abc, def) -> {
            for(Direction direction : ALL_DIRECTIONS) {
                def.accept(abc.relative(direction));
            }
        }, (p_279054_) -> {
            if (p_279054_.equals(bp)) {
                return true;
            } else {
                BlockState blockstate = lvl.getBlockState(p_279054_);
                FluidState fluidstate = lvl.getFluidState(p_279054_);
                if (conditionalFake == 1) {
                    return false;
                }
                else {
                    Block block = blockstate.getBlock();
                    if (block instanceof BucketPickup) {
                        BucketPickup bucketpickup = (BucketPickup)block;
                        if (!bucketpickup.pickupBlock(lvl, p_279054_, blockstate).isEmpty()) {
                            return true;
                        }
                    }

                    if (blockstate.getBlock() instanceof LiquidBlock) {
                        lvl.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                    }
                    else {
                        if (!blockstate.is(Blocks.KELP) && !blockstate.is(Blocks.KELP_PLANT) && !blockstate.is(Blocks.SEAGRASS) && !blockstate.is(Blocks.TALL_SEAGRASS)) {
                            return false; // we don't want to change this (these blocks hold water)
                        }
                        BlockEntity blockentity = blockstate.hasBlockEntity() ? lvl.getBlockEntity(p_279054_) : null;
                        dropResources(blockstate, lvl, p_279054_, blockentity);
                        lvl.setBlock(p_279054_, Blocks.AIR.defaultBlockState(), 3);
                    }
                    return true;
                }
            }
        }) > 1;
    }
}
