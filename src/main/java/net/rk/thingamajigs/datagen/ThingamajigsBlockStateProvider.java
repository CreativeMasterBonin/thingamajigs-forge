package net.rk.thingamajigs.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;

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

        //blockItem(ThingamajigsBlocks.CONCRETE);
        //blockItem(ThingamajigsBlocks.CONCRETE_BRICKS);
        //blockItem(ThingamajigsBlocks.COBBLED_CONCRETE);

        stairsBlock((StairBlock)ThingamajigsBlocks.CONCRETE_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/concrete"));

        stairsBlock((StairBlock)ThingamajigsBlocks.CONCRETE_BRICKS_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/concrete_bricks"));

        stairsBlock((StairBlock)ThingamajigsBlocks.COBBLED_CONCRETE_STAIRS.get(),
                new ResourceLocation("thingamajigs:block/cobbled_concrete"));
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
