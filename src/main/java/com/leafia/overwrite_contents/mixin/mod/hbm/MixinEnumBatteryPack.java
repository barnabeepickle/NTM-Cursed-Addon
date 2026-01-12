package com.leafia.overwrite_contents.mixin.mod.hbm;

import com.hbm.items.machine.ItemBatteryPack.EnumBatteryPack;
import com.hbm.lib.internal.MethodHandleHelper;
import com.leafia.AddonBase;
import com.leafia.contents.control.battery.AddonEnumBatteryPack;
import com.leafia.overwrite_contents.interfaces.IMixin;
import com.llib.exceptions.LeafiaDevFlaw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

@Mixin(value = EnumBatteryPack.class)
public class MixinEnumBatteryPack {
	private static MethodHandle ordinal = MethodHandleHelper.findVirtual(EnumBatteryPack.class,"ordinal",MethodType.methodType(int.class));
	@Inject(method = "isCapacitor",at = @At(value = "HEAD"),require = 1,remap = false,cancellable = true)
	public void onIsCapacitor(CallbackInfoReturnable<Boolean> cir) {
		try {
			if ((int)ordinal.invokeExact(this) >= AddonEnumBatteryPack.BATTERY_DESH.ordinal()) {
				cir.setReturnValue(false);
				cir.cancel();
			}
		} catch (Throwable e) {
			throw new LeafiaDevFlaw(e);
		}
	}
	@Inject(method = "<clinit>",at = @At(value = "FIELD", target = "Lcom/hbm/items/machine/ItemBatteryPack$EnumBatteryPack;VALUES:[Lcom/hbm/items/machine/ItemBatteryPack$EnumBatteryPack;"),require = 1,remap = false)
	private static void onClinit(CallbackInfo ci) {
		AddonBase._initClass(AddonEnumBatteryPack.class);
	}
}
