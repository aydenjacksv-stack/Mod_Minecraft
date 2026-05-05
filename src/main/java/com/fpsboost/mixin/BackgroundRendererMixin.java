package com.fpsboost.mixin;

import com.fpsboost.FpsBoostConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Reduces fog recalculation frequency to save GPU time.
 */
@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    private static int fpsboost$fogTick = 0;

    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void fpsboost$skipFogRecalc(Camera camera, BackgroundRenderer.FogType fogType,
            float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (!FpsBoostConfig.INSTANCE.fogOptimizer) return;
        // Recalculate fog only every 4 frames instead of every frame
        if (fpsboost$fogTick++ % 4 != 0) {
            ci.cancel();
        }
    }
}
