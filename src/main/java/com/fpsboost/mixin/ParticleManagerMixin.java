package com.fpsboost.mixin;

import com.fpsboost.FpsBoostConfig;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Queue;

/**
 * Drops new particles when the active count exceeds the configured maximum.
 */
@Mixin(ParticleManager.class)
public class ParticleManagerMixin {

    @Shadow private Queue<Particle> particles;

    @Inject(method = "addParticle(Lnet/minecraft/client/particle/Particle;)V",
            at = @At("HEAD"), cancellable = true)
    private void fpsboost$limitParticles(Particle particle, CallbackInfo ci) {
        if (particles.size() >= FpsBoostConfig.INSTANCE.maxParticles) {
            ci.cancel();
        }
    }
}
