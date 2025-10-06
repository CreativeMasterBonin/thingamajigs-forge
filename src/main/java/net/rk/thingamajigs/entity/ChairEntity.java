package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
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
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.xtrablock.ChairBlock;

import java.util.List;

public class ChairEntity extends Entity {
    public ChairEntity(Level lvl) {
        super(ThingamajigsEntities.CHAIR.get(), lvl);
        this.noPhysics = true;
    }

    private ChairEntity(Level level, BlockPos pos, Direction dir) {
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
        // experimental offsets for certain things
        List<Entity> passengers = this.getPassengers();
        Entity ent0 = passengers.get(0);

        // secret poses for entities

        if(ent0 instanceof LivingEntity){
            if(ent0 instanceof Player){
                ItemStack itsk = new ItemStack(((Player) ent0).getMainHandItem().getItem());
                if(itsk.is(ThingamajigsItems.WATER_SOURCE.get())) {
                    return 2.0D;
                }
                else{
                    if(this.level().getBlockState(this.blockPosition()).is(ThingamajigsBlocks.BARBER_CHAIR.get())){
                        return -0.015D;
                    }
                }
            }
        }
        return 0.0D;
    }

    @Override
    protected boolean canRide(Entity e) {
        return true;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this); // servers MUST have packets sent to and from it (this is for clients)
    }

    // we want other things to call this instead of some more complicated methods
    // this allows the entity to spawned on ANY block we choose
    public static ChairEntity requestNewEntity(Level lvl, BlockPos pos, BlockState bs){
        return new ChairEntity(lvl,pos,bs.getValue(ChairBlock.FACING));
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

        // do this for the four directions in the array (this will place the entity riding at a valid good block location next to this entity)
        for(Direction dir : dirArr){
            Vec3 vector3all = DismountHelper.findSafeDismountLocation(le.getType(), this.level(), this.blockPosition().relative(dir),false);
            if(vector3all != null){
                return vector3all.add(0,0.25,0);
            }
        }
        return super.getDismountLocationForPassenger(le);
    }

    @Override
    public void onPassengerTurned(Entity e) {
        // do nothing, "i like it simple..."
    }

    // it will look as though the entity sitting is being teleported, which is normal (cause' i ain't doing no fancy stuff)
    @Override
    protected void addPassenger(Entity e) {
        super.addPassenger(e);
        e.setYRot(this.getYRot());
    }
}
