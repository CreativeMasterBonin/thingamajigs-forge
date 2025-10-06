package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.stream.Stream;

public class TallLamp extends Block {

    public static final VoxelShape TALL_LAMP_SHAPE = Stream.of(
            Block.box(5, 0, 5, 11, 1, 11),
            Block.box(7.5, 1, 7.5, 8.5, 27, 8.5),
            Block.box(3.5, 27, 3.5, 12.5, 32, 12.5)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public static final BooleanProperty ON = BooleanProperty.create("on");

    public TallLamp(Properties p) {
        super(p.sound(SoundType.LANTERN).strength(1.2F,5F).noOcclusion().mapColor(MapColor.COLOR_YELLOW));
        this.registerDefaultState(this.defaultBlockState().setValue(ON, false));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return TALL_LAMP_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player player, InteractionHand ih, BlockHitResult bhr) {
        if(player.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                lvl.setBlock(bp, bs.cycle(ON), 2);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ON);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(ON,false);
    }
}
