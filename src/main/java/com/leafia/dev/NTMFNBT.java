package com.leafia.dev;

import com.hbm.inventory.fluid.tank.FluidTankNTM;
import com.leafia.contents.fluids.AddonFluids;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Fuck off NTMF
 */
@Deprecated
public class NTMFNBT {
	public static NBTTagCompound getNBT(FluidTankNTM tank) {
		NBTTagCompound tag;
		try {
			tag = (NBTTagCompound)(tank.getClass().getField("addon_nbt").get(tank));
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LeafiaDevFlaw flaw = new LeafiaDevFlaw(e.toString());
			flaw.appendStackTrace(e);
			throw flaw;
		}
		return tag;
	}
	public static void setNBT(FluidTankNTM tank,NBTTagCompound nbt) {
		try {
			tank.getClass().getField("addon_nbt").set(tank,nbt);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			LeafiaDevFlaw flaw = new LeafiaDevFlaw(e.toString());
			flaw.appendStackTrace(e);
			throw flaw;
		}
	}

	/**
	 * Should be called while calculating fluid distribution in networks to see if tank contents are compatible.
	 * @param sending the sending tank
	 * @param receiving the receiving tank
	 * @return true if compatible
	 */
	public boolean areTagsCompatible(FluidTankNTM sending,FluidTankNTM receiving) {
		NBTTagCompound sendingTag = getNBT(sending);
		NBTTagCompound receivingTag = getNBT(receiving);
		if (receivingTag == null) return true;
		if (receivingTag.equals(sendingTag)) return true;
		//if (sending.getTankType().equals(AddonFluids.FLUORIDE)) return true; for future; MSR
		return false;
	}

	/**
	 * Should be called whilst doing transferFluid.
	 * For example MSR would need this to calculate combined temperature and fuel mixture.
	 * @param sending the sending tank
	 * @param receiving the receiving tank
	 */
	public void transferTags(FluidTankNTM sending,FluidTankNTM receiving) {
		NBTTagCompound sendingTag = getNBT(sending);
		NBTTagCompound receivingTag = getNBT(receiving);
		if (receivingTag == null) {
			receivingTag = new NBTTagCompound();
			for (String s : sendingTag.getKeySet())
				receivingTag.setTag(s,sendingTag.getTag(s));
		}
	}
}
