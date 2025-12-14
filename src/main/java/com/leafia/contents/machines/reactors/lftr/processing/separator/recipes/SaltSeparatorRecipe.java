package com.leafia.contents.machines.reactors.lftr.processing.separator.recipes;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.recipes.loader.GenericRecipe;

public class SaltSeparatorRecipe extends GenericRecipe {
	public FluidType saltType;
	public int saltAmount;
	public SaltSeparatorRecipe(String name) {
		super(name);
	}
}
