package net.rk.thingamajigs.datagen;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.ForgeRegistries;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.item.ThingamajigsItems;
import net.rk.thingamajigs.xtrablock.CheeseBlock;
import net.rk.thingamajigs.xtrablock.GlowingCheeseBlock;
import net.rk.thingamajigs.xtrablock.LockableDoor;

import java.util.Map;
import java.util.stream.Collectors;

public class ThingamajigsLootTables extends VanillaBlockLoot {
    @Override
    protected void generate() {
        this.dropSelf(ThingamajigsBlocks.ABANDONED_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.AC_DUCT_ALLWAY.get());
        this.dropSelf(ThingamajigsBlocks.AC_DUCT_CORNER.get());
        this.dropSelf(ThingamajigsBlocks.AC_DUCT.get());
        this.dropSelf(ThingamajigsBlocks.AC_THERMOSTAT.get());
        this.dropSelf(ThingamajigsBlocks.ACACIA_LANE.get());
        this.dropSelf(ThingamajigsBlocks.AHEAD_HALF.get());
        this.dropSelf(ThingamajigsBlocks.AHEAD_YELLOW_HALF.get());
        this.dropSelf(ThingamajigsBlocks.AIR_CONDITIONER.get());
        this.dropSelf(ThingamajigsBlocks.AIR_HOCKEY_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.AIR_PURIFIER.get());
        this.dropSelf(ThingamajigsBlocks.AIRDUCT_VENT.get());
        this.dropSelf(ThingamajigsBlocks.AISLE_SIGN.get());
        this.add(ThingamajigsBlocks.ALARMED_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.ALARMED_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.ALARMED_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.ALARMED_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.ALL_DAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.ALL_PROHIBITED.get());
        this.dropSelf(ThingamajigsBlocks.ALL_WAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.ALL_WAY_POLE.get());
        this.dropSelf(ThingamajigsBlocks.ALT_FRENCH_BRICK.get());
        this.dropSelf(ThingamajigsBlocks.ALT_ORANGE_CAUTION.get());
        this.dropSelf(ThingamajigsBlocks.ALT_ROAD_COVER.get());
        this.dropSelf(ThingamajigsBlocks.ALT_ROAD_PANEL_COVER.get());
        this.dropSelf(ThingamajigsBlocks.ALT_US_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.ALT_NEON_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.AMBER_STRING_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.ANCIENT_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.ANGLED_BIG_MERGE_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.ANGLED_BIG_MERGE_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.ANTENNA.get());
        this.dropSelf(ThingamajigsBlocks.AQUARIUM.get());
        this.dropSelf(ThingamajigsBlocks.ARCADE_MACHINE_OPENABLE.get());
        this.dropSelf(ThingamajigsBlocks.ARCADE_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.ARROW_BEACON.get());
        this.dropSelf(ThingamajigsBlocks.ARROW_BOARD.get());
        this.dropSelf(ThingamajigsBlocks.ARROW_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.ASCENT.get());

        this.add(ThingamajigsBlocks.ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.ASPHALT.get());

        this.dropSelf(ThingamajigsBlocks.ATM.get());
        this.dropSelf(ThingamajigsBlocks.AUDIO_CONTROLLER.get());
        this.dropSelf(ThingamajigsBlocks.AUSTRALIA_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.AUSTRALIAN_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.AXIS_POLE.get());
        this.dropSelf(ThingamajigsBlocks.BABY_CARRIAGE.get());
        this.dropOther(ThingamajigsBlocks.BALLOON_BLOCK.get(),ThingamajigsItems.BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BARREL_KEG.get());
        this.dropSelf(ThingamajigsBlocks.BASIC_BATHROOM_TILE.get());
        this.dropSelf(ThingamajigsBlocks.BASKETBALL_HOOP.get());
        this.dropSelf(ThingamajigsBlocks.BASKETBALL_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.BATHTUB_NOZZLE.get());
        this.dropSelf(ThingamajigsBlocks.BEAKER.get());
        this.dropSelf(ThingamajigsBlocks.BEEP_FIRE_ALARM.get());
        this.dropSelf(ThingamajigsBlocks.BENDS.get());
        this.dropSelf(ThingamajigsBlocks.BIG_ARROW_ROAD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BIG_LEFT_MERGES.get());
        this.dropSelf(ThingamajigsBlocks.BIG_RIGHT_MERGES.get());
        this.dropSelf(ThingamajigsBlocks.BIG_ROAD_CONE.get());
        this.dropSelf(ThingamajigsBlocks.BIG_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BIG_TV.get());
        this.dropSelf(ThingamajigsBlocks.BIKE.get());
        this.dropSelf(ThingamajigsBlocks.BIO_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BIOHAZARD_BIN.get());
        this.dropOther(ThingamajigsBlocks.BLACK_BALLOON_BLOCK.get(),ThingamajigsItems.BLACK_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_FAN.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_MAILBOX.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_TELEPHONE.get());
        this.dropSelf(ThingamajigsBlocks.BLANK_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.BLAST_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BLASTING_ZONE_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.BLENDER.get());

        this.add(ThingamajigsBlocks.BLOCKED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.BLOCKED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.BLOCKED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BLOCKED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.SIDEWALK_BLOCKED.get());

        this.dropOther(ThingamajigsBlocks.BLUE_BALLOON_BLOCK.get(),ThingamajigsItems.BLUE_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.MINI_BLUE_BUILDING.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_PARKING_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_SODA_MACHINE.get());
        this.dropOther(ThingamajigsBlocks.BLUE_STANDING_SIGN.get(),ThingamajigsItems.BLUE_ROADWAY_SIGN_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_STOP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_STRING_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_TOME_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_VENDING_MACHINE.get());
        this.dropOther(ThingamajigsBlocks.BLUE_WALL_SIGN.get(),ThingamajigsItems.BLUE_ROADWAY_SIGN_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BLUEBERRY_STONE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEMAN_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.SNOWMAN_BLUEMAN_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEMAN_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEY_MECHANICAL_BELL.get());
        this.dropSelf(ThingamajigsBlocks.BLUEY_MECHANICAL_BELL_TWO.get());
        this.dropSelf(ThingamajigsBlocks.BLUEY_DESKTOP_COMPUTER.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYBOX.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYCUBE_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYDOWS_LAPTOP.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYPASS_ONLY.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYSNAP_BASE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYSNAP_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYTOSH_LAPTOP_OLD.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYTOSH_LAPTOP.get());
        this.dropSelf(ThingamajigsBlocks.BLUEYTOSH_STUDIO.get());
        this.dropSelf(ThingamajigsBlocks.BOMB_THREAT_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.BONE_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.BOTH_BATHROOM_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.BOWLING_ALLEY_OILER.get());
        this.dropSelf(ThingamajigsBlocks.BOWLING_BALL_RETRIEVER.get());
        this.dropSelf(ThingamajigsBlocks.BOWLING_FLOORING.get());
        this.dropSelf(ThingamajigsBlocks.BOWLING_GAME_CONTROLLER.get());
        this.dropSelf(ThingamajigsBlocks.BOWLING_PIN.get());
        this.dropSelf(ThingamajigsBlocks.BOX_SECURITY_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.BPTS.get());
        this.dropSelf(ThingamajigsBlocks.BREAD_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.BRICK_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.BRICK_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.BRICK_SIDEWALK_HB.get());
        this.dropSelf(ThingamajigsBlocks.BRICK_SIDEWALK.get());
        this.dropSelf(ThingamajigsBlocks.BRIDGE_BARRIER.get());
        this.dropSelf(ThingamajigsBlocks.BRIDGE_OUT_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.BROKEN_COMPUTER.get());
        this.dropOther(ThingamajigsBlocks.BROWN_BALLOON_BLOCK.get(),ThingamajigsItems.BROWN_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.BROWN_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.BROWN_PATHWAY.get());
        this.dropOther(ThingamajigsBlocks.BROWN_WALL_SIGN.get(),ThingamajigsItems.BROWN_ROADWAY_SIGN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.BROWN_STANDING_SIGN.get(),ThingamajigsItems.BROWN_ROADWAY_SIGN_ITEM.get());
        this.add(ThingamajigsBlocks.BUBBLE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.BUBBLE_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.BUBBLE_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BUBBLE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.BULBY_FLOWER.get());
        this.dropSelf(ThingamajigsBlocks.BULK_PRODUCT.get());
        this.dropSelf(ThingamajigsBlocks.BUMP.get());
        this.dropSelf(ThingamajigsBlocks.BUS_STOP_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.BUTTER_CHURNER.get());
        this.dropSelf(ThingamajigsBlocks.BUTTON_SWITCH.get());
        this.dropSelf(ThingamajigsBlocks.BYPRODUCT.get());
        this.dropSelf(ThingamajigsBlocks.CALENDAR.get());
        this.dropSelf(ThingamajigsBlocks.CANADIAN_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_BLUE_BRUSH.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_DRIPPER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_DRYER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_MITTER_CURTAIN.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_MIXED_BRUSH.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_RED_BRUSH.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_SIGNAGE.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_SIGNAL.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_SOAPER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_SPRAYER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_TIRE_SCRUBBER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_TRIFOAMER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_WAXER.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WASH_YELLOW_BRUSH.get());
        this.dropSelf(ThingamajigsBlocks.CAR.get());
        this.dropSelf(ThingamajigsBlocks.CARNIVAL_AWNING.get());
        this.dropSelf(ThingamajigsBlocks.CASH_REGISTER.get());
        this.dropSelf(ThingamajigsBlocks.CATEYE_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.CATTLE_GRID.get());
        this.dropSelf(ThingamajigsBlocks.CATWALK_CENTER.get());
        this.dropSelf(ThingamajigsBlocks.CATWALK.get());
        this.dropSelf(ThingamajigsBlocks.CEILING_FAN.get());
        this.dropSelf(ThingamajigsBlocks.CELL_MULTI_ANGLED_TRANSMITTER.get());
        this.dropSelf(ThingamajigsBlocks.CELL_MULTI_TRANSMITTER.get());
        this.dropSelf(ThingamajigsBlocks.CELL_TRANSMITTER.get());
        this.dropSelf(ThingamajigsBlocks.CHAINLINK_FENCE.get());
        this.dropSelf(ThingamajigsBlocks.CHANGE_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.CHARGED_VOLCANIC_STONE.get());
        this.dropSelf(ThingamajigsBlocks.CHECKBOARD_WOOL.get());
        this.add(ThingamajigsBlocks.CHEESE_BLOCK.get(), thing -> LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ThingamajigsBlocks.CHEESE_BLOCK.get()))
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(thing)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(CheeseBlock.FULLNESS, 0)))));
        this.dropSelf(ThingamajigsBlocks.CHEMICAL_TUBE.get());
        this.dropSelf(ThingamajigsBlocks.CHERRY_LANE.get());
        this.dropSelf(ThingamajigsBlocks.CHEVRON.get());
        this.dropSelf(ThingamajigsBlocks.CHILDREN.get());
        this.dropSelf(ThingamajigsBlocks.CHISELED_PANEL_DARKENED_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.CHISELED_PANEL_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.CHISELED_STONE_BRICK_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.CHISELED_TECHNO_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.CHRISTMAS_FIREPLACE.get());
        this.dropSelf(ThingamajigsBlocks.CHRISTMAS_LIGHTS_ALT.get());
        this.dropSelf(ThingamajigsBlocks.CHRISTMAS_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.CHRISTMAS_TREE.get());
        this.dropSelf(ThingamajigsBlocks.CHRISTMAS_WREATH.get());
        this.dropSelf(ThingamajigsBlocks.CIRCUITS.get());
        this.dropSelf(ThingamajigsBlocks.CLASSIC_TV.get());
        this.dropSelf(ThingamajigsBlocks.CLAW_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.CLOCK_RADIO.get());
        this.dropSelf(ThingamajigsBlocks.COFFEE_GRINDER.get());
        this.dropSelf(ThingamajigsBlocks.COFFEE_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.COFFIN.get());
        this.dropWhenSilkTouch(ThingamajigsBlocks.COLORED_GLASS.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_CONDIMENT_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_DRYER.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_JUICE_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_LIQUID_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_UTENCIL_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.COMMERCIAL_WASHER.get());
        this.dropSelf(ThingamajigsBlocks.COMPUTER_CONTROLS.get());
        this.dropSelf(ThingamajigsBlocks.CONCRETE_BARRIER.get());
        this.dropSelf(ThingamajigsBlocks.CONST_ROAD_CLOSED_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.CONVENIENCE_SHELF.get());
        this.dropSelf(ThingamajigsBlocks.COOKIE_JAR.get());
        this.dropSelf(ThingamajigsBlocks.COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.CORNER_COMPUTER_WM.get());
        this.dropSelf(ThingamajigsBlocks.CORNER_COMPUTER.get());
        this.dropSelf(ThingamajigsBlocks.COTTON_CANDY_MAKER.get());
        this.dropSelf(ThingamajigsBlocks.CRACKED_PANEL_STONE_BRICKS.get());

        this.add(ThingamajigsBlocks.CRACKED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.CRACKED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.CRACKED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.CRACKED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.SIDEWALK_CRACKED.get());

        this.dropSelf(ThingamajigsBlocks.CREEPER_PLUSHY.get());
        this.dropSelf(ThingamajigsBlocks.CRIB.get());
        this.dropSelf(ThingamajigsBlocks.CRIME_SCENE_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.CRIMSON_LANE.get());
        this.dropSelf(ThingamajigsBlocks.CROSS_GRAVESTONE.get());
        this.dropSelf(ThingamajigsBlocks.CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.CROSSING_AHEAD_F.get());
        this.dropSelf(ThingamajigsBlocks.CROSSING_AHEAD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.CROSSING_NO_GATES.get());
        this.dropSelf(ThingamajigsBlocks.CROSSING_SIGN_F.get());
        this.dropSelf(ThingamajigsBlocks.CROSSING_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.CROSSWALK_BUTTON.get());
        this.dropSelf(ThingamajigsBlocks.CRYO_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.CRYSTAL_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.CRYSTALINE_STONE.get());
        this.dropSelf(ThingamajigsBlocks.CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.CUP.get());
        this.dropOther(ThingamajigsBlocks.CYAN_BALLOON_BLOCK.get(),ThingamajigsItems.CYAN_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.CZECH_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.DANGER.get());
        this.dropSelf(ThingamajigsBlocks.DARK_CRYSTAL_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.DARK_DARKENED_STONE.get());
        this.dropSelf(ThingamajigsBlocks.DARK_FIREOUS_GLAZED_TERRACOTTA.get());
        this.dropSelf(ThingamajigsBlocks.DARK_OAK_LANE.get());
        this.dropSelf(ThingamajigsBlocks.DARKENED_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.DARKENED_STONE.get());
        this.dropSelf(ThingamajigsBlocks.DEAD_END.get());
        this.dropSelf(ThingamajigsBlocks.DEATH_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.DECORATIVE_PORTAL.get());
        this.dropSelf(ThingamajigsBlocks.DEER.get());
        this.dropSelf(ThingamajigsBlocks.DEHUMIDIFIER.get());
        this.dropSelf(ThingamajigsBlocks.DESCENT.get());
        this.dropSelf(ThingamajigsBlocks.DETOUR_ARROW_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.DETOUR_ARROW_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.DIAGONAL_TRACK_LEFTORRIGHT.get());
        this.dropSelf(ThingamajigsBlocks.DIAGONAL_TRACK_RIGHTORLEFT.get());
        this.dropSelf(ThingamajigsBlocks.DIAMOND_BOWLING_PIN.get());
        this.dropSelf(ThingamajigsBlocks.DIAMOND_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.DIP.get());
        this.dropSelf(ThingamajigsBlocks.DIRT_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.DISABLED_PARKING_SIGN_ALT_TWO.get());
        this.dropSelf(ThingamajigsBlocks.DISABLED_PARKING_SIGN_ALT.get());
        this.dropSelf(ThingamajigsBlocks.DISABLED_PARKING_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.DISHWASHER_WALL.get());
        this.dropSelf(ThingamajigsBlocks.DIVIDED_WAY.get());
        this.dropSelf(ThingamajigsBlocks.DIVIDER_ENDS.get());
        this.dropSelf(ThingamajigsBlocks.DIVIDER_START.get());
        this.dropSelf(ThingamajigsBlocks.DIVIDER.get());
        this.dropSelf(ThingamajigsBlocks.DIVING_BOARD.get());
        this.dropSelf(ThingamajigsBlocks.DO_NOT_ENTER.get());
        this.dropSelf(ThingamajigsBlocks.DOG_HOUSE.get());
        this.dropSelf(ThingamajigsBlocks.DOME_SECURITY_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.DOOR_BELL.get());
        this.dropSelf(ThingamajigsBlocks.DOUBLE_CORNER_WHITE_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.DOUBLE_CORNER_YELLOW_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.DOUBLE_WHITE_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.DOUBLE_YELLOW_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.DRYER.get());
        this.dropSelf(ThingamajigsBlocks.DUAL_ENDS.get());
        this.dropSelf(ThingamajigsBlocks.DUAL_RAILWAY_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.DUCK_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.DUMPSTER.get());
        this.dropSelf(ThingamajigsBlocks.DVD_COLLECTION.get());
        this.dropSelf(ThingamajigsBlocks.DVD_PLAYER.get());
        this.dropSelf(ThingamajigsBlocks.EATING_UTENCILS.get());
        this.dropSelf(ThingamajigsBlocks.EBELL_ONE.get());
        this.dropSelf(ThingamajigsBlocks.EBELL_TWO.get());
        this.dropSelf(ThingamajigsBlocks.EFE_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.ELDER.get());
        this.dropSelf(ThingamajigsBlocks.ENTER_AHEAD_HALF.get());
        this.dropSelf(ThingamajigsBlocks.EQUESTRIAN.get());
        this.dropSelf(ThingamajigsBlocks.EMERGENCY_SCENE_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.ESCALATOR.get());
        this.dropSelf(ThingamajigsBlocks.ESCALATOR_DOWN.get());
        this.dropSelf(ThingamajigsBlocks.EXEMPT_HALF.get());
        this.dropSelf(ThingamajigsBlocks.EXEMPT_YELLOW_HALF.get());
        this.dropSelf(ThingamajigsBlocks.EXIT_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.EXIT_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.EXPENSIVE_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.EXPERIENCE_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.EXPLORER_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.EXPOSED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.EXPOSED_RUNICSTONE_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.FALLING_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.FALLING_ROCKS.get());
        this.dropSelf(ThingamajigsBlocks.FAN_BLOCK_FAST.get());
        this.dropSelf(ThingamajigsBlocks.FAN_BLOCK_OFF.get());
        this.dropSelf(ThingamajigsBlocks.FAN_BLOCK_SPARK.get());
        this.dropSelf(ThingamajigsBlocks.FAN_BLOCK_ULTRASONIC.get());
        this.dropSelf(ThingamajigsBlocks.FAN_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.FANCY_QUARTZ_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.FANCY_SINK.get());
        this.dropSelf(ThingamajigsBlocks.FARMER_ORIGINAL.get());
        this.dropSelf(ThingamajigsBlocks.FAX_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.FEATURED_CORDLESS_PHONE.get());
        this.dropSelf(ThingamajigsBlocks.FEMALE_BATHROOM_SIGN.get());
        this.add(ThingamajigsBlocks.FESTIVE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.FESTIVE_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.FESTIVE_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.FESTIVE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.FILM_SECURITY_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.FINNISH_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.FIRE_DETECTOR.get());
        this.dropSelf(ThingamajigsBlocks.FIRE_ESCAPE_LADDER.get());
        this.dropSelf(ThingamajigsBlocks.FIRE_EXTINGUISHER.get());
        this.dropSelf(ThingamajigsBlocks.FIRE_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.FIRE_TRUCK.get());
        this.dropSelf(ThingamajigsBlocks.FIREOUS_GLAZED_TERRACOTTA.get());
        this.dropSelf(ThingamajigsBlocks.FIREWORKS_DISPLAY.get());
        this.dropSelf(ThingamajigsBlocks.FLAGGER_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.FLASK.get());
        this.dropSelf(ThingamajigsBlocks.FLOWERING_LILY_PAD.get());
        this.dropSelf(ThingamajigsBlocks.FOOD_DEHYDRATOR.get());
        this.dropSelf(ThingamajigsBlocks.FOOD_PROCESSOR.get());
        this.dropSelf(ThingamajigsBlocks.FOOSBALL_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.FORWARD_OR_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.FORWARD_OR_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.FOUR_WAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.FREEZER.get());
        this.dropSelf(ThingamajigsBlocks.FRENCH_BRICK.get());
        this.dropSelf(ThingamajigsBlocks.FRENCH_PRESS.get());
        this.dropSelf(ThingamajigsBlocks.FRIDGE.get());
        this.dropSelf(ThingamajigsBlocks.FRIER.get());
        this.dropSelf(ThingamajigsBlocks.FULL_POOP_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.GARDEN_GNOME.get());
        this.dropSelf(ThingamajigsBlocks.GATED_CROSSING.get());
        this.dropSelf(ThingamajigsBlocks.GARDEN_HOSE.get());
        this.dropSelf(ThingamajigsBlocks.GAS_CAN.get());
        this.dropSelf(ThingamajigsBlocks.GAS_HEATER.get());
        this.dropSelf(ThingamajigsBlocks.GAS_PUMP.get());
        this.dropSelf(ThingamajigsBlocks.GEARS_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.GENERAL_DIGITAL_PHONE.get());
        this.dropSelf(ThingamajigsBlocks.GENERAL_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.GENERATOR.get());
        this.dropSelf(ThingamajigsBlocks.GERMAN_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.GERMAN_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.GINGERBREAD_HOUSE.get());
        this.dropSelf(ThingamajigsBlocks.GIVE_WAY.get());
        this.add(ThingamajigsBlocks.GLOWING_CHEESE_BLOCK.get(), thing -> LootTable.lootTable().withPool(LootPool.lootPool()
                .add(LootItem.lootTableItem(ThingamajigsBlocks.GLOWING_CHEESE_BLOCK.get()))
                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(thing)
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(GlowingCheeseBlock.FULLNESS, 0)))));
        this.dropSelf(ThingamajigsBlocks.GLOWSTONE_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.GOLD_BOWLING_PIN.get());
        this.dropSelf(ThingamajigsBlocks.GOLD_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.GOLD_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.GOLF_CART_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.GRADIENT_DARKENED_STONE.get());
        this.dropSelf(ThingamajigsBlocks.GRAPHICS_CARD.get());
        this.dropSelf(ThingamajigsBlocks.GRATE.get());
        this.dropOther(ThingamajigsBlocks.GRAY_BALLOON_BLOCK.get(),ThingamajigsItems.GRAY_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_FAN.get());
        this.dropOther(ThingamajigsBlocks.GREEN_BALLOON_BLOCK.get(),ThingamajigsItems.GREEN_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.MINI_GREEN_BUILDING.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_CAUTION.get());
        this.dropOther(ThingamajigsBlocks.GREEN_HANGING_SIGN.get(),ThingamajigsItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GREEN_STANDING_SIGN.get(),ThingamajigsItems.GREEN_ROADWAY_SIGN_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_STOP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_TOME_BOOKSHELF.get());
        this.dropOther(ThingamajigsBlocks.GREEN_WALL_HANGING_SIGN.get(),ThingamajigsItems.GREEN_HANGING_ROADWAY_SIGN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GREEN_WALL_SIGN.get(),ThingamajigsItems.GREEN_ROADWAY_SIGN_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.GREY_MAILBOX.get());
        this.dropSelf(ThingamajigsBlocks.GRID_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.GUMBALL_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.HAMMER_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.HARD_DRIVE.get());
        this.dropSelf(ThingamajigsBlocks.HARDHAT_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.HAWK_SIGNAL_AHEAD.get());
        this.dropOther(ThingamajigsBlocks.HAWK_SIGNAL_FLASHING_RED.get(),
                ThingamajigsBlocks.HAWK_SIGNAL.get());
        this.dropOther(ThingamajigsBlocks.HAWK_SIGNAL_FLASHING_YELLOW.get(),
                ThingamajigsBlocks.HAWK_SIGNAL.get());
        this.dropOther(ThingamajigsBlocks.HAWK_SIGNAL_RED.get(),
                ThingamajigsBlocks.HAWK_SIGNAL.get());
        this.dropOther(ThingamajigsBlocks.HAWK_SIGNAL_YELLOW.get(),
                ThingamajigsBlocks.HAWK_SIGNAL.get());
        this.dropSelf(ThingamajigsBlocks.HAWK_SIGNAL.get());
        this.dropSelf(ThingamajigsBlocks.HEAD_CANDLE.get());
        this.dropSelf(ThingamajigsBlocks.HEART_MONITOR.get());
        this.dropSelf(ThingamajigsBlocks.HIGH_TRACK.get());
        this.dropSelf(ThingamajigsBlocks.HISTORIAN_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.HAZARDOUS_MATERIALS.get());
        this.dropSelf(ThingamajigsBlocks.HOLDER_POLE.get());
        this.dropSelf(ThingamajigsBlocks.HOME_BREAKER.get());
        this.dropSelf(ThingamajigsBlocks.HORIZONTAL_TRACK.get());
        this.dropSelf(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_1.get());
        this.dropSelf(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_2.get());
        this.dropSelf(ThingamajigsBlocks.HORIZONTAL_TRAFFIC_SIGNAL_3.get());
        this.dropSelf(ThingamajigsBlocks.HORN_FIRE_ALARM.get());
        this.dropSelf(ThingamajigsBlocks.HORSE_PROHIBITED.get());
        this.dropSelf(ThingamajigsBlocks.HOSPITAL_BED.get());
        this.dropSelf(ThingamajigsBlocks.HOSPITAL_COMPUTER.get());
        this.dropSelf(ThingamajigsBlocks.HOSPITAL_COVER.get());
        this.dropSelf(ThingamajigsBlocks.HOTDOG_ROTATOR.get());
        this.dropSelf(ThingamajigsBlocks.HOTTUB.get());
        this.dropSelf(ThingamajigsBlocks.HOV_ENDS.get());
        this.dropSelf(ThingamajigsBlocks.HOV_ONLY.get());
        this.dropSelf(ThingamajigsBlocks.HUMIDIFIER.get());
        this.dropSelf(ThingamajigsBlocks.HUMP_BRIDGE.get());
        this.dropSelf(ThingamajigsBlocks.ICE_CREAM_MAKER.get());
        this.dropSelf(ThingamajigsBlocks.ICECREAM_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.INDENTED_STONE.get());
        this.dropSelf(ThingamajigsBlocks.INSET_ATM.get());
        this.dropSelf(ThingamajigsBlocks.INSTANT_POT.get());
        this.dropSelf(ThingamajigsBlocks.INTERNET_JACK_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.INTERNET_MODEM.get());
        this.dropSelf(ThingamajigsBlocks.NEWER_INTERNET_ROUTER.get());
        this.dropSelf(ThingamajigsBlocks.INTERNET_ROUTER.get());
        this.dropSelf(ThingamajigsBlocks.INTERSTATE_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.INVERTED_CATEYE_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.IRON_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.IRONING_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.IV.get());
        this.dropSelf(ThingamajigsBlocks.JAPAN_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.JUICER.get());
        this.dropSelf(ThingamajigsBlocks.JUNGLE_LANE.get());
        this.dropSelf(ThingamajigsBlocks.KITCHEN_SINK.get());
        this.dropSelf(ThingamajigsBlocks.L_ONLY_POLE.get());
        this.dropSelf(ThingamajigsBlocks.L_POLE.get());
        this.dropSelf(ThingamajigsBlocks.LANES.get());
        this.dropSelf(ThingamajigsBlocks.LAVA_LAMP.get());
        this.dropSelf(ThingamajigsBlocks.LAWN_MOWER.get());
        this.dropSelf(ThingamajigsBlocks.LEFT_LANE_ENDS.get());
        this.dropSelf(ThingamajigsBlocks.LEFT_MERGES.get());
        this.dropSelf(ThingamajigsBlocks.LIBRARY_STOOL.get());
        this.dropOther(ThingamajigsBlocks.LIGHT_BLUE_BALLOON_BLOCK.get(),ThingamajigsItems.LIGHT_BLUE_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_CAUTION.get());
        this.dropOther(ThingamajigsBlocks.LIGHT_GRAY_BALLOON_BLOCK.get(),ThingamajigsItems.LIGHT_GRAY_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_POLE.get());
        this.dropSelf(ThingamajigsBlocks.LIGHTED_CHRISTMAS_TREE.get());
        this.dropSelf(ThingamajigsBlocks.LIGHTED_DEER.get());
        this.dropSelf(ThingamajigsBlocks.LIGHTUP_MACHINE.get());
        this.dropOther(ThingamajigsBlocks.LIME_BALLOON_BLOCK.get(),ThingamajigsItems.LIME_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.LIME_BOWLING_BALL.get());

        this.add(ThingamajigsBlocks.LOCKABLE_DOOR.get(),
                LootTable.lootTable().withPool(applyExplosionCondition(ThingamajigsBlocks.LOCKABLE_DOOR.get(),LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(ThingamajigsBlocks.LOCKABLE_DOOR.get())
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.LOCKABLE_DOOR.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER))
                                ).when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.LOCKABLE_DOOR.get())
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LockableDoor.LOCKED,false)))
                        ))));

        this.dropSelf(ThingamajigsBlocks.LOCKER.get());
        this.dropSelf(ThingamajigsBlocks.LOUD_FIRE_ALARM.get());
        this.dropSelf(ThingamajigsBlocks.LOVE_COUCH.get());
        this.dropSelf(ThingamajigsBlocks.LOVE_SEAT_WOOL.get());
        this.dropSelf(ThingamajigsBlocks.LOVE_SEAT.get());
        this.dropSelf(ThingamajigsBlocks.LOW_POWER_LINES.get());
        this.dropOther(ThingamajigsBlocks.MAGENTA_BALLOON_BLOCK.get(),ThingamajigsItems.MAGENTA_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.MAILBOX.get());
        this.dropSelf(ThingamajigsBlocks.MALE_BATHROOM_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.MANGROVE_LANE.get());

        this.add(ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.ASPHALT_MEDIOCRE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.ASPHALT_MEDIOCRE.get());

        this.dropSelf(ThingamajigsBlocks.MERGES.get());
        this.dropSelf(ThingamajigsBlocks.METAL_SCAFFOLDING.get());
        this.dropSelf(ThingamajigsBlocks.METAL_VENTS.get());
        this.dropSelf(ThingamajigsBlocks.METALLIC_DOOR_BELL.get());
        this.add(ThingamajigsBlocks.METALLIC_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.METALLIC_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.METALLIC_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.METALLIC_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.MICROSCOPE.get());
        this.dropSelf(ThingamajigsBlocks.CELL_MICROWAVE_TRANSMITTER.get());
        this.dropSelf(ThingamajigsBlocks.MICROWAVE.get());
        this.dropSelf(ThingamajigsBlocks.MINECART_CROSSING.get());
        this.dropSelf(ThingamajigsBlocks.MINECARTS.get());
        this.dropSelf(ThingamajigsBlocks.MINI_FRIDGE.get());
        this.dropSelf(ThingamajigsBlocks.MINI_RAIL.get());
        this.dropSelf(ThingamajigsBlocks.MINI_ROAD.get());
        this.dropSelf(ThingamajigsBlocks.MINIGOLF_FLAG.get());
        this.dropSelf(ThingamajigsBlocks.MINIGOLF_GRASS_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.MINIGOLF_HOLE.get());
        this.dropSelf(ThingamajigsBlocks.MIRROR.get());
        this.dropSelf(ThingamajigsBlocks.MOBILE_PHONE.get());
        this.dropSelf(ThingamajigsBlocks.MON_FRI_HALF.get());
        this.dropSelf(ThingamajigsBlocks.MOONSTONE_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.MOSSY_PANEL_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.MOTOR_VEHICLES_PROHIBITED.get());
        this.dropSelf(ThingamajigsBlocks.MOTORCYCLES_PROHIBITED.get());
        this.dropSelf(ThingamajigsBlocks.MOVING_GEARS_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.MRPUPPY.get());
        this.dropSelf(ThingamajigsBlocks.MULTI_TO_SINGLE.get());
        this.dropSelf(ThingamajigsBlocks.MYSTERIOUS_ONE_COUCH.get());
        this.dropSelf(ThingamajigsBlocks.MYSTERIOUS_ONE_WOOL.get());
        this.dropSelf(ThingamajigsBlocks.NARROW_BOTH.get());
        this.dropSelf(ThingamajigsBlocks.NARROW_BRIDGE.get());
        this.dropSelf(ThingamajigsBlocks.NARROW_SIDES.get());
        this.dropSelf(ThingamajigsBlocks.NEON_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.NETHER_BRICK_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.NETHER_BRICK_TABLE.get());
        this.dropWhenSilkTouch(ThingamajigsBlocks.NETHER_CHISELED_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.NETHERISH_STONE.get());
        this.dropSelf(ThingamajigsBlocks.NO_HAZARDOUS_MATERIALS.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_SYMBOL_1.get());
        this.dropSelf(ThingamajigsBlocks.NO_LEFT_TURN.get());
        this.dropSelf(ThingamajigsBlocks.NO_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.NO_OVERTAKING.get());
        this.dropSelf(ThingamajigsBlocks.NO_PARKING.get());
        this.dropSelf(ThingamajigsBlocks.NO_PASSING_ZONE_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.NO_PEDS.get());
        this.dropSelf(ThingamajigsBlocks.NO_RIGHT_TURN.get());
        this.dropSelf(ThingamajigsBlocks.NO_STOPPING.get());
        this.dropSelf(ThingamajigsBlocks.NO_STRAIGHT.get());
        this.dropSelf(ThingamajigsBlocks.NO_U_TURN.get());
        this.dropSelf(ThingamajigsBlocks.NO_WAITING.get());
        this.dropSelf(ThingamajigsBlocks.NOENTRY_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.NORTH_POLE.get());
        this.dropSelf(ThingamajigsBlocks.NOT_QUITE_MENGER.get());
        this.dropSelf(ThingamajigsBlocks.NUTCRACKER.get());
        this.dropSelf(ThingamajigsBlocks.OAK_LANE.get());
        this.dropSelf(ThingamajigsBlocks.OFFICE_PHONE.get());

        this.add(ThingamajigsBlocks.ASPHALT_OK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.ASPHALT_OK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.ASPHALT_OK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.ASPHALT_OK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.ASPHALT_OK.get());

        this.add(ThingamajigsBlocks.ASPHALT_OLD_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.ASPHALT_OLD_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.ASPHALT_OLD_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.ASPHALT_OLD_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.ASPHALT_OLD.get());

        this.dropSelf(ThingamajigsBlocks.OLD_DOOR_BELL.get());
        this.dropSelf(ThingamajigsBlocks.OLD_FLAT_COMPUTER.get());
        this.dropSelf(ThingamajigsBlocks.OLD_MICROWAVE_TRANSMITTER.get());
        this.dropSelf(ThingamajigsBlocks.OLD_PC.get());
        this.dropSelf(ThingamajigsBlocks.OLD_TEAL_WOOL.get());
        this.dropSelf(ThingamajigsBlocks.OLD_WOODEN_PHONE.get());
        this.dropSelf(ThingamajigsBlocks.ONE_WAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.ONE_WAY_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.ONE_WAY_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.ONLY_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.ONLY_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.ONLY_THRU_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.ONLY_THRU_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.ONLY_UP.get());
        this.dropSelf(ThingamajigsBlocks.OPEN_BRIDGE.get());
        this.dropSelf(ThingamajigsBlocks.OPEN_SIGN_ALT_TWO.get());
        this.dropSelf(ThingamajigsBlocks.OPEN_SIGN_ALT.get());
        this.dropSelf(ThingamajigsBlocks.OPEN_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.OPERATION_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.OPERATION_TOOLS.get());
        this.dropOther(ThingamajigsBlocks.ORANGE_BALLOON_BLOCK.get(),ThingamajigsItems.ORANGE_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.ORANGE_CAUTION.get());
        this.dropSelf(ThingamajigsBlocks.OUTLET_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.OVEN.get());
        this.dropSelf(ThingamajigsBlocks.OXIDIZED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.OXYGEN_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.PANEL_DARKENED_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.PANEL_STONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.PANEL_STONE.get());
        this.dropSelf(ThingamajigsBlocks.PANINI_MAKER.get());
        this.dropSelf(ThingamajigsBlocks.PAPER_FLOWER_WALL_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.PAPER_TOWEL.get());
        this.dropSelf(ThingamajigsBlocks.PAPER_WALL_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.PARKING_PERMITTED_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.PARTICULAR_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.PAVEMENT_ENDS_OLD.get());
        this.dropSelf(ThingamajigsBlocks.PAYPHONE_SEETHROUGH.get());
        this.dropSelf(ThingamajigsBlocks.PAYPHONE.get());
        this.dropSelf(ThingamajigsBlocks.PC_CONTROLS.get());
        this.dropSelf(ThingamajigsBlocks.OLD_PC_MONITOR.get());
        this.dropSelf(ThingamajigsBlocks.MODERN_PC_MONITOR.get());
        this.dropSelf(ThingamajigsBlocks.PED_DISABLED.get());
        this.dropSelf(ThingamajigsBlocks.PED_FLASHERS.get());
        this.dropSelf(ThingamajigsBlocks.PED_SIGNAL_MAN_1.get());
        this.dropSelf(ThingamajigsBlocks.PED_SIGNAL_WORDED.get());
        this.dropSelf(ThingamajigsBlocks.PED_SIGNAL_SYMBOLS.get());
        this.dropSelf(ThingamajigsBlocks.PEDS_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.PEDS_PROHIBITED.get());
        this.dropSelf(ThingamajigsBlocks.PHONE_SWITCHER.get());
        this.dropSelf(ThingamajigsBlocks.PICNIC_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.PILLAGER_SCENE.get());
        this.dropSelf(ThingamajigsBlocks.PIN_SETTER.get());
        this.dropSelf(ThingamajigsBlocks.PINBALL_MACHINE.get());
        this.dropOther(ThingamajigsBlocks.PINK_BALLOON_BLOCK.get(),ThingamajigsItems.PINK_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.PINK_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.PIZZA_BOX.get());
        this.dropSelf(ThingamajigsBlocks.PLACARD_GRAVESTONE.get());
        this.dropSelf(ThingamajigsBlocks.PLANT_ROAD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.PLATE.get());
        this.dropSelf(ThingamajigsBlocks.PLAYGROUND.get());
        this.dropSelf(ThingamajigsBlocks.PLUCK_DOOR_BELL.get());
        this.dropSelf(ThingamajigsBlocks.PLUS_POLE.get());
        this.dropSelf(ThingamajigsBlocks.PLUS.get());
        this.dropSelf(ThingamajigsBlocks.POISON_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.POOP_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.POOP.get());
        this.dropOther(ThingamajigsBlocks.POOPOO.get(),ThingamajigsBlocks.POOP.get());
        this.dropSelf(ThingamajigsBlocks.POOPSHELF.get());
        this.dropSelf(ThingamajigsBlocks.POPCORN_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.PORTA_POTTY.get());
        this.dropSelf(ThingamajigsBlocks.PORTABLE_DISH_WASHER.get());
        this.dropSelf(ThingamajigsBlocks.POTION_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.POWDER_KEG.get());
        this.dropSelf(ThingamajigsBlocks.PRESENT_PILE.get());
        this.dropSelf(ThingamajigsBlocks.PRINTER.get());
        this.dropSelf(ThingamajigsBlocks.PRISMARINE_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.PRISMARINE_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.PROFESSIONAL_TV_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.PROJECTOR.get());
        this.dropSelf(ThingamajigsBlocks.PUNCHING_BAG.get());
        this.dropSelf(ThingamajigsBlocks.PURIFYING_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.PURIFYING_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_ACTIVATOR_RAIL.get());
        this.dropOther(ThingamajigsBlocks.PURPLE_BALLOON_BLOCK.get(),ThingamajigsItems.PURPLE_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_DETECTOR_RAIL.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_POWERED_RAIL.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_RAIL.get());
        this.dropSelf(ThingamajigsBlocks.PURPUR_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.PURPUR_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.QUARTZ_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.QUARTZ_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.RADIATION_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.RADIOACTIVE_BARREL.get());
        this.dropSelf(ThingamajigsBlocks.RADIOWAVES_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.RAILROAD_CROSSING_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.RAILROAD_CROSSING.get());
        this.dropSelf(ThingamajigsBlocks.BRITISH_RAILWAY_ALARM.get());
        this.dropOther(ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS_AMBER.get(),
                ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get());
        this.dropOther(ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS_ON.get(),
                ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.BRITISH_RAILWAY_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.REBAR_CONCRETE_BARRIER.get());
        this.dropSelf(ThingamajigsBlocks.RECTANGLE_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.RECYCLE_BIN.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_ALLWAY_STOP_BEACON.get());
        this.dropOther(ThingamajigsBlocks.RED_BALLOON_BLOCK.get(),ThingamajigsItems.RED_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.RED_BEACON.get());
        this.dropSelf(ThingamajigsBlocks.RED_BOWLING_PIN.get());
        this.dropSelf(ThingamajigsBlocks.MINI_RED_BUILDING.get());
        this.dropSelf(ThingamajigsBlocks.RED_CAUTION.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_RED_FLASH.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get());
        this.dropSelf(ThingamajigsBlocks.RED_SODA_MACHINE.get());
        this.dropOther(ThingamajigsBlocks.RED_STANDING_SIGN.get(),ThingamajigsItems.RED_ROADWAY_SIGN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.RED_WALL_SIGN.get(),ThingamajigsItems.RED_ROADWAY_SIGN_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.RED_TOME_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.RED_VENDING_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.REFRESHMENT_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.REINDEER_PLUSHY.get());
        this.dropSelf(ThingamajigsBlocks.REINDEER_WALL_HEAD.get());
        this.dropSelf(ThingamajigsBlocks.REINFORCED_CONCRETE_BARRIER.get());
        this.dropWhenSilkTouch(ThingamajigsBlocks.REINFORCED_GLASS.get());
        this.dropSelf(ThingamajigsBlocks.RESERVED_DISABLED_PARKING_ALT.get());
        this.dropSelf(ThingamajigsBlocks.RESERVED_DISABLED_PARKING.get());
        this.dropSelf(ThingamajigsBlocks.RGB_PC_CONTROLS.get());
        this.dropSelf(ThingamajigsBlocks.RICE_COOKER.get());
        this.dropSelf(ThingamajigsBlocks.RIGHT_LANE_ENDS.get());
        this.dropSelf(ThingamajigsBlocks.RIGHT_MERGES.get());
        this.dropSelf(ThingamajigsBlocks.RIVERBANK.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARREL.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_BRIDGE_CLOSED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_BRIDGE_THRU_CLOSED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_CLOSED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_LIGHTED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_SMALL_LIGHTED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_SMALL.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_BARRIER_THRU_CLOSED.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_CHANNELIZER.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_COVER.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_CROSSES.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_PANEL_COVER.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_PANEL.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_TWISTY_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_TWISTY_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_WORK_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.ROAD_WORK.get());
        this.dropSelf(ThingamajigsBlocks.ROAST_TURKEY.get());
        this.dropSelf(ThingamajigsBlocks.ROBOT_SECURITY_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.ROCKER_SWITCH.get());
        this.dropSelf(ThingamajigsBlocks.ROUNDABOUT_OLD.get());
        this.dropSelf(ThingamajigsBlocks.ROUNDABOUT_UK.get());
        this.dropSelf(ThingamajigsBlocks.ROUNDABOUT.get());
        this.dropSelf(ThingamajigsBlocks.RR_AHEAD_OLD.get());
        this.dropSelf(ThingamajigsBlocks.RR_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.RAILROAD_CROSSING_BLOCKER.get());
        this.dropSelf(ThingamajigsBlocks.RR_CANTILEVER_END.get());
        this.dropSelf(ThingamajigsBlocks.RR_CANTILEVER_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.RR_CANTILEVER.get());
        // rubber wood
        this.dropSelf(ThingamajigsBlocks.RUBBER_LOG.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_PLANKS.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_SAPLING.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_BUTTON.get());
        this.add(ThingamajigsBlocks.RUBBER_WOOD_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.RUBBER_WOOD_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.RUBBER_WOOD_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.RUBBER_WOOD_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_FENCE_GATE.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_FENCE.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_PRESSURE_PLATE.get());

        this.add(ThingamajigsBlocks.RUBBER_WOOD_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.RUBBER_WOOD_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.RUBBER_WOOD_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.RUBBER_WOOD_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_STAIRS.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD_TRAPDOOR.get());
        this.dropSelf(ThingamajigsBlocks.RUBBER_WOOD.get());
        this.dropSelf(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get());
        this.dropSelf(ThingamajigsBlocks.STRIPPED_RUBBER_WOOD.get());
        //
        this.dropSelf(ThingamajigsBlocks.RUNICSTONE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.RUNICSTONE_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.SALT_TANK.get());
        this.dropSelf(ThingamajigsBlocks.SAND_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.SANDSTONE_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.SANTA_INFLATABLE.get());
        this.dropSelf(ThingamajigsBlocks.SANTA_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.SATELLITE_DISH.get());
        this.dropSelf(ThingamajigsBlocks.SAT_SUN_HALF.get());
        this.dropSelf(ThingamajigsBlocks.SCARY_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.SCHOOL_DESK.get());
        this.dropSelf(ThingamajigsBlocks.SCHOOL_HALF.get());
        this.dropSelf(ThingamajigsBlocks.SCHOOL_SPEED_LIMIT_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.SCHOOL_Y_HALF.get());
        this.dropSelf(ThingamajigsBlocks.SCRAP_PANELS.get());
        this.add(ThingamajigsBlocks.SCREEN_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.SCREEN_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.SCREEN_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.SCREEN_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.SCREEN.get());
        this.dropSelf(ThingamajigsBlocks.SCROLLING_YELLOW_CAUTION.get());
        this.dropSelf(ThingamajigsBlocks.SCULK_CHAIN.get());
        this.dropSelf(ThingamajigsBlocks.SCULK_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.SCULK_LANTERN.get());
        this.dropSelf(ThingamajigsBlocks.SCULK_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.SEATBELT.get());

        this.add(ThingamajigsBlocks.SECTIONED_SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.SECTIONED_SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.SECTIONED_SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.SECTIONED_SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.SIDEWALK_SECTIONED.get());

        this.dropSelf(ThingamajigsBlocks.SECURE_SECURITY_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.SERVER_RACK.get());
        this.dropSelf(ThingamajigsBlocks.SEWING_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.SHOCK_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.SHOP_VACUUM.get());
        this.dropSelf(ThingamajigsBlocks.SHOPPING_BASKET_PILE.get());
        this.dropSelf(ThingamajigsBlocks.SHOPPING_BASKET.get());
        this.dropSelf(ThingamajigsBlocks.SHOPPING_CART_MOVER.get());
        this.dropSelf(ThingamajigsBlocks.SHOPPING_CART.get());
        this.dropSelf(ThingamajigsBlocks.SHOULDER_DROP_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.SHOWER_HANDLES.get());
        this.dropSelf(ThingamajigsBlocks.SHOWER_HEAD.get());
        this.dropSelf(ThingamajigsBlocks.SIDE_WIND.get());

        this.add(ThingamajigsBlocks.SIDEWALK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.SIDEWALK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.SIDEWALK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.SIDEWALK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.dropSelf(ThingamajigsBlocks.SIDEWALK.get());

        this.dropSelf(ThingamajigsBlocks.SIGNAL_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.SINGLE_TO_MULTI.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_50.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_40.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_30.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_20.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_10.get());
        this.dropSelf(ThingamajigsBlocks.SLEIGH.get());
        this.dropSelf(ThingamajigsBlocks.SLIPPERY_WHEN_WET.get());
        this.dropSelf(ThingamajigsBlocks.SLIPPERY.get());
        this.dropSelf(ThingamajigsBlocks.SLOW_COOKER.get());
        this.dropSelf(ThingamajigsBlocks.SLOW_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.SLUDGE_CONVERTER.get());
        this.dropSelf(ThingamajigsBlocks.SLUSHY_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.SMALL_CHRISTMAS_TREE.get());
        this.dropSelf(ThingamajigsBlocks.SMALL_SINK.get());
        this.dropSelf(ThingamajigsBlocks.SMARTPHONE.get());
        this.dropSelf(ThingamajigsBlocks.SMILEY_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.SMOKER_GRILL.get());

        this.add(ThingamajigsBlocks.SNOWMAN_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.SNOWMAN_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.SNOWMAN_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.SNOWMAN_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.SNOWMAN_PLUSHY.get());
        this.dropSelf(ThingamajigsBlocks.SNOWMAN.get());
        this.dropSelf(ThingamajigsBlocks.SOFT_VERGE.get());
        this.dropSelf(ThingamajigsBlocks.SOLAR_PANEL.get());
        this.dropSelf(ThingamajigsBlocks.SPACE_HEATER.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_10.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_20.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_30.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_40.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_50.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_HUMP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.SPEED_LIMIT_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.SPEEDING_WORKERS_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.SPOOKY_STONE.get());
        this.dropOther(ThingamajigsBlocks.SPRING.get(),ThingamajigsItems.SPRING_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.SPRUCE_LANE.get());
        this.dropSelf(ThingamajigsBlocks.STAND_MIXER.get());
        this.dropSelf(ThingamajigsBlocks.STANDARD_GRAVESTONE.get());
        this.dropSelf(ThingamajigsBlocks.STAY_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.STAY_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.SMALL_TO_BIG_LEFT.get());
        this.dropSelf(ThingamajigsBlocks.SMALL_TO_BIG_RIGHT.get());
        this.dropSelf(ThingamajigsBlocks.STEAM_CLEANER.get());
        this.dropSelf(ThingamajigsBlocks.STEVE_PLUSHY.get());
        this.dropSelf(ThingamajigsBlocks.STONE_BRICK_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.STONE_BRICK_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.STONE_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.STONE_CULVERT.get());

        this.add(ThingamajigsBlocks.STONE_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.STONE_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.STONE_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.STONE_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.STONE_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.STONE_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.STOP_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.STOP_GATE.get());
        this.dropSelf(ThingamajigsBlocks.STOP_HERE_ON_RED.get());
        this.dropSelf(ThingamajigsBlocks.STOP_OR_GIVEWAY_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.STOP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.STORE_FLOORING.get());
        this.dropSelf(ThingamajigsBlocks.STORE_FREEZER.get());
        this.dropSelf(ThingamajigsBlocks.STORE_NUMBER_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.STORE_SHELF.get());
        this.dropSelf(ThingamajigsBlocks.STORE_STAND.get());
        this.dropSelf(ThingamajigsBlocks.STOVE_HOOD.get());
        this.dropSelf(ThingamajigsBlocks.STOVE.get());
        this.dropSelf(ThingamajigsBlocks.STRAIGHT_HORIZONTAL_POLE.get());
        this.dropSelf(ThingamajigsBlocks.STRAIGHT_POLE.get());
        this.dropSelf(ThingamajigsBlocks.STUDIO_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.SUNSTONE_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.SWIRLY_TECHNO_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.T_HORZ_ONLY_POLE.get());
        this.dropSelf(ThingamajigsBlocks.T_POLE_B.get());
        this.dropSelf(ThingamajigsBlocks.T_POLE_C.get());
        this.dropSelf(ThingamajigsBlocks.T_POLE.get());
        this.dropSelf(ThingamajigsBlocks.T_US_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.TALL_LAMP.get());
        this.dropSelf(ThingamajigsBlocks.MINI_TALL_YELLOW_BUILDING.get());
        this.dropSelf(ThingamajigsBlocks.TEACHING_BOARD.get());
        this.dropOther(ThingamajigsBlocks.TEAL_BALLOON_BLOCK.get(),ThingamajigsItems.TEAL_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.TECHNO_CORE.get());
        this.dropSelf(ThingamajigsBlocks.TECHNO_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.TENTH_ANNIVERSARY_CAKE.get());
        this.dropSelf(ThingamajigsBlocks.TERRACOTTA_CULVERT.get());
        this.dropSelf(ThingamajigsBlocks.TFF_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.THEATER_SEAT_CONTINUOUS.get());
        this.dropSelf(ThingamajigsBlocks.THEATER_SEAT.get());
        this.dropSelf(ThingamajigsBlocks.THERMOMETER.get());
        this.dropSelf(ThingamajigsBlocks.THINGAMAJIG_STATE_CELL.get());
        this.dropSelf(ThingamajigsBlocks.THINK_BEFORE_YOU_THROW.get());
        this.dropSelf(ThingamajigsBlocks.THREE_WAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.THREE_WAY_POLE.get());
        this.dropSelf(ThingamajigsBlocks.TICKET_TELLER_WINDOW.get());
        this.dropSelf(ThingamajigsBlocks.TINY_CROSSING.get());
        this.dropSelf(ThingamajigsBlocks.TISSUE_BOX.get());
        this.dropSelf(ThingamajigsBlocks.TL_CONNECTOR.get());
        this.dropSelf(ThingamajigsBlocks.TNT_THREAT.get());
        this.dropSelf(ThingamajigsBlocks.TO_ROUTE_HALF.get());
        this.dropSelf(ThingamajigsBlocks.TO_TOLL_HALF.get());
        this.dropSelf(ThingamajigsBlocks.TOASTER_OVEN.get());
        this.dropSelf(ThingamajigsBlocks.TOASTER.get());
        this.dropSelf(ThingamajigsBlocks.TOILET_PAPER.get());
        this.dropSelf(ThingamajigsBlocks.TOILET.get());
        this.dropSelf(ThingamajigsBlocks.TOLL_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.TOOL_STATION.get());
        this.dropSelf(ThingamajigsBlocks.TOY_BOX.get());
        this.dropSelf(ThingamajigsBlocks.TRACK_LEFT_SIDE.get());
        this.dropSelf(ThingamajigsBlocks.TRACK_RIGHT_SIDE.get());
        this.dropSelf(ThingamajigsBlocks.TRACTOR.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_CONTROL_BOX.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_LIGHT.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_DOGHOUSE_1.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_DOGHOUSE_2.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_1.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_2.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_3.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_NORMAL_4.get());
        this.dropSelf(ThingamajigsBlocks.TRAM_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.TRANSPARENT_FAN_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.TRANSPARENT_FAST_FAN_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.TRANSPARENT_OFF_FAN_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.TRASH_CAN.get());
        this.dropSelf(ThingamajigsBlocks.TRI_CANDLE_HOLDER_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.TRI_POLE_B.get());
        this.dropSelf(ThingamajigsBlocks.TRI_POLE.get());
        this.dropSelf(ThingamajigsBlocks.TRI_RAILWAY_LIGHTS.get());
        this.dropSelf(ThingamajigsBlocks.TRIPLE_LILY_PAD.get());
        this.dropSelf(ThingamajigsBlocks.TRUCK_SLOPE.get());
        this.dropSelf(ThingamajigsBlocks.TRUCK.get());
        this.dropSelf(ThingamajigsBlocks.TRY.get());
        this.dropSelf(ThingamajigsBlocks.TUNNEL.get());
        this.dropSelf(ThingamajigsBlocks.TV.get());
        this.dropSelf(ThingamajigsBlocks.TWAS_DIP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.TWO_WAY_HALF.get());
        this.dropSelf(ThingamajigsBlocks.TWO_WAY.get());
        this.dropSelf(ThingamajigsBlocks.ULTRA_HD_TV.get());
        this.dropSelf(ThingamajigsBlocks.UK_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.UK_STOP_ON_RED.get());
        this.dropSelf(ThingamajigsBlocks.UNGROUNDED_US_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.UNEVEN.get());
        this.dropSelf(ThingamajigsBlocks.US_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.USB_OUTLET.get());
        this.dropSelf(ThingamajigsBlocks.UTIL_WORK_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.STANDING_VACUUM.get());
        this.dropSelf(ThingamajigsBlocks.VELVET_ROPE_FENCE.get());
        this.dropSelf(ThingamajigsBlocks.VERTICAL_AXIS_POLE.get());
        this.dropSelf(ThingamajigsBlocks.VERTICAL_POLE_REDSTONE.get());
        this.dropSelf(ThingamajigsBlocks.VERTICAL_REDSTONE_SIDEWALK.get());
        this.dropSelf(ThingamajigsBlocks.VERTICAL_T_POLE.get());
        this.dropSelf(ThingamajigsBlocks.VERTICAL_TRACK.get());
        this.dropSelf(ThingamajigsBlocks.VHS_COLLECTION.get());
        this.dropSelf(ThingamajigsBlocks.VHS_PLAYER.get());
        this.dropSelf(ThingamajigsBlocks.VIDEO_CAMERA.get());
        this.dropSelf(ThingamajigsBlocks.VOLCANIC_STONE.get());
        this.dropSelf(ThingamajigsBlocks.WACK_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.WAFFLE_IRON.get());
        this.dropSelf(ThingamajigsBlocks.WARDEN_TROPHY.get());
        this.dropSelf(ThingamajigsBlocks.WASHER.get());
        this.dropSelf(ThingamajigsBlocks.WATER_BOTTLE_PACK.get());
        this.dropSelf(ThingamajigsBlocks.WATER_COURSE.get());
        this.dropSelf(ThingamajigsBlocks.WATER_FOUNTAIN.get());
        this.dropSelf(ThingamajigsBlocks.WATER_SLIDE.get());
        this.dropSelf(ThingamajigsBlocks.WATER_SOFTENER.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.WEATHERED_COPPER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.WHEELBARROW.get());
        this.dropSelf(ThingamajigsBlocks.WHEN_FLASHING_HALF.get());
        this.dropSelf(ThingamajigsBlocks.WHITE_FAN.get());
        this.dropSelf(ThingamajigsBlocks.WHITE_PARKING_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.WHITE_TELEPHONE.get());
        this.add(ThingamajigsBlocks.WHITE_WOOD_DOOR.get(),
                LootTable.lootTable()
                        .withPool(
                                this.applyExplosionCondition(
                                        ThingamajigsBlocks.WHITE_WOOD_DOOR.get(),
                                        LootPool.lootPool()
                                                .setRolls(ConstantValue.exactly(1.0F))
                                                .add(
                                                        LootItem.lootTableItem(ThingamajigsBlocks.WHITE_WOOD_DOOR.get())
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_WOOD_DOOR.get())
                                                                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(DoorBlock.HALF,DoubleBlockHalf.LOWER))
                                                                )
                                                )
                                )
                        ));
        this.dropSelf(ThingamajigsBlocks.WIFI_ROUTER.get());
        this.dropSelf(ThingamajigsBlocks.WINE_BOTTLE.get());
        this.dropSelf(ThingamajigsBlocks.WOOD_CAR.get());
        this.dropSelf(ThingamajigsBlocks.WOOD_DUCK.get());
        this.dropSelf(ThingamajigsBlocks.WORKERS_HAZARD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.WORKERS_PRESENT.get());
        this.dropSelf(ThingamajigsBlocks.WRONG_WAY.get());
        this.dropOther(ThingamajigsBlocks.YELLOW_BALLOON_BLOCK.get(), ThingamajigsItems.YELLOW_BALLOON_BLOCK_ITEM.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_BEACON.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_BOWLING_BALL.get());
        this.dropSelf(ThingamajigsBlocks.MINI_YELLOW_BUILDING.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_CAUTION.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT.get());
        this.dropSelf(ThingamajigsBlocks.TRAFFIC_SIGNAL_YELLOW_FLASH.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_STOP_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_TOME_BOOKSHELF.get());
        this.dropSelf(ThingamajigsBlocks.YIELD_AHEAD.get());
        this.dropSelf(ThingamajigsBlocks.YIELD_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.YOGURT_MAKER.get());
        this.dropSelf(ThingamajigsBlocks.ZEBRA.get());
        this.dropSelf(ThingamajigsBlocks.ZOMBIE_PLUSHIE.get());

        this.dropSelf(ThingamajigsBlocks.BIRCH_LANE.get());
        this.dropSelf(ThingamajigsBlocks.WARPED_LANE.get());

        this.dropSelf(ThingamajigsBlocks.GOBO_LIGHT.get());
        this.dropSelf(ThingamajigsBlocks.TURNTABLE.get());
        this.dropSelf(ThingamajigsBlocks.ITEM_DISPLAY_BLOCK.get());

        this.dropSelf(ThingamajigsBlocks.GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_GRAY_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.BROWN_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.RED_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.ORANGE_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.LIME_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.CYAN_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.MAGENTA_GLOW_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.PINK_GLOW_BLOCK.get());

        this.dropSelf(ThingamajigsBlocks.GRAY_SCREEN.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_SCREEN.get());

        this.dropSelf(ThingamajigsBlocks.RUBBER_LANE.get());

        this.add(ThingamajigsBlocks.BRAMBLE.get(),(sup) -> createShearsDispatchTable(sup,
                this.applyExplosionDecay(sup,
                        LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(
                                        UniformGenerator.between(0.0F, 1.0F))))));

        this.dropPottedContents(ThingamajigsBlocks.POTTED_BRAMBLE.get());

        this.dropSelf(ThingamajigsBlocks.DJ_LASER_LIGHT.get());
        this.dropSelf(ThingamajigsBlocks.CONVEYOR_BELT.get());
        this.dropSelf(ThingamajigsBlocks.ANALOG_CLOCK.get());

        // fire hydrants
        this.dropSelf(ThingamajigsBlocks.RED_FIRE_HYDRANT.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_FIRE_HYDRANT.get());
        this.dropSelf(ThingamajigsBlocks.SILVER_FIRE_HYDRANT.get());

        this.dropSelf(ThingamajigsBlocks.PARKING_METER.get());

        this.dropSelf(ThingamajigsBlocks.WAXED_COPPER_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_EXPOSED_COPPER_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_WEATHERED_COPPER_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.WAXED_OXIDIZED_COPPER_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.IRON_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.DIAMOND_TABLE.get());

        this.dropSelf(ThingamajigsBlocks.DOOR_BLOCKADE.get());
        this.dropSelf(ThingamajigsBlocks.WINDOW_BLOCKADE.get());

        this.dropSelf(ThingamajigsBlocks.CINDER_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.CINDER_BLOCK_SMALL.get());

        this.add(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.CINDER_BLOCK_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(ThingamajigsBlocks.I_BEAM.get());
        this.dropSelf(ThingamajigsBlocks.STEEL_HOARDING.get());
        this.dropSelf(ThingamajigsBlocks.STEEL.get());
        this.dropSelf(ThingamajigsBlocks.CONCRETE.get());
        this.dropSelf(ThingamajigsBlocks.CONCRETE_BRICKS.get());
        this.dropSelf(ThingamajigsBlocks.COBBLED_CONCRETE.get());
        this.dropSelf(ThingamajigsBlocks.LADDER_RAILING.get());
        this.dropSelf(ThingamajigsBlocks.CURVED_RAILING.get());
        this.dropSelf(ThingamajigsBlocks.RED_PATHWAY.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_PATHWAY.get());
        this.dropSelf(ThingamajigsBlocks.CONCRETE_STAIRS.get());

        this.add(ThingamajigsBlocks.CONCRETE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.CONCRETE_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.CONCRETE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.CONCRETE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get());

        this.add(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.CONCRETE_BRICKS_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get());

        this.add(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.COBBLED_CONCRETE_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.dropSelf(ThingamajigsBlocks.DISCO_BALL.get());
        this.dropSelf(ThingamajigsBlocks.LAUNDRY_PILE.get());
        this.dropSelf(ThingamajigsBlocks.LAUNDRY_BASKET.get());
        this.dropSelf(ThingamajigsBlocks.BAR_STOOL.get());
        this.dropSelf(ThingamajigsBlocks.SPEAKER.get());
        this.dropSelf(ThingamajigsBlocks.AUDIO_MIXER.get());



        this.add(ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_DT_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_DT_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_DT_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BLUE_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BLUE_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BLUE_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.BLUE_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.YELLOW_DT_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_DT_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_DT_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_DT_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_DT_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_DT_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_PARKING_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_PARKING_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_PARKING_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_PARKING_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.YELLOW_D_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_D_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_D_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_D_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_D_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_D_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.YELLOW_D_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.YELLOW_D_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.YELLOW_D_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));

        this.add(ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_D_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_D_OLD_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_D_MEDIOCRE_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));
        this.add(ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get(),
                LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(this.applyExplosionDecay(ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get(),
                                LootItem.lootTableItem(ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ThingamajigsBlocks.WHITE_D_OK_ASPHALT_SLAB.get())
                                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(SlabBlock.TYPE, SlabType.DOUBLE))))))));



        this.dropSelf(ThingamajigsBlocks.BARBER_POLE.get());
        this.dropSelf(ThingamajigsBlocks.WALL_TV.get());
        this.dropSelf(ThingamajigsBlocks.BARBER_CHAIR.get());
        this.dropSelf(ThingamajigsBlocks.BARBER_HAIR_DRYER.get());
        this.dropSelf(ThingamajigsBlocks.BOXY_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.ORANGE_BOXY_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.TALL_BOXY_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.DENTAL_CUP.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_GAME_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_GAME_CONSOLE.get());

        // 1.7.6
        this.dropSelf(ThingamajigsBlocks.AIR_FRYER.get());
        this.dropSelf(ThingamajigsBlocks.WATER_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.CAT_TREE.get());
        this.dropSelf(ThingamajigsBlocks.TRASH_BAG.get());
        this.dropSelf(ThingamajigsBlocks.GAMING_PC.get());
        this.dropSelf(ThingamajigsBlocks.LITTER_BOX.get());
        this.dropSelf(ThingamajigsBlocks.DESK_LAMP.get());
        this.dropSelf(ThingamajigsBlocks.REFINED_THINGAMAJIG_BLOCK.get());
        this.dropSelf(ThingamajigsBlocks.CAMPING_FUEL_CAN.get());
        this.dropSelf(ThingamajigsBlocks.ICECREAM_DISPLAY.get());
        this.dropSelf(ThingamajigsBlocks.PTAC_AC.get());
        this.dropSelf(ThingamajigsBlocks.CLOTHES_RACK.get());
        this.dropSelf(ThingamajigsBlocks.CARD_READER.get());
        this.dropSelf(ThingamajigsBlocks.CURVED_MONITOR.get());
        // 1.7.7
        this.dropSelf(ThingamajigsBlocks.SECURITY_METAL_DETECTOR.get());
        this.dropSelf(ThingamajigsBlocks.CLEVER_BLACKBOARD.get());
        this.dropSelf(ThingamajigsBlocks.SUPERMARKET_CONVEYOR.get());
        this.dropSelf(ThingamajigsBlocks.UMBRELLA.get());
        this.dropSelf(ThingamajigsBlocks.STRING_BASS.get());
        this.dropSelf(ThingamajigsBlocks.BASS_DRUM.get());
        this.dropSelf(ThingamajigsBlocks.SNARE_DRUM.get());
        this.dropSelf(ThingamajigsBlocks.CYMBAL_CRASH.get());
        this.dropSelf(ThingamajigsBlocks.FLOOR_TOM.get());
        this.dropSelf(ThingamajigsBlocks.RACK_TOM.get());
        this.dropSelf(ThingamajigsBlocks.HI_HAT.get());
        this.dropSelf(ThingamajigsBlocks.BONGOS.get());
        this.dropSelf(ThingamajigsBlocks.CONGAS.get());
        this.dropSelf(ThingamajigsBlocks.CAJON.get());
        this.dropSelf(ThingamajigsBlocks.VOICE_MICROPHONE.get());
        this.dropSelf(ThingamajigsBlocks.THEATER_PROJECTOR.get());
        this.dropSelf(ThingamajigsBlocks.TRIPLE_SHELF.get());
        this.dropSelf(ThingamajigsBlocks.TEDDY_BEAR.get());
        this.dropSelf(ThingamajigsBlocks.CHIMNEY.get());
        this.dropSelf(ThingamajigsBlocks.MYSTERIOUS_PILLAR.get());
        this.dropSelf(ThingamajigsBlocks.GOAL.get());
        this.dropSelf(ThingamajigsBlocks.GLOWING_INVERTED_CATEYE_CROSSBUCK.get());
        this.dropSelf(ThingamajigsBlocks.ORANGE_PRIVATE_PROPERTY_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.VIDEO_IN_PROGRESS_SIGN.get());
        this.dropSelf(ThingamajigsBlocks.NO_STARING_PRIVATE_PROPERTY_SIGN.get());
        // 1.8.1
        this.dropSelf(ThingamajigsBlocks.ANIMATED_ICE_RINK.get());
        this.dropSelf(ThingamajigsBlocks.NEWSPAPER_DISPENSER.get());
        this.dropSelf(ThingamajigsBlocks.RESTAURANT_TRASH_CAN.get());
        this.dropSelf(ThingamajigsBlocks.SPECIAL_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.SNOW_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.BALL_PIT.get());
        this.dropSelf(ThingamajigsBlocks.BONDING_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.CATCHING_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.RESIN_FILLED_ROCK.get());
        this.dropOther(ThingamajigsBlocks.STRANGE_STATUE.get(),ThingamajigsItems.STRANGE_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.ANIMATED_DEER.get());
        this.dropSelf(ThingamajigsBlocks.VALIANT_STATUE.get());
        this.add(ThingamajigsBlocks.ROUND_BUSH.get(), (supplier) ->
                createGrassLikeDrops(ThingamajigsBlocks.ROUND_BUSH.get(), Items.WHEAT_SEEDS));
        this.add(ThingamajigsBlocks.BULBLET.get(), (supplier) ->
                createGrassLikeDrops(ThingamajigsBlocks.BULBLET.get(), Items.WHEAT_SEEDS));
        this.add(ThingamajigsBlocks.WISPY_WEED.get(), (supplier) ->
                createGrassLikeDrops(ThingamajigsBlocks.WISPY_WEED.get(), Items.WHEAT_SEEDS));
        // 1.8.3
        this.dropSelf(ThingamajigsBlocks.FOOD_COOLER.get());
        this.dropSelf(ThingamajigsBlocks.FOOTBALL_GOAL.get());
        this.dropSelf(ThingamajigsBlocks.SPHERES_AND_RINGS_MACHINE.get());
        this.dropSelf(ThingamajigsBlocks.ROUND_CLOTHES_RACK.get());
        this.dropSelf(ThingamajigsBlocks.PLUNGER.get());
        this.dropSelf(ThingamajigsBlocks.PIZZA.get());
        this.dropSelf(ThingamajigsBlocks.CAR_WHEEL.get());
        this.dropSelf(ThingamajigsBlocks.POOL_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.METAL_POOL_TABLE.get());
        this.dropSelf(ThingamajigsBlocks.TOWEL_STACK.get());
        this.dropSelf(ThingamajigsBlocks.RARE_BLUE_GRAY_GAME_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.GOLDME_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.FUNDEVICE_GAME_CONSOLE.get());
        this.dropSelf(ThingamajigsBlocks.CARDBOARD_BOX.get());


        // 1.8.4
        this.add(ThingamajigsBlocks.SIDEWALK_LAYER_LEFT.get(),noDrop());
        this.add(ThingamajigsBlocks.SIDEWALK_LAYER.get(),noDrop());
        this.add(ThingamajigsBlocks.SIDEWALK_LAYER_RIGHT.get(),noDrop());
        this.dropSelf(ThingamajigsBlocks.FURIOUS_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.SORROW_STATUE.get());
        this.dropSelf(ThingamajigsBlocks.SOCCER_BALL.get());
        this.dropSelf(ThingamajigsBlocks.BASKETBALL.get());
        this.add(ThingamajigsBlocks.TENNIS_BALL.get(),noDrop());
        this.dropSelf(ThingamajigsBlocks.TENNIS_NET.get());
        this.dropSelf(ThingamajigsBlocks.TENNIS_RACKET.get());
        this.dropSelf(ThingamajigsBlocks.PHONE_CROSSBAR.get());
        this.dropSelf(ThingamajigsBlocks.STAINLESS_WASHER.get());
        this.dropSelf(ThingamajigsBlocks.WEIGHT_SCALE.get());


        // old end
        this.add(ThingamajigsBlocks.WHITE_ROAD_MARKING.get(),noDrop());
        this.add(ThingamajigsBlocks.YELLOW_ROAD_MARKING.get(),noDrop());
        this.add(ThingamajigsBlocks.BLUE_ROAD_MARKING.get(),noDrop());

        this.dropOther(ThingamajigsBlocks.EXPLODING_PC.get(),ThingamajigsBlocks.OLD_PC.get());
        this.add(ThingamajigsBlocks.TNT_SLAB.get(),noDrop());
        this.add(ThingamajigsBlocks.SUPER_SPONGE.get(),noDrop());

        // torches
        this.dropOther(ThingamajigsBlocks.WALL_CLEAR_BULB.get(), ThingamajigsItems.CLEAR_BULB_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GROUND_CLEAR_BULB.get(), ThingamajigsItems.CLEAR_BULB_ITEM.get());

        this.dropOther(ThingamajigsBlocks.WALL_FULL_BULB.get(), ThingamajigsItems.FULL_BULB_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GROUND_FULL_BULB.get(), ThingamajigsItems.FULL_BULB_ITEM.get());

        this.dropOther(ThingamajigsBlocks.WALL_CLEAR_LANTERN.get(), ThingamajigsItems.CLEAR_LANTERN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GROUND_CLEAR_LANTERN.get(), ThingamajigsItems.CLEAR_LANTERN_ITEM.get());

        this.dropOther(ThingamajigsBlocks.WALL_FULL_LANTERN.get(), ThingamajigsItems.FULL_LANTERN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.GROUND_FULL_LANTERN.get(), ThingamajigsItems.FULL_LANTERN_ITEM.get());


        this.dropOther(ThingamajigsBlocks.WALL_PAPER_LANTERN.get(), ThingamajigsItems.PAPER_LANTERN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.PAPER_LANTERN.get(), ThingamajigsItems.PAPER_LANTERN_ITEM.get());

        this.dropOther(ThingamajigsBlocks.WALL_RED_LANTERN.get(), ThingamajigsItems.RED_LANTERN_ITEM.get());
        this.dropOther(ThingamajigsBlocks.RED_LANTERN.get(), ThingamajigsItems.RED_LANTERN_ITEM.get());

        // rubber leaves
        this.add(ThingamajigsBlocks.RUBBER_LEAVES.get(),
                thing -> this.createRubberLeavesDrops(thing, ThingamajigsBlocks.RUBBER_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ThingamajigsBlocks.DROOPY_FLOWER.get());

        this.dropPottedContents(ThingamajigsBlocks.POTTED_BULBY_FLOWER.get());
        this.dropPottedContents(ThingamajigsBlocks.POTTED_DROOPY_FLOWER.get());
        // pumpkins
        this.dropSelf(ThingamajigsBlocks.WHITE_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.WHITE_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.WHITE_JOL.get());

        this.dropSelf(ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_GRAY_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_GRAY_JOL.get());

        this.dropSelf(ThingamajigsBlocks.GRAY_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.GRAY_JOL.get());

        this.dropSelf(ThingamajigsBlocks.BLACK_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BLACK_JOL.get());

        this.dropSelf(ThingamajigsBlocks.BROWN_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BROWN_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BROWN_JOL.get());

        this.dropSelf(ThingamajigsBlocks.RED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.RED_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.RED_JOL.get());

        this.dropSelf(ThingamajigsBlocks.YELLOW_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.YELLOW_JOL.get());

        this.dropSelf(ThingamajigsBlocks.LIME_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIME_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIME_JOL.get());

        this.dropSelf(ThingamajigsBlocks.GREEN_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.GREEN_JOL.get());

        this.dropSelf(ThingamajigsBlocks.CYAN_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.CYAN_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.CYAN_JOL.get());

        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.LIGHT_BLUE_JOL.get());

        this.dropSelf(ThingamajigsBlocks.BLUE_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.BLUE_JOL.get());

        this.dropSelf(ThingamajigsBlocks.PURPLE_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.PURPLE_JOL.get());

        this.dropSelf(ThingamajigsBlocks.MAGENTA_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.MAGENTA_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.MAGENTA_JOL.get());

        this.dropSelf(ThingamajigsBlocks.PINK_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.PINK_CARVED_PUMPKIN.get());
        this.dropSelf(ThingamajigsBlocks.PINK_JOL.get());

        // dyed stems
        this.add(ThingamajigsBlocks.WHITE_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.WHITE_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_WHITE_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.WHITE_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.LIGHT_GRAY_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.LIGHT_GRAY_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_LIGHT_GRAY_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.LIGHT_GRAY_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.GRAY_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.GRAY_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_GRAY_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.GRAY_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.BLACK_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.BLACK_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_BLACK_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.BLACK_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.BROWN_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.BROWN_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_BROWN_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.BROWN_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.RED_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.RED_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_RED_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.RED_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.YELLOW_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.YELLOW_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_YELLOW_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.YELLOW_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.LIME_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.LIME_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_LIME_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.LIME_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.GREEN_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.GREEN_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_GREEN_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.GREEN_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.CYAN_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.CYAN_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_CYAN_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.CYAN_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.LIGHT_BLUE_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.LIGHT_BLUE_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_LIGHT_BLUE_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.LIGHT_BLUE_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.BLUE_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.BLUE_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_BLUE_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.BLUE_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.PURPLE_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.PURPLE_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_PURPLE_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.PURPLE_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.MAGENTA_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.MAGENTA_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_MAGENTA_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.MAGENTA_PUMPKIN_SEEDS.get()));

        this.add(ThingamajigsBlocks.PINK_PUMPKIN_STEM.get(),
                thing -> this.createStemDrops(thing, ThingamajigsItems.PINK_PUMPKIN_SEEDS.get()));
        this.add(ThingamajigsBlocks.ATTATCHED_PINK_PUMPKIN_STEM.get(),
                thing -> this.createAttachedStemDrops(thing, ThingamajigsItems.PINK_PUMPKIN_SEEDS.get()));
    }

    // why is everything secret?
    private static final LootItemCondition.Builder HS_OR_ST = HAS_SHEARS.or(HAS_SILK_TOUCH);

    public LootTable.Builder createRubberLeavesDrops(Block rubberLeaves, Block saplingBlock, float... chances) {
        return this.createLeavesDrops(rubberLeaves,saplingBlock,chances)
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(HS_OR_ST.invert())
                        .add(this.applyExplosionCondition(rubberLeaves, LootItem.lootTableItem(ThingamajigsItems.TREE_RESIN.get()))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(
                                        Enchantments.BLOCK_FORTUNE, 0.002F,
                                        0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }

    public LootTable.Builder createGrassLikeDrops(Block block, Item dropItem) {
        LootItemCondition.Builder conditionBuilder = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));
        return LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block).when(conditionBuilder).otherwise(
                                this.applyExplosionDecay(
                                        block,
                                        LootItem.lootTableItem(dropItem)
                                                .when(LootItemRandomChanceCondition.randomChance(0.125F))
                                                .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE, 2))
                                )
                        )));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(Thingamajigs.MOD_ID))
                //.filter(e -> e.getValue() != ThingamajigsBlocks.SPHERES_AND_RINGS_MACHINE.get())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
