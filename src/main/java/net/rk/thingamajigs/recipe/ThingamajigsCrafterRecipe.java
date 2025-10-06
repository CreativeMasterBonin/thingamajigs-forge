package net.rk.thingamajigs.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.rk.thingamajigs.Thingamajigs;
import net.rk.thingamajigs.block.ThingamajigsBlocks;
import org.jetbrains.annotations.Nullable;

public class ThingamajigsCrafterRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;

    public ThingamajigsCrafterRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
    }


    @Override
    public boolean matches(SimpleContainer sc, Level l) {
        if(l.isClientSide()){
            return false;
        }

        // get JSON items, are they the same as in the inventory?
        boolean itemOneOk = inputItems.get(0).test(sc.getItem(0));
        boolean itemTwoOk = inputItems.get(1).test(sc.getItem(1));
        boolean itemThreeOk = inputItems.get(2).test(sc.getItem(2));
        boolean itemFourOk = inputItems.get(3).test(sc.getItem(3));

        return itemOneOk && itemTwoOk && itemThreeOk && itemFourOk;
    }

    @Override
    public ItemStack assemble(SimpleContainer sc, RegistryAccess ra) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i1, int i2) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess ra1) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ThingamajigsBlocks.NOT_QUITE_MENGER.get());
    }

    public static class Type implements RecipeType<ThingamajigsCrafterRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "thingamajigs_crafting";
    }

    public static class Serializer implements RecipeSerializer<ThingamajigsCrafterRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation((Thingamajigs.MOD_ID), "thingamajigs_crafting");

        @Override
        public ThingamajigsCrafterRecipe fromJson(ResourceLocation recipeId, JsonObject serializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(serializedRecipe,"output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(serializedRecipe,"ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(4,Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i,Ingredient.fromJson(ingredients.get(i)));
            }

            return new ThingamajigsCrafterRecipe(inputs,output,recipeId);
        }

        @Override
        public @Nullable ThingamajigsCrafterRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf fbuff) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(fbuff.readInt(),Ingredient.EMPTY);

            for(int i = 0; i < inputs.size(); i++){
                inputs.set(i, Ingredient.fromNetwork(fbuff));
            }

            ItemStack output = fbuff.readItem();

            return new ThingamajigsCrafterRecipe(inputs,output,recipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf fbuff, ThingamajigsCrafterRecipe tcr) {
            fbuff.writeInt(tcr.inputItems.size());

            for(Ingredient ingredient : tcr.getIngredients()){
                ingredient.toNetwork(fbuff);
            }
            fbuff.writeItemStack(tcr.getResultItem(null),false);
        }
    }
}
