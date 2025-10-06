package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.entity.customblock.RailroadCrossingBE;
import net.rk.thingamajigs.misc.ThingamajigsColors;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;
import net.rk.thingamajigs.network.messages.RailroadCrossingArmUpdateMessage;

import java.util.HashMap;

public class RailroadCrossingArmScreen extends AbstractContainerScreen<RailroadCrossingArmMenu>{
    private final static HashMap<String, Object> guistate = RailroadCrossingArmMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;

    private RailroadCrossingBE rcbe;

    private static final ResourceLocation BG_TEXTURE =
            new ResourceLocation("thingamajigs:textures/gui/rrc_component_setup_bg.png");

    public RailroadCrossingArmScreen(RailroadCrossingArmMenu container, Inventory inventory, Component text){
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 176;
        this.imageHeight = 166;

        this.minecraft = Minecraft.getInstance();
        this.font = this.minecraft.font;

        int widthx = (this.width - this.imageWidth) / 2;
        int heighty = (this.height - this.imageHeight) / 2;

        this.rcbe = (RailroadCrossingBE)world.getBlockEntity(new BlockPos(x,y,z)); // be access
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, true);

        // offset text
        guiGraphics.drawString(this.font,
                Component.translatable("title.thingamajigs.length",rcbe.armLength),
                26, 84,
                ThingamajigsColors.getWhite(), false);

        // offset text
        guiGraphics.drawString(this.font,
                Component.translatable("title.thingamajigs.offset",rcbe.armGateOffsetZ),
                26, 84 + 16,
                ThingamajigsColors.getWhite(), false);

        // rotation text
        guiGraphics.drawString(this.font,
                Component.translatable("title.thingamajigs.rot",rcbe.yAngle),
                26, 84 + 16 + 16,
                ThingamajigsColors.getWhite(), false);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1){
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, BG_TEXTURE);
        guiGraphics.blit(BG_TEXTURE,
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
        if(key == 256){
            this.getMinecraft().player.closeContainer();
            return true;
        }
        return false;
    }

    private Button decreaseGateLength;
    private Button increaseGateLength;
    private Button decreaseGateOffset;
    private Button increaseGateOffset;

    private Button decreaseRotation;
    private Button increaseRotation;

    @Override
    protected void init() {
        super.init();
        setupWidgets();
        addRenderableWidget(decreaseGateLength);
        addRenderableWidget(increaseGateLength);
        addRenderableWidget(decreaseGateOffset);
        addRenderableWidget(increaseGateOffset);
        addRenderableWidget(decreaseRotation);
        addRenderableWidget(increaseRotation);
    }

    private void setupWidgets(){
        int horzLeftButtonPos = leftPos + 25;
        int topRowButtonY = topPos + 25;
        int spacingButtonWidth = 2;
        int spacingButtonHeight = spacingButtonWidth;
        float lowPitch = 0.95f;
        float normalPitch = 1.0f;

        decreaseGateLength = Button.builder(Component.translatable("button.thingamajigs.dec_gate"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength + 1.0f,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength + 1.0f,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle
                            );
                        })
                .bounds(horzLeftButtonPos,topRowButtonY,
                        64,16)
                .build();

        int horzRightButtonPos = horzLeftButtonPos + decreaseGateLength.getWidth() + spacingButtonWidth;

        increaseGateLength = Button.builder(Component.translatable("button.thingamajigs.inc_gate"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength - 1.0f,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength - 1.0f,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle
                            );
                        })
                .bounds(horzRightButtonPos,topRowButtonY,
                        64,16)
                .build();

        int secondRowY = decreaseGateLength.getY() + decreaseGateLength.getHeight() + spacingButtonHeight;

        decreaseGateOffset = Button.builder(Component.translatable("button.thingamajigs.dec_gate_off"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ - 0.5f,
                                    rcbe.yAngle
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ - 0.5f,
                                    rcbe.yAngle
                            );
                        })
                .bounds(horzLeftButtonPos,secondRowY,
                        64,16)
                .build();

        increaseGateOffset = Button.builder(Component.translatable("button.thingamajigs.inc_gate_off"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ + 0.5f,
                                    rcbe.yAngle
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ + 0.5f,
                                    rcbe.yAngle
                            );
                        })
                .bounds(horzRightButtonPos,secondRowY,
                        64,16)
                .build();

        int thirdRowY = decreaseGateOffset.getY() + decreaseGateOffset.getHeight() + spacingButtonHeight;

        decreaseRotation = Button.builder(Component.translatable("button.thingamajigs.dec_gate_rot"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle - 0.05f
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle - 0.05f
                            );
                        })
                .bounds(horzLeftButtonPos,thirdRowY,
                        64,16)
                .build();

        increaseRotation = Button.builder(Component.translatable("button.thingamajigs.inc_gate_rot"),
                        onPressSup -> {
                            ThingamajigsPacketHandler.INSTANCE.sendToServer(new RailroadCrossingArmUpdateMessage(
                                    new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle + 0.05f
                            ));
                            RailroadCrossingArmUpdateMessage.handleit(
                                    entity,rcbe,new BlockPos(x,y,z),
                                    rcbe.startingArmAngle,
                                    rcbe.endArmAngle,
                                    rcbe.armLength,
                                    rcbe.armGateOffsetZ,
                                    rcbe.yAngle + 0.05f
                            );
                        })
                .bounds(horzRightButtonPos,thirdRowY,
                        64,16)
                .build();

        // extra text display
    }
}
