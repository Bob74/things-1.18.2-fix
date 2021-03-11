package com.glisco.things.mixin;

import com.glisco.things.items.ThingsItems;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "eatFood", at = @At("TAIL"))
    public void onConsume(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {

        if (!stack.getItem().equals(Items.POISONOUS_POTATO)) return;

        PlayerEntity player = (PlayerEntity) (Object) this;
        if (!TrinketsApi.getTrinketsInventory(player).containsAny(Collections.singleton(ThingsItems.LUCK_OF_THE_IRISH))) return;

        player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.LUCK, 400, 0));
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 2, 0));
        player.removeStatusEffect(StatusEffects.POISON);

    }


}
