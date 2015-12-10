package common.AllPI.api.utils;

import net.minecraft.block.BlockLever;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockPropertyUtils {

    public static void setBlockProperty(World par1World, BlockPos pos, PropertyInteger property, int value) {
        par1World.setBlockState(pos, par1World.getBlockState(pos).withProperty(property, value));
    }
    
    public static void setBlockProperty(World par1World, BlockPos pos, PropertyEnum property, EnumFacing value) {
        par1World.setBlockState(pos, par1World.getBlockState(pos).withProperty(property, value));
    }

    public static boolean hasBlockProperty(World par1World, BlockPos pos, IProperty property){
        try{
            par1World.getBlockState(pos).getValue(property);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }

    public static boolean hasBlockProperty(World par1World, int par2, int par3, int par4, IProperty property){
        return hasBlockProperty(par1World, BlockUtils.toPos(par2, par3, par4), property);
    }

    public static boolean getBlockPropertyAsBoolean(World par1World, BlockPos pos, PropertyBool property){
        return ((Boolean) par1World.getBlockState(pos).getValue(property)).booleanValue();
    }

    public static boolean getBlockPropertyAsBoolean(World par1World, int par2, int par3, int par4, PropertyBool property){
        return ((Boolean) par1World.getBlockState(BlockUtils.toPos(par2, par3, par4)).getValue(property)).booleanValue();
    }
    
    public static int getBlockPropertyAsInteger(World par1World, BlockPos pos, PropertyInteger property){
        return ((Integer) par1World.getBlockState(pos).getValue(property)).intValue();
    }

    public static int getBlockPropertyAsInteger(World par1World, int par2, int par3, int par4, PropertyInteger property){
        return ((Integer) par1World.getBlockState(BlockUtils.toPos(par2, par3, par4)).getValue(property)).intValue();
    }
    
    public static EnumFacing getBlockPropertyAsEnum(World par1World, BlockPos pos, PropertyEnum property){
        return ((EnumFacing) par1World.getBlockState(pos).getValue(property));
    }

    public static EnumFacing getBlockPropertyAsEnum(World par1World, int par2, int par3, int par4, PropertyEnum property){
        return ((EnumFacing) par1World.getBlockState(BlockUtils.toPos(par2, par3, par4)).getValue(property));
    }
    
    public static BlockLever.EnumOrientation getBlockPropertyAsOrientation(World par1World, BlockPos pos, PropertyEnum property){
        return ((BlockLever.EnumOrientation) par1World.getBlockState(pos).getValue(property));
    }

    public static BlockLever.EnumOrientation getBlockPropertyAsOrientation(World par1World, int par2, int par3, int par4, PropertyEnum property){
        return ((BlockLever.EnumOrientation) par1World.getBlockState(BlockUtils.toPos(par2, par3, par4)).getValue(property));
    }

    public static EnumFacing getBlockProperty(World par1World, BlockPos pos, PropertyDirection property) {
        return (EnumFacing) par1World.getBlockState(pos).getValue(property);
    }

    public static EnumFacing getBlockProperty(World par1World, int par2, int par3, int par4, PropertyDirection property) {
        return (EnumFacing) par1World.getBlockState(new BlockPos(par2, par3, par4)).getValue(property);
    }
    
}
