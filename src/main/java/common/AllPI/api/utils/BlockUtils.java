package common.AllPI.api.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockUtils{
    
    /**
     * Updates a block and notifies neighboring blocks of a change.
     * 
     * @param world The world you are in
     * @param pos The position of the block
     * @param block The block instance
     * @param shouldUpdate Should schedule a block update?
     * @param tickRate If shouldUpdate, how many ticks should pass before the block updates?
     */
    public static void updateAndNotify(World world, BlockPos pos, Block block, boolean shouldUpdate, int tickRate){
        if(shouldUpdate){
            world.scheduleUpdate(pos, block, tickRate);
        }
        
        world.notifyBlockOfStateChange(pos.east(), world.getBlockState(pos).getBlock());
        world.notifyBlockOfStateChange(pos.west(), world.getBlockState(pos).getBlock());
        world.notifyBlockOfStateChange(pos.south(), world.getBlockState(pos).getBlock());
        world.notifyBlockOfStateChange(pos.north(), world.getBlockState(pos).getBlock());
        world.notifyBlockOfStateChange(pos.up(), world.getBlockState(pos).getBlock());
        world.notifyBlockOfStateChange(pos.down(), world.getBlockState(pos).getBlock());
    }
    
    public static void destroyBlock(World world, BlockPos pos, boolean shouldDropBlock){
        world.destroyBlock(pos, shouldDropBlock);
    }

    public static void destroyBlock(World world, int x, int y, int z, boolean shouldDropBlock){
        world.destroyBlock(toPos(x, y, z), shouldDropBlock);
    }
    
    public static boolean isAirBlock(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock() == Blocks.air;
    }

    public static boolean isAirBlock(World world, int x, int y, int z){
        return world.getBlockState(toPos(x, y, z)).getBlock() == Blocks.air;
    }
    
    public static int getBlockMeta(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
    }

    public static int getBlockMeta(World world, int x, int y, int z){
        return world.getBlockState(toPos(x, y, z)).getBlock().getMetaFromState(world.getBlockState(toPos(x, y, z)));
    }

    public static void setBlock(World world, BlockPos pos, Block block){
        world.setBlockState(pos, block.getDefaultState());
    }

    public static void setBlock(World world, int x, int y, int z, Block block){
        setBlock(world, toPos(x, y, z), block);
    }

    public static Block getBlock(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock();
    }

    public static Block getBlock(World world, int x, int y, int z){
        return world.getBlockState(toPos(x, y, z)).getBlock();
    }
    
    public static Material getBlockMaterial(World world, BlockPos pos){
        return world.getBlockState(pos).getBlock().getMaterial();
    }
    
    /**
     * @return True if the block at x, y, z is touching the specified block on any side.
     */
    public static boolean blockSurroundedBy(World world, BlockPos pos, Block blockToCheckFor, boolean checkYAxis) {
        if(!checkYAxis && (world.getBlockState(pos.east()).getBlock() == blockToCheckFor || world.getBlockState(pos.west()).getBlock() == blockToCheckFor || world.getBlockState(pos.south()).getBlock() == blockToCheckFor || world.getBlockState(pos.north()).getBlock() == blockToCheckFor)){
            return true;
        }else if(checkYAxis && (world.getBlockState(pos.east()).getBlock() == blockToCheckFor || world.getBlockState(pos.west()).getBlock() == blockToCheckFor || world.getBlockState(pos.south()).getBlock() == blockToCheckFor || world.getBlockState(pos.north()).getBlock() == blockToCheckFor || world.getBlockState(pos.up()).getBlock() == blockToCheckFor || world.getBlockState(pos.down()).getBlock() == blockToCheckFor)){
            return true;
        }else{
            return false;
        }
    }
    
    public static ItemStack getItemInTileEntity(IInventory inventory, ItemStack item){
        for(int i = 0; i < inventory.getSizeInventory(); i++){
            if(inventory.getStackInSlot(i) != null){
                if(inventory.getStackInSlot(i) == item){
                    return inventory.getStackInSlot(i);
                }
            }
        }
        
        return null;
    }
    
    /**
     * Converts X, Y, and Z coordinates to BlockPos.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return A new BlockPos object
     */
    public static BlockPos toPos(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
    
    /**
     * Converts a BlockPos object to a int array.
     * 
     * @param pos The BlockPos to convert
     * @return An int array containing the BlockPos' X, Y, and Z coordinate
     */
    public static int[] fromPos(BlockPos pos){
        return new int[]{pos.getX(), pos.getY(), pos.getZ()};
    }
    
}
