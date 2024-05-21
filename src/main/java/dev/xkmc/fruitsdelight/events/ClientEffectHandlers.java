package dev.xkmc.fruitsdelight.events;

import dev.xkmc.fruitsdelight.content.item.FDFoodItem;
import dev.xkmc.fruitsdelight.init.FruitsDelight;
import dev.xkmc.fruitsdelight.init.registrate.FDEffects;
import dev.xkmc.l2library.util.Proxy;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FruitsDelight.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEffectHandlers {
	public static void suspiciousBlockAnimate(BlockState state, Level level, BlockPos pos, RandomSource random) {
		var player = Proxy.getClientPlayer();
		if (player == null) return;
		if (!player.hasEffect(FDEffects.SUSPICIOUS_SMELL.get())) return;
		BlockState curState = state;
		BlockPos curPos = pos;
		for (int i = 0; i < 16; i++) {
			if (curState.isAir()) {
				for (int j = 0; j < 2; j++) {
					double d0 = curPos.getX() + random.nextDouble();
					double d1 = curPos.getY() + 0.2D;
					double d2 = curPos.getZ() + random.nextDouble();
					level.addParticle(ParticleTypes.HAPPY_VILLAGER, d0, d1, d2, 0, 0, 0);
				}
				for (int j = 0; j < 2; j++) {
					double d0 = curPos.getX() + random.nextDouble();
					double d1 = curPos.getY() + 0.2D;
					double d2 = curPos.getZ() + random.nextDouble();
					level.addParticle(ParticleTypes.EFFECT, d0, d1, d2, 0, 0, 0);
				}

				break;
			}
			curPos = curPos.above();
			curState = level.getBlockState(curPos);
		}
	}

	@SubscribeEvent
	public static void onItemTooltip(ItemTooltipEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity == null || !entity.hasEffect(FDEffects.SUSPICIOUS_SMELL.get())) return;
		ItemStack stack = event.getItemStack();
		if (!stack.is(Items.SUSPICIOUS_STEW)) return;
		FoodProperties food = stack.getFoodProperties(entity);
		if (food != null && !food.getEffects().isEmpty()) {
			FDFoodItem.getFoodEffects(food, event.getToolTip());
		}
	}

}
