package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.stream.Stream;

public class KitchenAppliance extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NS_INSTANT_POT = Stream.of(
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 1, 0, 16, 3, 16),
            Block.box(0, 17, 0, 16, 19, 16),
            Block.box(0, 3, 0, 16, 17, 16),
            Block.box(-2, 18, 5, 0, 19, 11),
            Block.box(-1, 19, 6, 1, 20, 10),
            Block.box(1, 20, 6.5, 15, 21, 9.5),
            Block.box(15, 19, 6, 17, 20, 10),
            Block.box(16, 18, 5, 18, 19, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EW_INSTANT_POT = Stream.of(
            Block.box(15, 0, 0, 16, 1, 1),
            Block.box(15, 0, 15, 16, 1, 16),
            Block.box(0, 0, 15, 1, 1, 16),
            Block.box(0, 0, 0, 1, 1, 1),
            Block.box(0, 1, 0, 16, 3, 16),
            Block.box(0, 17, 0, 16, 19, 16),
            Block.box(0, 3, 0, 16, 17, 16),
            Block.box(5, 18, -2, 11, 19, 0),
            Block.box(6, 19, -1, 10, 20, 1),
            Block.box(6.5, 20, 1, 9.5, 21, 15),
            Block.box(6, 19, 15, 10, 20, 17),
            Block.box(5, 18, 16, 11, 19, 18)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape N_AIR_FRYER = Stream.of(
            Block.box(3, 1, 0, 13, 9, 2),
            Block.box(1, 0, 0, 15, 1, 15),
            Block.box(1, 1, 0, 3, 9, 9),
            Block.box(13, 1, 0, 15, 9, 9),
            Block.box(7, 7, -2, 9, 9, 0),
            Block.box(7, 3, -4, 9, 9, -2),
            Block.box(1, 9, 0, 15, 16, 9),
            Block.box(1, 1, 9, 15, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape E_AIR_FRYER = Stream.of(
            Block.box(14, 1, 3, 16, 9, 13),
            Block.box(1, 0, 1, 16, 1, 15),
            Block.box(7, 1, 1, 16, 9, 3),
            Block.box(7, 1, 13, 16, 9, 15),
            Block.box(16, 7, 7, 18, 9, 9),
            Block.box(18, 3, 7, 20, 9, 9),
            Block.box(7, 9, 1, 16, 16, 15),
            Block.box(1, 1, 1, 7, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape S_AIR_FRYER = Stream.of(
            Block.box(3, 1, 14, 13, 9, 16),
            Block.box(1, 0, 1, 15, 1, 16),
            Block.box(13, 1, 7, 15, 9, 16),
            Block.box(1, 1, 7, 3, 9, 16),
            Block.box(7, 7, 16, 9, 9, 18),
            Block.box(7, 3, 18, 9, 9, 20),
            Block.box(1, 9, 7, 15, 16, 16),
            Block.box(1, 1, 1, 15, 16, 7)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape W_AIR_FRYER = Stream.of(
            Block.box(0, 1, 3, 2, 9, 13),
            Block.box(0, 0, 1, 15, 1, 15),
            Block.box(0, 1, 13, 9, 9, 15),
            Block.box(0, 1, 1, 9, 9, 3),
            Block.box(-2, 7, 7, 0, 9, 9),
            Block.box(-4, 3, 7, -2, 9, 9),
            Block.box(0, 9, 1, 9, 16, 15),
            Block.box(9, 1, 1, 15, 16, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape BREAD_MACHINE_ALL = Stream.of(
            Block.box(1, 12, 1, 15, 13, 15),
            Block.box(2, 0, 2, 3, 1, 3),
            Block.box(13, 0, 2, 14, 1, 3),
            Block.box(13, 0, 13, 14, 1, 14),
            Block.box(2, 0, 13, 3, 1, 14),
            Block.box(1, 1, 1, 15, 2, 15),
            Block.box(1, 2, 1, 2, 12, 15),
            Block.box(14, 2, 1, 15, 12, 15),
            Block.box(2, 2, 1, 14, 12, 1),
            Block.box(2, 2, 15, 14, 12, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final VoxelShape RICE_COOKER_ALL = Stream.of(
            Block.box(1, 0, 1, 2, 1, 2),
            Block.box(14, 0, 1, 15, 1, 2),
            Block.box(1, 0, 14, 2, 1, 15),
            Block.box(14, 0, 14, 15, 1, 15),
            Block.box(1, 1, 1, 15, 13, 15),
            Block.box(0, 13, 0, 15, 14, 1),
            Block.box(15, 13, 0, 16, 14, 15),
            Block.box(0, 13, 1, 1, 14, 16),
            Block.box(1, 13, 15, 16, 14, 16),
            Block.box(1, 14, 1, 15, 15, 15),
            Block.box(6, 15, 6, 10, 16, 10),
            Block.box(1, 1, 15, 15, 13, 15),
            Block.box(1, 1, 1, 1, 13, 15),
            Block.box(1, 1, 1, 15, 13, 1),
            Block.box(15, 1, 1, 15, 13, 15),
            Block.box(1, 1, 1, 15, 1, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();





    public KitchenAppliance(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        Direction direction = state.getValue(FACING);
        if(state.is(ThingamajigsBlocks.INSTANT_POT.get())){
            switch (direction){
                case NORTH,SOUTH -> {return NS_INSTANT_POT;}
                case EAST,WEST -> {return EW_INSTANT_POT;}
                default -> {return Shapes.block();}
            }
        }
        else if(state.is(ThingamajigsBlocks.RICE_COOKER.get())){
            return RICE_COOKER_ALL;
        }
        else if(state.is(ThingamajigsBlocks.BREAD_MACHINE.get())){
            return BREAD_MACHINE_ALL;
        }
        else if(state.is(ThingamajigsBlocks.AIR_FRYER.get())){
            switch (direction){
                case NORTH -> {return N_AIR_FRYER;}
                case SOUTH -> {return S_AIR_FRYER;}
                case EAST -> {return E_AIR_FRYER;}
                case WEST -> {return W_AIR_FRYER;}
                default -> {return Shapes.block();}
            }
        }
        return Shapes.block();
    }
}
