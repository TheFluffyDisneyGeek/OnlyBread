package me.peiskos.onlybread.mixin;

import net.minecraft.item.FoodComponent;
import net.minecraft.item.FoodComponents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    @Mutable
    @Shadow
    @Final
    public static FoodComponent BREAD;

    @Inject(method = "<clinit>", at=@At("RETURN"))
    private static void clinit(CallbackInfo ci){
        // Makes bread have the same stats as steak
        BREAD = new FoodComponent.Builder().hunger(8).saturationModifier(0.8F).meat().build();
    }
}
