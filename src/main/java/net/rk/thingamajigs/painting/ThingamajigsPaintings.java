package net.rk.thingamajigs.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;

public class ThingamajigsPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_MOTIFS = DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, Thingamajigs.MOD_ID);

    // a farm picture that was taken a picture of... we don't know who originally made the picture in the photo (or what happened to it), but it has been pixelated and put in minecraft
    public static final RegistryObject<PaintingVariant> FARM = PAINTING_MOTIFS.register("farm", () -> new PaintingVariant(16,16));
    // a pile on fire... what else do you want?
    public static final RegistryObject<PaintingVariant> FIRE_PILE = PAINTING_MOTIFS.register("fire_pile", () -> new PaintingVariant(16,16));
    // lilies that were growing in a now non-existent pond, and place in the world
    public static final RegistryObject<PaintingVariant> LILIES = PAINTING_MOTIFS.register("lilies", () -> new PaintingVariant(16,16));
    // a portable light switch picture. what else is there to say?
    public static final RegistryObject<PaintingVariant> PORTABLE_LIGHT_SWITCH = PAINTING_MOTIFS.register("portable_light_switch", () -> new PaintingVariant(16,16));
    // Railroad Cabinet. Chugga Chugga, Chugga Chugga, Choo Choo!
    public static final RegistryObject<PaintingVariant> RAILROAD_CABINET = PAINTING_MOTIFS.register("railroad_cabinet", () -> new PaintingVariant(16,16));
    // hey want an anole in the game? no? too bad! it will look at you for all eternity... well, ok, maybe not, but at least for what time is left in the world...
    public static final RegistryObject<PaintingVariant> ANOLE = PAINTING_MOTIFS.register("anole", () -> new PaintingVariant(64,64));
    // turtle picture, there are turtles in the game, but here is one turtle that exists in real life.
    public static final RegistryObject<PaintingVariant> TURTLE = PAINTING_MOTIFS.register("turtle", () -> new PaintingVariant(64,64));
    // this parody is technically a parody of a parody, to be more in line with minecraft entities ('looks'-wise)
    public static final RegistryObject<PaintingVariant> TEST_CARD_PARODY = PAINTING_MOTIFS.register("test_card_parody", () -> new PaintingVariant(32,32));
    // the legacy mod's 1.0.0 update image is shown here in this painting
    public static final RegistryObject<PaintingVariant> LEGACY = PAINTING_MOTIFS.register("legacy", () -> new PaintingVariant(32,32));
    // Colors from a bg that was created by the author of this mod!
    public static final RegistryObject<PaintingVariant> COLORS = PAINTING_MOTIFS.register("colors", () -> new PaintingVariant(16,32));
    // A mysterious realm that was home to a mysterious person...
    public static final RegistryObject<PaintingVariant> MYSTERIOUS = PAINTING_MOTIFS.register("mysterious", () -> new PaintingVariant(64,48));
    // One of a certain doctor's inventions
    public static final RegistryObject<PaintingVariant> FLAME_BOMBER = PAINTING_MOTIFS.register("flame_bomber", () -> new PaintingVariant(32,32));
    // A drawing of a dream's place of choice... a history center that does not actually exist!
    public static final RegistryObject<PaintingVariant> HISTORY_CENTER = PAINTING_MOTIFS.register("history_center", () -> new PaintingVariant(64,64));
    // A requested painting by blueman
    public static final RegistryObject<PaintingVariant> THE_PLAYERS = PAINTING_MOTIFS.register("the_players", () -> new PaintingVariant(64,32));
    // A 2nd requested painting by blueman
    public static final RegistryObject<PaintingVariant> BRICK_FIGURE = PAINTING_MOTIFS.register("brick_figure", () -> new PaintingVariant(64,64));
    // A painting depicting a final version of a world from a certain story...
    public static final RegistryObject<PaintingVariant> FINALE_WORLD = PAINTING_MOTIFS.register("finale_world", () -> new PaintingVariant(64,48));
    // Not a joke! However, this sign is on a field...
    public static final RegistryObject<PaintingVariant> SIGN_FIELD = PAINTING_MOTIFS.register("sign_field", () -> new PaintingVariant(64,64));
    // An island of some area...
    public static final RegistryObject<PaintingVariant> ISLAND = PAINTING_MOTIFS.register("island", () -> new PaintingVariant(64,64));
    // Blue People? Hmm... where have they been seen before?
    public static final RegistryObject<PaintingVariant> BLUE_PEOPLE = PAINTING_MOTIFS.register("blue_people", () -> new PaintingVariant(64,32));
    // "Seasonings Greetings!" - TC
    public static final RegistryObject<PaintingVariant> SEASONINGS_GREETINGS =
            PAINTING_MOTIFS.register("seasonings_greetings",
                    () -> new PaintingVariant(64,64));
    // Oppose The Threat Of The Other Twin
    public static final RegistryObject<PaintingVariant> OPPOSE =
            PAINTING_MOTIFS.register("oppose",
                    () -> new PaintingVariant(64,64));
    // Ice Physics Editor
    public static final RegistryObject<PaintingVariant> ICY_PHYSICS =
            PAINTING_MOTIFS.register("icy_physics",
                    () -> new PaintingVariant(32,16));
    // speedy cat
    public static final RegistryObject<PaintingVariant> SPEEDY_CAT =
            PAINTING_MOTIFS.register("speedy_cat",
                    () -> new PaintingVariant(64,32));
    // a red-eyed vulture stares at you
    public static final RegistryObject<PaintingVariant> VULTURE =
            PAINTING_MOTIFS.register("vulture",
                    () -> new PaintingVariant(32,16));
    // the characters
    public static final RegistryObject<PaintingVariant> THE_CHARS =
            PAINTING_MOTIFS.register("the_chars",
                    () -> new PaintingVariant(32,16));
    // a generic gate-less railroad crossing
    public static final RegistryObject<PaintingVariant> CROSSING =
            PAINTING_MOTIFS.register("crossing",
            () -> new PaintingVariant(16,32));
    // a very lovely world
    public static final RegistryObject<PaintingVariant> LOVELY =
            PAINTING_MOTIFS.register("lovely",
                    () -> new PaintingVariant(32,16));
    public static final RegistryObject<PaintingVariant> MANSION =
            PAINTING_MOTIFS.register("mansion",
                    () -> new PaintingVariant(80,64));


    public static void register(IEventBus eventBus){
        PAINTING_MOTIFS.register(eventBus);
    }
}
