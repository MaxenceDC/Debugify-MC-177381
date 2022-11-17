package dev.isxander.debugify.mixins.basic.mc206922;

import dev.isxander.debugify.fixes.BugFix;
import dev.isxander.debugify.fixes.FixCategory;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@BugFix(id = "MC-206922", category = FixCategory.BASIC, env = BugFix.Env.SERVER)
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends EntityMixin {
    @Override
    protected void struckByLightningHead(ServerLevel world, LightningBolt lightning, CallbackInfo ci) {
        if (tickCount <= 8) {
            ci.cancel();
        }
    }
}
