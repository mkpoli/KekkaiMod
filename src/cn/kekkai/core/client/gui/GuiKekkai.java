package cn.kekkai.core.client.gui;

import java.util.Set;

import org.lwjgl.opengl.GL11;

import cn.kekkai.core.KQKMain;
import cn.kekkai.core.block.tile.TileKekkai;
import cn.kekkai.core.data.MsgRChange;
import cn.liutils.api.gui.LIGui;
import cn.liutils.api.gui.LIGuiContainer;
import cn.liutils.util.HudUtils;
import cn.liutils.util.RenderUtils;
import cn.liutils.util.render.TextUtils;
import cn.liutils.util.render.TrueTypeFont;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiKekkai extends LIGuiContainer {

	TileKekkai te;
	PageMain pageMain;
//	private int r = 10;

	public static final TrueTypeFont FONT = TextUtils.FONT_CONSOLAS_64;

	public GuiKekkai(TileEntity te) {
		super(new ContainerKekkai(te)); // xSize ySize container
		this.te = (TileKekkai) te;
		this.gui = new LIGui();
		gui.addWidget(pageMain = new PageMain(this));
		this.xSize = 256;
		this.ySize = 167;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		TextUtils.drawText(FONT, getR() + "", 133 + offset(), 74, 25);
		super.drawGuiContainerForegroundLayer(i, j);
	}

	/**
	 * @return the radius of the kekkai
	 */
	protected int getR() {
		return te.getR();
	}
	
	protected void changeR(boolean isInc)
	{
		KQKMain.netHandler.sendToServer(new MsgRChange(isInc, te));
		// TODO
//		if (isInc && (r <= 108)) {
//
//			r += 2;
//		} else if (!isInc && (r >= 2)) {
//			r -= 2;
//		}
//		System.out.println(isInc ? ++r : --r);
	}

	private int offset() {
		return -((getR() + "").length() * 5 - 5);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int i, int j) {
		GL11.glPushMatrix();
		{
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderUtils.loadTexture(new ResourceLocation(
					"kekkai:textures/gui/bg.png"));
			int x = (width - xSize) / 2; // Screen-full's size
			int y = (height - ySize) / 2; // TARINAI
			this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
			GL11.glDisable(GL11.GL_BLEND);
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
}
