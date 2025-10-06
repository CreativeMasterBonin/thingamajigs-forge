package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GameMasterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.ticks.TickPriority;

import java.util.List;

public class WallBarrierBlock extends Block implements GameMasterBlock {
    public List<BlockPos> LOCKED_POSITIONS;

    public WallBarrierBlock(Properties p) {
        super(p.mapColor(MapColor.COLOR_RED).strength(-1f,3600000f).noLootTable());
    }

    @Override
    public void onPlace(BlockState bs, Level level, BlockPos bp, BlockState bs2, boolean bool1) {
        LOCKED_POSITIONS = BlockPos.betweenClosedStream(
                -1, level.getMinBuildHeight(), -1, 1, level.getMaxBuildHeight(), 1)
                .map(BlockPos::immutable).toList();
        level.scheduleTick(bp,this,10, TickPriority.EXTREMELY_LOW);
    }

    @Override
    public boolean isValidSpawn(BlockState state, BlockGetter level, BlockPos pos, SpawnPlacements.Type type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        if(!(LOCKED_POSITIONS == null)){
            if(!LOCKED_POSITIONS.isEmpty() && lvl.getGameTime() % 100 == 0){
                int rid = rs.nextInt(LOCKED_POSITIONS.size());
                lvl.addParticle(ParticleTypes.END_ROD,
                        LOCKED_POSITIONS.get(rid).getX(), LOCKED_POSITIONS.get(rid).getY() + 1.0D,LOCKED_POSITIONS.get(rid).getZ(),
                        0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        if(LOCKED_POSITIONS == null){
            slvl.scheduleTick(bp,this,10, TickPriority.EXTREMELY_LOW);
            return;
        }
        for(int o = 0; o < LOCKED_POSITIONS.size(); o++){
            Player player = slvl.getNearestPlayer(TargetingConditions.forNonCombat(),(double)LOCKED_POSITIONS.get(o).getX(),(double)LOCKED_POSITIONS.get(o).getY(),(double)LOCKED_POSITIONS.get(o).getZ());
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            if(player.canUseGameMasterBlocks()){
                break;
            }

            if(player.getBlockX() == LOCKED_POSITIONS.get(o).getX() && player.getBlockY() == LOCKED_POSITIONS.get(o).getY() && player.getBlockZ() == LOCKED_POSITIONS.get(o).getZ()){
                player.setPos(LOCKED_POSITIONS.get(o).getX(),LOCKED_POSITIONS.get(o).getY(),LOCKED_POSITIONS.get(o).getZ());
                player.setTicksFrozen(20);
                slvl.addParticle(ParticleTypes.ITEM_SNOWBALL,
                        LOCKED_POSITIONS.get(o).getX(), LOCKED_POSITIONS.get(o).getY() + 1.0D,LOCKED_POSITIONS.get(o).getZ(),
                        0.0D, 0.0D, 0.0D);
            }
        }
        slvl.scheduleTick(bp,this,10, TickPriority.EXTREMELY_LOW);
    }
}
