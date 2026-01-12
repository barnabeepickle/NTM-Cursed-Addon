package com.leafia.contents.machines.powercores.ams.base;

import com.hbm.render.loader.WaveFrontObjectVAO;
import com.leafia.contents.machines.powercores.ams.stabilizer.AMSStabilizerTE;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

import static com.leafia.init.ResourceInit.getIntegrated;
import static com.leafia.init.ResourceInit.getVAO;

public class AMSBaseRender extends TileEntitySpecialRenderer<AMSBaseTE> {
	public static final WaveFrontObjectVAO mdl0 = getVAO(getIntegrated("ams/ams_limiter.obj"));
	public static final WaveFrontObjectVAO mdld = getVAO(getIntegrated("ams/ams_limiter_destroyed.obj"));
	public static final ResourceLocation texd = getIntegrated("ams/ams_destroyed.png");
	public static final ResourceLocation tex0 = getIntegrated("ams/ams_limiter.png");
	public static final ResourceLocation tex1 = getIntegrated("ams/balefire/ams_limiter.png");
}
