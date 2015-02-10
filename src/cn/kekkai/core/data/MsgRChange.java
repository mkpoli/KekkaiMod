package cn.kekkai.core.data;

import net.minecraft.client.Minecraft;
import cn.annoreg.core.RegistrationClass;
import cn.annoreg.mc.RegMessageHandler;
import cn.kekkai.core.block.tile.TileKekkai;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

@RegistrationClass
public class MsgRChange implements IMessage {

//	private int r;
	private boolean isInc;
	public int xCoord, yCoord, zCoord;
//	public TileKekkai te;
	
//	public MsgRChange() {}
//	public MsgRChange(int r) {
//		this.r = r;
//	}
//	
//	@Override
//	public void fromBytes(ByteBuf buf) {
//		this.r = buf.readInt();
//	}
//
//	@Override
//	public void toBytes(ByteBuf buf) {
//		buf.writeInt(r);
//	}
	
	public MsgRChange() {}
	public MsgRChange(boolean isInc, TileKekkai te) {
		this.isInc = isInc;
		this.xCoord = te.xCoord;
		this.yCoord = te.yCoord;
		this.zCoord = te.zCoord;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.isInc = buf.readBoolean();
		this.xCoord = buf.readInt();
		this.yCoord = buf.readInt();
		this.zCoord = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isInc);
		buf.writeInt(xCoord);
		buf.writeInt(yCoord);
		buf.writeInt(zCoord);
	}
	
	@RegMessageHandler(msg = MsgRChange.class, side = RegMessageHandler.Side.CLIENT)
	public static class Handler implements IMessageHandler<MsgRChange, IMessage> {

		@Override
		public IMessage onMessage(MsgRChange message, MessageContext ctx) {
			TileKekkai te = (TileKekkai) Minecraft.getMinecraft().theWorld.getTileEntity(message.xCoord, message.yCoord, message.zCoord);
//			TileKekkai te = message.te;
			if (te == null) return null;
			if (message.isInc && (te.getR() <= 108)) {
				te.rInc();
			} else if (!(message.isInc) && (te.getR() >= 2)) {
				te.rDec();
			}
			return null;
		}

	}
}
