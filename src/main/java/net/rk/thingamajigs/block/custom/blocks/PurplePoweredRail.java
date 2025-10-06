package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.RailShape;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurplePoweredRail extends PoweredRailBlock {
    public static final EnumProperty<RailShape> SHAPE = BlockStateProperties.RAIL_SHAPE_STRAIGHT;
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    private final boolean isActivator;

    public PurplePoweredRail(BlockBehaviour.Properties p_55218_) {
        this(p_55218_.noCollission(), false);
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable BlockGetter bg, List<Component> c, TooltipFlag flag) {
        c.add(Component.translatable("block.thingamajigs.purple_powered_rail.desc").withStyle(ChatFormatting.GRAY));
    }

    protected PurplePoweredRail(Properties builder, boolean isPoweredRail) {
        super(builder, true);
        this.registerDefaultState(this.stateDefinition.any().setValue(SHAPE, RailShape.NORTH_SOUTH).setValue(POWERED,false).setValue(WATERLOGGED, Boolean.valueOf(false)));
        this.isActivator = false;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SHAPE, POWERED, WATERLOGGED);
    }

    public boolean isActivatorRail() {
        return false;
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart) {
        boolean p = state.getValue(POWERED);
        if (!p){
            return 0.9F;
        }
        else {
            return 1.1F;
        }
    }
}
