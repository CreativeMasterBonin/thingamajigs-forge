package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;

public class DyedPumpkinBlock extends PumpkinBlock {
    public static Block stemBlock = Blocks.PUMPKIN_STEM;
    public static Block attachedStemBlock = Blocks.ATTACHED_PUMPKIN_STEM;
    public static Block carvedPumpkinBlock = Blocks.CARVED_PUMPKIN;
    public static Item pumpkinSeeds = Items.PUMPKIN_SEEDS;

    public DyedPumpkinBlock(Properties p) {
        super(p.sound(SoundType.WOOD).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).pushReaction(PushReaction.DESTROY));
    }

    public DyedPumpkinBlock(Properties p, Block stem, Block attachedStem, Block carvedPumpkin, Item seeds) {
        super(p.sound(SoundType.WOOD).instrument(NoteBlockInstrument.DIDGERIDOO).strength(1.0F).pushReaction(PushReaction.DESTROY));
        stemBlock = stem;
        attachedStemBlock = attachedStem;
        carvedPumpkinBlock = carvedPumpkin;
        pumpkinSeeds = seeds;
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand ih, BlockHitResult bhr) {
        ItemStack itemstack = ply.getItemInHand(ih);

        if (itemstack.canPerformAction(net.minecraftforge.common.ToolActions.SHEARS_CARVE)) {
            if (!lvl.isClientSide) {
                Direction direction = bhr.getDirection();
                Direction direction1 = direction.getAxis() == Direction.Axis.Y ? ply.getDirection().getOpposite() : direction;
                double x = bp.getX();
                double y = bp.getY();
                double z = bp.getZ();
                RandomSource rnd = lvl.random;
                double rndd = rnd.nextDouble();

                lvl.playSound((Player)null, bp, SoundEvents.PUMPKIN_CARVE, SoundSource.BLOCKS, 1.0F, 1.0F);

                lvl.setBlock(bp, carvedPumpkinBlock.defaultBlockState()
                        .setValue(DyedCarvedPumpkinBlock.FACING, direction1), 11);

                ItemEntity itementity = new ItemEntity(lvl,
                        x + 0.5D + (double)direction1.getStepX() * 0.65D,
                        y + 0.1D, z + 0.5D + (double)direction1.getStepZ() * 0.65D,
                        new ItemStack(pumpkinSeeds, 4));

                itementity.setDeltaMovement(0.05D * (double)direction1.getStepX() + rndd * 0.02D,
                        0.05D, 0.05D * (double)direction1.getStepZ() + rndd * 0.02D);

                lvl.addFreshEntity(itementity);

                itemstack.hurtAndBreak(1, ply, (p_55287_) -> {
                    p_55287_.broadcastBreakEvent(ih);
                });
                lvl.gameEvent(ply, GameEvent.SHEAR, bp);
                ply.awardStat(Stats.ITEM_USED.get(Items.SHEARS));
            }

            return InteractionResult.sidedSuccess(lvl.isClientSide);
        } else {
            return super.use(bs, lvl, bp, ply, ih, bhr);
        }
    }

    @Override
    public StemBlock getStem() {
        return (StemBlock)stemBlock;
    }

    @Override
    public AttachedStemBlock getAttachedStem() {
        return (AttachedStemBlock)attachedStemBlock;
    }
}
