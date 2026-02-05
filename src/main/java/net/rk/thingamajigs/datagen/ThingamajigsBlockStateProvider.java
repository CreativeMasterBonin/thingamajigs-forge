package net.rk.thingamajigs.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.SidewalkLayer;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import net.rk.thingamajigs.block.custom.ThingamajigsDecorativeBlock;
import net.rk.thingamajigs.block.custom.blocks.ToggledStateBlock;

public class ThingamajigsBlockStateProvider extends BlockStateProvider {
    public ThingamajigsBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Thingamajigs.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        logBlock((RotatedPillarBlock)ThingamajigsBlocks.RUBBER_LOG.get());

        axisBlock((RotatedPillarBlock)ThingamajigsBlocks.RUBBER_WOOD.get(),
                blockTexture(ThingamajigsBlocks.RUBBER_LOG.get()),
                blockTexture(ThingamajigsBlocks.RUBBER_LOG.get()));

        axisBlock((RotatedPillarBlock)ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get(),
                blockTexture(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get()),
                new ResourceLocation(Thingamajigs.MOD_ID,"block/stripped_rubber_log_top"));

        axisBlock((RotatedPillarBlock)ThingamajigsBlocks.STRIPPED_RUBBER_WOOD.get(),
                blockTexture(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get()),
                blockTexture(ThingamajigsBlocks.STRIPPED_RUBBER_LOG.get()));

        blockItem(ThingamajigsBlocks.RUBBER_LOG);
        blockItem(ThingamajigsBlocks.RUBBER_WOOD);
        blockItem(ThingamajigsBlocks.STRIPPED_RUBBER_LOG);
        blockItem(ThingamajigsBlocks.STRIPPED_RUBBER_WOOD);
        blockWithItem(ThingamajigsBlocks.RUBBER_PLANKS);
        leavesBlock(ThingamajigsBlocks.RUBBER_LEAVES);
        saplingBlock(ThingamajigsBlocks.RUBBER_SAPLING);

        slabBlock((SlabBlock)ThingamajigsBlocks.RUBBER_WOOD_SLAB.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        stairsBlock((StairBlock)ThingamajigsBlocks.RUBBER_WOOD_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        doorBlockWithRenderType((DoorBlock)ThingamajigsBlocks.RUBBER_WOOD_DOOR.get(),
                new ResourceLocation("thingamajigs:block/doors/rubber_door_bottom"),
                new ResourceLocation("thingamajigs:block/doors/rubber_door_top"),
                "cutout");

        trapdoorBlockWithRenderType((TrapDoorBlock)ThingamajigsBlocks.RUBBER_WOOD_TRAPDOOR.get(),
                new ResourceLocation("thingamajigs:block/trapdoors/rubber_trapdoor"),
                true,"cutout");

        buttonBlock((ButtonBlock)ThingamajigsBlocks.RUBBER_WOOD_BUTTON.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        pressurePlateBlock((PressurePlateBlock)ThingamajigsBlocks.RUBBER_WOOD_PRESSURE_PLATE.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        fenceBlock((FenceBlock)ThingamajigsBlocks.RUBBER_WOOD_FENCE.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        fenceGateBlock((FenceGateBlock)ThingamajigsBlocks.RUBBER_WOOD_FENCE_GATE.get(),
                new ResourceLocation("thingamajigs:block/rubber_planks"));

        stairsBlock((StairBlock)ThingamajigsBlocks.CONCRETE_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/concrete"));

        stairsBlock((StairBlock)ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/concrete_bricks"));

        stairsBlock((StairBlock)ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/cobbled_concrete"));


        rotatedThingamajigsDecoration(ThingamajigsBlocks.STAINLESS_WASHER.get(),"thingamajigs:block/stainless_washer");
        rotatedThingamajigsDecoration(ThingamajigsBlocks.WEIGHT_SCALE.get(),"thingamajigs:block/weight_scale");
        rotatedThingamajigsDecoration(ThingamajigsBlocks.PHONE_GROUP_SELECTOR.get(),"thingamajigs:block/phone_group_selector");
        rotatedThingamajigsDecoration(ThingamajigsBlocks.PHONE_AXIS_SWITCH.get(),"thingamajigs:block/phone_axis_switch");
        rotatedThingamajigsDecoration(ThingamajigsBlocks.PHONE_AXIS_SWITCH_RELAY.get(),"thingamajigs:block/phone_axis_switch_relay");
    }

    // ?
    public void rotatedLayeredBlock(Block block, String modelsDir, String modelsPrefix){
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(SidewalkLayer.FACING);
            int layer = state.getValue(SidewalkLayer.LAYERS);
            String fullLocation = "thingamajigs:block/" + modelsDir + modelsPrefix + "_layer_2";

            ModelFile layerModel = new ModelFile(ResourceLocation.tryParse(fullLocation)) {
                @Override
                protected boolean exists() {
                    return ResourceLocation.isValidResourceLocation(fullLocation);
                }
            };
            return ConfiguredModel.builder()
                    .modelFile(layerModel)
                    .rotationY((int)facing.getOpposite().toYRot())
                    .uvLock(false)
                    .build();
        });
    }

    public void rotatedThingamajigsDecoration(Block block,String modelLocation){
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ThingamajigsDecorativeBlock.FACING);
            ModelFile decorationModel = new ModelFile(ResourceLocation.tryParse(modelLocation)) {
                @Override
                protected boolean exists() {
                    return ResourceLocation.isValidResourceLocation(modelLocation);
                }
            };
            return ConfiguredModel.builder()
                    .modelFile(decorationModel)
                    .rotationY((int)(facing.getOpposite()).toYRot())
                    .uvLock(false)
                    .build();
        });
    }

    public void rotatedToggledThingamajigsDecoration(Block block,String toggledModel,String untoggledModel){
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(ToggledStateBlock.FACING);
            boolean toggled = state.getValue(ToggledStateBlock.TOGGLED);
            ModelFile decorationModelToggled = new ModelFile(ResourceLocation.tryParse(toggledModel)) {
                @Override
                protected boolean exists() {
                    return ResourceLocation.isValidResourceLocation(toggledModel);
                }
            };
            ModelFile decorationModelUntoggled = new ModelFile(ResourceLocation.tryParse(untoggledModel)) {
                @Override
                protected boolean exists() {
                    return ResourceLocation.isValidResourceLocation(untoggledModel);
                }
            };
            return ConfiguredModel.builder()
                    .modelFile(toggled ? decorationModelToggled : decorationModelUntoggled)
                    .rotationY((int)(facing.getOpposite()).toYRot())
                    .uvLock(false)
                    .build();
        });
    }


    // tutor stuff kj thx

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(),
                new ModelFile.UncheckedModelFile("thingamajigs:block/"
                        + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
