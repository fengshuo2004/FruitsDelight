package dev.xkmc.fruitsdelight.init;

import com.mojang.logging.LogUtils;
import com.simibubi.create.Create;
import dev.ghen.thirst.Thirst;
import dev.xkmc.fruitsdelight.compat.create.CreateCompat;
import dev.xkmc.fruitsdelight.compat.thirst.ThirstCompat;
import dev.xkmc.fruitsdelight.events.BlockEffectToClient;
import dev.xkmc.fruitsdelight.init.data.*;
import dev.xkmc.fruitsdelight.init.food.FDCauldrons;
import dev.xkmc.fruitsdelight.init.food.FDFood;
import dev.xkmc.fruitsdelight.init.food.FDJuice;
import dev.xkmc.fruitsdelight.init.plants.*;
import dev.xkmc.fruitsdelight.init.registrate.*;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.base.effects.EffectSyncEvents;
import dev.xkmc.l2library.repack.registrate.providers.ProviderType;
import dev.xkmc.l2library.serial.network.PacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkDirection;
import org.slf4j.Logger;

@Mod(FruitsDelight.MODID)
@Mod.EventBusSubscriber(modid = FruitsDelight.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FruitsDelight {

	public static final String MODID = "fruitsdelight";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static final L2Registrate REGISTRATE = new L2Registrate(MODID);

	public static final PacketHandler HANDLER = new PacketHandler(new ResourceLocation(MODID, "main"), 1,
			e -> e.create(BlockEffectToClient.class, NetworkDirection.PLAY_TO_CLIENT)
	);

	public static final FDTab TAB = new FDTab();

	static {
		REGISTRATE.creativeModeTab(() -> TAB);
	}

	public FruitsDelight() {
		FDTrees.register();
		FDBushes.register();
		FDMelons.register();
		FDPineapple.register();
		FDItems.register();
		FDEffects.register();
		FDJuice.register();
		FDFood.register();
		FDBlocks.register();
		FDCauldrons.register();
		FDMiscs.register();
		FDModConfig.init();
		FDGLMProvider.register();
		FDFluids.register();

		REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, TagGen::onBlockTagGen);
		REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, TagGen::onItemTagGen);
		REGISTRATE.addDataGenerator(ProviderType.RECIPE, RecipeGen::genRecipes);
		REGISTRATE.addDataGenerator(ProviderType.LANG, LangData::genLang);
	}

	@SubscribeEvent
	public static void commonSetup(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			PlantDataEntry.run(PlantDataEntry::registerConfigs);
			PlantDataEntry.run(PlantDataEntry::registerPlacements);
			PlantDataEntry.run(PlantDataEntry::registerComposter);
			Durian.registerComposter();

			if (FDModConfig.COMMON.enableCauldronRecipe.get())
				FDCauldrons.init();

			if (ModList.get().isLoaded(Create.ID)) {
				CreateCompat.init();
			}

			EffectSyncEvents.TRACKED.add(FDEffects.RAGE_AURA.get());
			EffectSyncEvents.TRACKED.add(FDEffects.HEAL_AURA.get());

			if (ModList.get().isLoaded(Thirst.ID)){
				ThirstCompat.init();
			}
		});
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		boolean server = event.includeServer();
		var gen = event.getGenerator();
		gen.addProvider(server, new FDConfigGen(gen));
		gen.addProvider(server, new FDBiomeTagsProvider(gen, event.getExistingFileHelper()));
		gen.addProvider(server, new FDGLMProvider(gen));
	}

}
