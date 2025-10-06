package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.block.custom.blocks.FlatWallPlaneBlock;
import net.rk.thingamajigs.events.ThingamajigsSoundTypes;

public class Calendar extends ThingamajigsDecorativeBlock {
    public static final IntegerProperty DAY = IntegerProperty.create("day",0,6);

    public Calendar(Properties properties) {
        super(properties.strength(1f,2f).pushReaction(PushReaction.DESTROY).sound(ThingamajigsSoundTypes.CALENDAR));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        Direction d = bs.getValue(FACING);
        return switch (d) {
            case NORTH -> FlatWallPlaneBlock.NORTH_SHAPE;
            case SOUTH -> FlatWallPlaneBlock.SOUTH_SHAPE;
            case EAST -> FlatWallPlaneBlock.EAST_SHAPE;
            case WEST -> FlatWallPlaneBlock.WEST_SHAPE;
            default -> Shapes.block();
        };
    }
}
