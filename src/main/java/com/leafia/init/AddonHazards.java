package com.leafia.init;

import com.hbm.hazard.HazardData;
import static com.hbm.hazard.HazardRegistry.*;

import com.hbm.hazard.HazardEntry;
import com.hbm.hazard.HazardSystem;
import com.hbm.hazard.type.HazardTypeHydroactive;
import com.hbm.hazard.type.IHazardType;
import com.hbm.inventory.OreDictManager;
import com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.inventory.RecipesCommon;
import com.hbm.items.ModItems;
import com.leafia.contents.AddonItems;
import com.leafia.database.AddonOreDictHazards;
import com.leafia.init.hazards.ItemRads;
import com.leafia.init.hazards.types.HazardTypeAlkaline;
import com.leafia.init.hazards.types.HazardTypeSharpEdges;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

public class AddonHazards {
	public static final IHazardType SHARP = new HazardTypeSharpEdges();
	public static final IHazardType ALKALINE = new HazardTypeAlkaline();
	//call after com.hbm.hazard.HazardRegistry.registerItems
	public static void register() {
		//cobalt60.register(ModItems.ingot_co60);
		//HashMap<String,HazardData> dat = HazardSystem.oreMap;
		//Map<String,Float> fuck = dictMap.get(OreDictManager.CO60);
		//System.out.println(fuck);

		ItemRads.actinium227.register(OreDictManager.AC227);
		ItemRads.americium241.register(OreDictManager.AM241);
		ItemRads.americium242.register(OreDictManager.AM242);
		ItemRads.americiumRG.register(OreDictManager.AMRG);
		ItemRads.cobalt60.register(OreDictManager.CO60);
		ItemRads.gold198.register(OreDictManager.AU198);
		ItemRads.lead209.register(OreDictManager.PB209);
		ItemRads.neptunium237.register(OreDictManager.NP237);
		ItemRads.plutonium.register(OreDictManager.PU);
		ItemRads.plutoniumRG.register(OreDictManager.PURG);
		ItemRads.plutonium238.register(OreDictManager.PU238);
		ItemRads.plutonium239.register(OreDictManager.PU239);
		ItemRads.plutonium240.register(OreDictManager.PU240);
		ItemRads.plutonium241.register(OreDictManager.PU241);
		ItemRads.polonium210.register(OreDictManager.PO210);
		ItemRads.radium226.register(OreDictManager.RA226);
		ItemRads.schrabidium326.register(OreDictManager.SA326);
		ItemRads.solinium327.register(OreDictManager.SA327);
		ItemRads.schrabidate.register(OreDictManager.SBD);
		ItemRads.schraranium.register(OreDictManager.SRN);
		ItemRads.technetium99.register(OreDictManager.TC99);
		ItemRads.thorium232.register(OreDictManager.TH232);
		ItemRads.uranium.register(OreDictManager.U);
		ItemRads.uranium233.register(OreDictManager.U233);
		ItemRads.uranium235.register(OreDictManager.U235);
		ItemRads.uranium238.register(OreDictManager.U238);
		ItemRads.waste.copy().multiply(3).register(ModItems.nuclear_waste);
		ItemRads.waste_v.copy().multiply(3).register(ModItems.nuclear_waste_vitrified);
		ItemRads.waste.copy().multiply(0.3).register(ModItems.nuclear_waste_tiny);
		ItemRads.waste_v.copy().multiply(0.3).register(ModItems.nuclear_waste_vitrified_tiny);
		ItemRads.waste.register(ModItems.billet_nuclear_waste);

		HazardSystem.register(AddonItems.dfcsh_cable,makeData(SHARP,5).addEntry(DIGAMMA,0.003));
		HazardSystem.register(AddonItems.dfcsh_core,makeData(HOT,10));
		HazardSystem.register(AddonItems.dfcsh_corner,makeData(SHARP,5).addEntry(DIGAMMA,0.005));
		HazardSystem.register(AddonItems.dfcsh_front,makeData(DIGAMMA,0.004F));
		HazardSystem.register(AddonItems.dfcsh_beam,makeData(SHARP,25).addEntry(DIGAMMA,0.002));

		registerHazard(OreDictManager.LI,new HazardEntry(ALKALINE,1));
		registerHazard(OreDictManager.NA,new HazardEntry(ALKALINE,2));
		registerHazard(AddonOreDict.K,new HazardEntry(ALKALINE,3));
		registerHazard(AddonOreDict.RB,new HazardEntry(ALKALINE,4));
		registerHazard(OreDictManager.CS,new HazardEntry(ALKALINE,5));
		registerHazard(OreDictManager.CS137,new HazardEntry(ALKALINE,5));
		registerHazard(AddonOreDict.FR,new HazardEntry(ALKALINE,6));
		registerHazard(OreDictManager.SR,new HazardEntry(ALKALINE,1.5));
		registerHazard(OreDictManager.SR90,new HazardEntry(ALKALINE,1.5));

		compute((object,data)->{
			// do not fucking modify this array, modify data.entries
			List<HazardEntry> ____________ = new ArrayList<>(data.entries);
			List<HazardEntry> entries = data.entries;
			for (HazardEntry entry : ____________) {
				if (entry.type instanceof HazardTypeHydroactive)
					entries.remove(entry);
			}
		});
	}
	public static void registerHazard(DictFrame frame,HazardEntry... entries) {
		Map<String,Float> map = AddonOreDictHazards.dictMap.get(frame);
		if (map == null) {
			System.out.println("\uD83C\uDF3FCAUTION: dictMap for "+frame.ingot()+" could not be captured");
			return;
		}
		for (Entry<String,Float> entry : map.entrySet()) {
			HazardData data = HazardSystem.oreMap.computeIfAbsent(entry.getKey(), k -> new HazardData());
			for (HazardEntry hazard : entries)
				data.addEntry(hazard);
		}
	}
	public static void computeOreMap(Map<String,HazardData> map,BiConsumer<Object,HazardData> processor) {
		for (Entry<String,HazardData> entry : map.entrySet())
			processor.accept(entry.getKey(),entry.getValue());
	}
	public static void computeItemMap(Map<Item,HazardData> map,BiConsumer<Object,HazardData> processor) {
		for (Entry<Item,HazardData> entry : map.entrySet())
			processor.accept(entry.getKey(),entry.getValue());
	}
	public static void computeStackMap(Map<RecipesCommon.ComparableStack,HazardData> map,BiConsumer<Object,HazardData> processor) {
		for (Entry<RecipesCommon.ComparableStack,HazardData> entry : map.entrySet())
			processor.accept(entry.getKey(),entry.getValue());
	}
	public static void compute(BiConsumer<Object,HazardData> processor) {
		computeOreMap(HazardSystem.oreMap,processor);
		computeItemMap(HazardSystem.itemMap,processor);
		computeStackMap(HazardSystem.stackMap,processor);
	}
	// why'd you had to make these private
	public static HazardData makeData(IHazardType hazard) { return (new HazardData()).addEntry(hazard); }
	public static HazardData makeData(IHazardType hazard, float level) { return (new HazardData()).addEntry(hazard, (double)level); }
	public static HazardData makeData(IHazardType hazard, float level, boolean override) { return (new HazardData()).addEntry(hazard, (double)level, override); }
}
