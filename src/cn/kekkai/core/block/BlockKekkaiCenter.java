package cn.kekkai.core.block;

import cn.kekkai.core.KQKMain;
import cn.kekkai.core.block.tile.TileKekkai;
import cn.kekkai.core.register.KQKRegister;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockKekkaiCenter extends BlockContainer {

	public BlockKekkaiCenter() {
		super(Material.rock);
		setCreativeTab(CreativeTabs.tabMisc);
		setBlockUnbreakable();
		setBlockName("kekkaic");
	}

	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon("kekkai:kekkaic");
	}
	
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z,
                    EntityPlayer player, int idk, float what, float these, float are) {
            TileEntity tileEntity = world.getTileEntity(x, y, z);
            //如果TileEntity没有被加载或者玩家在潜行，则不打开GUI。
            if (tileEntity == null || player.isSneaking()) {
                    return false;
            }
            player.openGui(KQKMain.INSTANCE, KQKRegister.GUI_ID_KEKKAI, world, x, y, z);
            return true;
    }
 

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		 return new TileKekkai();
	}

	@Override
	public boolean hasTileEntity() {
		return true;
	}
	
	
}
