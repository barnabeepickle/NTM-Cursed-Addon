package com.leafia.dev.items.itembase;

import com.leafia.dev.hazards.ItemRads.MultiRadContainer;

public class AddonItemHazardBase extends AddonItemBase {
	public AddonItemHazardBase(String s) {
		super(s);
	}
	public MultiRadContainer radContainer = null;
	public double digamma;
	public int fire;
	public int cryogenic;
	public int toxic;
	public boolean blinding;
	public int asbestos;
	public int coal;
	public int alkaline;
	public double explosive;
	public double sharp;
	public AddonItemHazardBase addRad(MultiRadContainer container) { radContainer = container; return this; }
	public AddonItemHazardBase addDigamma(double value) { digamma = value; return this; }
	public AddonItemHazardBase addCryogenic(int value) { cryogenic = value; return this; }
	public AddonItemHazardBase addToxic(int value) { toxic = value; return this; }
	public AddonItemHazardBase addBlinding() { blinding = true; return this; }
	public AddonItemHazardBase addAsbestos(int value) { asbestos = value; return this; }
	public AddonItemHazardBase addCoal(int value) { coal = value; return this; }
	public AddonItemHazardBase addAlkaline(int value) { alkaline = value; return this; }
	public AddonItemHazardBase addExplosive(double value) { explosive = value; return this; }
	public AddonItemHazardBase addSharp(double value) { sharp = value; return this; }
}
