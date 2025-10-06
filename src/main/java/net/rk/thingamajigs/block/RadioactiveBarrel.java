package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RadioactiveBarrel extends ThingamajigsDecorativeBlock {
    public static final VoxelShape BARREL = Block.box(1, 0, 1, 15, 16, 15);
    public RadioactiveBarrel(Properties properties) {
        super(properties.sound(SoundType.COPPER).strength(1F,50F));
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return BARREL;
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable BlockGetter bg, List<Component> list, TooltipFlag tf) {
        list.add(Component.translatable("tooltip.thingamajigs.useless_storage"));
    }
}
