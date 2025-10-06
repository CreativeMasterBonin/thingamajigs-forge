package net.rk.thingamajigs.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;

public class InfiMoveCart extends AbstractMinecart {
    private static final EntityDataAccessor<Boolean> FUELED =
            SynchedEntityData.defineId(InfiMoveCart.class, EntityDataSerializers.BOOLEAN);
    private int fuel;
    private final int maxFuel = 32767; // sure!
    public double xPush;
    public double zPush;
    public double lerpedYaw = 0.0f;

    protected InfiMoveCart(EntityType<?> et, Level lvl) {
        super(et, lvl);
        setDragAir(2.2D);
        setMaxSpeedAirLateral(0.8f);
        setMaxSpeedAirVertical(-1.2f);
    }

    protected InfiMoveCart(EntityType<?> et2, Level l2, double xo1, double yo1, double zo1) {
        super(et2, l2, xo1, yo1, zo1);
        setDragAir(2.2D);
        setMaxSpeedAirLateral(0.8f);
        setMaxSpeedAirVertical(-1.2f);
    }

    public InfiMoveCart(Level level1, double d0, double d1, double d2) {
        super(ThingamajigsEntities.INFIMOVE_MINECART.get(), level1, d0, d1, d2);
        setDragAir(2.2D);
        setMaxSpeedAirLateral(0.8f);
        setMaxSpeedAirVertical(-1.2f);
    }

    @Override
    protected Item getDropItem() {
        Item item;
        item = ThingamajigsItems.INFIMOVE_MINECART_ITEM.get();
        return item;
    }

    @Override
    public Type getMinecartType() {
        return Type.FURNACE; // just in case
    }

    @Override
    public void tick() {
        double oldYaw = yRotO;
        super.tick();

        // get the blocks we need that are around this entity
        BlockState bsAbove = this.level().getBlockState(this.blockPosition().above());
        BlockState bsBelow = this.level().getBlockState(this.blockPosition().below());
        BlockState rail = this.level().getBlockState(this.blockPosition());

        // control mechanisms (automation?)
        // red will stop the cart
        // green will start and push minecart (if slowing down)
        // if the rails are gone the minecart will cease to use fuel or have any push power
        if(bsAbove.is(Blocks.RED_CONCRETE) || bsAbove.is(Blocks.RED_WOOL)){
            this.fuel = 0;
        }
        else if (bsAbove.is(Blocks.LIME_CONCRETE) || bsAbove.is(Blocks.GREEN_CONCRETE) || bsAbove.is(Blocks.LIME_WOOL) || bsAbove.is(Blocks.GREEN_WOOL)) {
            this.fuel = maxFuel;
            setHasFuel(true);
            if(this.getDeltaMovement().x <= 0.0D){
                this.xPush = 1.0D;
            }
            if (this.getDeltaMovement().z <= 0.0D){
                this.zPush = 1.0D;
            }
        }
        else if (bsBelow.is(Blocks.LIME_CONCRETE) || bsBelow.is(Blocks.GREEN_CONCRETE) || bsBelow.is(Blocks.LIME_WOOL) || bsBelow.is(Blocks.GREEN_WOOL)) {
            this.fuel = maxFuel;
            setHasFuel(true);
            if(this.getDeltaMovement().x >= -0.00001D){
                this.xPush = 1.0D;
            }
            if (this.getDeltaMovement().z >= -0.00001D){
                this.zPush = 1.0D;
            }
        }
        else if(rail.is(Blocks.AIR) || !(rail.getBlock() instanceof BaseRailBlock)){

        }

        // not client side stuff
        if (!this.level().isClientSide()) {
            // max cap protection
            if (this.fuel > this.maxFuel) {
                this.fuel = this.maxFuel;
            }
            // stop the cart push power completely if there is no fuel (not very useful for anything BUT stopping)
            // cause' fuel DOESN'T matter here, it is just here for logic compatibility and ease of use
            if (this.fuel <= 0) {
                this.xPush = 0.0D;
                this.zPush = 0.0D;
            }

            this.setHasFuel(this.fuel > 0);

            if(getDeltaMovement().y > 0 || getDeltaMovement().y < 0){
                this.setDeltaMovement(getDeltaMovement().x,getDeltaMovement().y / 1.5D,getDeltaMovement().z);
            }
        }

        if((rail.getBlock() instanceof BaseRailBlock) && this.hasFuel()){
            if (this.tickCount % 4 == 0) {
                this.level().addParticle(ParticleTypes.END_ROD,
                        this.getX(), this.getY() + 0.2D, this.getZ(),
                        0.0D, 0.0D, 0.0D);
            }
            else if(this.tickCount % 200 == 0){
                this.level().playSound(null,blockPosition(),
                        SoundEvents.BLASTFURNACE_FIRE_CRACKLE,
                        SoundSource.NEUTRAL,1.0f,1.0f);
            }
        }

        lerpedYaw = Mth.lerp(tickCount,yRotO,oldYaw);
        yRotO = (float) lerpedYaw;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FUELED, false);
    }

    @Override
    public ItemStack getPickResult() {
        Item item;
        item = ThingamajigsItems.INFIMOVE_MINECART_ITEM.get();
        return new ItemStack(item);
    }

    @Override
    protected double getMaxSpeed() {
        return (this.isInWater() ? 4.0D : 8.0D) / 20.0D; // 0.15 or 0.2 come out of this
    }

    @Override
    protected void moveAlongTrack(BlockPos bp, BlockState bs) {
        double d0 = 1.0E-4D;
        double d1 = 0.001D;
        super.moveAlongTrack(bp, bs);
        Vec3 vec3 = this.getDeltaMovement();
        double d2 = vec3.horizontalDistanceSqr();
        double d3 = this.xPush * this.xPush + this.zPush * this.zPush;
        if (d3 > 1.0E-4D && d2 > 0.001D) {
            double d4 = Math.sqrt(d2);
            double d5 = Math.sqrt(d3);
            this.xPush = vec3.x / d4 * d5;
            this.zPush = vec3.z / d4 * d5;
        }
    }

    @Override
    protected void applyNaturalSlowdown() {
        double d0 = this.xPush * this.xPush + this.zPush * this.zPush;
        if (d0 > 1.0E-7D) {
            d0 = Math.sqrt(d0);
            this.xPush /= d0;
            this.zPush /= d0;
            Vec3 vec3 = this.getDeltaMovement().multiply(0.8D, 0.0D, 0.8D).add(this.xPush, 0.0D, this.zPush);
            if (this.isInWater()) {
                vec3 = vec3.scale(0.1D);
            }
            this.setDeltaMovement(vec3);
        } else {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.98D, 0.0D, 0.98D));
        }
        super.applyNaturalSlowdown();
    }

    // better ingredients, better furnace minecart, NOT SAYING THE LAST PART
    private static final Ingredient INGREDIENTS = Ingredient.of(
            Items.COAL_BLOCK,
            Items.COAL,
            Items.CHARCOAL,
            Items.STICK,
            Items.WOODEN_SWORD,
            Items.WOODEN_AXE,
            Items.WOODEN_PICKAXE,
            Items.WOODEN_SHOVEL,
            Items.WOODEN_HOE
    );

    @Override
    public InteractionResult interact(Player p, InteractionHand ih) {
        InteractionResult ret = super.interact(p, ih);
        if (ret.consumesAction()){
            return ret;
        }

        ItemStack itemstack = p.getItemInHand(ih);

        // add more fuel types so that this cart isn't stupid
        boolean flagIsLogsThatBurn = itemstack.is(ItemTags.LOGS_THAT_BURN);
        boolean flagIsSigns = itemstack.is(ItemTags.SIGNS); // almost ok, except nether signs... oh well
        boolean wd = itemstack.is(ItemTags.WOODEN_DOORS);
        boolean wb = itemstack.is(ItemTags.WOODEN_BUTTONS);
        boolean ws = itemstack.is(ItemTags.WOODEN_SLABS);
        boolean wstairs = itemstack.is(ItemTags.WOODEN_STAIRS);
        boolean wtrapdoors = itemstack.is(ItemTags.WOODEN_TRAPDOORS);
        boolean wp = itemstack.is(ItemTags.WOODEN_PRESSURE_PLATES);

        if ((INGREDIENTS.test(itemstack) || flagIsLogsThatBurn || flagIsSigns || wd || wb || ws || wstairs || wtrapdoors || wp) && this.fuel <= maxFuel) {
            if (!p.getAbilities().instabuild) {
                itemstack.shrink(1); // shrink it
                p.swing(ih);
            }
            this.fuel = this.maxFuel; // the 32bit limit, hehe, also FREE fuel! infinite fuel time.
        }
        if (this.fuel > 0) {
            this.xPush = this.getX() - p.getX();
            this.zPush = this.getZ() - p.getZ();
        }
        return InteractionResult.CONSUME;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag ctadd) {
        super.addAdditionalSaveData(ctadd);
        ctadd.putDouble("PushX", this.xPush);
        ctadd.putDouble("PushZ", this.zPush);
        ctadd.putShort("Fuel", (short)this.fuel);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag ctread) {
        super.readAdditionalSaveData(ctread);
        this.xPush = ctread.getDouble("PushX");
        this.zPush = ctread.getDouble("PushZ");
        this.fuel = ctread.getShort("Fuel");
    }

    protected boolean hasFuel() {
        return this.entityData.get(FUELED);
    }

    protected void setHasFuel(boolean p_38577_) {
        this.entityData.set(FUELED, p_38577_);
    }

    @Override
    public boolean canCollideWith(Entity ent) {
        return ent instanceof Player || ent instanceof Boat || ent instanceof AbstractMinecart;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected boolean canRide(Entity p_20339_) {
        return false;
    }

    @Override
    public BlockState getDefaultDisplayBlockState() {
        return ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().defaultBlockState().trySetValue(RedstoneLampBlock.LIT,true);
    }
}
