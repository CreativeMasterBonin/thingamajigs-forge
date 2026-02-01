package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.network.ThingamajigsPacketHandler;
import net.rk.thingamajigs.network.messages.PhoneUIButtonMessage;
import net.rk.thingamajigs.screen.widget.PhoneEditBox;

import java.util.HashMap;

import static org.lwjgl.glfw.GLFW.*;

public class PhoneUIScreen extends AbstractContainerScreen<PhoneMenu> {
    private final static HashMap<String, Object> guistate = PhoneMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonPound, buttonStar, callButton;
    EditBox numberBox;

    public PhoneUIScreen(PhoneMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 200;
        this.imageHeight = 200;
    }

    private static final ResourceLocation TEXTURE = new ResourceLocation("thingamajigs:textures/gui/phone_menu.png");

    @Override
    public void render(GuiGraphics ms, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(ms);
        super.render(ms, mouseX, mouseY, partialTicks);
        this.renderTooltip(ms, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics2, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, TEXTURE);
        guiGraphics2.blit(TEXTURE,
                this.leftPos,this.topPos,0,0,
                this.imageWidth,this.imageHeight,this.imageWidth,this.imageHeight);
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == GLFW_KEY_ESCAPE && (c == GLFW_KEY_LEFT_SHIFT || c == GLFW_KEY_RIGHT_SHIFT)) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key,b,c);
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
    public void init() {
        super.init();
        setupButtons();

        guistate.put("button:button1", button1);
        this.addRenderableWidget(button1);

        guistate.put("button:button2", button2);
        this.addRenderableWidget(button2);

        guistate.put("button:button3", button3);
        this.addRenderableWidget(button3);

        guistate.put("button:button4", button4);
        this.addRenderableWidget(button4);

        guistate.put("button:button5", button5);
        this.addRenderableWidget(button5);

        guistate.put("button:button6", button6);
        this.addRenderableWidget(button6);

        guistate.put("button:button7", button7);
        this.addRenderableWidget(button7);

        guistate.put("button:button8", button8);
        this.addRenderableWidget(button8);

        guistate.put("button:button9", button9);
        this.addRenderableWidget(button9);

        guistate.put("button:buttonStar", buttonStar);
        this.addRenderableWidget(buttonStar);

        guistate.put("button:button0", button0);
        this.addRenderableWidget(button0);

        guistate.put("button:buttonPound", buttonPound);
        this.addRenderableWidget(buttonPound);

        guistate.put("editbox:phoneeditbox",numberBox);
        this.addRenderableWidget(numberBox);

        guistate.put("button:sendCall",callButton);
        this.addRenderableWidget(callButton);
    }

    private void setupButtons(){
        // pre button setup values
        int xFirstOffset = 78;
        int xSecondOffset = xFirstOffset + 20;
        int xThirdOffset = xSecondOffset + 20;

        int yOffsetTopRow = 14;
        int yOffsetMiddleRow = yOffsetTopRow + 20;
        int yOffsetMidBottomRow = yOffsetMiddleRow + 20;
        int yOffsetBottomRow = yOffsetMidBottomRow + 20;

        int button_size_x = 20;
        int button_size_y = 20;

        numberBox = new PhoneEditBox(this.minecraft,this.font,this.leftPos + xSecondOffset,this.topPos + yOffsetBottomRow + 64,64,24,Component.literal("Number:"));
        numberBox.setMaxLength(11);
        numberBox.setBordered(true);
        numberBox.setCanLoseFocus(true);
        numberBox.setTooltip(Tooltip.create(Component.translatable("tooltip.thingamajigs.phone_number_input")
                .withStyle(ChatFormatting.GRAY)));

        Component fake = Component.literal("-1");

        callButton = Button.builder(Component.literal("[:"),e -> {
            Component num = Component.literal("-1");
            if(numberBox.getValue().isEmpty() || numberBox.getValue().isBlank()){
                num = Component.literal("-1");
            }
            else{
                num = Component.literal(numberBox.getValue());
            }
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(32, x, y, z,num));
            PhoneUIButtonMessage.handleButtonAction(entity, 32, x, y, z,num);
            numberBox.setValue("");
        }).bounds(this.leftPos + xSecondOffset - 16, this.topPos + yOffsetBottomRow + 94, button_size_x, button_size_y).build();


        // button 1
        button1 = Button.builder(Component.literal("1"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(0, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 0, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "1");
        }).bounds(this.leftPos + xFirstOffset, this.topPos + yOffsetTopRow, button_size_x, button_size_y).build();

        // button 2
        button2 = Button.builder(Component.literal("2"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(1, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 1, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "2");
        }).bounds(this.leftPos + xSecondOffset, this.topPos + yOffsetTopRow, button_size_x, button_size_y).build();

        // button 3
        button3 = Button.builder(Component.literal("3"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(2, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 2, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "3");
        }).bounds(this.leftPos + xThirdOffset, this.topPos + yOffsetTopRow, button_size_x, button_size_y).build();



        // button 4
        button4 = Button.builder(Component.literal("4"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(3, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 3, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "4");
        }).bounds(this.leftPos + xFirstOffset, this.topPos + yOffsetMiddleRow, button_size_x, button_size_y).build();

        // button 5
        button5 = Button.builder(Component.literal("5"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(4, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 4, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "5");
        }).bounds(this.leftPos + xSecondOffset, this.topPos + yOffsetMiddleRow, button_size_x, button_size_y).build();

        // button 6
        button6 = Button.builder(Component.literal("6"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(5, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 5, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "6");
        }).bounds(
                this.leftPos + xThirdOffset,
                this.topPos + yOffsetMiddleRow,
                button_size_x, button_size_y).build();



        // button 7
        button7 = Button.builder(Component.literal("7"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(6, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 6, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "7");
        }).bounds(
                this.leftPos + xFirstOffset,
                this.topPos + yOffsetMidBottomRow,
                button_size_x, button_size_y).build();

        // button 8
        button8 = Button.builder(Component.literal("8"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(7, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 7, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "8");
        }).bounds(
                this.leftPos + xSecondOffset,
                this.topPos + yOffsetMidBottomRow,
                button_size_x, button_size_y).build();

        // button 9
        button9 = Button.builder(Component.literal("9"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(8, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 8, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "9");
        }).bounds(
                this.leftPos + xThirdOffset,
                this.topPos + yOffsetMidBottomRow,
                button_size_x, button_size_y).build();



        // button 10
        buttonPound = Button.builder(Component.literal("*"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(11, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 11, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "*");
        }).bounds(
                this.leftPos + xFirstOffset,
                this.topPos + yOffsetBottomRow,
                button_size_x, button_size_y).build();

        // button 11
        button0 = Button.builder(Component.literal("0"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(10, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 10, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "0");
        }).bounds(
                this.leftPos + xSecondOffset,
                this.topPos + yOffsetBottomRow,
                button_size_x, button_size_y).build();

        // button 12
        buttonStar = Button.builder(Component.literal("#"),e -> {
            ThingamajigsPacketHandler.INSTANCE.sendToServer(new PhoneUIButtonMessage(9, x, y, z,fake));
            PhoneUIButtonMessage.handleButtonAction(entity, 9, x, y, z,fake);
            numberBox.setValue(numberBox.getValue() + "#");
        }).bounds(
                this.leftPos + xThirdOffset,
                this.topPos + yOffsetBottomRow,
                button_size_x, button_size_y).build();
    }
}

