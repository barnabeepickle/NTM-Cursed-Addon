package com.leafia.overwrite_contents.mixin.mod.hbm;

import com.hbm.items.machine.ItemBatteryPack.EnumBatteryPack;
import com.leafia.overwrite_contents.interfaces.IMixin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = EnumBatteryPack.class)
public class MixinEnumBatteryPack {
	@Inject(method = "isCapacitor",at = @At(value = "HEAD"),require = 1,remap = false,cancellable = true)
	public void onIsCapacitor(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(false);
		cir.cancel();
	}
}
