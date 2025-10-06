package net.rk.thingamajigs.item.bases;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BaseGlob extends Item {
    public String FLAVOR_TEXT = "Used to craft decorations."; // set text in constructor
    public static boolean NO_LANG = true; // will not translate by default

    public static Rarity RARE_TYPE = Rarity.COMMON;

    public BaseGlob(Properties p) {
        super(p.rarity(RARE_TYPE));
    }

    public BaseGlob(Properties p, String str, boolean langOpt, Rarity rareType) {
        super(p.rarity(rareType));
        FLAVOR_TEXT = str;
        NO_LANG = langOpt;
    }

    @Override
    public void appendHoverText(ItemStack is, @Nullable Level l, List<Component> lc, TooltipFlag tf) {
        if(NO_LANG){
            lc.add(Component.literal(FLAVOR_TEXT).withStyle(ChatFormatting.GRAY));
        }
        else{
            lc.add(Component.translatable(FLAVOR_TEXT).withStyle(ChatFormatting.GRAY));
        }
    }
}
