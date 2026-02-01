package net.rk.thingamajigs.screen.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class PhoneEditBox extends EditBox{
    Minecraft mc;

    public PhoneEditBox(Minecraft instance,Font font, int x, int y, int sizeX, int sizeY, Component title) {
        super(font, x, y, sizeX, sizeY, title);
        this.mc = instance;
        this.setResponder(this::onEdited);
    }

    private void onEdited(String s) {

    }

    public PhoneEditBox(Minecraft instance,Font font, int x, int y, int sizeX, int sizeY, @Nullable EditBox editbox, Component title) {
        super(font, x, y, sizeX, sizeY, editbox, title);
        this.mc = instance;
    }
}
