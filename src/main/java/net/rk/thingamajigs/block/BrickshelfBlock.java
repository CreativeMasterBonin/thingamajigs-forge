package net.rk.thingamajigs.block;

public class BrickshelfBlock extends CustomBookshelf {
    public BrickshelfBlock(Properties p) {
        super(p);
    }

    /*
    @Override
    public InteractionResult use(BlockState bs, Level lv, BlockPos bp, Player pl, InteractionHand ih, BlockHitResult bhr) {
        if(!lv.isClientSide()){
            if(pl.isShiftKeyDown()){
                if(pl.getItemInHand(ih) == Items.BRICK.getDefaultInstance()){
                    lv.setBlock(bp, Blocks.AIR.defaultBlockState(), 3);
                    // make new falling block entity object
                    FallingBlockEntity fbe = FallingBlockEntity.fall(lv,bp,bs);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
     */
}