package dev.xkmc.fruitsdelight.content.effects;

import dev.xkmc.fruitsdelight.init.data.FDModConfig;
import dev.xkmc.fruitsdelight.init.registrate.FDEffects;
import dev.xkmc.l2library.base.effects.EffectUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class AlienatingAuraEffect extends RangeRenderEffect {

	public AlienatingAuraEffect(MobEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	protected ParticleOptions getParticle() {
		return ParticleTypes.POOF;
	}

	protected int getRange() {
		return FDModConfig.COMMON.alienatingEffectRange.get();
	}

	@Override
	protected int getParticleCount(int lv) {
		return 1;
	}

	@Override
	protected int getPeriod() {
		return 20;
	}

	@Override
	protected void searchEntities(LivingEntity entity, int amplifier) {
		super.searchEntities(entity, amplifier);
		applyEffect(entity, entity, amplifier);
	}

	@Override
	protected void applyEffect(LivingEntity le, LivingEntity target, int amplifier) {
		if (target.hasEffect(FDEffects.ALIENATING.get())) return;
		EffectUtil.addEffect(target, new MobEffectInstance(MobEffects.WEAKNESS,
						40, 0, true, true),
				EffectUtil.AddReason.SKILL, le);
		EffectUtil.addEffect(target, new MobEffectInstance(FDEffects.DISGUSTED.get(),
						40, 0, true, true),
				EffectUtil.AddReason.SKILL, le);
	}

}
