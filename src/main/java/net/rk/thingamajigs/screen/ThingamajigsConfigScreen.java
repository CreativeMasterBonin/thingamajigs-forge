package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.SpacerElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.rk.thingamajigs.config.ThingamajigsClientConfigs;
import net.rk.thingamajigs.screen.widget.RevertedButton;

@SuppressWarnings("removal")
@OnlyIn(Dist.CLIENT)
public class ThingamajigsConfigScreen extends Screen {
    public static int leftPos = 0;
    public static int topPos = 0;

    public static final int imageWidth = 320;
    public static final int imageHeight = 240;

    public Screen previousScreen;
    private Minecraft mc;
    public Button goBack;
    public GridLayout configGrid;
    public Checkbox blueTabs;
    public ForgeSlider configXPos;
    public ForgeSlider configYPos;
    public Checkbox allowTTS;

    public ThingamajigsConfigScreen(Minecraft instance,Screen screenToGoTo,Component component) {
        super(component);
        previousScreen = screenToGoTo;
        mc = instance;
        leftPos = imageWidth / 2;
        topPos = (imageHeight / 2) - 120;
    }

    public static final ResourceLocation CONFIG_BACKGROUND_TEXTURE = new ResourceLocation("thingamajigs:textures/gui/laser_light_menu.png");

    @Override
    public boolean shouldCloseOnEsc(){
        return false;
    }

    @Override
    protected void init() {
        super.init();
        this.configGrid = new GridLayout(leftPos + 16,topPos + 64);
        this.configGrid.defaultCellSetting().paddingHorizontal(8).paddingBottom(4).alignHorizontallyCenter();
        GridLayout.RowHelper gridHelper = this.configGrid.createRowHelper(1);
        // the button that allows you to return to the main game menu
        this.goBack = new RevertedButton(leftPos + 16,topPos + 32,64,18,Component.literal("<-"),
                onPressSup -> {
                    int newCfgPosX = (int)configXPos.getValue();
                    int newCfgPosY = (int)configYPos.getValue();
                    boolean creativeTabBlueTheme = blueTabs.selected();
                    boolean allowTTSMessages = allowTTS.selected();
                    ThingamajigsClientConfigs.setBlueTabs(creativeTabBlueTheme);
                    ThingamajigsClientConfigs.setConfigOffsetX(newCfgPosX);
                    ThingamajigsClientConfigs.setConfigOffsetY(newCfgPosY);
                    ThingamajigsClientConfigs.setAllowTTSMessages(allowTTSMessages);
                    ThingamajigsClientConfigs.saveConfig();
                    this.mc.setScreen(previousScreen);
                });

        this.addRenderableWidget(this.goBack);
        // the blue tabs option
        this.blueTabs = new Checkbox(leftPos + 16,topPos + 80,18,18,Component.translatable("config.thingamajigs.blue_tabs"),ThingamajigsClientConfigs.CLIENT.blueTabs.get().booleanValue());
        // the bg pos x option
        this.configXPos = new ForgeSlider(leftPos + 16, topPos + 72,120,32,Component.translatable("config.thingamajigs.config_offset_x"),Component.literal(""),
                -32767,32767,ThingamajigsClientConfigs.CLIENT.configOffsetX.get().intValue(),1,1,true);
        // the bg pos y option
        this.configYPos = new ForgeSlider(leftPos + 16, topPos + 72,120,32,Component.translatable("config.thingamajigs.config_offset_y"),Component.literal(""),
                -32767,32767,ThingamajigsClientConfigs.CLIENT.configOffsetY.get().intValue(),1,1,true);
        // the text to speech option
        this.allowTTS = new Checkbox(leftPos + 16,topPos + 90,18,18,Component.translatable("config.thingamajigs.allow_tts_messages"),ThingamajigsClientConfigs.CLIENT.allowTalkingTTSMessages.get().booleanValue());

        // tooltips displayed on top of the options when hovered over
        this.blueTabs.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.blue_tabs")));
        this.configXPos.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.config_offset_x")));
        this.configYPos.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.config_offset_y")));
        this.allowTTS.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.allow_tts_messages")));

        // add the elements to the layout
        gridHelper.addChild(new SpacerElement(0,16));
        gridHelper.addChild(this.blueTabs); // checkbox for enabling blue themed tabs
        gridHelper.addChild(this.configXPos); // the screen offset x
        gridHelper.addChild(this.configYPos); // the screen offset y
        gridHelper.addChild(this.allowTTS); // the text to speech toggle checkbox

        this.configGrid.arrangeElements();
        this.configGrid.visitWidgets(this::addRenderableWidget);
    }

    public static final ResourceLocation FUN_SPRITE = new ResourceLocation(
            "thingamajigs:textures/gui/trickcart.png");

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick){
        guiGraphics.setColor(0.15f, 0.15f, 0.45f, 1.0f);
        guiGraphics.blit(BACKGROUND_LOCATION, 0, 0, 0,
                0.0f, 0.0f,
                this.width, this.height,
                16, 16);
        guiGraphics.setColor(1.0f, 1.0f, 1.0f, 1.0f);

        this.renderBg(guiGraphics,partialTick,mouseX,mouseY);
        super.render(guiGraphics,mouseX,mouseY,partialTick);
    }

    public void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy){
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0,FUN_SPRITE);
        // for fun
        guiGraphics.blit(FUN_SPRITE,
                leftPos - 120,(int)(topPos - 256 * Mth.sin((float)Util.getMillis() / 3200.0f) + 2),
                0,0,
                64,64,64,64);
        RenderSystem.setShaderTexture(0,CONFIG_BACKGROUND_TEXTURE);
        guiGraphics.blit(CONFIG_BACKGROUND_TEXTURE,
                leftPos - 100,topPos,0,0,
                imageWidth,imageHeight,imageWidth,imageHeight);
        RenderSystem.disableBlend();
    }
}
