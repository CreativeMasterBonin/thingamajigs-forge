package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.ChairEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.rk.thingamajigs.xtrablock.ChairBlock.*;

public class WaxedCopperChairBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public WaxedCopperChairBlock(Properties p) {
        super(p);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return Shapes.block();
        }
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player pl, InteractionHand h, BlockHitResult bhr) {
        if(pl.getItemInHand(h).is(ItemTags.AXES)){
            toBeStripped(bs,lvl,bp);
            lvl.playSound(null,bp,SoundEvents.AXE_WAX_OFF,SoundSource.PLAYERS,1.0F,1.0F);
            ParticleUtils.spawnParticlesOnBlockFaces(lvl, bp, ParticleTypes.WAX_OFF, UniformInt.of(3, 5));
            pl.swing(h);
            return InteractionResult.CONSUME;
        }
        else{
            try{
                double a = bp.getX() + 1.0D;
                double b = bp.getY() + 1.0D;
                double c = bp.getZ() + 1.0D;
                AABB aabb = new AABB(bp.getX(),bp.getY(),bp.getZ(),a,b,c);

                if(!lvl.isClientSide){

                    List<ChairEntity> chairs = lvl.getEntitiesOfClass(ChairEntity.class, aabb);
                    // we WANT a chair
                    if(chairs.isEmpty()){
                        ChairEntity ce = ChairEntity.requestNewEntity(lvl,bp,bs);
                        lvl.addFreshEntity(ce);
                        pl.startRiding(ce,false);
                    }
                }

                playCustomSitSound(lvl,bp,pl);
                return InteractionResult.SUCCESS;
            }
            catch(Exception e){
                return InteractionResult.FAIL;
            }
        }
    }

    public void toBeStripped(BlockState b, Level l, BlockPos p){
        BlockState normal = ThingamajigsBlocks.COPPER_CHAIR.get().defaultBlockState();
        BlockState ok = ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get().defaultBlockState();
        BlockState bad = ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get().defaultBlockState();
        BlockState awful = ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get().defaultBlockState();

        // states to choose from
        if(b.is(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get())){
            l.setBlock(p,awful.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_CHAIR.get())){
            l.setBlock(p,bad.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_CHAIR.get())){
            l.setBlock(p,ok.setValue(FACING,b.getValue(FACING)),3);
        }
        else if(b.is(ThingamajigsBlocks.WAXED_COPPER_CHAIR.get())){
            l.setBlock(p,normal.setValue(FACING,b.getValue(FACING)),3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext bpc) {
        return this.defaultBlockState().setValue(FACING, bpc.getHorizontalDirection().getOpposite());
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SoundEvents.COPPER_HIT;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
