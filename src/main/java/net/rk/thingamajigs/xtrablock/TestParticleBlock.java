package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.particle.ThingamajigsParticles;

public class TestParticleBlock extends Block {
    public TestParticleBlock(Properties p) {
        super(p.strength(1f,50f).noOcclusion().noCollission().noParticlesOnBreak());
    }

    @Override
    public void animateTick(BlockState bs, Level lvl, BlockPos bp, RandomSource rs) {
        super.animateTick(bs, lvl, bp, rs);
        double x = bp.getX();
        double y = (double)bp.getY() + 1.0D;
        double z = bp.getZ();

        if(rs.nextInt(5) == 0){
            for(int i = 0; i < rs.nextInt(1) + 1; ++i) {
                lvl.addParticle(ThingamajigsParticles.CHIMNEY_SMOKE.get(), x + 0.5D, y + 0.5D, z + 0.5D, (double)(rs.nextFloat() / 2.0F), 5.0E-5D, (double)(rs.nextFloat() / 2.0F));
            }
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel slvl, BlockPos bp, RandomSource rs) {
        super.tick(bs, slvl, bp, rs);
        slvl.removeBlock(bp,false);
    }

    @Override
    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        super.randomTick(bs, sl, bp, rs);
        tick(bs,sl,bp,rs);
    }

    @Override
    public InteractionResult use(BlockState bs, Level lvl, BlockPos bp, Player ply, InteractionHand ih, BlockHitResult bhr) {
        if(!lvl.isClientSide){
            int i = ply.getItemInHand(ih).getCount();
            if(i < 64){
                ply.getItemInHand(ih).grow(i + 1);
                if(!ply.getItemInHand(ih).isEnchanted()){
                    ply.getItemInHand(ih).enchant(Enchantments.MENDING,0);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }
}
