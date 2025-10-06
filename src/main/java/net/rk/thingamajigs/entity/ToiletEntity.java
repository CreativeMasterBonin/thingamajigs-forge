package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.custom.blocks.Toilet;
import net.rk.thingamajigs.events.ThingamajigsSoundEvents;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.xtrablock.ChairBlock;

import java.util.List;

public class ToiletEntity extends Entity {
    public ToiletEntity(Level lvl) {
        super(ThingamajigsEntities.TOILET_ENTITY.get(), lvl);
        this.noPhysics = true;
    }

    private ToiletEntity(Level level, BlockPos pos, Direction dir) {
        this(level);
        double x = pos.getX() + 0.5;
        double y = pos.getY() + 0.5;
        double z = pos.getZ() + 0.5;
        this.setPos(x, y, z);
        this.setRot(dir.getOpposite().toYRot(), 0F);
    }

    @Override
    public void tick() {
        super.tick(); // do the base entity tick stuff
        if(!this.level().isClientSide){
            // get rid of this entity if when ticked this entity has no rider or block in its position
            if(this.getPassengers().isEmpty() || this.level().isEmptyBlock(this.blockPosition())){
                this.remove(RemovalReason.DISCARDED); // tell me WHY this was removed
                this.level().updateNeighbourForOutputSignal(blockPosition(),this.level().getBlockState(blockPosition()).getBlock());
            }
        }
    }

    // we will not bother with these methods being full of stuff
    @Override
    protected void checkInsideBlocks() {}
    @Override
    protected void defineSynchedData() {}
    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {}
    @Override
    protected void addAdditionalSaveData(CompoundTag cadd) {}

    @Override
    public double getPassengersRidingOffset() {
        return -0.1;
    }

    @Override
    protected boolean canRide(Entity e) {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this); // servers MUST have packets sent to and from it (this is for clients)
    }

    // never mind, this is self-explanatory
    public static ToiletEntity requestNewEntity(Level lvl, BlockPos pos, BlockState bs){
        return new ToiletEntity(lvl,pos,bs.getValue(Toilet.FACING));
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity le) {
        Direction dir1 = this.getDirection();
        // the four directions of the block
        // "...north, south, east and west, a book, can be your passport..."
        Direction secDir = dir1.getClockWise();
        Direction thiDir = dir1.getCounterClockWise();
        Direction finDir = dir1.getOpposite();

        Direction[] dirArr = {
                dir1, secDir, thiDir, finDir
        }; // an easy way to group all directions for input later

        for(Direction dir : dirArr){
            Vec3 vec1 = DismountHelper.findSafeDismountLocation(le.getType(), this.level(),
                    this.blockPosition().relative(dir),false);
            if(vec1 != null){
                return vec1.add(0,0.25,0);
            }
        }
        return super.getDismountLocationForPassenger(le);
    }

    @Override
    public void onPassengerTurned(Entity e) {
        // do nothing we are on a toilet
    }

    @Override
    protected void addPassenger(Entity e) {
        super.addPassenger(e);
        e.setYRot(this.getYRot()); // save the entity! don't fall!
    }
}
