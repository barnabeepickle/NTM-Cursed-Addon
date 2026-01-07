package com.leafia.unsorted;

import com.hbm.lib.internal.MethodHandleHelper;
import com.hbm.tileentity.machine.TileEntityMachineSiren;
import com.llib.exceptions.LeafiaDevFlaw;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;
import pl.asie.computronics.api.audio.AudioPacket;
import pl.asie.computronics.api.audio.IAudioReceiver;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

// Oh, this is so retared.
@Optional.InterfaceList({
        @Optional.Interface(iface = "pl.asie.computronics.api.audio.IAudioReceiver", modid = "computronics")
})
public class TileEntityMachineSirenSounder extends TileEntity implements IAudioReceiver {
    static final MethodHandle getSoundWorld =
            MethodHandleHelper.findVirtual(
                    TileEntityMachineSiren.class,"getSoundWorld",MethodType.methodType(World.class)
            );
    static final MethodHandle getSoundPos =
            MethodHandleHelper.findVirtual(
                    TileEntityMachineSiren.class,"getSoundPos",MethodType.methodType(Vec3d.class)
            );
    static final MethodHandle getSoundDistance =
            MethodHandleHelper.findVirtual(
                    TileEntityMachineSiren.class,"getSoundDistance",MethodType.methodType(int.class)
            );
    static final MethodHandle getID =
            MethodHandleHelper.findVirtual(
                    TileEntityMachineSiren.class,"getID",MethodType.methodType(String.class)
            );

    TileEntityMachineSiren host;
    int id;
    public TileEntityMachineSirenSounder(TileEntityMachineSiren host,int id) {
        this.host = host;
        this.id = id;
    }

    @Override
    @Optional.Method(modid="computronics")
    public World getSoundWorld() {
	    try {
		    return (World)getSoundWorld.invokeExact(host);
	    } catch (Throwable e) {
		    throw new LeafiaDevFlaw(e);
	    }
    }

    @Override
    @Optional.Method(modid="computronics")
    public Vec3d getSoundPos() {
	    try {
		    return (Vec3d)getSoundPos.invokeExact(host);
	    } catch (Throwable e) {
		    throw new LeafiaDevFlaw(e);
	    }
    }

    @Override
    @Optional.Method(modid="computronics")
    public int getSoundDistance() {
	    try {
		    return (int)getSoundDistance.invokeExact(host);
	    } catch (Throwable e) {
		    throw new LeafiaDevFlaw(e);
	    }
    }

    @Override
    public void receivePacket(AudioPacket audioPacket, @Nullable EnumFacing enumFacing) {}

    @Override
    @Optional.Method(modid="computronics")
    public String getID() {
	    try {
		    return (String)getID.invokeExact(host)+" ["+id+"]";
	    } catch (Throwable e) {
		    throw new LeafiaDevFlaw(e);
	    }
    }

    @Override
    @Optional.Method(modid="computronics")
    public boolean connectsAudio(EnumFacing enumFacing) {
        return false;
    }
}
