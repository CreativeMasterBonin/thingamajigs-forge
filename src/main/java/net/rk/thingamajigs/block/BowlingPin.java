package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.Thingamajigs;

import java.util.logging.Logger;

public class BowlingPin extends Block {
    public static final VoxelShape BOWLING_PIN_SHAPE = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

    public BowlingPin(Properties p) {
        super(p.strength(0.8F,1.2F).sound(SoundType.BONE_BLOCK).noOcclusion());
    }

    @SuppressWarnings("deprecated")
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return BOWLING_PIN_SHAPE;
    }

    @SuppressWarnings("deprecated")
    @Override
    public void entityInside(BlockState bs, Level lvl, BlockPos bp, Entity e) {
        if(e instanceof ItemEntity){
            lvl.playSound(null,bp, SoundEvents.DECORATED_POT_HIT, SoundSource.BLOCKS,2.0f,1.0f);
            lvl.setBlock(bp,Blocks.AIR.defaultBlockState(),3);
            double xd = e.getDeltaMovement().x;
            double yd = e.getDeltaMovement().y;
            double zd = e.getDeltaMovement().z;

            try{
                ItemEntity nEe1 = new ItemEntity(lvl,bp.getX(),bp.getY(),bp.getZ(),new ItemStack(this.asItem()),xd,yd,zd);
                lvl.addFreshEntity(nEe1);
                lvl.updateNeighborsAt(bp,this);
            }
            catch(Exception ne1){
                Logger.getAnonymousLogger().warning(ne1.getMessage());
            }
        }
    }
}
