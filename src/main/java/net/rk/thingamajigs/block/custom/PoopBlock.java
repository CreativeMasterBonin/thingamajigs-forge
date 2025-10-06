package net.rk.thingamajigs.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.events.ThingamajigsSoundTypes;

public class PoopBlock extends Block {
    private static final VoxelShape CUSTOM_SHAPE = Block.box(1.5D, 0.0D, 1.5D, 13.5D, 13.5D, 13.5D);

    public PoopBlock(Properties p) {
        super(p.sound(ThingamajigsSoundTypes.POOP));
    }

    @Override
    public InteractionResult use(BlockState bs, Level l, BlockPos bp, Player ply, InteractionHand h, BlockHitResult bhr) {
        BlockState nbs = ThingamajigsBlocks.POOPOO.get().defaultBlockState();

        if(!l.isClientSide()){
            boolean handismain = h.equals(InteractionHand.MAIN_HAND);
            boolean shiftdown = ply.isShiftKeyDown();
            boolean requiredItem = ply.getMainHandItem().is(Items.MUSIC_DISC_CHIRP);

            if(requiredItem){
                l.setBlock(bp,nbs,3);
                l.playSound(null,bp.above(),SoundEvents.ILLUSIONER_CAST_SPELL, SoundSource.BLOCKS,1.0f,1.0f);
                ply.swing(InteractionHand.MAIN_HAND);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }

    @SuppressWarnings("deprecated")
    @Override
    public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_) {
        return CUSTOM_SHAPE;
    }

    @SuppressWarnings("deprecated")
    @Override
    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        BlockState cbb = levelReader.getBlockState(blockPos.below());
        return Block.isFaceFull(cbb.getCollisionShape(levelReader, blockPos.below()), Direction.UP);
    }
}
