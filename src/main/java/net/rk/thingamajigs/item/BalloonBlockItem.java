package net.rk.thingamajigs.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;

public class BalloonBlockItem extends BlockItem {
    public BalloonBlockItem(Properties p) {
        super(ThingamajigsBlocks.BALLOON_BLOCK.get(),p.setNoRepair().stacksTo(64));
    }

    public BalloonBlockItem(Block block, Properties p) {
        super(block,p.setNoRepair().stacksTo(64));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level l, Player p, InteractionHand ih) {
        boolean swordItem = p.getMainHandItem().getItem() instanceof SwordItem;

        boolean offhanded = p.getOffhandItem().is(this);

        float rndPitchF = l.random.nextFloat();

        if(p.getAirSupply() < p.getMaxAirSupply()){
            if(swordItem && offhanded){
                this.pop(l,p,ih);
                l.playSound(null,new BlockPos(p.getBlockX(),p.getBlockY(),p.getBlockZ()),
                        ThingamajigsSoundEvents.POP.get(), SoundSource.BLOCKS,1.0f,rndPitchF + 0.8f);
                // from campfire
                for(int i = 0; i < l.random.nextInt(1) + 1; ++i) {
                    l.addParticle(ParticleTypes.POOF,
                            (double)p.getX() + 0.5D, (double)p.getY() + 0.5D, (double)p.getZ() + 0.5D,
                            (double)(l.random.nextFloat() / 2.0F), 5.0E-5D, (double)(l.random.nextFloat() / 2.0F));
                }
                l.playLocalSound(new BlockPos(p.getBlockX(),p.getBlockY(),p.getBlockZ()),
                        SoundEvents.PLAYER_BREATH,SoundSource.PLAYERS,1.0f,rndPitchF + 0.95f,false);
            }
            else if(p.getMainHandItem().is(Items.CACTUS)){
                p.getOffhandItem().shrink(1);
                p.setAirSupply(p.getMaxAirSupply());
                l.gameEvent(p, GameEvent.ITEM_INTERACT_FINISH,new Vec3(p.getBlockX(),p.getBlockY(),p.getBlockZ()));
                l.playSound(null,new BlockPos(p.getBlockX(),p.getBlockY(),p.getBlockZ()),
                        ThingamajigsSoundEvents.POP.get(), SoundSource.BLOCKS,1.0f,rndPitchF + 0.8f);
                // from campfire
                for(int i = 0; i < l.random.nextInt(1) + 1; ++i) {
                    l.addParticle(ParticleTypes.POOF,
                            (double)p.getX() + 0.5D, (double)p.getY() + 0.5D, (double)p.getZ() + 0.5D,
                            (double)(l.random.nextFloat() / 2.0F), 5.0E-5D, (double)(l.random.nextFloat() / 2.0F));
                }
            }
        }
        return super.use(l,p,ih);
    }

    private void pop(Level l, Player p, InteractionHand ih){
        p.getOffhandItem().shrink(1);
        p.getMainHandItem().hurtAndBreak(1,p,(h) ->{
            h.broadcastBreakEvent(ih);
        });
        p.setAirSupply(p.getMaxAirSupply());
        l.gameEvent(p, GameEvent.ITEM_INTERACT_FINISH,new Vec3(p.getBlockX(),p.getBlockY(),p.getBlockZ()));
    }
}
