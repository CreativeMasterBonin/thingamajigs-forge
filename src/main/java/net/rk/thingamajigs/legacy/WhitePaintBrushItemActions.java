package net.rk.thingamajigs.legacy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.bases.AbstractPaintbrush;

public class WhitePaintBrushItemActions {
    public static void paint(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack stack, int marking_type,int length) {
        double variable = 0;
        BlockPos pos = new BlockPos((int)x,(int)y,(int)z);

        if(stack.hasTag()){
            variable = marking_type;
        }
        if (entity == null)
            return;
        if (entity.isShiftKeyDown()) {
            return;
        }
        if(!world.getBlockState(pos).isSolid()){
            return;
        }
        else {
            if(world.getBlockState(pos.above()).isSolid()){
                world.playSound(null,new BlockPos((int)x,(int)y,(int)z),
                        SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1F, 1.1F);
                world.addParticle(ParticleTypes.CLOUD,pos.getX(),pos.getY(),pos.getZ(),0,1,0);
                return;
            }
            else{
                boolean needstone = world.getBlockState(pos.above()).is(BlockTags.NEEDS_STONE_TOOL);
                boolean neediron = world.getBlockState(pos.above()).is(BlockTags.NEEDS_IRON_TOOL);
                boolean needdiamond = world.getBlockState(pos.above()).is(BlockTags.NEEDS_DIAMOND_TOOL);

                if(!needstone && !neediron && !needdiamond){
                    world.destroyBlock(pos.above(),true);
                }
                else{
                    world.playSound(null,new BlockPos((int)x,(int)y,(int)z),
                            SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1f, 1.1f);
                    world.addParticle(ParticleTypes.CLOUD,pos.getX(),pos.getY(),pos.getZ(),0,1,0);
                    return;
                }
            }

            AbstractPaintbrush.newPaintLogic(world,x,y,z,entity,stack,marking_type,length,ThingamajigsBlocks.WHITE_ROAD_MARKING.get());
        }
    }
}