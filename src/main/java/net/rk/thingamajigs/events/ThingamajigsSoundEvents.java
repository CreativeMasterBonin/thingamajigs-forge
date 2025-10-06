package net.rk.thingamajigs.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;

public class ThingamajigsSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Thingamajigs.MOD_ID);


    public static final RegistryObject<SoundEvent> STATIC = registerSoundEvent("static");
    public static final RegistryObject<SoundEvent> HORN = registerSoundEvent("horn");
    public static final RegistryObject<SoundEvent> CODE = registerSoundEvent("code");
    public static final RegistryObject<SoundEvent> BEEP = registerSoundEvent("beep");
    public static final RegistryObject<SoundEvent> ELECTRONIC = registerSoundEvent("electronic");
    public static final RegistryObject<SoundEvent> METALLIC = registerSoundEvent("metallic");
    public static final RegistryObject<SoundEvent> OLD = registerSoundEvent("old");
    public static final RegistryObject<SoundEvent> PLUCK = registerSoundEvent("pluck");
    public static final RegistryObject<SoundEvent> POOP = registerSoundEvent("poop");
    public static final RegistryObject<SoundEvent> AIR = registerSoundEvent("air");
    public static final RegistryObject<SoundEvent> WATER_NOISE = registerSoundEvent("water_noise");
    public static final RegistryObject<SoundEvent> POOP_BREAK = registerSoundEvent("poop_break");
    public static final RegistryObject<SoundEvent> POOP_HIT = registerSoundEvent("poop_hit");
    public static final RegistryObject<SoundEvent> POOP_STEP = registerSoundEvent("poop_step");
    public static final RegistryObject<SoundEvent> MECH_BELL_ONE = registerSoundEvent("mech_bell_one");
    public static final RegistryObject<SoundEvent> MECH_BELL_TWO = registerSoundEvent("mech_bell_two");

    public static final RegistryObject<SoundEvent> EBELL_ONE = registerSoundEvent("ebell_one");
    public static final RegistryObject<SoundEvent> EBELL_TWO = registerSoundEvent("ebell_two");

    public static final RegistryObject<SoundEvent> PLASTIC_SHUFFLING = registerSoundEvent("plastic_shuffling");


    // phone sounds
    public static final RegistryObject<SoundEvent> MOBILE_BEEP = registerSoundEvent("mobile_beep");
    // tones
    public static final RegistryObject<SoundEvent> MOBILE_ZER0 = registerSoundEvent("mobile_zero");
    public static final RegistryObject<SoundEvent> MOBILE_ONE = registerSoundEvent("mobile_one");
    public static final RegistryObject<SoundEvent> MOBILE_TWO = registerSoundEvent("mobile_two");
    public static final RegistryObject<SoundEvent> MOBILE_THREE = registerSoundEvent("mobile_three");
    public static final RegistryObject<SoundEvent> MOBILE_FOUR = registerSoundEvent("mobile_four");
    public static final RegistryObject<SoundEvent> MOBILE_FIVE = registerSoundEvent("mobile_five");
    public static final RegistryObject<SoundEvent> MOBILE_SIX = registerSoundEvent("mobile_six");
    public static final RegistryObject<SoundEvent> MOBILE_SEVEN = registerSoundEvent("mobile_seven");
    public static final RegistryObject<SoundEvent> MOBILE_EIGHT = registerSoundEvent("mobile_eight");
    public static final RegistryObject<SoundEvent> MOBILE_NINE = registerSoundEvent("mobile_nine");
    public static final RegistryObject<SoundEvent> MOBILE_POUND = registerSoundEvent("mobile_pound");
    public static final RegistryObject<SoundEvent> MOBILE_STAR = registerSoundEvent("mobile_star");

    public static final RegistryObject<SoundEvent> POP = registerSoundEvent("pop");

    public static final RegistryObject<SoundEvent> YODELER = registerSoundEvent("yodeler");

    // phone calls
    public static final RegistryObject<SoundEvent> PHONE_BIRDS_PERHAPS = registerSoundEvent("birds_perhaps");
    public static final RegistryObject<SoundEvent> PHONE_NO_SERVICE = registerSoundEvent("no_service");
    public static final RegistryObject<SoundEvent> PHONE_NOT_A_NUMBER = registerSoundEvent("not_a_number");
    public static final RegistryObject<SoundEvent> PHONE_SONG_MAYBE = registerSoundEvent("song_maybe");

    // instruments for blocks
    public static final RegistryObject<SoundEvent> KICK_DRUM = registerSoundEvent("kick");
    public static final RegistryObject<SoundEvent> SNARE_DRUM = registerSoundEvent("snare");
    public static final RegistryObject<SoundEvent> TOM_DRUM = registerSoundEvent("tom");
    public static final RegistryObject<SoundEvent> FLOOR_TOM_DRUM = registerSoundEvent("floor_tom");
    public static final RegistryObject<SoundEvent> HIHAT = registerSoundEvent("hihat");
    public static final RegistryObject<SoundEvent> CRASH_CYMBAL = registerSoundEvent("crash");
    public static final RegistryObject<SoundEvent> CONGA = registerSoundEvent("conga");
    public static final RegistryObject<SoundEvent> BONGO = registerSoundEvent("bongo");

    // instrument hit noises
    public static final RegistryObject<SoundEvent> METALLIC_HIT = registerSoundEvent("metallic_hit");
    public static final RegistryObject<SoundEvent> NATURAL_HIT = registerSoundEvent("natural_hit");
    public static final RegistryObject<SoundEvent> PAPER_HIT = registerSoundEvent("paper_hit");
    public static final RegistryObject<SoundEvent> RATTLE_HIT = registerSoundEvent("rattle_hit");

    // custom instrument sounds
    public static final RegistryObject<SoundEvent> LARGE_POOP = registerSoundEvent("large_poop");

    // variable range sound effects
    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name,
                () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Thingamajigs.MOD_ID,name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }

}
