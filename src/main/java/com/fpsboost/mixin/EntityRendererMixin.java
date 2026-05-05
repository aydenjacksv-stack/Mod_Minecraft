package com.fpsboost.mixin;

import com.fpsboost.FpsBoostConfig;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Skips rendering entities that are outside the camera frustum.
 */
@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void fpsboost$cullOutOfFrustum(T entity, Frustum frustum,
            double cameraX, double cameraY, double cameraZ,
            CallbackInfoReturnable<Boolean> cir) {
        if (!FpsBoostConfig.INSTANCE.entityCulling) return;
        if (!frustum.isVisible(entity.getBoundingBox())) {
            cir.setReturnValue(false);
        }
    }
}
