package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.entity.customblock.DJLaserLightBE;
import net.rk.thingamajigs.misc.ThingamajigsColors;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;
import net.rk.thingamajigs.network.messages.DJLaserLightUpdateMessage;

import java.util.HashMap;

public class DJLaserLightScreen extends AbstractContainerScreen<DJLaserLightMenu> {
    private final static HashMap<String, Object> guistate = DJLaserLightMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;

    private DJLaserLightBE djllbe;

    public Button decreaseHeightButton;
    public Button increaseHeightButton;
    public Button decVertAngleButton;
    public Button incVertAngleButton;

    public Button incred;
    public Button decred;

    public Button incgreen;
    public Button decgreen;

    public Button incblue;
    public Button decblue;

    public Button incosc;
    public Button decosc;

    public Button incmm;
    public Button decmm;

    public Button inclasersize;
    public Button declasersize;

    public Button turnOnCustomColors;
    public Button turnOffCustomColors;

    public Button turnOnRandomColors;
    public Button turnOffRandomColors;


    public Button incvoscAngle;
    public Button decvoscAngle;

    public Button incvmm;
    public Button decvmm;


    public Button inclasercnt;
    public Button declasercnt;


    private static final ResourceLocation BG_TEXTURE = new ResourceLocation("thingamajigs:textures/gui/laser_light_menu.png");

    public DJLaserLightScreen(DJLaserLightMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 320;
        this.imageHeight = 240;

        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;

        this.djllbe = (DJLaserLightBE)world.getBlockEntity(new BlockPos(x,y,z));
    }

    @Override
    protected void init() {
        super.init();
        setupExtras();
        if (djllbe != null) {
            addRenderableWidget(decreaseHeightButton);
            addRenderableWidget(increaseHeightButton);
            addRenderableWidget(decVertAngleButton);
            addRenderableWidget(incVertAngleButton);

            addRenderableWidget(turnOnCustomColors);
            addRenderableWidget(turnOffCustomColors);
            addRenderableWidget(turnOnRandomColors);
            addRenderableWidget(turnOffRandomColors);
            addRenderableWidget(incred);
            addRenderableWidget(decred);
            addRenderableWidget(incgreen);
            addRenderableWidget(decgreen);
            addRenderableWidget(incblue);
            addRenderableWidget(decblue);

            addRenderableWidget(incosc);
            addRenderableWidget(decosc);
            addRenderableWidget(incmm);
            addRenderableWidget(decmm);

            addRenderableWidget(inclasersize);
            addRenderableWidget(declasersize);

            addRenderableWidget(incvoscAngle);
            addRenderableWidget(decvoscAngle);
            addRenderableWidget(incvmm);
            addRenderableWidget(decvmm);

            addRenderableWidget(inclasercnt);
            addRenderableWidget(declasercnt);
        }
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        this.renderBg(pGuiGraphics,pPartialTick,pMouseX,pMouseY);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        Component modelabel = Component.translatable("container.thingamajigs.laser_light.mode_title")
                .withStyle(ChatFormatting.WHITE);
        Component extraInfolabel = Component.translatable("container.thingamajigs.laser_light.mode_unused");

        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY,
                ThingamajigsColors.getWhite(), false);


        if(djllbe != null){
            if(djllbe.lightMode == 0){
                extraInfolabel = Component.translatable("container.thingamajigs.laser_light.mode_zero_label");
            }

            pGuiGraphics.drawString(this.font,
                    Component.literal(modelabel.getString() + " " + djllbe.lightMode + " (" + extraInfolabel.getString() + ")"),
                    32, 142,
                    ThingamajigsColors.getWhite(),
                    false);

            int xpos = 180;
            int startstrh = 135;
            int secondstrth = startstrh + 20;
            int thirdth = secondstrth + 10;

            // height
            if(djllbe.height >= 32){
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.height_max"),
                        xpos, startstrh,
                        ThingamajigsColors.getColorFromList(3),
                        false);
            }
            else if (djllbe.height <= 0) {
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.height_min"),
                        xpos, startstrh,
                        ThingamajigsColors.getColorFromList(3),
                        false);
            }
            else{
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.height_title",djllbe.height),
                        xpos, startstrh,
                        ThingamajigsColors.getWhite(),
                        false);
            }

            pGuiGraphics.setColor(djllbe.red,djllbe.green,djllbe.blue,1.0f);
            // rgb
            pGuiGraphics.drawString(this.font,
                    Component.translatable("container.thingamajigs.laser_light.rgb_title",djllbe.red,djllbe.green,djllbe.blue),
                    xpos, secondstrth,
                    ThingamajigsColors.getWhite(),
                    false);
            pGuiGraphics.setColor(1.0f,1.0f,1.0f,1.0f);
            // angles
            pGuiGraphics.drawString(this.font,
                    Component.translatable("container.thingamajigs.laser_light.angles_title",djllbe.angle,djllbe.verticalAngle),
                    xpos, thirdth,
                    ThingamajigsColors.getWhite(),
                    false);
            // angle multipliers
            pGuiGraphics.drawString(this.font,
                    Component.translatable("container.thingamajigs.laser_light.angle_mms_title",djllbe.degreeOfMotionMultiplier,djllbe.dOfMotMultiVert),
                    xpos, thirdth + 20,
                    ThingamajigsColors.getWhite(),
                    false);
            // angle multipliers
            pGuiGraphics.drawString(this.font,
                    Component.translatable("container.thingamajigs.laser_light.osc_angles_title",djllbe.oscAngle,djllbe.vertOscAngle),
                    32, 152,
                    ThingamajigsColors.getWhite(),
                    false);
            // size
            if(djllbe.laserSize >= 3.0){
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.size_max"),
                        xpos, thirdth + 30,
                        ThingamajigsColors.getWhite(),
                        false);
            }
            else if(djllbe.laserSize <= 0.01){
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.size_min"),
                        xpos, thirdth + 30,
                        ThingamajigsColors.getWhite(),
                        false);
            }
            else{
                pGuiGraphics.drawString(this.font,
                        Component.translatable("container.thingamajigs.laser_light.size_title",djllbe.laserSize),
                        xpos, thirdth + 30,
                        ThingamajigsColors.getWhite(),
                        false);
            }
        }
    }

    // fun
    @Override
    public void renderBackground(GuiGraphics gui) {
        if (this.minecraft.level != null){
            this.renderCustomBg(gui);
        }
        else{
            this.renderDirtBackground(gui);
        }
    }

    public void renderCustomBg(GuiGraphics guigraph) {
        ResourceLocation BG_LOC = new ResourceLocation(Thingamajigs.MOD_ID,"textures/block/sidewalk_new.png");

        if(djllbe != null){
            guigraph.setColor(
                    (float)djllbe.red,
                    (float)djllbe.green,
                    (float)djllbe.blue,
                    1.0F);
        }

        guigraph.blit(BG_LOC, this.leftPos - 18, this.topPos + 107, 0, 0.0F, 0.0F,
                16, 16, 16, 16);

        guigraph.setColor(1.0F, 1.0F, 1.0F, 1.0F);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(
                new net.minecraftforge.client.event.ScreenEvent.BackgroundRendered(this, guigraph));
    }



    @Override
    protected void renderBg(GuiGraphics ggraph, float ptick, int mousx, int mousy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        ggraph.blit(BG_TEXTURE,
                this.leftPos,this.topPos,0,0,
                this.imageWidth,this.imageHeight,this.imageWidth,this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    // send to server all updated BE elements so the BE can be updated and saved and synced properly to all clients
    private void setupExtras(){

        int smallSliderwidth = 72;
        int sliderheight = 16;
        int mediumSliderwidth = 102;
        int largeSliderwidth = 184;


        decreaseHeightButton = Button.builder(Component.translatable("button.thingamajigs.laser_light.height_dec"),
                onPressSup -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                    new BlockPos(x,y,z),
                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height - 1,
                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
            ));
            DJLaserLightUpdateMessage.handleit(
                    entity,djllbe,new BlockPos(x,y,z),
                    djllbe.useCustomColor,
                    djllbe.randomlyGenerateColor,
                    djllbe.height - 1,
                    djllbe.color,
                    djllbe.angle,
                    djllbe.verticalAngle,
                    djllbe.oscAngle,
                    djllbe.degreeOfMotionMultiplier,
                    djllbe.laserSize,
                    djllbe.red,
                    djllbe.green,
                    djllbe.blue,
                    djllbe.lightMode,
                    djllbe.vertOscAngle,
                    djllbe.dOfMotMultiVert,djllbe.laserCount
            );
                })
                .bounds(this.leftPos + 23,this.topPos + 24,
                        64,16)
                .build();

        increaseHeightButton = Button.builder(Component.translatable("button.thingamajigs.laser_light.height_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height + 1,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height + 1,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(decreaseHeightButton.getX() + decreaseHeightButton.getWidth() + 2,
                        decreaseHeightButton.getY(),
                        64,16)
                .build();

        decVertAngleButton = Button.builder(Component.translatable("button.thingamajigs.laser_light.vert_ang_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle - 1, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle - 1,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(decreaseHeightButton.getX(),
                        decreaseHeightButton.getY() + decreaseHeightButton.getHeight() + 2,
                        64,16)
                .build();

        incVertAngleButton = Button.builder(Component.translatable("button.thingamajigs.laser_light.vert_ang_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle + 1, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle + 1,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(decVertAngleButton.getX() + decVertAngleButton.getWidth() + 2,
                        decVertAngleButton.getY(),
                        64,16)
                .build();

        Component oncc = Component.literal(
                Component.translatable(
                        "button.thingamajigs.laser_light.custom_colors").getString()
                        + CommonComponents.OPTION_ON.getString());

        Component offcc = Component.literal(
                Component.translatable(
                        "button.thingamajigs.laser_light.custom_colors").getString()
                        + CommonComponents.OPTION_OFF.getString());

        Component onrc = Component.literal(
                Component.translatable(
                        "button.thingamajigs.laser_light.random_colors").getString()
                        + CommonComponents.OPTION_ON.getString());

        Component offrc = Component.literal(
                Component.translatable(
                        "button.thingamajigs.laser_light.random_colors").getString()
                        + CommonComponents.OPTION_OFF.getString());


        turnOnCustomColors = Button.builder(oncc,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    true, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    true,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 180,
                        this.topPos + 5,
                        64,16)
                .build();

        turnOffCustomColors = Button.builder(offcc,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    false, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    false,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(turnOnCustomColors.getX(),
                        turnOnCustomColors.getY() + turnOnCustomColors.getHeight() + 2,
                        64,16)
                .build();


        turnOnRandomColors = Button.builder(onrc,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, true, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    true,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(turnOnCustomColors.getX() + (turnOnCustomColors.getWidth() + 2),
                        turnOnCustomColors.getY(),
                        64,16)
                .build();

        turnOffRandomColors = Button.builder(offrc,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, false, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    false,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(turnOnRandomColors.getX(),
                        turnOnRandomColors.getY() + turnOnRandomColors.getHeight() + 2,
                        64,16)
                .build();


        int leftmostbuttonx = this.leftPos + 29;
        int redy = this.topPos + 75;
        int redx2 = this.leftPos + 95;

        int bluey = this.topPos + 92;
        int bluex2 = this.leftPos + 95;

        int greeny = this.topPos + 109;
        int greenx2 = this.leftPos + 95;

        Component dred = Component.translatable("button.thingamajigs.laser_light.red_dec");
        Component ired = Component.translatable("button.thingamajigs.laser_light.red_inc");

        Component dgre = Component.translatable("button.thingamajigs.laser_light.green_dec");
        Component igre = Component.translatable("button.thingamajigs.laser_light.green_inc");

        Component dblu = Component.translatable("button.thingamajigs.laser_light.blue_dec");
        Component iblu = Component.translatable("button.thingamajigs.laser_light.blue_inc");

        decred = Button.builder(dred,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red - (float)0.1, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red - (float)0.1,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(leftmostbuttonx,
                        redy,
                        64,16)
                .build();

        incred = Button.builder(ired,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red + (float)0.1, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red + (float)0.1,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(redx2,
                        redy,
                        64,16)
                .build();

        decgreen = Button.builder(dgre,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green - (float)0.1,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green - (float)0.1,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(leftmostbuttonx,
                        greeny,
                        64,16)
                .build();

        incgreen = Button.builder(igre,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green + (float)0.1,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green + (float)0.1,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(greenx2,
                        greeny,
                        64,16)
                .build();

        decblue = Button.builder(dblu,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue - (float)0.1, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue - (float)0.1,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(leftmostbuttonx,
                        bluey,
                        64,16)
                .build();

        incblue = Button.builder(iblu,
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue + (float)0.1, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue + (float)0.1,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(bluex2,
                        bluey,
                        64,16)
                .build();


        decosc = Button.builder(Component.translatable("button.thingamajigs.laser_light.osc_angle_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle - 1,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle - 1,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 186,
                        this.topPos + 42,
                        64,16)
                .build();

        incosc = Button.builder(Component.translatable("button.thingamajigs.laser_light.osc_angle_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle + 1,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle + 1,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 42,
                        64,16)
                .build();


        decmm = Button.builder(Component.translatable("button.thingamajigs.laser_light.mm_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier - 1, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier - 1,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 186,
                        this.topPos + 60,
                        64,16)
                .build();

        incmm = Button.builder(Component.translatable("button.thingamajigs.laser_light.mm_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier + 1, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier + 1,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 60,
                        64,16)
                .build();


        declasersize = Button.builder(Component.translatable("button.thingamajigs.laser_light.laser_size_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize - (float)0.01, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize - (float)0.01,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 186,
                        this.topPos + 78,
                        64,16)
                .build();

        inclasersize = Button.builder(Component.translatable("button.thingamajigs.laser_light.laser_size_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize + (float)0.01, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize + (float)0.01,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 78,
                        64,16)
                .build();


        decvoscAngle = Button.builder(Component.translatable("button.thingamajigs.laser_light.vosc_angle_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle - 1, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle - 1,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 186,
                        this.topPos + 96,
                        64,16)
                .build();

        incvoscAngle = Button.builder(Component.translatable("button.thingamajigs.laser_light.vosc_angle_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle + 1, djllbe.dOfMotMultiVert,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle + 1,
                                    djllbe.dOfMotMultiVert,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 96,
                        64,16)
                .build();

        decvmm = Button.builder(Component.translatable("button.thingamajigs.laser_light.vmm_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert - 1,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert - 1,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 186,
                        this.topPos + 114,
                        64,16)
                .build();

        incvmm = Button.builder(Component.translatable("button.thingamajigs.laser_light.vmm_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert + 1,djllbe.laserCount
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert + 1,djllbe.laserCount
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 114,
                        64,16)
                .build();

        // laser cnt
        inclasercnt = Button.builder(Component.translatable("button.thingamajigs.laser_light.laser_count_inc"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount + 1
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,
                                    djllbe.laserCount + 1
                            );
                        })
                .bounds(this.leftPos + 252,
                        this.topPos + 216,
                        64,16)
                .build();

        declasercnt = Button.builder(Component.translatable("button.thingamajigs.laser_light.laser_count_dec"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new DJLaserLightUpdateMessage(
                                    new BlockPos(x,y,z),
                                    djllbe.useCustomColor, djllbe.randomlyGenerateColor, djllbe.height,
                                    djllbe.color, djllbe.angle, djllbe.verticalAngle, djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier, djllbe.laserSize, djllbe.red, djllbe.green,
                                    djllbe.blue, djllbe.lightMode, djllbe.vertOscAngle, djllbe.dOfMotMultiVert,djllbe.laserCount - 1
                            ));
                            DJLaserLightUpdateMessage.handleit(
                                    entity,djllbe,new BlockPos(x,y,z),
                                    djllbe.useCustomColor,
                                    djllbe.randomlyGenerateColor,
                                    djllbe.height,
                                    djllbe.color,
                                    djllbe.angle,
                                    djllbe.verticalAngle,
                                    djllbe.oscAngle,
                                    djllbe.degreeOfMotionMultiplier,
                                    djllbe.laserSize,
                                    djllbe.red,
                                    djllbe.green,
                                    djllbe.blue,
                                    djllbe.lightMode,
                                    djllbe.vertOscAngle,
                                    djllbe.dOfMotMultiVert,
                                    djllbe.laserCount - 1
                            );
                        })
                .bounds(this.leftPos + 118,
                        this.topPos + 216,
                        64,16)
                .build();
    }
}
