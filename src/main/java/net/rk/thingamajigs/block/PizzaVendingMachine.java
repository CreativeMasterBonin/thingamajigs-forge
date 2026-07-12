package net.rk.thingamajigs.block;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.item.ThingamajigsItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PizzaVendingMachine extends DirectionalConnectedSideBlock{
    public PizzaVendingMachine(Properties p) {
        super(p.sound(SoundType.LANTERN).mapColor(MapColor.COLOR_RED).noOcclusion().strength(1f,5f)
                .instrument(NoteBlockInstrument.DIDGERIDOO));
        this.registerDefaultState(this.defaultBlockState()
                .setValue(FACING, Direction.NORTH)
                .setValue(CONNECTED_SIDE, ConnectedSide.UNCONNECTED)
                .setValue(WATERLOGGED,false)
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter getter, List<Component> comp, TooltipFlag flag) {
        comp.add(Component.translatable("generic.thingamajigs.vending_machine.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return DoubleTallDecorationBlock.BLOCK_SHAPE;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(level.isClientSide()){
            if(player.getItemInHand(hand).is(Items.EMERALD) || player.getItemInHand(hand).is(ThingamajigsItems.MONEY.get()) || player.getItemInHand(hand).is(ThingamajigsItems.COIN.get())){
                player.playSound(SoundEvents.SMOKER_SMOKE,0.5f,1.0f);
                return InteractionResult.SUCCESS;
            }
        }
        else{
            if(player.getItemInHand(hand).is(Items.EMERALD) || player.getItemInHand(hand).is(ThingamajigsItems.MONEY.get()) || player.getItemInHand(hand).is(ThingamajigsItems.COIN.get())){
                player.getItemInHand(hand).shrink(1);
                ItemEntity item = new ItemEntity(level,
                        pos.getX(),pos.getY(),pos.getZ(),
                        new ItemStack(ThingamajigsBlocks.PIZZA.get()),
                        0D,0D,0D);
                level.addFreshEntity(item);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(!level.isClientSide()){
            if(state.getValue(FACING) == Direction.NORTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.SOUTH){
                // both east and west
                if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.east()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.west()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.LEFT),3);
                }// neither east nor west
                else {
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.EAST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.RIGHT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.LEFT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.UNCONNECTED),3);
                }
            }
            else if(state.getValue(FACING) == Direction.WEST){
                // both east and west
                if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.CENTER),3);
                }// not east but west
                else if(!(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine) && level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.LEFT),3);
                }// east but not west
                else if(level.getBlockState(pos.north()).getBlock() instanceof PizzaVendingMachine && !(level.getBlockState(pos.south()).getBlock() instanceof PizzaVendingMachine)){
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.RIGHT),3);
                }// neither east nor west
                else{
                    level.setBlock(pos,state.setValue(CONNECTED_SIDE,ConnectedSide.UNCONNECTED),3);
                }
            }
        }
    }
}
