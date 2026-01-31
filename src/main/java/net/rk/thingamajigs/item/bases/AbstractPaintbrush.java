package net.rk.thingamajigs.item.bases;

import net.minecraft.world.item.Item;

public abstract class AbstractPaintbrush extends Item {
    public String currentName = "Undefined";
    public int currentLength = 1;

    public AbstractPaintbrush(Properties p) {
        super(p);
    }
}
