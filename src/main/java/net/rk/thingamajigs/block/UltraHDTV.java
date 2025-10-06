package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UltraHDTV extends ThingamajigsDecorativeBlock {
    public UltraHDTV(Properties properties) {
        super(properties.strength(0.75F,1.25F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player plr, InteractionHand hand, BlockHitResult bhr) {
        if(plr.getItemInHand(hand).is(Items.AIR)){
            plr.displayClientMessage(Component.translatable("block.uhd_tv.message"),true);
            return InteractionResult.CONSUME;
        }
        else{
            return InteractionResult.PASS;
        }
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.uhd_tv.desc"));
    }
}
