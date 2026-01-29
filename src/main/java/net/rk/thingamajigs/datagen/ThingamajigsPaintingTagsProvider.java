package net.rk.thingamajigs.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.painting.ThingamajigsPaintings;
import net.rk.thingamajigs.tag.ThingamajigsTags;

import java.util.concurrent.CompletableFuture;

public class ThingamajigsPaintingTagsProvider extends TagsProvider<PaintingVariant> {
    public ThingamajigsPaintingTagsProvider(PackOutput po, CompletableFuture<HolderLookup.Provider> cf, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
        super(po, Registries.PAINTING_VARIANT, cf, Thingamajigs.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p){
        this.tag(ThingamajigsTags.THINGAMAJIGS_PAINTING)
                .add(ThingamajigsPaintings.ANOLE.getKey())
                .add(ThingamajigsPaintings.BLUE_PEOPLE.getKey())
                .add(ThingamajigsPaintings.BRICK_FIGURE.getKey())
                .add(ThingamajigsPaintings.COLORS.getKey())
                .add(ThingamajigsPaintings.FARM.getKey())
                .add(ThingamajigsPaintings.FINALE_WORLD.getKey())
                .add(ThingamajigsPaintings.FIRE_PILE.getKey())
                .add(ThingamajigsPaintings.FLAME_BOMBER.getKey())
                .add(ThingamajigsPaintings.HISTORY_CENTER.getKey())
                .add(ThingamajigsPaintings.ISLAND.getKey())
                .add(ThingamajigsPaintings.LEGACY.getKey())
                .add(ThingamajigsPaintings.LILIES.getKey())
                .add(ThingamajigsPaintings.MYSTERIOUS.getKey())
                .add(ThingamajigsPaintings.PORTABLE_LIGHT_SWITCH.getKey())
                .add(ThingamajigsPaintings.RAILROAD_CABINET.getKey())
                .add(ThingamajigsPaintings.SIGN_FIELD.getKey())
                .add(ThingamajigsPaintings.TEST_CARD_PARODY.getKey())
                .add(ThingamajigsPaintings.THE_PLAYERS.getKey())
                .add(ThingamajigsPaintings.TURTLE.getKey())
                .add(ThingamajigsPaintings.SEASONINGS_GREETINGS.getKey())
                .add(ThingamajigsPaintings.OPPOSE.getKey())
                .add(ThingamajigsPaintings.ICY_PHYSICS.getKey())
                .add(ThingamajigsPaintings.SPEEDY_CAT.getKey())
                .add(ThingamajigsPaintings.VULTURE.getKey())
                .add(ThingamajigsPaintings.THE_CHARS.getKey())
                .add(ThingamajigsPaintings.CROSSING.getKey())
                .add(ThingamajigsPaintings.LOVELY.getKey())
                .replace(false)
        ;
    }
}
