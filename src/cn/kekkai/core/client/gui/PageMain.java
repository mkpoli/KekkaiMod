package cn.kekkai.core.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import cn.kekkai.core.KQKMain;
import cn.liutils.api.gui.Widget;

public class PageMain extends Widget {
	private final static int OFFSET_X = 85, OFFSET_Y = 37;
	GuiKekkai gk;
	
	private abstract static class Button extends Widget {
		public Button(int x, int y, int w, int h) {
			super(x, y , w, h);
		}
		
		@Override
		public void onMouseDown(double mx, double my) {
			this.onButtonPressed();
			Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.func_147674_a(new ResourceLocation("minecraft:gui.button.press"), 1.0F));
		}
		
		public abstract void onButtonPressed();
	}
	
	public PageMain(GuiKekkai gk) {
		super(0, 0, 256, 167);
		this.gk = gk;
		this.initTexDraw(new ResourceLocation("kekkai:textures/gui/bg.png"), 0, 0, 256, 256);
	}
	
	@Override
	protected void onAdded() {
		addWidgets(new Button(OFFSET_X + 77, OFFSET_Y + 107, 100, 32) {
			@Override
			public void onButtonPressed() {
				gk.mc.displayGuiScreen((GuiScreen)null);
			}
		},
		new Button(OFFSET_X + 12, OFFSET_Y + 72, 60, 24)  {
			 @Override
			public void onButtonPressed() {
				 gk.changeR(false); // Decrease
			}
		},
		new Button(OFFSET_X + 175, OFFSET_Y + 72, 60, 24)  {
			 @Override
			public void onButtonPressed() {
				 gk.changeR(true); // Increase
			}
		});
		
	}
	
}
