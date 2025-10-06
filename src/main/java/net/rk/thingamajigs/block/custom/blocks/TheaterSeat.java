package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.entity.ChairEntity;

import java.util.List;
import java.util.stream.Stream;

public class TheaterSeat extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTH_S = Stream.of(
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 2, 0, 16, 7, 16),
            Block.box(0, 7, 0, 16, 9, 16),
            Block.box(0, 9, 16, 16, 12, 17),
            Block.box(0, 12, 17, 16, 15, 18),
            Block.box(0, 15, 18, 16, 18, 19),
            Block.box(0, 21, 20, 16, 24, 21),
            Block.box(0, 18, 19, 16, 21, 20)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH_S = Stream.of(
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(0, 2, 0, 16, 7, 16),
            Block.box(0, 7, 0, 16, 9, 16),
            Block.box(0, 9, -1, 16, 12, 0),
            Block.box(0, 12, -2, 16, 15, -1),
            Block.box(0, 15, -3, 16, 18, -2),
            Block.box(0, 21, -5, 16, 24, -4),
            Block.box(0, 18, -4, 16, 21, -3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST_S = Stream.of(
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(0, 2, 0, 16, 7, 16),
            Block.box(0, 7, 0, 16, 9, 16),
            Block.box(-1, 9, 0, 0, 12, 16),
            Block.box(-2, 12, 0, -1, 15, 16),
            Block.box(-3, 15, 0, -2, 18, 16),
            Block.box(-5, 21, 0, -4, 24, 16),
            Block.box(-4, 18, 0, -3, 21, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST_S = Stream.of(
            Block.box(0, 0, 14, 2, 2, 16),
            Block.box(14, 0, 14, 16, 2, 16),
            Block.box(0, 0, 0, 2, 2, 2),
            Block.box(14, 0, 0, 16, 2, 2),
            Block.box(0, 2, 0, 16, 7, 16),
            Block.box(0, 7, 0, 16, 9, 16),
            Block.box(16, 9, 0, 17, 12, 16),
            Block.box(17, 12, 0, 18, 15, 16),
            Block.box(18, 15, 0, 19, 18, 16),
            Block.box(20, 21, 0, 21, 24, 16),
            Block.box(19, 18, 0, 20, 21, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public TheaterSeat(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH: return NORTH_S;
            case SOUTH: return SOUTH_S;
            case EAST: return EAST_S;
            case WEST: return WEST_S;
            default: return Shapes.block();
        }
    }

    @Override
    public InteractionResult use(BlockState bs, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide){
                List<ChairEntity> chairs = level.getEntitiesOfClass(ChairEntity.class, aabb);
                // we WANT a chair
                if(chairs.isEmpty()){
                    ChairEntity ce = ChairEntity.requestNewEntity(level,pos,bs);
                    level.addFreshEntity(ce);
                    player.startRiding(ce,false);
                }
            }

            playCustomSitSound(level,pos,player);
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
        return InteractionResult.SUCCESS;
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SoundEvents.WOOL_PLACE;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
