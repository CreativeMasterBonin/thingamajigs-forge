package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.LibraryStool;

public class StoolBlock extends ChairBlock{
    public StoolBlock(Properties p) {
        super(p);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        return LibraryStool.ALL;
    }

    public void playCustomSitSound(Level level, BlockPos pos, Player player){
        SoundEvent se = SoundEvents.WOOL_PLACE;
        level.playSound(player,pos,se, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
