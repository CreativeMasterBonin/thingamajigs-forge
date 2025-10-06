package net.rk.thingamajigs.xtrablock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.entity.ChairEntity;
import net.rk.thingamajigs.interfacing.WeatheringCopperChair;

import java.util.List;

import static net.rk.thingamajigs.xtrablock.ChairBlock.*;

public class WeatheringCopperChairBlock extends Block implements WeatheringCopperChair {
    private final WeatheringCopperChair.RustState ruststate;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public WeatheringCopperChairBlock(WeatheringCopperChair.RustState rs, Properties p) {
        super(p.mapColor(MapColor.COLOR_ORANGE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 6.0F)
                .sound(SoundType.COPPER));
        this.ruststate = rs;
    }

    public void randomTick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs1) {
        this.onRandomTick(bs, sl, bp, rs1);
    }

    public boolean isRandomlyTicking(BlockState bsr1) {
        return WeatheringCopperChair.getNext(bsr1.getBlock()).isPresent();
    }

    public WeatheringCopperChair.RustState getAge() {
        return this.ruststate;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState bs, BlockGetter bg, BlockPos bp, CollisionContext cc) {
        switch(bs.getValue(FACING)){
            case NORTH: return NORTH;
            case SOUTH: return SOUTH;
            case EAST: return EAST;
            case WEST: return WEST;
            default: return Shapes.block();
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public InteractionResult use(BlockState bs, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockState normal = ThingamajigsBlocks.COPPER_CHAIR.get().defaultBlockState();
        BlockState ok = ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get().defaultBlockState();
        BlockState bad = ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get().defaultBlockState();
        BlockState current = bs;
        boolean successful = false;

        if(player.getItemInHand(hand).is(ItemTags.AXES)){
            if(level.getBlockState(pos).is(ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get())){
                level.setBlock(pos,bad.setValue(FACING,current.getValue(FACING)),3);
                player.getItemInHand(hand).hurtAndBreak(1, player, (p_uh) -> {
                    p_uh.broadcastBreakEvent(hand);
                });
                successful = true;
            }
            else if(level.getBlockState(pos).is(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get())){
                level.setBlock(pos,ok.setValue(FACING,current.getValue(FACING)),3);
                player.getItemInHand(hand).hurtAndBreak(1, player, (p_uh) -> {
                    p_uh.broadcastBreakEvent(hand);
                });
                successful = true;
            }
            else if(level.getBlockState(pos).is(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get())){
                level.setBlock(pos,normal.setValue(FACING,current.getValue(FACING)),3);
                player.getItemInHand(hand).hurtAndBreak(1, player, (p_uh) -> {
                    p_uh.broadcastBreakEvent(hand);
                });
                successful = true;
            }
            if(successful){
                level.playSound(null,pos,SoundEvents.AXE_SCRAPE,SoundSource.PLAYERS,1.0F,1.0F);
                ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.SCRAPE, UniformInt.of(3, 5));
                player.swing(hand);
            }
            else{
                return spawnChairPerhaps(pos,level,bs,player);
            }
            return InteractionResult.CONSUME;
        }
        else if(player.getItemInHand(hand).is(Items.HONEYCOMB)){
            player.getItemInHand(hand).shrink(1);
            applyWaxItemToBlock(bs,level,pos); // apply the wax to the block, converting it to a waxed block variant
            // level renderer
            level.playLocalSound(pos,SoundEvents.HONEYCOMB_WAX_ON,SoundSource.PLAYERS,1.0F,1.0F,false);
            ParticleUtils.spawnParticlesOnBlockFaces(level, pos, ParticleTypes.WAX_ON, UniformInt.of(3, 5));
            // end
            player.swing(hand);
            return InteractionResult.CONSUME;
        }
        else{
            return spawnChairPerhaps(pos,level,bs,player);
        }
    }

    public InteractionResult spawnChairPerhaps(BlockPos pos, Level level, BlockState bs, Player player){
        try{
            double a = pos.getX() + 1.0D;
            double b = pos.getY() + 1.0D;
            double c = pos.getZ() + 1.0D;
            AABB aabb = new AABB(pos.getX(),pos.getY(),pos.getZ(),a,b,c);

            if(!level.isClientSide){

                List<ChairEntity> chairs = level.getEntitiesOfClass(ChairEntity.class, aabb);
                // we WANT a chair
                if(chairs.isEmpty()){
                    ChairEntity ce = ChairEntity.requestNewEntity(level,pos,bs);
                    level.addFreshEntity(ce);
                    player.startRiding(ce,false);
                }
            }

            playCustomSitSound(level,pos,player); // custom sitting sounds!
            return InteractionResult.SUCCESS;
        }
        catch(Exception e){
            return InteractionResult.FAIL;
        }
    }

    public void applyWaxItemToBlock(BlockState bs, Level lvl, BlockPos bp){
        BlockState w_normal = ThingamajigsBlocks.WAXED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_ok = ThingamajigsBlocks.WAXED_EXPOSED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_bad = ThingamajigsBlocks.WAXED_WEATHERED_COPPER_CHAIR.get().defaultBlockState();
        BlockState w_awful = ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get().defaultBlockState();

        if(bs.is(ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_awful.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_bad.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_ok.setValue(FACING,bs.getValue(FACING)),3);
        }
        else if(bs.is(ThingamajigsBlocks.COPPER_CHAIR.get())){
            lvl.setBlock(bp,w_normal.setValue(FACING,bs.getValue(FACING)),3);
        }
    }

    public void playCustomSitSound(Level l, BlockPos bp, Player p){
        SoundEvent event = SoundEvents.COPPER_HIT;
        l.playSound(p,bp, event, SoundSource.PLAYERS,1.0F,1.0F);
    }
}
