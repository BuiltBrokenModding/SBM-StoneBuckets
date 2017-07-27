package com.builtbroken.stonebucket;

import com.builtbroken.mc.fluids.FluidModule;
import com.builtbroken.mc.fluids.bucket.BucketMaterial;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public enum BucketTypes
{
    STONE("block@minecraft:stone"),
    SAND_STONE("block@minecraft:sandstone"),
    END_STONE("block@minecraft:end_stone"),
    GRANITE("block@minecraft:stone", BlockStone.EnumType.GRANITE.getMetadata()),
    POLISHED_GRANITE("block@minecraft:stone", BlockStone.EnumType.GRANITE_SMOOTH.getMetadata()),
    DIORITE("block@minecraft:stone", BlockStone.EnumType.DIORITE.getMetadata()),
    POLISHED_DIORITE("block@minecraft:stone", BlockStone.EnumType.DIORITE_SMOOTH.getMetadata()),
    ANDESITE("block@minecraft:stone", BlockStone.EnumType.ANDESITE.getMetadata()),
    POLISHED_ANDESITE("block@minecraft:stone", BlockStone.EnumType.ANDESITE_SMOOTH.getMetadata()),
    MOSSY_COBBLE("block@minecraft:end_stone"),
    PRISMARINE("block@minecraft:prismarine");

    public BucketMaterial material;

    public String craftingMaterial;
    public int meta = 0;

    BucketTypes(String craftingMaterial)
    {
        this.craftingMaterial = craftingMaterial;
    }

    BucketTypes(String craftingMaterial, int meta)
    {
        this.craftingMaterial = craftingMaterial;
        this.meta = meta;
    }

    public Object getCraftingMaterial()
    {
        if (craftingMaterial != null)
        {
            if (craftingMaterial.startsWith("block@"))
            {
                String sub = craftingMaterial.substring(6, craftingMaterial.length());
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(sub));
                if (block != null && block != Blocks.AIR)
                {
                    return new ItemStack(block, 1, meta);
                }
            }
            else if (craftingMaterial.startsWith("item@"))
            {
                String sub = craftingMaterial.substring(5, craftingMaterial.length());
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(sub));
                if (item != null && item != Item.getItemFromBlock(Blocks.AIR))
                {
                    return new ItemStack(item, meta);
                }
            }
            return craftingMaterial;
        }
        return null;
    }

    public ItemStack getBucket()
    {
        return new ItemStack(FluidModule.bucket, 1, material.metaValue);
    }
}