package net.rk.thingamajigs.misc;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ThingamajigsBlockTypes {
    public static BlockSetType GENERIC_ROAD = BlockSetType.register(new BlockSetType(
            "generic_road",
            false,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON));

    public static BlockSetType GENERIC_RED_ROAD = BlockSetType.register(new BlockSetType(
            "generic_red_road",
            false,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON));

    public static BlockSetType GENERIC_BLUE_ROAD = BlockSetType.register(new BlockSetType(
            "generic_blue_road",
            false,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON));

    public static BlockSetType GENERIC_BROWN_ROAD = BlockSetType.register(new BlockSetType(
            "generic_brown_road",
            false,
            SoundType.LANTERN,
            SoundEvents.IRON_DOOR_CLOSE,
            SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE,
            SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF,
            SoundEvents.STONE_BUTTON_CLICK_ON));

    public static WoodType GENERIC_ROAD_WOOD = new WoodType("generic_road_wood",GENERIC_ROAD);
    public static WoodType GENERIC_RED_ROAD_WOOD = new WoodType("generic_red_road_wood",GENERIC_RED_ROAD);
    public static WoodType GENERIC_BLUE_ROAD_WOOD = new WoodType("generic_blue_road_wood",GENERIC_BLUE_ROAD);
    public static WoodType GENERIC_BROWN_ROAD_WOOD = new WoodType("generic_brown_road_wood",GENERIC_BROWN_ROAD);
}
