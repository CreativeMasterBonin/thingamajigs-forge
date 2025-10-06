package net.rk.thingamajigs.screen.widget;

import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.widget.ExtendedButton;

public class RevertedButton extends ExtendedButton{
    public RevertedButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler) {
        super(xPos, yPos, width, height, displayString, handler);
    }

    public RevertedButton(int xPos, int yPos, int width, int height, Component displayString, OnPress handler, CreateNarration createNarration) {
        super(xPos, yPos, width, height, displayString, handler, createNarration);
    }

    public RevertedButton(Builder builder) {
        super(builder);
    }

    // no different
}
