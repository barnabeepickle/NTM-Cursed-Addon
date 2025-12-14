package com.leafia.init.recipes;

import com.hbm.blocks.ModBlocks;
import com.hbm.inventory.OreDictManager;
import com.hbm.inventory.OreDictManager.DictFrame;
import com.hbm.items.ItemEnums.EnumCircuitType;
import com.hbm.items.ModItems;
import com.leafia.contents.AddonBlocks;
import com.leafia.contents.AddonItems;
import com.leafia.contents.AddonItems.LeafiaRods;
import com.leafia.contents.control.fuel.nuclearfuel.LeafiaRodItem;
import net.minecraft.item.ItemStack;

import static com.hbm.inventory.OreDictManager.*;
import static com.hbm.inventory.OreDictManager.ZR;
import static com.hbm.main.CraftingManager.addRecipeAuto;
import static com.hbm.main.CraftingManager.addShapelessAuto;

public class AddonCraftingRecipes {
	public static void craftingRegister() {
		addRecipeAuto(new ItemStack(AddonBlocks.spk_cable, 16), " W ", "RRR", " W ", 'W', ModItems.plate_dineutronium, 'R',OreDictManager.MAGTUNG.wireFine());
		addShapelessAuto(new ItemStack(ModBlocks.dfc_receiver, 1), AddonItems.dfcsh_beam, AddonItems.dfcsh_cable, AddonItems.dfcsh_corner, AddonItems.dfcsh_core, OreDictManager.STEEL.heavyBarrel(), AddonItems.dfcsh_front, AddonItems.dfcsh_corner, AddonItems.dfcsh_beam, AddonItems.dfcsh_beam);
		addRecipeAuto(new ItemStack(AddonBlocks.dfc_reinforced, 1), "SDS", "TXL", "SDS", 'S', OSMIRIDIUM.plateWelded(), 'D', ModItems.plate_dineutronium, 'T', ModItems.thermo_unit_endo, 'L', ModBlocks.dfc_receiver, 'X', ModBlocks.block_dineutronium);
		addRecipeAuto(new ItemStack(AddonBlocks.dfc_exchanger, 1), "SCS", "HMP", "SCS", 'S', OSMIRIDIUM.plateWelded(), 'C', ModItems.plate_combine_steel, 'H', ModBlocks.heater_heatex, 'M', ModItems.motor, 'P', ModItems.pipes_steel);

		addRecipeAuto(new ItemStack(AddonItems.fuzzy_identifier, 1), "=  ", "@CS", "@MP", '@', OreDictManager.GOLD.wireFine(), 'P', ANY_PLASTIC.ingot(), '=', DictFrame.fromOne(ModItems.circuit, EnumCircuitType.ADVANCED), 'M', ModItems.motor_desh, 'C', ModItems.coil_gold, 'S', ModItems.screwdriver_desh);

		addRecipeAuto(new ItemStack(LeafiaRods.leafRod, 4), "O", "I", "O", 'O', ZR.billet(), 'I', ZR.nugget());
		for (LeafiaRodItem rod : LeafiaRodItem.fromResourceMap.values()) {
			if (rod.baseItem != null) {
				addShapelessAuto(new ItemStack(rod,1),LeafiaRods.leafRod,new ItemStack(rod.baseItem,1,rod.baseMeta));
				addShapelessAuto(new ItemStack(rod.baseItem,1,rod.baseMeta),rod);
			}
		}
	}
}
