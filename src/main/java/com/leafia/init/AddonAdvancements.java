package com.leafia.init;

import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.util.Set;
import static com.hbm.main.AdvancementManager.*;

public class AddonAdvancements {
	public static Advancement openpwr;
	public static Advancement nukepwr;
	public static Advancement nukebwr;
	public static Advancement genericfuel;
	public static void init(MinecraftServer serv) {
		AdvancementManager adv = serv.getAdvancementManager();
		openpwr = get(adv,"openpwr");
		nukepwr = get(adv,"nukepwr");
		nukebwr = get(adv,"nukebwr");
		genericfuel = get(adv,"genericfuel");
		refactor();
	}
	static Field parent;
	static Field children;
	static {
		parent = ObfuscationReflectionHelper.findField(Advancement.class,"field_192076_a");
		children = ObfuscationReflectionHelper.findField(Advancement.class,"field_192082_g"); //Advancement.class.getDeclaredField("children");
		parent.setAccessible(true);
		children.setAccessible(true);
	}
	static void refactorAdvancement(Advancement adv,Advancement newParent) {
		try {
			((Set<Advancement>)children.get(adv)).remove(adv);
			parent.set(adv,newParent);
			//((Set<Advancement>)children.get(newParent)).add(adv);
			System.out.println("SUCCESS???");
		} catch (IllegalAccessException e) {
			throw new LeafiaDevFlaw(e);
		}
	}
	public static void refactor() {

	}
	static Advancement get(AdvancementManager manager,String path) {
		return manager.getAdvancement(new ResourceLocation("leafia",path));
	}
}
