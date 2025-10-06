package net.rk.thingamajigs.events;

import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.common.util.ForgeSoundType;


public class ThingamajigsSoundTypes {
    // Order: vol in, pitch in, break, step, place, hit, fall
    public static final ForgeSoundType POOP = new ForgeSoundType(1.0F,1.0F,
            ThingamajigsSoundEvents.POOP_BREAK,ThingamajigsSoundEvents.POOP_STEP,
            ThingamajigsSoundEvents.POOP,ThingamajigsSoundEvents.POOP_HIT,ThingamajigsSoundEvents.POOP);

    public static final ForgeSoundType CALENDAR = new ForgeSoundType(1.0f,1.0f,
            () -> SoundEvents.ITEM_FRAME_BREAK,() -> SoundEvents.BAMBOO_WOOD_STEP,
            () -> SoundEvents.ITEM_FRAME_PLACE,() -> SoundEvents.BAMBOO_WOOD_HIT,() -> SoundEvents.ITEM_FRAME_BREAK);

    public static final ForgeSoundType METALLIC_INSTRUMENT = new ForgeSoundType(1.0f,1.0f,
            () -> SoundEvents.METAL_BREAK,() -> SoundEvents.METAL_STEP,
            () -> SoundEvents.METAL_PLACE,ThingamajigsSoundEvents.METALLIC_HIT,ThingamajigsSoundEvents.METALLIC_HIT);

    public static final ForgeSoundType NATURAL_INSTRUMENT = new ForgeSoundType(1.0f,1.0f,
            () -> SoundEvents.PAINTING_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PAINTING_PLACE,ThingamajigsSoundEvents.NATURAL_HIT,ThingamajigsSoundEvents.NATURAL_HIT);

    public static final ForgeSoundType PAPER_INSTRUMENT = new ForgeSoundType(1.0f,1.0f,
            () -> SoundEvents.PAINTING_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PAINTING_PLACE,ThingamajigsSoundEvents.PAPER_HIT,ThingamajigsSoundEvents.PAPER_HIT);

    public static final ForgeSoundType RATTLE_INSTRUMENT = new ForgeSoundType(1.0f,1.0f,
            () -> SoundEvents.BAMBOO_WOOD_HANGING_SIGN_BREAK,() -> SoundEvents.BOAT_PADDLE_LAND,
            () -> SoundEvents.PINK_PETALS_PLACE,ThingamajigsSoundEvents.RATTLE_HIT,ThingamajigsSoundEvents.RATTLE_HIT);
}
