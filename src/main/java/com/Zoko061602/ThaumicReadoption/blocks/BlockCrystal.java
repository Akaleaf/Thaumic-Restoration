package com.Zoko061602.ThaumicReadoption.blocks;

import com.Zoko061602.ThaumicReadoption.items.block.ItemBlockCrystal;
import com.Zoko061602.ThaumicReadoption.tile.TileCrystal;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;

public class BlockCrystal extends BlockBase implements ITileEntityProvider, IBlockColor {

	ItemStack drop;

	public BlockCrystal() {
		super(Material.ROCK, "pickaxe", 1, 3F, 3F, "block_crystal");
		setLightLevel(3);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> items) {
		if(tab==getCreativeTabToDisplayOn())
			for(Aspect a:Aspect.getPrimalAspects())
				items.add(ItemBlockCrystal.ofType(a));
			for(Aspect a:Aspect.getCompoundAspects())
				items.add(ItemBlockCrystal.ofType(a));
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,EntityPlayer player) {
		return ItemBlockCrystal.ofType(((TileCrystal) world.getTileEntity(pos)).getAspect());
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		drop = ItemBlockCrystal.ofType(((TileCrystal) worldIn.getTileEntity(pos)).getAspect());
		super.breakBlock(worldIn, pos, state);
	}

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
      drops.add(drop);
    }

	@Override
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		if(worldIn.getTileEntity(pos) instanceof TileCrystal) {
		TileCrystal tile = (TileCrystal) worldIn.getTileEntity(pos);
		Aspect a = tile.getAspect();
		if(a==null)a = Aspect.AIR;
		if(tintIndex==1)
		return a.getColor();
		}
		return 0;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileCrystal();
	}



}
