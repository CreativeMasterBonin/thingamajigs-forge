package net.rk.thingamajigs.legacy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.misc.ThingamajigsCalcStuffs;

public class ClearLanternItemActions {
    public static void rightClickOnBlock(LevelAccessor world, double x, double y, double z, Direction direction, Entity entity, ItemStack itemstack){
        if(!world.isClientSide()){
            world.playSound(null,
                    new BlockPos((int)x,(int)y,(int)z),
                    SoundEvents.LANTERN_PLACE,SoundSource.BLOCKS,
                    1.0f, ThingamajigsCalcStuffs.nextFloatBetweenInclusive(0.95f,1.17f));
        }

        if(direction == null || entity == null){
            return;
        }
        if(entity instanceof LivingEntity _entity) _entity.swing(InteractionHand.MAIN_HAND, true);if (!(new Object(){
            public boolean checkGamemode(Entity _ent){
                if(_ent instanceof ServerPlayer _serverPlayer) {
                    return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
                } else if(_ent instanceof Player _player) {
                    return _player.isCreative();
                }
                return false;
            }
        }.checkGamemode(entity))) {(itemstack).shrink(1);}if (direction == Direction.UP) {if ((world.getBlockState(new BlockPos((int)x,(int)(y+1),(int)z))).getBlock() == Blocks.AIR) {world.setBlock(new BlockPos((int)x,(int)(y+1),(int)z), ThingamajigsBlocks.GROUND_CLEAR_LANTERN.get().defaultBlockState(),3);if(world instanceof Level _level) {
            if(!_level.isClientSide()) {

            } else {

            }
        }}}else if (direction == Direction.NORTH) {if ((world.getBlockState(new BlockPos((int)x,(int)y,(int)(z-1)))).getBlock() == Blocks.AIR) {world.setBlock(new BlockPos((int)x,(int)y,(int)(z-1)), ThingamajigsBlocks.WALL_CLEAR_LANTERN.get().defaultBlockState(), 3);{
            Direction _dir = Direction.NORTH;
            BlockPos _pos = new BlockPos((int)x,(int)y,(int)(z-1));
            BlockState _bs = world.getBlockState(_pos);
            Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
            if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
            } else {
                _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                    world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
            }
        }if(world instanceof Level _level) {
            if(!_level.isClientSide()) {

            } else {

            }
        }}}else if (direction == Direction.SOUTH) {if ((world.getBlockState(new BlockPos((int)x,(int)y,(int)(z+1)))).getBlock() == Blocks.AIR) {world.setBlock(new BlockPos((int)x,(int)y,(int)(z+1)), ThingamajigsBlocks.WALL_CLEAR_LANTERN.get().defaultBlockState(),3);{
            Direction _dir = Direction.SOUTH;
            BlockPos _pos = new BlockPos((int)x,(int)y,(int)(z+1));
            BlockState _bs = world.getBlockState(_pos);
            Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
            if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
            } else {
                _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                    world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
            }
        }if(world instanceof Level _level) {
            if(!_level.isClientSide()) {

            } else {

            }
        }}}else if (direction == Direction.WEST) {if ((world.getBlockState(new BlockPos((int)(x-1),(int)y,(int)z))).getBlock() == Blocks.AIR) {world.setBlock(new BlockPos((int)(x-1),(int)y,(int)z), ThingamajigsBlocks.WALL_CLEAR_LANTERN.get().defaultBlockState(),3);{
            Direction _dir = Direction.WEST;
            BlockPos _pos = new BlockPos((int)(x-1),(int)y,(int)z);
            BlockState _bs = world.getBlockState(_pos);
            Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
            if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
            } else {
                _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                    world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
            }
        }if(world instanceof Level _level) {
            if(!_level.isClientSide()) {

            } else {

            }
        }}}else if (direction == Direction.EAST) {if ((world.getBlockState(new BlockPos((int)(x+1),(int)y,(int)z))).getBlock() == Blocks.AIR) {world.setBlock(new BlockPos((int)(x+1),(int)y,(int)z), ThingamajigsBlocks.WALL_CLEAR_LANTERN.get().defaultBlockState(),3);{
            Direction _dir = Direction.EAST;
            BlockPos _pos = new BlockPos((int)(x+1),(int)y,(int)z);
            BlockState _bs = world.getBlockState(_pos);
            Property<?> _property = _bs.getBlock().getStateDefinition().getProperty("facing");
            if (_property instanceof DirectionProperty _dp && _dp.getPossibleValues().contains(_dir)) {
                world.setBlock(_pos, _bs.setValue(_dp, _dir), 3);
            } else {
                _property = _bs.getBlock().getStateDefinition().getProperty("axis");
                if (_property instanceof EnumProperty _ap && _ap.getPossibleValues().contains(_dir.getAxis()))
                    world.setBlock(_pos, _bs.setValue(_ap, _dir.getAxis()), 3);
            }
        }if(world instanceof Level _level) {
            if(!_level.isClientSide()) {

            } else {

            }
        }}}
    }
}
