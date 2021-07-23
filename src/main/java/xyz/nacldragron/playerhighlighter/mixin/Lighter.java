package xyz.nacldragron.playerhighlighter.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class Lighter {
    @Inject(method = "hasOutline", at = @At(value = "RETURN"), cancellable = true)
    private void checkPlayerEntity(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (!cir.getReturnValue()) {
            if (entity instanceof PlayerEntity){
                cir.setReturnValue(true);
            }
        }
    }
}