package com.leafia.contents.machines.reactors.lftr.processing.separator.recipes;

import com.hbm.inventory.recipes.loader.GenericRecipe;
import com.hbm.inventory.recipes.loader.GenericRecipes;

public class SaltSeparatorRecipes extends GenericRecipes<SaltSeparatorRecipe> {
	public static final SaltSeparatorRecipes INSTANCE = new SaltSeparatorRecipes();

	@Override public int inputItemLimit() { return 4; }
	@Override public int inputFluidLimit() { return 1; }
	@Override public int outputItemLimit() { return 4; }
	@Override public int outputFluidLimit() { return 2; }

	@Override public String getFileName() { return "leafiaSaltSeparator.json"; }

	@Override public SaltSeparatorRecipe instantiateRecipe(String name) { return new SaltSeparatorRecipe(name); }

	@Override
	public void registerDefaults() {

	}
}
