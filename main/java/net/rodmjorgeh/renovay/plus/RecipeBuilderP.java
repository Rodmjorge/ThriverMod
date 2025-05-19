package net.rodmjorgeh.renovay.plus;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.rodmjorgeh.renovay.RenovayMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RecipeBuilder.class)
public interface RecipeBuilderP {

    /**
     * Alright, let's do another rant, because Mojang's coding pisses me off.
     *
     * <p>For some godforsaken reason, whenever a recipe is saved with another name outside solely the item name, it
     * ALWAYS saves on the vanilla folder. Now, you explain why the actual fuck does this happen. Whatever, you can
     * mixin it to fix it. Maybe Mojang should fix it, eh??! Fucking useless.
     */
    @Redirect(method = "save(Lnet/minecraft/data/recipes/RecipeOutput;Ljava/lang/String;)V", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/resources/ResourceLocation;parse(Ljava/lang/String;)Lnet/minecraft/resources/ResourceLocation;"))
    private ResourceLocation save(String id) {
        RecipeBuilder instance = (RecipeBuilder)(Object)this;

        ResourceLocation loc = BuiltInRegistries.ITEM.getKey(instance.getResult());
        return (loc.getNamespace().equals(RenovayMod.MOD_ID))
                ? RenovayMod.createLoc(id)
                : ResourceLocation.parse(id);
    }
}
