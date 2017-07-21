package com.builtbroken.stonebucket;

import com.builtbroken.mc.fluids.FluidModule;
import com.builtbroken.mc.fluids.bucket.BucketMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public enum BucketTypes
{
    STONE("block@minecraft:stone"),
    SAND_STONE("block@minecraft:sandstone"),
    END_STONE("block@minecraft:end_stone");

    public BucketMaterial material;

    public String craftingMaterial;

    BucketTypes(String craftingMaterial)
    {
        this.craftingMaterial = craftingMaterial;
    }

    public Object getCraftingMaterial()
    {
        if (craftingMaterial != null)
        {
            if (craftingMaterial.startsWith("block@"))
            {
                String sub = craftingMaterial.substring(6, craftingMaterial.length());
                return ForgeRegistries.BLOCKS.getValue(new ResourceLocation(sub));
            }
            else if (craftingMaterial.startsWith("item@"))
            {
                String sub = craftingMaterial.substring(5, craftingMaterial.length());
                return ForgeRegistries.ITEMS.getValue(new ResourceLocation(sub));
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