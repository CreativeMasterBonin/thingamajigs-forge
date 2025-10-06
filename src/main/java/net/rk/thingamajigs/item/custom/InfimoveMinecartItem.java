package net.rk.thingamajigs.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.RailShape;
import net.minecraft.world.level.gameevent.GameEvent;
import net.rk.thingamajigs.entity.InfiMoveCart;

import javax.annotation.Nullable;
import java.util.List;

public class InfimoveMinecartItem extends Item {
    public InfimoveMinecartItem(Properties p) {
        super(p);
        DispenserBlock.registerBehavior(this, CUSTOM_DIB_1);
    }

    private static final DispenseItemBehavior CUSTOM_DIB_1 = new DefaultDispenseItemBehavior() {
        private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

        public ItemStack execute(BlockSource bs, ItemStack itsk) {
            Direction direction = bs.getBlockState().getValue(DispenserBlock.FACING);
            Level level = bs.getLevel();
            double d0 = bs.x() + (double)direction.getStepX() * 1.125D;
            double d1 = Math.floor(bs.y()) + (double)direction.getStepY();
            double d2 = bs.z() + (double)direction.getStepZ() * 1.125D;
            BlockPos blockpos = bs.getPos().relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock)blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, null) : RailShape.NORTH_SOUTH;
            double d3;
            if (blockstate.is(BlockTags.RAILS)) {
                if (railshape.isAscending()) {
                    d3 = 0.6D;
                } else {
                    d3 = 0.1D;
                }
            } else {
                if (!blockstate.isAir() || !level.getBlockState(blockpos.below()).is(BlockTags.RAILS)) {
                    return this.defaultDispenseItemBehavior.dispense(bs, itsk);
                }

                BlockState blockstate1 = level.getBlockState(blockpos.below());
                RailShape railshape1 = blockstate1.getBlock() instanceof BaseRailBlock ? blockstate1.getValue(((BaseRailBlock)blockstate1.getBlock()).getShapeProperty()) : RailShape.NORTH_SOUTH;
                if (direction != Direction.DOWN && railshape1.isAscending()) {
                    d3 = -0.4D;
                } else {
                    d3 = -0.9D;
                }
            }
            // override for this specific cart
            double d4 = d1 + d3;
            InfiMoveCart cart = new InfiMoveCart(level,d0,d4,d2);

            if (itsk.hasCustomHoverName()) {
                cart.setCustomName(itsk.getHoverName());
            }

            level.addFreshEntity(cart);
            itsk.shrink(1);
            return itsk;
        }

        protected void playSound(BlockSource bs2) {
            bs2.getLevel().levelEvent(1000, bs2.getPos(), 0);
        }
    };

    @Override
    public InteractionResult useOn(UseOnContext uoc) {
        Level level = uoc.getLevel();
        BlockPos blockpos = uoc.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        if (!blockstate.is(BlockTags.RAILS)) {
            return InteractionResult.FAIL;
        } else {
            ItemStack itemstack = uoc.getItemInHand();
            if (!level.isClientSide) {
                RailShape railshape = blockstate.getBlock() instanceof BaseRailBlock ? ((BaseRailBlock)blockstate.getBlock()).getRailDirection(blockstate, level, blockpos, null) : RailShape.NORTH_SOUTH;
                double d0 = 0.0D;
                if (railshape.isAscending()) {
                    d0 = 0.5D;
                }

                // override for this specific cart item
                double xd0 = (double)blockpos.getX() + 0.5D;
                double yd0 = (double)blockpos.getY() + 0.0625D + d0;
                double zd0 = (double)blockpos.getZ() + 0.5D;

                InfiMoveCart cart = new InfiMoveCart(level, xd0, yd0, zd0);

                if (itemstack.hasCustomHoverName()) {
                    cart.setCustomName(itemstack.getHoverName());
                }

                level.addFreshEntity(cart);
                level.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(uoc.getPlayer(), level.getBlockState(blockpos.below())));

                // extra pizzazz!
                RandomSource rs = level.getRandom();
                float pitch = (float)rs.nextFloat() + 0.5F;
                level.playSound(uoc.getPlayer(),uoc.getClickedPos(), SoundEvents.LANTERN_PLACE, SoundSource.PLAYERS,0.8F,pitch);
            }

            itemstack.shrink(1);
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
    }

    @Override
    public void appendHoverText(ItemStack stk, @Nullable Level lvl, List<Component> ttc, TooltipFlag adv) {
        ttc.add(Component.translatable("tooltip.thingamajigs.infimove_cart_item").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stk, lvl, ttc, adv);
    }

}
