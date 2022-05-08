package com.miki4920.furnacemod.item.custom;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

public class EffectSword extends SwordItem {
    static final int EFFECT_DURATION_IN_TICKS = 200;
    static final int AMPLIFIER = 2;
    MobEffect[] mobEffects;
    int currentEffectIndex = 0;

    public EffectSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties, MobEffect[] mobEffects) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
        this.mobEffects = mobEffects;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(level.isClientSide()) {
            currentEffectIndex += 1;
            if(currentEffectIndex >= mobEffects.length) {
                currentEffectIndex = 0;
            }
            player.sendMessage(new TranslatableComponent("item.furnacemod.obsidian_ingot_sword_use", new TranslatableComponent(mobEffects[currentEffectIndex].getDescriptionId())), player.getUUID());
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(mobEffects[currentEffectIndex], EFFECT_DURATION_IN_TICKS, AMPLIFIER));
        return super.hurtEnemy(stack, target, attacker);
    }
}
