package net.rk.thingamajigs.fluid.fluidblocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.rk.thingamajigs.fluid.ThingamajigsFluids;

public class FryingOilBlock extends LiquidBlock {


    public FryingOilBlock() {
        super(ThingamajigsFluids.FRYING_OIL, BlockBehaviour.Properties.of()
                .liquid()
                .mapColor(MapColor.COLOR_YELLOW)
                .strength(100f)
                .replaceable()
                .noCollission()
                .noLootTable()
                .sound(SoundType.EMPTY));
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if(level.isClientSide()){

        }
        else{
            if(level.getGameTime() % 32 == 0){
                if(entity instanceof ItemEntity item){
                    if(item.getItem().is(Items.CHICKEN)){
                        item.setItem(new ItemStack(Items.COOKED_CHICKEN));
                    }
                    else if(item.getItem().is(Items.BEEF)){
                        item.setItem(new ItemStack(Items.COOKED_BEEF));
                    }
                    else if(item.getItem().is(Items.COD)){
                        item.setItem(new ItemStack(Items.COOKED_COD));
                    }
                    else if(item.getItem().is(Items.MUTTON)){
                        item.setItem(new ItemStack(Items.COOKED_MUTTON));
                    }
                    else if(item.getItem().is(Items.RABBIT)){
                        item.setItem(new ItemStack(Items.COOKED_RABBIT));
                    }
                    else if(item.getItem().is(Items.SALMON)){
                        item.setItem(new ItemStack(Items.COOKED_SALMON));
                    }
                    else if(item.getItem().is(Items.PORKCHOP)){
                        item.setItem(new ItemStack(Items.COOKED_PORKCHOP));
                    }
                }
                else{
                    entity.hurt(level.damageSources().inFire(),1.0f);
                }
            }
        }
    }
}
