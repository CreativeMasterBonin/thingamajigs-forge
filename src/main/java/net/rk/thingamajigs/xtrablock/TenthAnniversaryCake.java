package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.List;

public class TenthAnniversaryCake extends ThingamajigsDecorativeBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final VoxelShape ALL = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
    public TenthAnniversaryCake(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("block.thingamajigs.tenth_anniversary_cake.desc"));
    }

    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return ALL;
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player pl, InteractionHand ih, BlockHitResult bhr) {
        if(!lvl.isClientSide){
            if(pl.isShiftKeyDown()){
                lvl.playSound(null,bp, SoundEvents.GENERIC_EAT, SoundSource.PLAYERS,1F,0.5F);
                lvl.playSound(null,bp, SoundEvents.PLAYER_BURP, SoundSource.PLAYERS,1F,0.7F);
                pl.addEffect(new MobEffectInstance(
                        MobEffects.SATURATION,
                        5,
                        1,
                        true,
                        false,
                        false));
                lvl.destroyBlock(bp,false);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }
}
