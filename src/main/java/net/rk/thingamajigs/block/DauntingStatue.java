package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.blocks.Podium;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class DauntingStatue extends Podium {
    // NOTE: statue can't do anything due to missing features in this version
    public DauntingStatue(Properties properties) {
        super(properties.randomTicks().sound(SoundType.COPPER)
                .requiresCorrectToolForDrops()
                .strength(3.0f,6.0f));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED,false));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("statue.thingamajigs.author.cmb").withStyle(ChatFormatting.BLUE));
    }

    public static final VoxelShape NORTH = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(4, 16, 7, 12, 18, 9),
            Block.box(4, 26, 7, 12, 28, 9),
            Block.box(12, 18, 7, 14, 26, 9),
            Block.box(2, 18, 7, 4, 26, 9),
            Block.box(7, 21, 12, 9, 23, 14),
            Block.box(7.5, 21.5, 3, 8.5, 22.5, 4),
            Block.box(5, 21, 2, 6, 23, 6),
            Block.box(10, 21, 2, 11, 23, 6),
            Block.box(7, 24, 2, 9, 25, 6),
            Block.box(7, 19, 2, 9, 20, 6),
            Block.box(7, 24, 10, 9, 25, 14),
            Block.box(7, 19, 10, 9, 20, 14),
            Block.box(10, 21, 10, 11, 23, 14),
            Block.box(5, 21, 10, 6, 23, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape EAST = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(7, 16, 4, 9, 18, 12),
            Block.box(7, 26, 4, 9, 28, 12),
            Block.box(7, 18, 12, 9, 26, 14),
            Block.box(7, 18, 2, 9, 26, 4),
            Block.box(2, 21, 7, 4, 23, 9),
            Block.box(12, 21.5, 7.5, 13, 22.5, 8.5),
            Block.box(10, 21, 5, 14, 23, 6),
            Block.box(10, 21, 10, 14, 23, 11),
            Block.box(10, 24, 7, 14, 25, 9),
            Block.box(10, 19, 7, 14, 20, 9),
            Block.box(2, 24, 7, 6, 25, 9),
            Block.box(2, 19, 7, 6, 20, 9),
            Block.box(2, 21, 10, 6, 23, 11),
            Block.box(2, 21, 5, 6, 23, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape SOUTH = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(4, 16, 7, 12, 18, 9),
            Block.box(4, 26, 7, 12, 28, 9),
            Block.box(2, 18, 7, 4, 26, 9),
            Block.box(12, 18, 7, 14, 26, 9),
            Block.box(7, 21, 2, 9, 23, 4),
            Block.box(7.5, 21.5, 12, 8.5, 22.5, 13),
            Block.box(10, 21, 10, 11, 23, 14),
            Block.box(5, 21, 10, 6, 23, 14),
            Block.box(7, 24, 10, 9, 25, 14),
            Block.box(7, 19, 10, 9, 20, 14),
            Block.box(7, 24, 2, 9, 25, 6),
            Block.box(7, 19, 2, 9, 20, 6),
            Block.box(5, 21, 2, 6, 23, 6),
            Block.box(10, 21, 2, 11, 23, 6)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    public static final VoxelShape WEST = Stream.of(
            Block.box(3, 0, 3, 13, 2, 13),
            Block.box(4, 2, 4, 12, 12, 12),
            Block.box(3, 12, 3, 13, 14, 13),
            Block.box(6, 20, 6, 10, 24, 10),
            Block.box(7, 16, 4, 9, 18, 12),
            Block.box(7, 26, 4, 9, 28, 12),
            Block.box(7, 18, 2, 9, 26, 4),
            Block.box(7, 18, 12, 9, 26, 14),
            Block.box(12, 21, 7, 14, 23, 9),
            Block.box(3, 21.5, 7.5, 4, 22.5, 8.5),
            Block.box(2, 21, 10, 6, 23, 11),
            Block.box(2, 21, 5, 6, 23, 6),
            Block.box(2, 24, 7, 6, 25, 9),
            Block.box(2, 19, 7, 6, 20, 9),
            Block.box(10, 24, 7, 14, 25, 9),
            Block.box(10, 19, 7, 14, 20, 9),
            Block.box(10, 21, 5, 14, 23, 6),
            Block.box(10, 21, 10, 14, 23, 11)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext cc) {
        switch(state.getValue(FACING)){
            case NORTH -> {
                return NORTH;
            }
            case SOUTH -> {
                return SOUTH;
            }
            case EAST -> {
                return EAST;
            }
            case WEST -> {
                return WEST;
            }
            default -> {return Shapes.block();}
        }
    }
}
