package net.rk.thingamajigs.datagen;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.tag.ThingamajigsTags;

import java.util.function.Consumer;

public class ThingamajigsRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ThingamajigsRecipeProvider(PackOutput out) {
        super(out);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> frc) {
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.GRAY_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_gray_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.GRAY_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.BLACK_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_black_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.BLACK_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.BROWN_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.BROWN_DYE)
                .unlockedBy("has_brown_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.BROWN_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.RED_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.RED_DYE)
                .unlockedBy("has_red_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.RED_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.YELLOW_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.YELLOW_DYE)
                .unlockedBy("has_yellow_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.YELLOW_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.LIME_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.LIME_DYE)
                .unlockedBy("has_lime_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.LIME_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.GREEN_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.GREEN_DYE)
                .unlockedBy("has_green_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.GREEN_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.CYAN_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.CYAN_DYE)
                .unlockedBy("has_cyan_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.CYAN_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.LIGHT_BLUE_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.LIGHT_BLUE_DYE)
                .unlockedBy("has_light_blue_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.LIGHT_BLUE_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.BLUE_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.BLUE_DYE)
                .unlockedBy("has_blue_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.BLUE_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.PURPLE_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.PURPLE_DYE)
                .unlockedBy("has_purple_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.PURPLE_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.MAGENTA_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.MAGENTA_DYE)
                .unlockedBy("has_magenta_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.MAGENTA_DYE).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.MISC, ThingamajigsItems.PINK_PUMPKIN_SEEDS.get(), 1)
                .requires(Items.PUMPKIN_SEEDS)
                .requires(Items.PINK_DYE)
                .unlockedBy("has_pink_dye",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.PINK_DYE).build()))
                .save(frc);

        // jol recipes
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.WHITE_JOL.get(), 1)
                .requires(ThingamajigsBlocks.WHITE_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.WHITE_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.LIGHT_GRAY_JOL.get(), 1)
                .requires(ThingamajigsBlocks.LIGHT_GRAY_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.LIGHT_GRAY_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.GRAY_JOL.get(), 1)
                .requires(ThingamajigsBlocks.GRAY_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.GRAY_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.BLACK_JOL.get(), 1)
                .requires(ThingamajigsBlocks.BLACK_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.BLACK_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.BROWN_JOL.get(), 1)
                .requires(ThingamajigsBlocks.BROWN_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.BROWN_CARVED_PUMPKIN.get()).build()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.RED_JOL.get(), 1)
                .requires(ThingamajigsBlocks.RED_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RED_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.YELLOW_JOL.get(), 1)
                .requires(ThingamajigsBlocks.YELLOW_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.YELLOW_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.LIME_JOL.get(), 1)
                .requires(ThingamajigsBlocks.LIME_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.LIME_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.GREEN_JOL.get(), 1)
                .requires(ThingamajigsBlocks.GREEN_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.GREEN_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.CYAN_JOL.get(), 1)
                .requires(ThingamajigsBlocks.CYAN_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.CYAN_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.LIGHT_BLUE_JOL.get(), 1)
                .requires(ThingamajigsBlocks.LIGHT_BLUE_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.LIGHT_BLUE_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.BLUE_JOL.get(), 1)
                .requires(ThingamajigsBlocks.BLUE_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.BLUE_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.PURPLE_JOL.get(), 1)
                .requires(ThingamajigsBlocks.PURPLE_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.PURPLE_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.MAGENTA_JOL.get(), 1)
                .requires(ThingamajigsBlocks.MAGENTA_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.MAGENTA_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.PINK_JOL.get(), 1)
                .requires(ThingamajigsBlocks.PINK_CARVED_PUMPKIN.get())
                .requires(Items.TORCH)
                .unlockedBy("has_carved_pumpkin",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.PINK_CARVED_PUMPKIN.get()).build()))
                .save(frc);
        balloonItemBuilder(ThingamajigsItems.BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.WHITE_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build()))
                .save(frc);
        balloonItemBuilder(ThingamajigsItems.LIGHT_GRAY_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.LIGHT_GRAY_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.GRAY_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.GRAY_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.BLACK_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.BLACK_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.BROWN_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.BROWN_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.RED_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.RED_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.ORANGE_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.ORANGE_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.YELLOW_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.YELLOW_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.LIME_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.LIME_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.GREEN_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.GREEN_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.CYAN_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.CYAN_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.LIGHT_BLUE_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.LIGHT_BLUE_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.BLUE_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.BLUE_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.PURPLE_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.PURPLE_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.MAGENTA_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.MAGENTA_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        balloonItemBuilder(ThingamajigsItems.PINK_BALLOON_BLOCK_ITEM.get(),Ingredient.of(Items.PINK_DYE),1)
                .unlockedBy("has_rubber",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.RUBBER.get()).build())).save(frc);
        //
        stonecutterType(Ingredient.of(Items.ACACIA_PLANKS.asItem()),ThingamajigsBlocks.ACACIA_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.SPRUCE_PLANKS.asItem()),ThingamajigsBlocks.SPRUCE_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.BIRCH_PLANKS.asItem()),ThingamajigsBlocks.BIRCH_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.DARK_OAK_PLANKS.asItem()),ThingamajigsBlocks.DARK_OAK_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.WARPED_PLANKS.asItem()),ThingamajigsBlocks.WARPED_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.CRIMSON_PLANKS.asItem()),ThingamajigsBlocks.CRIMSON_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.CHERRY_PLANKS.asItem()),ThingamajigsBlocks.CHERRY_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.JUNGLE_PLANKS.asItem()),ThingamajigsBlocks.JUNGLE_LANE.get().asItem(),1).save(frc);
        stonecutterType(Ingredient.of(Items.MANGROVE_PLANKS.asItem()),ThingamajigsBlocks.MANGROVE_LANE.get().asItem(),1).save(frc);

        stonecutterTypeDarkenedStone(Ingredient.of(ThingamajigsBlocks.DARKENED_STONE.get().asItem()),ThingamajigsBlocks.DARKENED_STONE_BRICKS.get().asItem(),1).save(frc);
        stonecutterTypeDarkenedStone(Ingredient.of(ThingamajigsBlocks.DARKENED_STONE.get().asItem()),ThingamajigsBlocks.PANEL_DARKENED_STONE_BRICKS.get().asItem(),1).save(frc);
        stonecutterTypeDarkenedStone(Ingredient.of(ThingamajigsBlocks.DARKENED_STONE.get().asItem()),ThingamajigsBlocks.CHISELED_PANEL_DARKENED_STONE_BRICKS.get().asItem(),1).save(frc);

        stonecutterTypeStripeFactory(Ingredient.of(
                ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.RED_CAUTION.get().asItem(),1).save(frc);
        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.ORANGE_CAUTION.get().asItem(),1).save(frc);
        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.YELLOW_CAUTION.get().asItem(),1).save(frc);
        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.GREEN_CAUTION.get().asItem(),1).save(frc);
        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.LIGHT_BLUE_CAUTION.get().asItem(),1).save(frc);
        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsItems.FACTORY_COMPONENT.get()),
                ThingamajigsBlocks.ALT_ORANGE_CAUTION.get().asItem(),1).save(frc);

        stonecutterTypeBricks(Ingredient.of(
                        Blocks.BRICKS),
                ThingamajigsBlocks.FRENCH_BRICK.get().asItem(),1).save(frc);
        stonecutterTypeBricks(Ingredient.of(
                        Blocks.BRICKS),
                ThingamajigsBlocks.ALT_FRENCH_BRICK.get().asItem(),1).save(frc);

        stonecutterTypeStripeFactory(Ingredient.of(
                        ThingamajigsBlocks.YELLOW_CAUTION.get().asItem()),
                ThingamajigsBlocks.SCROLLING_YELLOW_CAUTION.get().asItem(),1).save(frc);

        // vertical redstone full blocks
        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.REDSTONE, ThingamajigsBlocks.VERTICAL_REDSTONE_SIDEWALK.get(), 1)
                .requires(ThingamajigsBlocks.VERTICAL_POLE_REDSTONE.get())
                .requires(ThingamajigsBlocks.SIDEWALK.get())
                .unlockedBy("has_sidewalk",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.SIDEWALK.get()).build())).save(frc);

        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.NO_LEFT_TURN.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.NO_RIGHT_TURN.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.NO_STRAIGHT.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.NO_U_TURN.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.DO_NOT_ENTER.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.HAZARDOUS_MATERIALS.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.NO_HAZARDOUS_MATERIALS.get().asItem(),1)
                .save(frc);

        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.ONLY_LEFT.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.ONLY_RIGHT.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.ONLY_UP.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.HOV_ONLY.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.SQUARE_SIGN_GLOB.get(),
                Ingredient.of(ThingamajigsItems.SQUARE_SIGN_GLOB.get().asItem()),
                ThingamajigsBlocks.HOV_ENDS.get().asItem(),1)
                .save(frc);

        stonecutterAny(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get(),
                Ingredient.of(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get().asItem()),
                ThingamajigsBlocks.MALE_BATHROOM_SIGN.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get(),
                Ingredient.of(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get().asItem()),
                ThingamajigsBlocks.FEMALE_BATHROOM_SIGN.get().asItem(),1)
                .save(frc);
        stonecutterAny(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get(),
                Ingredient.of(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get().asItem()),
                ThingamajigsBlocks.BOTH_BATHROOM_SIGN.get().asItem(),1)
                .save(frc);

        slabBuilder(RecipeCategory.BUILDING_BLOCKS,
                ThingamajigsBlocks.RUBBER_WOOD_SLAB.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        stairBuilder(ThingamajigsBlocks.RUBBER_WOOD_STAIRS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        doorBuilder(ThingamajigsBlocks.RUBBER_WOOD_DOOR.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        trapdoorBuilder(ThingamajigsBlocks.RUBBER_WOOD_TRAPDOOR.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        buttonBuilder(ThingamajigsBlocks.RUBBER_WOOD_BUTTON.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        pressurePlateBuilder(RecipeCategory.REDSTONE,ThingamajigsBlocks.RUBBER_WOOD_PRESSURE_PLATE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        fenceBuilder(ThingamajigsBlocks.RUBBER_WOOD_FENCE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        fenceGateBuilder(ThingamajigsBlocks.RUBBER_WOOD_FENCE_GATE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()))
                .unlockedBy("has_rubber_planks",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsBlocks.RUBBER_PLANKS.get()).build())).save(frc);

        stonecutterTypeBricks(Ingredient.of(ThingamajigsBlocks.METAL_VENTS.get().asItem()),
                ThingamajigsBlocks.OUTLET_BLOCK.get().asItem(),1).save(frc);

        stonecutterAnyWithTag(
                ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS,
                Ingredient.of(ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS),
                ThingamajigsBlocks.WHITE_PARKING_ASPHALT.get().asItem(),
                1).save(frc);
        stonecutterAnyWithTag(
                ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS,
                Ingredient.of(ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS),
                ThingamajigsBlocks.YELLOW_PARKING_ASPHALT.get().asItem(),
                1).save(frc);
        stonecutterAnyWithTag(
                ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS,
                Ingredient.of(ThingamajigsTags.ASPHALT_CRAFTING_INGREDIENTS),
                ThingamajigsBlocks.BLUE_PARKING_ASPHALT.get().asItem(),
                1).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.COMMERCIAL_LIQUID_DISPENSER.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Blocks.DISPENSER)
                .requires(Items.GLASS_BOTTLE)
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Blocks.DISPENSER).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.COMMERCIAL_CONDIMENT_DISPENSER.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Blocks.DISPENSER)
                .requires(Items.RED_DYE)
                .requires(Items.YELLOW_DYE)
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Blocks.DISPENSER).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.COMMERCIAL_JUICE_DISPENSER.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Blocks.DISPENSER)
                .requires(Items.ORANGE_DYE)
                .requires(Items.LIME_DYE)
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Blocks.DISPENSER).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.COMMERCIAL_UTENCIL_DISPENSER.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Blocks.DISPENSER)
                .requires(ThingamajigsBlocks.EATING_UTENCILS.get().asItem())
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Blocks.DISPENSER).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.TRASH_CAN.get(), 1)
                .requires(ThingamajigsItems.HOME_COMPONENT.get())
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsBlocks.SCREEN.get().asItem())
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsItems.HOME_COMPONENT.get()).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.CHANGE_MACHINE.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Items.EMERALD)
                .requires(Items.DISPENSER)
                .requires(ThingamajigsItems.COMPUTER_COMPONENT.get())
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(Items.EMERALD).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(
                        RecipeCategory.DECORATIONS, ThingamajigsBlocks.PARTICULAR_STATUE.get(), 1)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.ARMOR_STAND)
                .requires(Items.GOLD_INGOT)
                .requires(Items.QUARTZ)
                .unlockedBy("has_thing",inventoryTrigger(
                        ItemPredicate.Builder.item().of(ThingamajigsItems.MISC_COMPONENT.get()).build())).save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ThingamajigsBlocks.VIDEO_CAMERA.get(), 1)
                .requires(ThingamajigsItems.COMPUTER_COMPONENT.get())
                .requires(Items.SPYGLASS)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsBlocks.CIRCUITS.get())
                .group("video_cameras")
                .unlockedBy("has_thing", InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.COMPUTER_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ThingamajigsBlocks.PROFESSIONAL_TV_CAMERA.get(), 1)
                .requires(ThingamajigsBlocks.VIDEO_CAMERA.get())
                .requires(Items.GLASS_PANE)
                .requires(Items.COBBLED_DEEPSLATE_WALL)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .group("video_cameras")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.VIDEO_CAMERA.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ThingamajigsBlocks.STUDIO_CAMERA.get(), 1)
                .requires(ThingamajigsBlocks.PROFESSIONAL_TV_CAMERA.get())
                .requires(Items.STONE_SLAB)
                .requires(Items.BLACK_CONCRETE)
                .group("video_cameras")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.PROFESSIONAL_TV_CAMERA.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ThingamajigsBlocks.COMMERCIAL_DRYER.get(), 1)
                .requires(ThingamajigsBlocks.DRYER.get())
                .requires(Items.BLUE_STAINED_GLASS_PANE)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .group("commercial_laundromat")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.DRYER.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ThingamajigsBlocks.COMMERCIAL_WASHER.get(), 1)
                .requires(ThingamajigsBlocks.WASHER.get())
                .requires(Items.BLUE_STAINED_GLASS_PANE)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .group("commercial_laundromat")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.WASHER.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.GOBO_LIGHT.get(), 1)
                .requires(Items.SEA_LANTERN)
                .requires(ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .requires(Items.NOTE_BLOCK)
                .group("dj_lights")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.NOTE_BLOCK))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.TURNTABLE.get(), 1)
                .requires(ItemTags.MUSIC_DISCS)
                .requires(ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .requires(Items.JUKEBOX)
                .group("dj_equipment")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.JUKEBOX))
                .save(frc);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC,ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get(),1)
                .define('g',Items.GLASS)
                .define('i',Items.GLOW_ITEM_FRAME)
                .define('m',ThingamajigsItems.MISC_COMPONENT.get())
                .define('e',Items.IRON_INGOT)
                .pattern("ggg")
                .pattern("gig")
                .pattern("eme")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);


        glowBlockBuilder(ThingamajigsBlocks.GLOW_BLOCK.get(),Ingredient.of(Items.WHITE_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.LIGHT_GRAY_GLOW_BLOCK.get(),Ingredient.of(Items.LIGHT_GRAY_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.GRAY_GLOW_BLOCK.get(),Ingredient.of(Items.GRAY_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.BLACK_GLOW_BLOCK.get(),Ingredient.of(Items.BLACK_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.BROWN_GLOW_BLOCK.get(),Ingredient.of(Items.BROWN_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.RED_GLOW_BLOCK.get(),Ingredient.of(Items.RED_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.ORANGE_GLOW_BLOCK.get(),Ingredient.of(Items.ORANGE_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.YELLOW_GLOW_BLOCK.get(),Ingredient.of(Items.YELLOW_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.LIME_GLOW_BLOCK.get(),Ingredient.of(Items.LIME_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.GREEN_GLOW_BLOCK.get(),Ingredient.of(Items.GREEN_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.CYAN_GLOW_BLOCK.get(),Ingredient.of(Items.CYAN_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.LIGHT_BLUE_GLOW_BLOCK.get(),Ingredient.of(Items.LIGHT_BLUE_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.BLUE_GLOW_BLOCK.get(),Ingredient.of(Items.BLUE_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.PURPLE_GLOW_BLOCK.get(),Ingredient.of(Items.PURPLE_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.MAGENTA_GLOW_BLOCK.get(),Ingredient.of(Items.MAGENTA_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);
        glowBlockBuilder(ThingamajigsBlocks.PINK_GLOW_BLOCK.get(),Ingredient.of(Items.PINK_STAINED_GLASS),8)
                .unlockedBy("has_clear_bulb_item",
                        inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.CLEAR_BULB_ITEM.get()).build()))
                .save(frc);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ThingamajigsBlocks.GRAY_SCREEN.get(),4)
                .define('t',ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .define('g',Items.GRAY_CONCRETE)
                .define('a',Items.STONE_SLAB)
                .pattern("aga")
                .pattern("gtg")
                .pattern("aga")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.TECHNOLOGY_COMPONENT.get()))
                .save(frc);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ThingamajigsBlocks.BLUE_SCREEN.get(),4)
                .define('t',ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .define('g',Items.BLUE_CONCRETE)
                .define('a',Items.STONE_SLAB)
                .pattern("aga")
                .pattern("gtg")
                .pattern("aga")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.TECHNOLOGY_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.RUBBER_PLANKS.get().asItem()),
                ThingamajigsBlocks.RUBBER_LANE.get().asItem(),1).save(frc);

        stonecutterAny(ThingamajigsBlocks.BRAMBLE.get().asItem(),
                Ingredient.of(Items.DEAD_BUSH),
                ThingamajigsBlocks.BRAMBLE.get().asItem(),1).save(frc);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ThingamajigsBlocks.DJ_LASER_LIGHT.get(),1)
                .define('m',ThingamajigsItems.MISC_COMPONENT.get())
                .define('d',Items.DROPPER)
                .define('r',Items.RED_DYE)
                .define('g',Items.GREEN_DYE)
                .define('b',Items.BLUE_DYE)
                .define('l',Items.GLASS)
                .define('i',Items.IRON_INGOT)
                .pattern("lgl")
                .pattern("rdb")
                .pattern("imi")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.CONVEYOR_BELT.get(), 4)
                .requires(ThingamajigsBlocks.ESCALATOR.get())
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(ItemTags.SLABS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.ANALOG_CLOCK.get(), 1)
                .requires(Items.IRON_INGOT)
                .requires(Items.PAPER)
                .requires(Items.GRAY_DYE)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.CLOCK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        // fire hydrants
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.RED_FIRE_HYDRANT.get(), 1)
                .requires(Items.IRON_BLOCK)
                .requires(Items.WATER_BUCKET)
                .requires(Items.RED_DYE)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.YELLOW_FIRE_HYDRANT.get(), 1)
                .requires(Items.IRON_BLOCK)
                .requires(Items.WATER_BUCKET)
                .requires(Items.YELLOW_DYE)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.SILVER_FIRE_HYDRANT.get(), 1)
                .requires(Items.IRON_BLOCK)
                .requires(Items.WATER_BUCKET)
                .requires(Items.LIGHT_GRAY_DYE)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.PARKING_METER.get(), 1)
                .requires(Items.IRON_BARS)
                .requires(Items.GLASS_PANE)
                .requires(Items.HOPPER)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);

        // copper tables
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.WAXED_COPPER_TABLE.get(), 1)
                .requires(Items.COPPER_INGOT)
                .requires(ThingamajigsBlocks.STONE_TABLE.get())
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.COPPER_INGOT))
                .save(frc);
        stonecutterAny(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.WAXED_COPPER_TABLE.get().asItem()),
                ThingamajigsBlocks.WAXED_EXPOSED_COPPER_TABLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.WAXED_COPPER_TABLE.get()))
                .save(frc);
        stonecutterAny(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.WAXED_COPPER_TABLE.get().asItem()),
                ThingamajigsBlocks.WAXED_WEATHERED_COPPER_TABLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.WAXED_COPPER_TABLE.get()))
                .save(frc);
        stonecutterAny(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.WAXED_COPPER_TABLE.get().asItem()),
                ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_TABLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.WAXED_COPPER_TABLE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.IRON_TABLE.get().asItem(),
                Ingredient.of(Items.IRON_BLOCK),
                ThingamajigsBlocks.IRON_TABLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BLOCK))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.DIAMOND_TABLE.get().asItem(),
                Ingredient.of(Items.DIAMOND_BLOCK),
                ThingamajigsBlocks.DIAMOND_TABLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(Items.DIAMOND_BLOCK))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.DOOR_BLOCKADE.get(), 2)
                .requires(Ingredient.of(ItemTags.PLANKS))
                .requires(Ingredient.of(ItemTags.PLANKS))
                .requires(Items.STICK)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.WINDOW_BLOCKADE.get(), 4)
                .requires(Ingredient.of(ItemTags.PLANKS))
                .requires(Items.STICK)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.CINDER_BLOCK.get(), 4)
                .requires(Ingredient.of(ItemTags.STONE_BRICKS))
                .requires(Items.DEEPSLATE_TILES)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CINDER_BLOCK.get()),
                ThingamajigsBlocks.CINDER_BLOCK_SLAB.get().asItem(),2)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CINDER_BLOCK.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CINDER_BLOCK_SMALL.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CINDER_BLOCK.get()),
                ThingamajigsBlocks.CINDER_BLOCK_SMALL.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CINDER_BLOCK.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.I_BEAM.get(), 4)
                .requires(Ingredient.of(ThingamajigsBlocks.CINDER_BLOCK.get()))
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CINDER_BLOCK.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.STEEL_HOARDING.get(), 2)
                .requires(Ingredient.of(ThingamajigsBlocks.CHAINLINK_FENCE.get()))
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Items.IRON_BARS)
                .requires(Items.IRON_TRAPDOOR)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_TRAPDOOR))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.CONCRETE.get(), 1)
                .requires(Ingredient.of(ThingamajigsBlocks.CINDER_BLOCK.get()))
                .requires(Items.GRAY_CONCRETE)
                .requires(Items.CLAY_BALL)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAY_CONCRETE))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONCRETE_BRICKS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CONCRETE.get()),
                ThingamajigsBlocks.CONCRETE_BRICKS.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CONCRETE.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.COBBLED_CONCRETE.get(), 2)
                .requires(Ingredient.of(ThingamajigsBlocks.CONCRETE.get()))
                .requires(Items.COBBLESTONE)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.COBBLESTONE))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.STEEL.get(), 1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Items.IRON_BARS)
                .requires(Items.IRON_BLOCK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_BLOCK))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.LADDER_RAILING.get(), 1)
                .requires(Ingredient.of(ThingamajigsBlocks.BRIDGE_BARRIER.get()))
                .requires(Items.LADDER)
                .requires(Items.IRON_BARS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.BRIDGE_BARRIER.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CURVED_RAILING.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.LADDER_RAILING.get()),
                ThingamajigsBlocks.CURVED_RAILING.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.LADDER_RAILING.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.RED_PATHWAY.get(), 6)
                .requires(Items.RED_CONCRETE_POWDER)
                .requires(Items.WATER_BUCKET)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(Items.SLIME_BALL)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.RED_CONCRETE_POWDER))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.GRAY_PATHWAY.get(), 6)
                .requires(Items.GRAY_CONCRETE_POWDER)
                .requires(Items.WATER_BUCKET)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(Items.SLIME_BALL)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.GRAY_CONCRETE_POWDER))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONCRETE_STAIRS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CONCRETE.get()),
                ThingamajigsBlocks.CONCRETE_STAIRS.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CONCRETE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONCRETE_SLAB.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CONCRETE.get()),
                ThingamajigsBlocks.CONCRETE_SLAB.get().asItem(),2)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CONCRETE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CONCRETE_BRICKS.get()),
                ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CONCRETE_BRICKS.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CONCRETE_BRICKS.get()),
                ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get().asItem(),2)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.CONCRETE_BRICKS.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.COBBLED_CONCRETE.get()),
                ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.COBBLED_CONCRETE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.COBBLED_CONCRETE.get()),
                ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get().asItem(),2)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.COBBLED_CONCRETE.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.DISCO_BALL.get(), 1)
                .requires(Items.GLASS)
                .requires(Ingredient.of(ThingamajigsBlocks.MIRROR.get()))
                .requires(Items.CHAIN)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.MIRROR.get().asItem()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.LAUNDRY_BASKET.get(), 1)
                .requires(Items.SCAFFOLDING)
                .requires(ThingamajigsItems.HOME_COMPONENT.get())
                .requires(ItemTags.WOOL)
                .requires(ItemTags.WOOL_CARPETS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.HOME_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.LAUNDRY_PILE.get(), 2)
                .requires(ThingamajigsItems.HOME_COMPONENT.get())
                .requires(ItemTags.WOOL_CARPETS)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(Items.PAPER)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.HOME_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.BAR_STOOL.get(), 2)
                .requires(Items.BROWN_WOOL)
                .requires(Items.SPRUCE_PLANKS)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.BROWN_WOOL))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.SPEAKER.get(), 2)
                .requires(Items.NOTE_BLOCK)
                .requires(Items.NOTE_BLOCK)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.AUDIO_MIXER.get(), 1)
                .requires(Items.NOTE_BLOCK)
                .requires(ItemTags.BUTTONS)
                .requires(Items.LEVER)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS,ThingamajigsItems.SCRAPE_TOOL.get(),1)
                .define('f',Items.FLINT)
                .define('s',Items.STICK)
                .define('a',ThingamajigsItems.THINGAMAJIG.get())
                .pattern("faf")
                .pattern(" s ")
                .pattern(" s ")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.THINGAMAJIG.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.BARBER_POLE.get(), 1)
                .requires(Items.SHEARS)
                .requires(Items.BLUE_DYE)
                .requires(Items.RED_DYE)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_NUGGET)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHEARS))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.WALL_TV.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.BIG_TV.get()),
                ThingamajigsBlocks.WALL_TV.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsBlocks.BIG_TV.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.BARBER_CHAIR.get(), 1)
                .requires(Items.SHEARS)
                .requires(ThingamajigsTags.CHAIRS_ITEM)
                .requires(Items.BLACK_DYE)
                .requires(ItemTags.WOOL_CARPETS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHEARS))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.BARBER_HAIR_DRYER.get(), 1)
                .requires(Items.SHEARS)
                .requires(Items.BLACK_STAINED_GLASS_PANE)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsBlocks.FAN_BLOCK.get().asItem())
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(Items.SHEARS))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem(),
                Ingredient.of(ThingamajigsItems.GAME_CONSOLE_COMPONENT.get()),
                ThingamajigsBlocks.BOXY_CONSOLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsItems.GAME_CONSOLE_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.ORANGE_BOXY_CONSOLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem()),
                ThingamajigsBlocks.ORANGE_BOXY_CONSOLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.TALL_BOXY_CONSOLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem()),
                ThingamajigsBlocks.TALL_BOXY_CONSOLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.BOXY_CONSOLE.get().asItem()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.DENTAL_CUP.get().asItem(),
                Ingredient.of(ThingamajigsItems.MISC_COMPONENT.get()),
                ThingamajigsBlocks.DENTAL_CUP.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get().asItem(),
                Ingredient.of(ThingamajigsItems.GAME_CONSOLE_COMPONENT.get()),
                ThingamajigsBlocks.GRAY_GAME_CONSOLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsItems.GAME_CONSOLE_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.BLACK_GAME_CONSOLE.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get()),
                ThingamajigsBlocks.BLACK_GAME_CONSOLE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.AIR_FRYER.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.OVEN.get()),
                ThingamajigsBlocks.AIR_FRYER.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.OVEN.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.WATER_DISPENSER.get(), 2)
                .requires(Items.WHITE_CONCRETE)
                .requires(Items.RED_DYE)
                .requires(Items.BLUE_DYE)
                .requires(Items.LIGHT_BLUE_STAINED_GLASS)
                .requires(ThingamajigsBlocks.WATER_FOUNTAIN.get().asItem())
                .requires(ThingamajigsItems.APPLIANCE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.APPLIANCE_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CAT_TREE.get().asItem(),
                Ingredient.of(Items.GRAY_CONCRETE),
                ThingamajigsBlocks.CAT_TREE.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(Items.GRAY_CONCRETE))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.TRASH_BAG.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.TRASH_CAN.get()),
                ThingamajigsBlocks.TRASH_BAG.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.TRASH_CAN.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.GAMING_PC.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.BLUEY_DESKTOP_COMPUTER.get()),
                ThingamajigsBlocks.GAMING_PC.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.BLUEY_DESKTOP_COMPUTER.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.LITTER_BOX.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CAT_TREE.get()),
                ThingamajigsBlocks.LITTER_BOX.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.CAT_TREE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.DESK_LAMP.get().asItem(),
                Ingredient.of(ThingamajigsItems.FULL_BULB_ITEM.get()),
                ThingamajigsBlocks.DESK_LAMP.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsItems.FULL_BULB_ITEM.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.NOT_QUITE_MENGER.get()),
                ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.NOT_QUITE_MENGER.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.CAMPING_FUEL_CAN.get(), 4)
                .requires(Items.GREEN_CONCRETE)
                .requires(ItemTags.COALS)
                .requires(Tags.Items.GUNPOWDER)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.ICECREAM_DISPLAY.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.ICECREAM_MACHINE.get()),
                ThingamajigsBlocks.ICECREAM_DISPLAY.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.ICECREAM_MACHINE.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.PTAC_AC.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.AIR_CONDITIONER.get()),
                ThingamajigsBlocks.PTAC_AC.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.AIR_CONDITIONER.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CLOTHES_RACK.get().asItem(),
                Ingredient.of(ThingamajigsItems.MISC_COMPONENT.get()),
                ThingamajigsBlocks.CLOTHES_RACK.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CARD_READER.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CASH_REGISTER.get()),
                ThingamajigsBlocks.CARD_READER.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.CASH_REGISTER.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CURVED_MONITOR.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.MODERN_PC_MONITOR.get()),
                ThingamajigsBlocks.CURVED_MONITOR.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.MODERN_PC_MONITOR.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, ThingamajigsBlocks.SECURITY_METAL_DETECTOR.get(), 1)
                .requires(Items.IRON_INGOT)
                .requires(Items.WHITE_CONCRETE)
                .requires(ThingamajigsBlocks.BOTH_BATHROOM_SIGN.get())
                .requires(ThingamajigsItems.SAFETY_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.SAFETY_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.CLEVER_BLACKBOARD.get(),1)
                .requires(Items.BLACK_CONCRETE)
                .requires(ThingamajigsBlocks.TEACHING_BOARD.get())
                .requires(ThingamajigsItems.COMPUTER_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.COMPUTER_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.SUPERMARKET_CONVEYOR.get(),1)
                .requires(ThingamajigsBlocks.CONVEYOR_BELT.get())
                .requires(Items.WHITE_CONCRETE)
                .requires(ThingamajigsBlocks.STORE_SHELF.get())
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.UMBRELLA.get(),1)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(ItemTags.WOOL_CARPETS)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.STICK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.STRING_BASS.get(),1)
                .requires(Items.STRING)
                .requires(Items.STRING)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.STICK)
                .requires(ItemTags.PLANKS)
                .requires(Items.NOTE_BLOCK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.BASS_DRUM.get(),1)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.NOTE_BLOCK)
                .requires(ItemTags.WOODEN_FENCES)
                .requires(Items.BARREL)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.SNARE_DRUM.get(),1)
                .requires(Items.PAPER)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.NOTE_BLOCK)
                .requires(ItemTags.PLANKS)
                .requires(ItemTags.WOODEN_FENCES)
                .requires(ItemTags.WOODEN_FENCES)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
                        ThingamajigsItems.MUSIC_COMPONENT.get(),4)
                .requires(Items.NOTE_BLOCK)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.JUKEBOX)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,
                        ThingamajigsBlocks.CYMBAL_CRASH.get(),1)
                .requires(Items.NOTE_BLOCK)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.LIGHT_WEIGHTED_PRESSURE_PLATE)
                .requires(Items.STICK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.FLOOR_TOM.get(),1)
                .requires(Items.PAPER)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.NOTE_BLOCK)
                .requires(ItemTags.WOODEN_PRESSURE_PLATES)
                .requires(ItemTags.WOODEN_FENCES)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.RACK_TOM.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.FLOOR_TOM.get()),
                ThingamajigsBlocks.RACK_TOM.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.FLOOR_TOM.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.BONGOS.get(),1)
                .requires(Items.PAPER)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(Items.NOTE_BLOCK)
                .requires(Items.LEATHER)
                .requires(Items.LEATHER)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.HI_HAT.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.CYMBAL_CRASH.get()),
                ThingamajigsBlocks.HI_HAT.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.CYMBAL_CRASH.get()))
                .save(frc);

        stonecutterAny(ThingamajigsBlocks.CONGAS.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.BONGOS.get()),
                ThingamajigsBlocks.CONGAS.get().asItem(),1)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.BONGOS.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.CAJON.get(),2)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(ThingamajigsBlocks.CONGAS.get().asItem())
                .requires(ThingamajigsBlocks.BASS_DRUM.get().asItem())
                .requires(ThingamajigsBlocks.SNARE_DRUM.get().asItem())
                .requires(ThingamajigsBlocks.HI_HAT.get().asItem())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.VOICE_MICROPHONE.get(),1)
                .requires(ThingamajigsItems.MUSIC_COMPONENT.get())
                .requires(ThingamajigsBlocks.SPEAKER.get().asItem())
                .requires(Items.WRITABLE_BOOK)
                .requires(ItemTags.COALS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MUSIC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.THEATER_PROJECTOR.get(),1)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(ThingamajigsBlocks.PROJECTOR.get().asItem())
                .requires(ThingamajigsBlocks.THEATER_SEAT_CONTINUOUS.get().asItem())
                .requires(Items.IRON_BLOCK)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.TRIPLE_SHELF.get(),1)
                .requires(ThingamajigsItems.FURNITURE_COMPONENT.get())
                .requires(ThingamajigsTags.TABLES_ITEM)
                .requires(ThingamajigsTags.TABLES_ITEM)
                .requires(Items.GRAY_DYE)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.FURNITURE_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.TEDDY_BEAR.get(),2)
                .requires(ThingamajigsItems.TOY_COMPONENT.get())
                .requires(Items.SPRUCE_SAPLING)
                .requires(Items.BROWN_WOOL)
                .requires(Items.BROWN_WOOL)
                .requires(Items.HONEYCOMB)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.TOY_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.CHIMNEY.get(),1)
                .requires(ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get())
                .requires(Items.BRICK)
                .requires(Items.BRICK)
                .requires(Items.BRICK)
                .requires(Items.BASALT)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.INFRASTRUCTURE_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.MYSTERIOUS_PILLAR.get(),3)
                .requires(ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem())
                .requires(Items.ENDER_EYE)
                .requires(Items.MAGENTA_DYE)
                .requires(Items.PURPUR_PILLAR)
                .requires(Items.DIAMOND_BLOCK)
                .requires(ThingamajigsItems.BASE_COMPONENT.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get().asItem()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.GOAL.get(),1)
                .requires(ThingamajigsBlocks.SCREEN.get().asItem())
                .requires(ThingamajigsItems.SPORTS_COMPONENT.get())
                .requires(Items.IRON_BARS)
                .requires(Items.IRON_BARS)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.SPORTS_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.GLOWING_INVERTED_CATEYE_CROSSBUCK.get(),1)
                .requires(ThingamajigsBlocks.INVERTED_CATEYE_CROSSBUCK.get().asItem())
                .requires(Items.GLOW_INK_SAC)
                .requires(Items.GLOWSTONE_DUST)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.INVERTED_CATEYE_CROSSBUCK.get().asItem()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN.get(),1)
                .requires(ThingamajigsBlocks.DO_NOT_ENTER.get().asItem())
                .requires(Items.WRITABLE_BOOK)
                .requires(Items.ORANGE_DYE)
                .requires(Items.BLACK_DYE)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.DO_NOT_ENTER.get().asItem()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN.get(),1)
                .requires(ThingamajigsBlocks.DO_NOT_ENTER.get().asItem())
                .requires(Items.WRITABLE_BOOK)
                .requires(Items.RED_DYE)
                .requires(ThingamajigsBlocks.SECURE_SECURITY_CAMERA.get())
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.DO_NOT_ENTER.get().asItem()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN.get(),1)
                .requires(ThingamajigsBlocks.DO_NOT_ENTER.get().asItem())
                .requires(Items.WRITABLE_BOOK)
                .requires(Items.RED_DYE)
                .requires(ThingamajigsBlocks.CHAINLINK_FENCE.get().asItem())
                .requires(ThingamajigsTags.CHAIRS_ITEM)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.DO_NOT_ENTER.get().asItem()))
                .save(frc);

        // 1.8.1
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                ThingamajigsBlocks.ANIMATED_ICE_RINK.get(),1)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.PACKED_ICE)
                .requires(Items.POWDER_SNOW_BUCKET)
                .requires(Items.TORCH)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                ThingamajigsBlocks.NEWSPAPER_DISPENSER.get(),1)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .requires(Items.BARREL)
                .requires(ItemTags.PLANKS)
                .requires(Items.IRON_INGOT)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS,
                        ThingamajigsBlocks.RESTAURANT_TRASH_CAN.get(),1)
                .requires(ThingamajigsBlocks.TRASH_CAN.get().asItem())
                .requires(Items.BARREL)
                .requires(ItemTags.PLANKS)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(
                        ThingamajigsBlocks.TRASH_CAN.get().asItem()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.SPECIAL_STATUE.get().asItem(),1)
                .requires(Items.IRON_INGOT)
                .requires(Items.WHITE_CONCRETE)
                .requires(Items.ARMOR_STAND)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        // part 2 of 1.8.1
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.SNOW_MACHINE.get().asItem(),1)
                .requires(ThingamajigsBlocks.SCREEN.get().asItem())
                .requires(ThingamajigsBlocks.FAN_BLOCK.get().asItem())
                .requires(Items.SNOWBALL)
                .requires(Items.SNOWBALL)
                .requires(Items.IRON_INGOT)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.BALL_PIT.get().asItem(),3)
                .requires(Tags.Items.DYES)
                .requires(Items.CLAY_BALL)
                .requires(Items.IRON_INGOT)
                .requires(Items.BAMBOO_BLOCK)
                .requires(ThingamajigsItems.ARCADE_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.ARCADE_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.BONDING_STATUE.get().asItem(),1)
                .requires(Items.DIAMOND)
                .requires(Items.WHITE_CONCRETE)
                .requires(Items.PINK_CONCRETE)
                .requires(Items.MAGENTA_CONCRETE)
                .requires(Items.ARMOR_STAND)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.CATCHING_STATUE.get().asItem(),1)
                .requires(Items.EMERALD)
                .requires(Items.PINK_CONCRETE)
                .requires(Items.RED_CONCRETE)
                .requires(Items.ORANGE_TERRACOTTA)
                .requires(Items.ENDER_EYE)
                .requires(Items.ARMOR_STAND)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.RESIN_FILLED_ROCK.get().asItem(),1)
                .requires(Items.COBBLESTONE)
                .requires(ThingamajigsTags.TREE_RESIN_TAG)
                .requires(ThingamajigsTags.TREE_RESIN_TAG)
                .unlockedBy("has_thingy",has(ThingamajigsTags.TREE_RESIN_TAG))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.STRANGE_STATUE.get().asItem(),1)
                .requires(Items.NETHERITE_INGOT)
                .requires(Items.IRON_BARS)
                .requires(ItemTags.PLANKS)
                .requires(Items.WHITE_DYE)
                .requires(Items.ENDER_EYE)
                .requires(Items.ARMOR_STAND)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.ANIMATED_DEER.get().asItem(),1)
                .requires(Items.PISTON)
                .requires(ThingamajigsBlocks.LIGHTED_DEER.get().asItem())
                .requires(ThingamajigsItems.CHRISTMAS_COMPONENT.get())
                .requires(Items.WHITE_DYE)
                .unlockedBy("has_thingy",has(ThingamajigsItems.CHRISTMAS_COMPONENT.get()))
                .save(frc);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.VALIANT_STATUE.get().asItem(),1)
                .requires(Items.AMETHYST_BLOCK)
                .requires(Items.PURPLE_TERRACOTTA)
                .requires(Items.DRAGON_BREATH)
                .requires(Items.ARMOR_STAND)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        // 1.8.3 recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.FOOD_COOLER.get().asItem(),2)
                .requires(Items.ICE)
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(Items.LIGHT_BLUE_TERRACOTTA)
                .requires(Items.WHITE_CONCRETE)
                .requires(ThingamajigsItems.HOME_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.HOME_COMPONENT.get()))
                .save(frc);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS,ThingamajigsBlocks.FOOTBALL_GOAL.get(),1)
                .define('y',Items.YELLOW_CONCRETE)
                .define('c',ThingamajigsItems.SPORTS_COMPONENT.get())
                .pattern("y y")
                .pattern("ycy")
                .pattern(" y ")
                .unlockedBy("has_thing",InventoryChangeTrigger.TriggerInstance.hasItems(ThingamajigsItems.SPORTS_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.SPHERES_AND_RINGS_MACHINE.get().asItem(),1)
                .requires(ThingamajigsBlocks.SCREEN.get().asItem())
                .requires(ThingamajigsBlocks.BASKETBALL_MACHINE.get())
                .requires(Items.BLUE_CONCRETE)
                .requires(ItemTags.WOODEN_SLABS)
                .requires(ItemTags.WOODEN_SLABS)
                .requires(ThingamajigsItems.ARCADE_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.ARCADE_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.ROUND_CLOTHES_RACK.get().asItem(),1)
                .requires(ThingamajigsBlocks.CLOTHES_RACK.get())
                .requires(Items.IRON_INGOT)
                .requires(Tags.Items.ARMORS)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.PLUNGER.get().asItem(),1)
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(Items.STICK)
                .requires(Items.RED_DYE)
                .requires(Items.PINK_DYE)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.PIZZA.get().asItem(),1)
                .requires(ThingamajigsBlocks.CHEESE_BLOCK.get())
                .requires(Items.WHEAT)
                .requires(Items.RED_DYE)
                .requires(Items.GREEN_DYE)
                .requires(Items.CHARCOAL)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.CAR_WHEEL.get().asItem(),1)
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(Items.IRON_BARS)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.POOL_TABLE.get().asItem(),1)
                .requires(ThingamajigsTags.TABLES_ITEM)
                .requires(Items.DARK_OAK_PLANKS)
                .requires(ThingamajigsBlocks.BALL_PIT.get())
                .requires(ThingamajigsItems.RUBBER.get())
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.METAL_POOL_TABLE.get().asItem(),1)
                .requires(ThingamajigsBlocks.POOL_TABLE.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .requires(Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .unlockedBy("has_thingy",has(ThingamajigsBlocks.POOL_TABLE.get().asItem()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.TOWEL_STACK.get().asItem(),1)
                .requires(Items.WHITE_WOOL)
                .requires(Items.STRING)
                .requires(Items.STRING)
                .requires(ThingamajigsBlocks.PAPER_TOWEL.get())
                .requires(ThingamajigsItems.HOME_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.HOME_COMPONENT.get()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.RARE_BLUE_GRAY_GAME_CONSOLE.get().asItem(),1)
                .requires(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get())
                .requires(Items.LAPIS_BLOCK)
                .requires(Items.GOLDEN_APPLE)
                .unlockedBy("has_thingy",has(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get().asItem()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.GOLDME_CONSOLE.get().asItem(),1)
                .requires(ThingamajigsBlocks.BLUEMAN_CONSOLE.get())
                .requires(Items.GOLD_BLOCK)
                .requires(Items.ENCHANTED_GOLDEN_APPLE)
                .unlockedBy("has_thingy",has(ThingamajigsBlocks.BLUEMAN_CONSOLE.get().asItem()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.FUNDEVICE_GAME_CONSOLE.get().asItem(),1)
                .requires(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get())
                .requires(Tags.Items.DYES)
                .requires(ThingamajigsItems.TECHNOLOGY_COMPONENT.get())
                .requires(ThingamajigsBlocks.DVD_PLAYER.get())
                .unlockedBy("has_thingy",has(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get().asItem()))
                .save(frc);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ThingamajigsBlocks.CARDBOARD_BOX.get().asItem(),1)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .requires(Items.PAPER)
                .requires(Items.BROWN_DYE)
                .requires(Items.BROWN_DYE)
                .requires(ThingamajigsItems.MISC_COMPONENT.get())
                .unlockedBy("has_thingy",has(ThingamajigsItems.MISC_COMPONENT.get()))
                .save(frc);
        // 1.8.4
        stonecutterAny(ThingamajigsBlocks.SIDEWALK_LAYER_LEFT.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.SIDEWALK_SLAB.get()),
                ThingamajigsBlocks.SIDEWALK_LAYER_LEFT.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.SIDEWALK_SLAB.get()))
                .save(frc);
        stonecutterAny(ThingamajigsBlocks.SIDEWALK_LAYER_RIGHT.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.SIDEWALK_SLAB.get()),
                ThingamajigsBlocks.SIDEWALK_LAYER_RIGHT.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.SIDEWALK_SLAB.get()))
                .save(frc);
        stonecutterAny(ThingamajigsBlocks.SIDEWALK_LAYER.get().asItem(),
                Ingredient.of(ThingamajigsBlocks.SIDEWALK_SLAB.get()),
                ThingamajigsBlocks.SIDEWALK_LAYER.get().asItem(),4)
                .unlockedBy("has_thingy",InventoryChangeTrigger.TriggerInstance
                        .hasItems(ThingamajigsBlocks.SIDEWALK_SLAB.get()))
                .save(frc);
    }

    public static RecipeBuilder stonecutterAny(Item requiredItem, Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_item",inventoryTrigger(ItemPredicate.Builder.item().of(requiredItem).build()));
    }

    public static RecipeBuilder stonecutterAnyWithTag(TagKey<Item> requiredItemTag, Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_item",inventoryTrigger(ItemPredicate.Builder.item().of(requiredItemTag).build()));
    }

    public static RecipeBuilder stonecutterType(Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_planks",inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PLANKS).build()));
    }

    public static RecipeBuilder stonecutterTypeBricks(Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_bricks",inventoryTrigger(ItemPredicate.Builder.item().of(Blocks.BRICKS).build()));
    }

    public static RecipeBuilder stonecutterTypeDarkenedStone(Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_darkened_stone",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsBlocks.DARKENED_STONE.get()).build()));
    }

    public static RecipeBuilder stonecutterTypeStripeFactory(Ingredient inputItem, Item result, int amt){
        return SingleItemRecipeBuilder.stonecutting(inputItem,RecipeCategory.BUILDING_BLOCKS,result,amt)
                .unlockedBy("has_factory_component",inventoryTrigger(ItemPredicate.Builder.item().of(ThingamajigsItems.FACTORY_COMPONENT.get()).build()));
    }


    public static RecipeBuilder balloonItemBuilder(ItemLike balloon, Ingredient dye, int manyBalloons) {
        return ShapedRecipeBuilder.shaped(
                RecipeCategory.MISC, balloon, manyBalloons)
                .define('#', ThingamajigsItems.RUBBER.get())
                .define('d',dye)
                .pattern("###")
                .pattern("#d#")
                .pattern("###");
    }

    public static RecipeBuilder glowBlockBuilder(ItemLike glowBlock, Ingredient stainedGlass, int count){
        return ShapedRecipeBuilder.shaped(
                        RecipeCategory.BUILDING_BLOCKS,glowBlock,count)
                .define('g',ThingamajigsItems.CLEAR_BULB_ITEM.get())
                .define('s',stainedGlass)
                .pattern("sss")
                .pattern("sgs")
                .pattern("sss");
    }
}
