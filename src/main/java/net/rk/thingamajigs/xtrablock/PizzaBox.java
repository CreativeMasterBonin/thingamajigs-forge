package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaBox extends Block {
    public static final BooleanProperty OPEN = BlockStateProperties.OPEN;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape ALL = Block.box(0.0D,0.0D,0.0D,16.0D,2.0D,16.0D);

    public PizzaBox(Properties p) {
        super(p.strength(1F)
                .sound(SoundType.BAMBOO_WOOD)
                .instrument(NoteBlockInstrument.BANJO)
                .mapColor(MapColor.TERRACOTTA_WHITE)
                .noCollission()
                .noOcclusion());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(OPEN,false));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.pizza_box.desc"));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player p, InteractionHand ih, BlockHitResult bhr) {
        if(p.isShiftKeyDown()){
            if(!lvl.isClientSide()){
                float rndPitchF = lvl.random.nextFloat();
                lvl.setBlock(bp,bs.cycle(OPEN),2);
                if(bs.getValue(OPEN)){
                    lvl.playSound(null,bp, SoundEvents.BARREL_CLOSE, SoundSource.BLOCKS,1F,rndPitchF + 0.7F);
                }
                else{
                    lvl.playSound(null,bp, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS,1F,rndPitchF + 0.7F);
                }
                return InteractionResult.SUCCESS;
            }
        }
        else{
            return InteractionResult.PASS;
        }
        return InteractionResult.CONSUME;
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
        p_49915_.add(FACING,OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite()).setValue(OPEN, false);
    }
}
