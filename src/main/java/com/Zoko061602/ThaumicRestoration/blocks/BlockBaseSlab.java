package com.Zoko061602.ThaumicRestoration.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public abstract class BlockBaseSlab extends BlockSlab {

	public static final PropertyEnum<Variant> VARIANT = PropertyEnum.<Variant>create("variant", Variant.class);

	public BlockBaseSlab(Material material, String tool, int tier, float hardness, float resistance, String name) {
		super(material);
		this.setHarvestLevel(tool, tier);
		this.setResistance(resistance);
		this.setHardness(hardness);
		this.setUnlocalizedName(name);
		this.setRegistryName(name);
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		return Variant.DEFAULT;
	}

	@Override
	public final int getMetaFromState(IBlockState state) {
		int meta = 0;
		if(!this.isDouble()&& state.getValue(HALF)==EnumBlockHalf.TOP)
			meta |= 8;

		return meta;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		if(!this.isDouble())
		return new BlockStateContainer(this, new IProperty[] {VARIANT, HALF});
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}

	@Override
	public final IBlockState getStateFromMeta(final int meta) {
		IBlockState blockstate = this.blockState.getBaseState().withProperty(VARIANT, Variant.DEFAULT);
		if(!this.isDouble())
			blockstate = blockstate.withProperty(HALF, ((meta&8)!=0)?EnumBlockHalf.TOP:EnumBlockHalf.BOTTOM);
		    return blockstate;
	}

	@Override
	public String getUnlocalizedName(int meta) {
		return super.getUnlocalizedName();
	}

	public static class Double extends BlockBaseSlab {

		public Double(Material material, String tool, int tier, float hardness, float resistance, String name) {
			super(material, tool, tier, hardness, resistance, name);
		}

		@Override
		public boolean isDouble() {
			return true;
		}

	}

	public static class Half extends BlockBaseSlab {

		public Half(Material material, String tool, int tier, float hardness, float resistance, String name) {
			super(material, tool, tier, hardness, resistance, name);
		}

		@Override
		public boolean isDouble() {
			return false;
		}

	}

	public static enum Variant implements IStringSerializable{
		DEFAULT;

		@Override
		public String getName() {
			return "default";
		}

	}

}