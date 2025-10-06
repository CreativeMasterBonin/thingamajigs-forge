package net.rk.thingamajigs.block.custom.blocks;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.entity.InfiMoveCart;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PurpleRailBlock extends RailBlock {
    public PurpleRailBlock(Properties p) {
        super(p.noCollission());
    }

    @Override
    public float getRailMaxSpeed(BlockState state, Level level, BlockPos pos, AbstractMinecart cart) {
        if(isFlexibleRail(state,level,pos)){
            return 0.91f;
        }
        return 1.1F;
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable BlockGetter bg, List<Component> c, TooltipFlag flag) {
        c.add(Component.translatable("block.thingamajigs.purple_rail.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean canSurvive(BlockState bs, LevelReader lr, BlockPos bp) {
        return true;
    }

    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block block1, BlockPos bp2, boolean bool1) {
        if (!lvl.isClientSide && lvl.getBlockState(bp).is(this)) {
            RailShape railshape = getRailDirection(bs, lvl, bp, null);
            this.updateState(bs, lvl, bp, block1);
        }
    }
}
