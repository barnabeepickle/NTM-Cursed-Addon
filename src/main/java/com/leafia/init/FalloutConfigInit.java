package com.leafia.init;

import com.hbm.blocks.ModBlocks;
import com.hbm.config.FalloutConfigJSON;
import com.hbm.config.FalloutConfigJSON.FalloutEntry;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.Tuple;

import java.lang.reflect.Field;
import java.util.List;

public class FalloutConfigInit {
	public static final List<FalloutEntry> entries = FalloutConfigJSON.entries;
	public static void onInit() {
		removeByMatchingMaterial(Material.GRASS);
		removeByMatchingMaterial(Material.GROUND);
		removeByMatchingMaterial(Material.SAND);
		removeByPrimary(ModBlocks.waste_trinitite);
		removeByPrimary(ModBlocks.waste_trinitite_red);
		for (int i = 1; i <= 3; i++) {
			int m = 7-i;
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_trinitite_red.getStateFromMeta(m), 1)
					.primaryChance(0.05)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchesState(Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND))
					.build());
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_trinitite.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.primaryChance(0.05)
					.opaque(true)
					.solid(true)
					.matchesState(Blocks.SAND.getDefaultState())
					.build());
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_sand_red.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchesState(Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND))
					.build());
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_sand.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchesState(Blocks.SAND.getDefaultState())
					.build());
			entries.add(0,FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_terracotta.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchesState(Blocks.HARDENED_CLAY.getDefaultState())
					.build());
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_dirt.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchingMaterial(Material.GROUND)
					.build());
			entries.add(FalloutEntry.builder()
					.addPrimary(ModBlocks.waste_earth.getStateFromMeta(m), 1)
					.max(i * 15+25)
					.opaque(true)
					.solid(true)
					.matchingMaterial(Material.GRASS)
					.build());
		}
	}
	static final Field matchesMaterial;
	static final Field primary;
	static {
		try {
			matchesMaterial = FalloutEntry.class.getDeclaredField("matchesMaterial");
			matchesMaterial.setAccessible(true);
			primary = FalloutEntry.class.getDeclaredField("primaryBlocks");
			primary.setAccessible(true);
		} catch (NoSuchFieldException e) {
			throw new LeafiaDevFlaw(e);
		}
	}
	static void removeByMatchingMaterial(Material material) {
		for (int i = 0; i < entries.size();) {
			FalloutEntry entry = entries.get(i);
			try {
				if (material.equals(matchesMaterial.get(entry))) {
					entries.remove(i);
					continue;
				}
			} catch (IllegalAccessException e) {
				throw new LeafiaDevFlaw(e);
			}
			i++;
		}
	}
	static void removeByPrimary(Block block) {
		for (int i = 0; i < entries.size();) {
			FalloutEntry entry = entries.get(i);
			try {
				List<Tuple<IBlockState, Integer>> primaryBlocks = (List<Tuple<IBlockState, Integer>>)primary.get(entry);
				boolean doRemove = false;
				for (Tuple<IBlockState,Integer> tuple : primaryBlocks) {
					if (block.equals(tuple.getFirst().getBlock())) {
						doRemove = true;
						break;
					}
				}
				if (doRemove) {
					entries.remove(i);
					continue;
				}
			} catch (IllegalAccessException e) {
				throw new LeafiaDevFlaw(e);
			}
			i++;
		}
	}
}
