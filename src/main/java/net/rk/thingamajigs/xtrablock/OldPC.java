package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;

import java.util.Calendar;
import java.util.Date;

public class OldPC extends ThingamajigsDecorativeBlock {
    public static final VoxelShape NORTHSOUTH = Block.box(3, 0, 0, 13, 16, 16);
    public static final VoxelShape EASTWEST = Block.box(0, 0, 3, 16, 16, 13);

    public OldPC(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        switch(state.getValue(FACING)){
            case NORTH,SOUTH -> {return NORTHSOUTH;}
            case EAST,WEST -> {return EASTWEST;}
            default -> {return Shapes.block();}
        }
    }

    public boolean getDateJoke(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH) == Calendar.APRIL && calendar.get(Calendar.DAY_OF_MONTH) == 1;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult blockHitResult) {
        ItemStack itemstack = player.getItemInHand(hand);
        boolean changed = false;

        // MUST BEE SERVER-SIDE ONLY to work! at least for blocks
        if (!level.isClientSide) {
            if(player.getAbilities().mayBuild){
                if(itemstack.getItem() == Items.DANGER_POTTERY_SHERD && itemstack.getDisplayName().getString().contains("Old PC")){
                    player.displayClientMessage(Component.translatable("block.thingamajigs.old_pc.use"), true);
                    changed = true;
                    if(getDateJoke()){
                        level.playSound(null,blockPos,SoundEvents.VEX_AMBIENT,SoundSource.BLOCKS,1.0f,0.5f);
                        level.setBlock(blockPos,Blocks.AIR.defaultBlockState(),2);
                        level.updateNeighborsAt(blockPos,this);
                        PrimedTnt primedtnt = new PrimedTnt(level,blockPos.getX() + 0.5D,blockPos.getY(),blockPos.getZ() + 0.5D,player);
                        int i = primedtnt.getFuse();
                        primedtnt.setFuse((short)(level.random.nextInt(i / 4) + i / 8));
                        level.addFreshEntity(primedtnt);
                        player.displayClientMessage(Component.translatable("block.thingamajigs.old_pc.gotem"), true);
                    }
                }
                else{
                    if(player.getItemInHand(hand) == ItemStack.EMPTY){
                        return InteractionResult.PASS; // we don't want to swing the arm or interact with this block
                    }
                }
                // update THIS block, no matter what happens
                if(changed){
                    level.playSound(null, blockPos, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }
}
