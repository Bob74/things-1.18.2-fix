package com.glisco.things.mixin;

import com.glisco.things.items.ThingsItems;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;

@Mixin(PotionItem.class)
public class PotionItemMixin {

    @Inject(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;incrementStat(Lnet/minecraft/stat/Stat;)V"), cancellable = true)
    public void consume(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        PlayerEntity playerEntity = (PlayerEntity) user;

        if (world.random.nextDouble() > 0.75) return;
        if (!TrinketsApi.getTrinketsInventory(playerEntity).containsAny(Collections.singleton(ThingsItems.PLACEBO))) return;

        playerEntity.incrementStat(Stats.USED.getOrCreateStat((PotionItem) (Object) this));
        cir.setReturnValue(stack);
    }

}
