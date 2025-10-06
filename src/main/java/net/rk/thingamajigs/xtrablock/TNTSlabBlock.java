package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class TNTSlabBlock extends SlabBlock {
    /*
        25% chance to spawn anvils above the nearest player's head not implemented yet
    */
    public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

    public TNTSlabBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(TYPE, SlabType.BOTTOM)
                .setValue(WATERLOGGED, false)
                .setValue(UNSTABLE,false));
    }

    public static void explode(Level lvl, BlockPos bp) {
        explode(lvl, bp, (LivingEntity)null);
    }

    private static void explode(Level lvl, BlockPos bp, @Nullable LivingEntity le) {
        double x = bp.getX();
        double y = bp.getY();
        double z = bp.getZ();

        if (!lvl.isClientSide) {
            PrimedTnt primedtnt = new PrimedTnt(lvl, x + 0.5D, y, z + 0.5D, le);
            lvl.addFreshEntity(primedtnt);
            lvl.playSound((Player)null, primedtnt.getX(), primedtnt.getY(), primedtnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            lvl.gameEvent(le, GameEvent.PRIME_FUSE, bp);
        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player player1, InteractionHand ih, BlockHitResult bhr) {
        ItemStack itemstack = player1.getItemInHand(ih);

        if(!bs.getValue(WATERLOGGED)){
            /*
            if(lvl.getRandom().nextInt(25) == 0){
                player1.displayClientMessage(Component.translatable("message.tnt_slab.too_lazy"),true);
                if(!lvl.isClientSide){
                    lvl.playSound(null,bp,SoundEvents.AMBIENT_CAVE.get(),SoundSource.BLOCKS,0.75F,1.0F);
                }
                lvl.removeBlock(bp,false);
            }
            */
            // normal stuff
            if (!itemstack.is(Items.FLINT_AND_STEEL) && !itemstack.is(Items.FIRE_CHARGE)) {
                return InteractionResult.PASS;
            }
            else {
                onCaughtFire(bs, lvl, bp, bhr.getDirection(), player1);
                lvl.setBlock(bp, Blocks.AIR.defaultBlockState(), 11);
                Item item = itemstack.getItem();
                if (!player1.isCreative()) {
                    if (itemstack.is(Items.FLINT_AND_STEEL)) {
                        itemstack.hurtAndBreak(1, player1, (p_57425_) -> {p_57425_.broadcastBreakEvent(ih);});
                    } else {
                        itemstack.shrink(1);
                    }
                }

                player1.awardStat(Stats.ITEM_USED.get(item));
                return InteractionResult.sidedSuccess(lvl.isClientSide);
            }
        }
        else{
            return InteractionResult.PASS;
        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public void onProjectileHit(Level lvl, BlockState bs, BlockHitResult bhr, Projectile proj) {
        if (!lvl.isClientSide) {
            if(!bs.getValue(WATERLOGGED)){
                BlockPos blockpos = bhr.getBlockPos();
                Entity entity = proj.getOwner();
                if (proj.isOnFire() && proj.mayInteract(lvl, blockpos)) {
                    onCaughtFire(bs, lvl, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
                    lvl.removeBlock(blockpos, false);
                }
            }
        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public boolean dropFromExplosion(Explosion explosion1) {
        return false;
    }

    @Override
    public void onCaughtFire(BlockState state, Level world, BlockPos pos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
        explode(world, pos, igniter);
    }

    @SuppressWarnings("deprecated")
    @Override
    public void onPlace(BlockState bs, Level lvl, BlockPos bp, BlockState bs2, boolean p_57470_) {
        if (!bs2.is(bs.getBlock())) {
            if (lvl.hasNeighborSignal(bp)) {
                onCaughtFire(bs, lvl, bp, null, null);
                lvl.removeBlock(bp, false);
            }

        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public void neighborChanged(BlockState p_57457_, Level p_57458_, BlockPos p_57459_, Block p_57460_, BlockPos p_57461_, boolean p_57462_) {
        if (p_57458_.hasNeighborSignal(p_57459_)) {
            onCaughtFire(p_57457_, p_57458_, p_57459_, null, null);
            p_57458_.removeBlock(p_57459_, false);
        }

    }

    @Override
    public void playerWillDestroy(Level p_57445_, BlockPos p_57446_, BlockState p_57447_, Player p_57448_) {
        if (!p_57445_.isClientSide() && !p_57448_.isCreative() && p_57447_.getValue(UNSTABLE)) {
            onCaughtFire(p_57447_, p_57445_, p_57446_, null, null);
        }
        super.playerWillDestroy(p_57445_, p_57446_, p_57447_, p_57448_);
    }

    @Override
    public void wasExploded(Level lvl, BlockPos bp, Explosion exp1) {
        double x = bp.getX();
        double y = bp.getY();
        double z = bp.getZ();

        if (!lvl.isClientSide) {
            PrimedTnt primedtnt = new PrimedTnt(lvl, x + 0.5D, y, z + 0.5D, exp1.getIndirectSourceEntity());
            int i = primedtnt.getFuse();
            primedtnt.setFuse((short)(lvl.random.nextInt(i / 4) + i / 8));
            lvl.addFreshEntity(primedtnt);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56388_) {
        p_56388_.add(TYPE, WATERLOGGED, UNSTABLE);
    }
}
