package com.fpsboost.mixin;

import com.fpsboost.FpsBoostConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

/**
 * Caps chunk rebuild count per frame to avoid frame-time spikes during world generation.
 */
@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @ModifyArg(
        method = "render",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/chunk/ChunkBuilder;upload()I"),
        index = 0
    )
    private int fpsboost$capChunkUpdates(int original) {
        return Math.min(original, FpsBoostConfig.INSTANCE.maxChunkUpdatesPerTick);
    }
}
