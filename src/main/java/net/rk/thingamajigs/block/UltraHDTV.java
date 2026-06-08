package net.rk.thingamajigs.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UltraHDTV extends ThingamajigsDecorativeBlock {
    // channels as blockstates here
    public static final IntegerProperty CHANNEL = IntegerProperty.create("channel",0,3);
    public UltraHDTV(Properties properties) {
        super(properties.strength(0.75F,1.25F).sound(SoundType.LANTERN).noOcclusion().noCollission());
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false)
                .setValue(CHANNEL,0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CHANNEL);
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player plr, InteractionHand hand, BlockHitResult bhr) {
        if(!lvl.isClientSide()){
            if(plr.getItemInHand(hand).is(Items.AIR)){
                plr.displayClientMessage(Component.translatable("block.uhd_tv.message"),true);
                lvl.setBlock(bp,bs.cycle(CHANNEL),3);
                return InteractionResult.CONSUME;
            }
        }
        else{
            if(plr.getItemInHand(hand).is(Items.AIR)){
                plr.playSound(SoundEvents.CAKE_ADD_CANDLE,1f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.0f));
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.uhd_tv.desc"));
    }
}
