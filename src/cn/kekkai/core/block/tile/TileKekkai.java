package cn.kekkai.core.block.tile;

import cn.kekkai.core.KQKMain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

public class TileKekkai extends TileEntity {
	public EntityPlayer player;
	private boolean isOp;
	private int r;
	
	public TileKekkai() {
//		r = 0;
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
	
	public boolean isUsableByPlayer(EntityPlayer entityplayer) {
		return entityplayer.getDistanceSq(
				xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) <= 64;
	}

	// TODO: NBT Processing
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.r = nbt.getInteger("r");
		KQKMain.log.debug("on reading : r = " + this.getR());
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		KQKMain.log.debug("on writing : r = " + this.getR());
        nbt.setInteger("r", this.r);
	}
	
	public void rInc() {
		r += 2;
	}
	
	public void rDec() {
		r -= 2;
	}
	
	/**
	 * @return the radius of the kekkai
	 */
	public int getR() {
		return r;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
//		if (r > 0)
//			this.markDirty();
		//System.err.println("UPDATE r = " + r);
	//	isOp = MinecraftServer.getServer().getConfigurationManager().isPlayerOpped(player.getCommandSenderName());
	}

//	@Override
//	public Packet getDescriptionPacket() {
//		NBTTagCompound tag = new NBTTagCompound();
//		this.writeToNBT(tag);
//		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
//	}
//		
//	@Override
//	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
//		readFromNBT(packet.func_148857_g());
//	}
}
