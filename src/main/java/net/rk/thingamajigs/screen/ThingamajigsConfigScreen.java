package net.rk.thingamajigs.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.SpacerElement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import net.rk.thingamajigs.config.ThingamajigsClientConfigs;
import net.rk.thingamajigs.screen.widget.RevertedButton;

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

        this.goBack = new RevertedButton(leftPos + 16,topPos + 32,64,18,Component.literal("Go Back"),
                onPressSup -> {
                    int newCfgPosX = (int)configXPos.getValue();
                    int newCfgPosY = (int)configYPos.getValue();
                    boolean creativeTabBlueTheme = blueTabs.selected();
                    ThingamajigsClientConfigs.setBlueTabs(creativeTabBlueTheme);
                    ThingamajigsClientConfigs.setConfigOffsetX(newCfgPosX);
                    ThingamajigsClientConfigs.setConfigOffsetY(newCfgPosY);
                    ThingamajigsClientConfigs.saveConfig();
                    this.mc.setScreen(previousScreen);
                });

        this.addRenderableWidget(this.goBack);

        this.blueTabs = new Checkbox(leftPos + 16,topPos + 80,18,18,Component.translatable("config.thingamajigs.blue_tabs"),ThingamajigsClientConfigs.CLIENT.blueTabs.get().booleanValue());
        this.configXPos = new ForgeSlider(leftPos + 16, topPos + 72,120,32,Component.translatable("config.thingamajigs.config_offset_x"),Component.literal(""),
                -32767,32767,ThingamajigsClientConfigs.CLIENT.configOffsetX.get().intValue(),1,1,true);
        this.configYPos = new ForgeSlider(leftPos + 16, topPos + 72,120,32,Component.translatable("config.thingamajigs.config_offset_y"),Component.literal(""),
                -32767,32767,ThingamajigsClientConfigs.CLIENT.configOffsetY.get().intValue(),1,1,true);

        this.blueTabs.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.blue_tabs")));
        this.configXPos.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.config_offset_x")));
        this.configYPos.setTooltip(Tooltip.create(Component.translatable("tooltip.screen.thingamajigs.thingamajigs_config.config_offset_y")));

        gridHelper.addChild(new SpacerElement(0,16));
        gridHelper.addChild(this.blueTabs);
        gridHelper.addChild(this.configXPos);
        gridHelper.addChild(this.configYPos);

        this.configGrid.arrangeElements();
        this.configGrid.visitWidgets(this::addRenderableWidget);
    }

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
        RenderSystem.setShaderTexture(0,CONFIG_BACKGROUND_TEXTURE);
        leftPos = (imageWidth / 2) + ThingamajigsClientConfigs.CLIENT.configOffsetX.get().intValue();
        topPos = (imageHeight / 2) + ThingamajigsClientConfigs.CLIENT.configOffsetY.get().intValue();
        guiGraphics.blit(CONFIG_BACKGROUND_TEXTURE,
                leftPos,topPos,0,0,
                imageWidth,imageHeight,imageWidth,imageHeight);
        RenderSystem.disableBlend();
    }
}
