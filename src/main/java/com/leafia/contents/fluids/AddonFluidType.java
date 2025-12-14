package com.leafia.contents.fluids;

import com.hbm.inventory.fluid.FluidType;
import com.hbm.inventory.fluid.trait.FluidTrait;
import com.hbm.render.misc.EnumSymbol;
import com.leafia.contents.AddonFluids;

import java.util.Locale;
import java.util.Map.Entry;
import java.util.function.Function;

public class AddonFluidType extends FluidType {
	public static int id = 0;
	public AddonFluidType(String name,int color,int p,int f,int r,EnumSymbol symbol) {
		this(name,color,p,f,r,symbol,name);
	}
	public AddonFluidType(String name,FluidType base) {
		this(name,base.getColor(),base.poison,base.flammability,base.reactivity,base.symbol,base.getName());
		temperature = base.temperature;
		copyTraits(base,(trait)->true);
	}
	public AddonFluidType(String name,FluidType base,Function<FluidTrait,Boolean> copyFunction) {
		this(name,base.getColor(),base.poison,base.flammability,base.reactivity,base.symbol,base.getName());
		temperature = base.temperature;
		copyTraits(base,copyFunction);
	}
	public AddonFluidType(String name,int color,int p,int f,int r,EnumSymbol symbol,String texFluid) {
		super(name,color,p,f,r,symbol,texFluid.toLowerCase(Locale.US),0xFFFFFF,1121+(id++) /* eevee */,null);
		AddonFluids.metaOrderPointer.add(this);
	}
	public void copyTraits(FluidType other,Function<FluidTrait,Boolean> copyFunction) {
		for (Entry<Class<? extends FluidTrait>,FluidTrait> entry : other.traits.entrySet()) {
			if (copyFunction.apply(entry.getValue()))
				this.traits.put(entry.getKey(),entry.getValue());
		}
	}
}
