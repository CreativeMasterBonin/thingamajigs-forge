package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.Sign;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmileySign extends Sign {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape SIGN_SHAPE_SIDE = Block.box(6.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D);
    public static final VoxelShape SIGN_SHAPE = Block.box(0.0D, 0.0D, 6.0D, 16.0D, 16.0D, 8.0D);

    public SmileySign(Properties properties) {
        super(properties.strength(0.25F).sound(SoundType.LANTERN).noOcclusion().noCollission());
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter blockGetter, List<Component> componentList, TooltipFlag tooltipFlag) {
        componentList.add(Component.translatable("tooltip.thingamajigs.smiley_sign"));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        switch(direction){
            case NORTH:
                return SIGN_SHAPE;
            case SOUTH:
                return SIGN_SHAPE;
            case EAST:
                return SIGN_SHAPE_SIDE;
            case WEST:
                return SIGN_SHAPE_SIDE;
            default:
                return SIGN_SHAPE;
        }
    }
}
