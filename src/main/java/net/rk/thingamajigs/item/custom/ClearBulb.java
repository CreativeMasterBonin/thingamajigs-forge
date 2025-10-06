package net.rk.thingamajigs.item.custom;


import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.legacy.BtlanternitemRightClickedOnBlockProcedure;


public class ClearBulb extends Item{

    private final Block pStandingBlock = ThingamajigsBlocks.GROUND_CLEAR_BULB.get();
    private final Block pWallBlock = ThingamajigsBlocks.WALL_CLEAR_BULB.get();

    public ClearBulb(Properties pProperties) {
        super(pProperties);
    }

    @Override public InteractionResult useOn(UseOnContext context) {
        InteractionResult retval = super.useOn(context);
        BtlanternitemRightClickedOnBlockProcedure.execute(
                context.getLevel(),context.getClickedPos().getX(),context.getClickedPos().getY(),context.getClickedPos().getZ(),context.getClickedFace(),context.getPlayer(),context.getItemInHand()
        );
        return retval;
    }
}
