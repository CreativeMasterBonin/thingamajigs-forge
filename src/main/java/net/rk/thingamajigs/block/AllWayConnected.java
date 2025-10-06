package net.rk.thingamajigs.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class AllWayConnected extends Block {
    public static final IntegerProperty TYPE = IntegerProperty.create("type",0,12);
    // north, south, east, west
    // north south, east west
    // north east, north west
    // south east, south west
    // north south east, north south west
    // north south east west
    public AllWayConnected(Properties p) {
        super(p);
    }
}
