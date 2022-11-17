package dev.isxander.debugify.mixins.basic.mc129909;

import com.mojang.authlib.GameProfile;
import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@BugFix(id = "MC-129909", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player {
    public ServerPlayerMixin(Level world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    /**
     * Also fixes
     * <a href="https://bugs.mojang.com/browse/MC-81773">MC-81773</a>, <a href="https://bugs.mojang.com/browse/MC-206705">MC-206705</a>
     */
    @Inject(method = "setGameMode", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;stopRiding()V"))
    private void onChangeToSpectator(GameType gameMode, CallbackInfoReturnable<Boolean> cir) {
        releaseUsingItem();
    }
}
