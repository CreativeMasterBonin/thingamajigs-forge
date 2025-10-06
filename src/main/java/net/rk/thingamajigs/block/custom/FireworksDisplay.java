package net.rk.thingamajigs.block.custom;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class FireworksDisplay extends Block {
    public FireworksDisplay(Properties properties) {
        super(properties.strength(1.5F,15F).noCollission());
    }

    @Override
    public void appendHoverText(ItemStack itemstack, BlockGetter world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("tooltip.thingamajigs.fireworks_display"));
    }

    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean b1) {
        boolean powered_flag = lvl.hasNeighborSignal(bp);
        if (powered_flag) {
            lvl.scheduleTick(bp,this,5, TickPriority.LOW);
        }
    }

    @Override
    public void tick(BlockState bs, ServerLevel serverLevel, BlockPos bp, RandomSource rnds) {
        fireRandomRocketNewer(serverLevel,rnds,null,bp);
    }

    // making our OWN firework rocket with randomness
    private ItemStack getFirework(DyeColor dyeColor, int num2) {
        ItemStack itemstack = new ItemStack(Items.FIREWORK_ROCKET, 1);
        ItemStack itemstack1 = new ItemStack(Items.FIREWORK_STAR);
        CompoundTag compoundtag = itemstack1.getOrCreateTagElement("Explosion");
        List<Integer> list = Lists.newArrayList();
        list.add(dyeColor.getFireworkColor());
        compoundtag.putIntArray("Colors", list);
        compoundtag.putByte("Type", (byte) FireworkRocketItem.Shape.BURST.getId());
        CompoundTag compoundtag1 = itemstack.getOrCreateTagElement("Fireworks");
        ListTag listtag = new ListTag();
        CompoundTag compoundtag2 = itemstack1.getTagElement("Explosion");
        if (compoundtag2 != null) {
            listtag.add(compoundtag2);
        }

        num2 = 3; // override to flight time of 3
        compoundtag1.putByte("Flight", (byte)num2);
        if (!listtag.isEmpty()) {
            compoundtag1.put("Explosions", listtag);
        }

        return itemstack;
    }

    public void fireRandomRocketNewer(ServerLevel level1, RandomSource rnd1, LivingEntity entity, BlockPos pos){
        DyeColor dyecolor = Util.getRandom(DyeColor.values(), rnd1);
        int num = rnd1.nextInt(3);
        ItemStack itemstack = this.getFirework(dyecolor, num);
        FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level1.getLevel(), entity, pos.getX(), pos.getY(), pos.getZ(), itemstack);
        level1.getLevel().addFreshEntity(fireworkrocketentity);
    }

    @Override
    public InteractionResult use(BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        // new method for random rockets
        if(world instanceof ServerLevel serverLevelS){
            fireRandomRocketNewer((ServerLevel)world,world.getRandom(),entity,pos);
        }
        return InteractionResult.SUCCESS;
    }
}
