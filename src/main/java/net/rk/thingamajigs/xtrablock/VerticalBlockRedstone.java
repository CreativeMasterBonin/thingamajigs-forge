package net.rk.thingamajigs.xtrablock;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.ticks.TickPriority;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.tag.ThingamajigsTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VerticalBlockRedstone extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    public VerticalBlockRedstone(Properties p) {
        super(p.strength(1F,5F));
        this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter bg, List<Component> list, TooltipFlag ttf) {
        list.add(Component.translatable("block.thingamajigs.vertical_pole_redstone.desc").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public boolean canConnectRedstone(BlockState state, BlockGetter level, BlockPos pos, @Nullable Direction direction) {
        return true;
    }

    @SuppressWarnings("deprecated")
    @Override
    public void neighborChanged(BlockState bs, Level lvl, BlockPos bp, Block blk, BlockPos bp2, boolean p_55671_) {
        if (!lvl.isClientSide) {
            boolean allverticalredstoneblocks = lvl.getBlockState(bp.below()).is(ThingamajigsTags.VERTICAL_REDSTONE_BLOCKS);
            boolean allrrbells = lvl.getBlockState(bp.below()).is(ThingamajigsTags.RAILROAD_CROSSING_BELLS);

            boolean allrrbellsabove = lvl.getBlockState(bp.above()).is(ThingamajigsTags.RAILROAD_CROSSING_BELLS);

            if(allrrbellsabove){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(lvl.getBlockState(bp.below()).getValue(POWERED) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                }
                return;
            }

            // some hardcoded-ness is actually needed for SPECIFIC annoying cases (may use cantilever tag later)
            boolean cant = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER.get());
            boolean cant2 = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER_END.get());
            boolean cant3 = lvl.getBlockState(bp.above()).is(ThingamajigsBlocks.RR_CANTILEVER_LIGHTS.get());

            if(cant || cant2 || cant3){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(!lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else if(lvl.getBlockState(bp.below()).is(ThingamajigsBlocks.CROSSWALK_BUTTON.get())){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED)){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else{
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                }
                return;
            }

            if(!allrrbells){
                if(allverticalredstoneblocks){
                    if(lvl.getBlockState(bp.below()).getValue(POWERED) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                    }
                    else if(lvl.getBlockState(bp.below()).getValue(POWERED) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                    }
                }
                else{
                    if(lvl.hasNeighborSignal(bp) == true){
                        lvl.setBlock(bp,bs.setValue(POWERED,true),3);
                        lvl.scheduleTick(bp.above(),this,3,TickPriority.LOW);
                    }
                    else if(lvl.hasNeighborSignal(bp) == false){
                        lvl.setBlock(bp,bs.setValue(POWERED,false),3);
                        lvl.scheduleTick(bp.above(),this,3,TickPriority.LOW);
                    }
                }
            }
        }
    }

    @SuppressWarnings("deprecated")
    @Override
    public void tick(BlockState bs, ServerLevel sl, BlockPos bp, RandomSource rs) {
        sl.updateNeighborsAt(bp.above(),this);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(POWERED, false);
    }
}
