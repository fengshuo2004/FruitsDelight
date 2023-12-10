package dev.xkmc.fruitsdelight.init.data;

import dev.xkmc.fruitsdelight.content.recipe.JellyCraftShapelessBuilder;
import dev.xkmc.fruitsdelight.init.FruitsDelight;
import dev.xkmc.fruitsdelight.init.food.*;
import dev.xkmc.fruitsdelight.init.registrate.FDBlocks;
import dev.xkmc.l2library.base.ingredients.PotionIngredient;
import dev.xkmc.l2library.repack.registrate.providers.RegistrateRecipeProvider;
import dev.xkmc.l2library.repack.registrate.util.DataIngredient;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.function.BiFunction;

public class RecipeGen {

	public static void genRecipes(RegistrateRecipeProvider pvd) {
		PlantDataEntry.gen(pvd, PlantDataEntry::genRecipe);
		FDCrates.genRecipes(pvd);
		{
			{
				jelly(pvd, FDFood.APPLE_JELLY, 2);
				jelly(pvd, FDFood.BLUEBERRY_JELLY, 4);
				jelly(pvd, FDFood.GLOWBERRY_JELLY, 4);
				jelly(pvd, FDFood.MANGO_JELLY, 2);
				jelly(pvd, FDFood.HAMIMELON_JELLY, 4);
				jelly(pvd, FDFood.MELON_JELLY, 4);
				jelly(pvd, FDFood.HAWBERRY_JELLY, 4);
				jelly(pvd, FDFood.LYCHEE_JELLY, 4);
				jelly(pvd, FDFood.ORANGE_JELLY, 2);
				jelly(pvd, FDFood.PEACH_JELLY, 2);
				jelly(pvd, FDFood.PEAR_JELLY, 2);
				jelly(pvd, FDFood.PERSIMMON_JELLY, 2);
				jelly(pvd, FDFood.PINEAPPLE_JELLY, 4);
				jelly(pvd, FDFood.SWEETBERRY_JELLY, 4);
				jelly(pvd, FDFood.LEMON_JELLY, 2);
			}

			{
				juice(pvd, FDFood.HAMIMELON_JUICE, 4, false, false);
				juice(pvd, FDFood.ORANGE_JUICE, 2, true, false);
				juice(pvd, FDFood.PEAR_JUICE, 2, true, false);
				juice(pvd, FDFood.LEMON_JUICE, 2, true, false);
				juice(pvd, FDFood.HAWBERRY_TEA, 4, true, true);
				juice(pvd, FDFood.MANGO_TEA, 2, true, true);
				juice(pvd, FDFood.PEACH_TEA, 2, true, true);
				juice(pvd, FDFood.MANGOSTEEN_TEA, 2, true, true);
			}

			{
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FDBushes.LEMON.getFruit()),
								Ingredient.of(ForgeTags.TOOLS_KNIVES), FDFood.LEMON_SLICE.item.get(), 4, 1)
						.addResult(FDBushes.LEMON.getSeed())
						.build(pvd, new ResourceLocation(FruitsDelight.MODID, "lemon_cutting"));

				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(FDTrees.ORANGE.getFruit()),
								Ingredient.of(ForgeTags.TOOLS_KNIVES), FDFood.ORANGE_SLICE.item.get(), 4, 1)
						.build(pvd, new ResourceLocation(FruitsDelight.MODID, "orange_cutting"));
			}

			{
				smoking(pvd, FDFood.BAKED_PEAR);
				smoking(pvd, FDFood.DRIED_PERSIMMON);
			}

			{

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.HAWBERRY_ROLL.item.get(), 1)::unlockedBy,
						FDFood.HAWBERRY_SHEET.item.get())
						.requires(FDFood.HAWBERRY_SHEET.item.get())
						.requires(TagGen.JELLY)
						.save(pvd);

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.MANGO_SALAD.item.get(), 1)::unlockedBy,
						FDFood.MANGO_SALAD.getFruit())
						.requires(Items.BOWL)
						.requires(FDTrees.MANGO.getFruit())
						.requires(TagGen.JELLY)
						.save(pvd);

				unlock(pvd, new JellyCraftShapelessBuilder(FDFood.JELLY_BREAD.item.get(), 1)::unlockedBy,
						Items.BREAD)
						.requires(Items.BREAD)
						.requires(TagGen.JELLY)
						.save(pvd);
			}

			{

				unlock(pvd, ShapelessRecipeBuilder.shapeless(FDFood.HAWBERRY_SHEET.item.get())::unlockedBy,
						FDFood.HAWBERRY_SHEET.getFruit())
						.requires(FDTrees.HAWBERRY.getFruit(), 3)
						.requires(Items.SUGAR)
						.save(pvd);


				unlock(pvd, ShapelessRecipeBuilder.shapeless(FDFood.HAWBERRY_STICK.item.get())::unlockedBy,
						FDFood.HAWBERRY_STICK.getFruit())
						.requires(FDTrees.HAWBERRY.getFruit(), 3)
						.requires(Items.STICK)
						.requires(Items.SUGAR, 2)
						.save(pvd);

				unlock(pvd, ShapelessRecipeBuilder.shapeless(FDFood.HAMIMELON_SHAVED_ICE.item.get())::unlockedBy,
						FDFood.HAMIMELON_SHAVED_ICE.getFruit())
						.requires(Items.GLASS_BOTTLE)
						.requires(FDFood.HAMIMELON_SHAVED_ICE.getFruit(), 2)
						.requires(ForgeTags.MILK_BOTTLE)
						.requires(Items.SUGAR)
						.requires(Items.ICE)
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(FDFood.PERSIMMON_COOKIE.item.get(), 8)::unlockedBy,
						FDFood.PERSIMMON_COOKIE.getFruit())
						.pattern("ABA")
						.define('A', Items.WHEAT)
						.define('B', FDFood.DRIED_PERSIMMON.item.get())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(FDFood.CRANBERRY_COOKIE.item.get(), 8)::unlockedBy,
						FDFood.CRANBERRY_COOKIE.getFruit())
						.pattern("ABA")
						.define('A', Items.WHEAT)
						.define('B', FDFood.CRANBERRY_COOKIE.getFruit())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(FDFood.LEMON_COOKIE.item.get(), 8)::unlockedBy,
						FDFood.LEMON_COOKIE.getFruit())
						.pattern(" C ").pattern("ABA")
						.define('C', ForgeTags.MILK_BOTTLE)
						.define('A', Items.WHEAT)
						.define('B', FDFood.LEMON_COOKIE.getFruit())
						.save(pvd);

				unlock(pvd, ShapedRecipeBuilder.shaped(FDFood.HAMIMELON_POPSICLE.item.get(), 1)::unlockedBy,
						FDFood.HAMIMELON_POPSICLE.getFruit())
						.pattern(" MM").pattern("IMM").pattern("SI ")
						.define('I', Items.ICE)
						.define('M', FDFood.HAMIMELON_POPSICLE.getFruit())
						.define('S', Items.STICK)
						.save(pvd);

			}
			{

				CookingPotRecipeBuilder.cookingPotRecipe(FDBlocks.PINEAPPLE_RICE.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(FDPineapple.PINEAPPLE.getWholeFruit())
						.addIngredient(Ingredient.of(ForgeTags.GRAIN_RICE), 3)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.addIngredient(Tags.Items.EGGS)
						.build(pvd);


				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LYCHEE_CHERRY_TEA.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.LYCHEE_CHERRY_TEA.getFruit(), 2)
						.addIngredient(Items.SPORE_BLOSSOM)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.MANGO_MILKSHAKE.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.MANGO_MILKSHAKE.getFruit())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BLUEBERRY_CUSTARD.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.BLUEBERRY_CUSTARD.getFruit(), 2)
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BELLINI_COCKTAIL.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
						.addIngredient(FDFood.PEACH_JELLY.getFruit(), 2)
						.addIngredient(Items.SUGAR)
						.addIngredient(Items.ICE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PINEAPPLE_PIE.item.get(), 2, 200, 0.1f)
						.addIngredient(FDFood.PINEAPPLE_PIE.getFruit(), 2)
						.addIngredient(ModItems.PIE_CRUST.get())
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LEMON_TART.item.get(), 2, 200, 0.1f)
						.addIngredient(FDFood.LEMON_TART.getFruit())
						.addIngredient(ModItems.PIE_CRUST.get())
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.build(pvd);


				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.BLUEBERRY_MUFFIN.item.get(), 2, 200, 0.1f)
						.addIngredient(FDFood.BLUEBERRY_MUFFIN.getFruit(), 2)
						.addIngredient(ModItems.WHEAT_DOUGH.get())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.CRANBERRY_MUFFIN.item.get(), 2, 200, 0.1f)
						.addIngredient(FDFood.CRANBERRY_MUFFIN.getFruit(), 2)
						.addIngredient(ModItems.WHEAT_DOUGH.get())
						.addIngredient(ForgeTags.MILK_BOTTLE)
						.addIngredient(Tags.Items.EGGS)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.ORANGE_CHICKEN.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_CHICKEN)
						.addIngredient(FDFood.ORANGE_SLICE.item.get(), 4)
						.addIngredient(Items.SUGAR)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.ORANGE_MARINATED_PORK.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(FDFood.ORANGE_SLICE.item.get(), 4)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PEAR_WITH_ROCK_SUGAR.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(Items.SUGAR, 4)
						.addIngredient(FDTrees.PEAR.getFruit(), 2)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.MANGOSTEEN_CAKE.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(Items.WHEAT, 2)
						.addIngredient(Items.SUGAR, 2)
						.addIngredient(FDTrees.MANGOSTEEN.getFruit(), 2)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.LYCHEE_CHICKEN.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_CHICKEN)
						.addIngredient(FDTrees.LYCHEE.getFruit(), 4)
						.addIngredient(ForgeTags.SALAD_INGREDIENTS_CABBAGE)
						.build(pvd);

				CookingPotRecipeBuilder.cookingPotRecipe(FDFood.PINEAPPLE_MARINATED_PORK.item.get(), 1, 200, 0.1f, Items.BOWL)
						.addIngredient(ForgeTags.RAW_PORK)
						.addIngredient(FDPineapple.PINEAPPLE.getSlice(), 4)
						.addIngredient(Items.CARROT)
						.build(pvd);
			}

		}

	}

	private static void jelly(RegistrateRecipeProvider pvd, FDFood jelly, int count) {
		CookingPotRecipeBuilder.cookingPotRecipe(jelly.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE)
				.addIngredient(jelly.getFruit(), count)
				.addIngredient(Items.SUGAR)
				.addIngredient(FDFood.LEMON_SLICE.item.get())
				.build(pvd);
	}

	private static void juice(RegistrateRecipeProvider pvd, FDFood juice, int count, boolean tea, boolean hot) {
		if (hot) {
			var e = CookingPotRecipeBuilder.cookingPotRecipe(juice.item.get(), 1, 200, 0.1f, Items.GLASS_BOTTLE);
			if (tea) {
				e.addIngredient(Items.SUGAR);
				e.addIngredient(ItemTags.LEAVES);
			}
			e.addIngredient(juice.getFruit(), count);
			e.build(pvd);
		} else {
			var e = unlock(pvd, ShapelessRecipeBuilder.shapeless(juice.item.get())::unlockedBy, juice.getFruit());
			if (tea) {
				e.requires(new PotionIngredient(Potions.WATER));
				e.requires(Items.SUGAR);
			} else {
				e.requires(Items.GLASS_BOTTLE);
			}
			e.requires(juice.getFruit(), count);
			e.save(pvd);
		}
	}

	private static void smoking(RegistrateRecipeProvider pvd, FDFood food) {
		pvd.smoking(DataIngredient.items(food.getFruit()), food.item, 0.1f);
		pvd.campfire(DataIngredient.items(food.getFruit()), food.item, 0.1f);
	}

	public static <T> T unlock(RegistrateRecipeProvider pvd, BiFunction<String, InventoryChangeTrigger.TriggerInstance, T> func, Item item) {
		return func.apply("has_" + pvd.safeName(item), DataIngredient.items(item).getCritereon(pvd));
	}


}
