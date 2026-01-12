package com.leafia.contents.control.battery;

import com.hbm.items.machine.ItemBatteryPack.EnumBatteryPack;
import net.minecraftforge.common.util.EnumHelper;

public class AddonEnumBatteryPack {
	public static final EnumBatteryPack BATTERY_DESH =
			EnumHelper.addEnum(
					EnumBatteryPack.class,
					"BATTERY_DESH",
					new Class[]{String.class,long.class,boolean.class},
					"battery_desh",125_000L,false
			);
	public static final EnumBatteryPack BATTERY_EUPHEMIUM =
			EnumHelper.addEnum(
					EnumBatteryPack.class,
					"BATTERY_EUPHEMIUM",
					new Class[]{String.class,long.class,boolean.class},
					"battery_euphemium",500_000L,false
			);
	public static final EnumBatteryPack BATTERY_SLOP =
			EnumHelper.addEnum(
					EnumBatteryPack.class,
					"BATTERY_SLOP",
					new Class[]{String.class,long.class,long.class},
					"battery_slop",833_333_333L,20 * 60 * 10
			);
	public static final EnumBatteryPack BATTERY_SPK =
			EnumHelper.addEnum(
					EnumBatteryPack.class,
					"BATTERY_SPK",
					new Class[]{String.class,long.class,long.class},
					"battery_spk",16_666_666_667L,20 * 60 * 5
			);
	public static final EnumBatteryPack BATTERY_ELECTRO =
			EnumHelper.addEnum(
					EnumBatteryPack.class,
					"BATTERY_ELECTRO",
					new Class[]{String.class,long.class,long.class},
					"battery_electro",166_666_666_667L,20 * 60 * 5
			);
}
