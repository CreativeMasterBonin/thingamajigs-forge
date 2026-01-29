package net.rk.thingamajigs.item.custom;


import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.*;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.legacy.ClearBulbItemActions;


public class ClearBulb extends Item{

    private final Block pStandingBlock = ThingamajigsBlocks.GROUND_CLEAR_BULB.get();
    private final Block pWallBlock = ThingamajigsBlocks.WALL_CLEAR_BULB.get();

    public ClearBulb(Properties pProperties) {
        super(pProperties);
    }

    @Override public InteractionResult useOn(UseOnContext context) {
        InteractionResult retval = super.useOn(context);
        ClearBulbItemActions.rightClickOnBlock(
                context.getLevel(),context.getClickedPos().getX(),context.getClickedPos().getY(),context.getClickedPos().getZ(),context.getClickedFace(),context.getPlayer(),context.getItemInHand()
        );
        return retval;
    }
}
