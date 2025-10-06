package net.rk.thingamajigs.legacy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.custom.blocks.WhiteRoadMarking;

public class ChangeTypeFirst {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack stack, int marking_type) {
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
                            SoundEvents.VILLAGER_NO, SoundSource.BLOCKS, 1F, 1.1F);
                    world.addParticle(ParticleTypes.CLOUD,pos.getX(),pos.getY(),pos.getZ(),0,1,0);
                    return;
                }
            }
            // new sound for paintbrushes
            world.playSound(null,new BlockPos((int)x,(int)y,(int)z), SoundEvents.CAKE_ADD_CANDLE, SoundSource.BLOCKS, 1F, 1F);
            world.setBlock(new BlockPos((int) x, (int) (y + 1), (int) z), ThingamajigsBlocks.WHITE_ROAD_MARKING.get().defaultBlockState(), 3);
            {
                Direction _dir = ((entity.getDirection()).getOpposite());
                BlockPos _pos = new BlockPos((int) x, (int) (y + 1), (int) z);
                BlockState _bs = world.getBlockState(_pos).setValue(WhiteRoadMarking.TYPE,marking_type);
                Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");

                // change block to face player
                if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                    world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
                } else {
                    _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                    if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                        world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);

                }

            }
        }
    }
}