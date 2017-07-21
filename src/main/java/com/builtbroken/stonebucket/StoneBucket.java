package com.builtbroken.stonebucket;

import com.builtbroken.mc.fluids.api.reg.BucketMaterialRegistryEvent;
import com.builtbroken.mc.fluids.bucket.BucketMaterialHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by Dark on 7/21/2017.
 */
@Mod(modid = StoneBucket.DOMAIN, name = "Stone Bucket", version = "@MAJOR@.@MINOR@.@REVIS@.@BUILD@", dependencies = "after:vefluids")
@Mod.EventBusSubscriber(modid = StoneBucket.DOMAIN)
public class StoneBucket
{
    public static final String DOMAIN = "stonebucket";
    public static final String PREFIX = DOMAIN + ":";

    public StoneBucket()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void registerBucketMaterials(BucketMaterialRegistryEvent.Pre event)
    {
        for (BucketTypes type : BucketTypes.values())
        {
            type.material = new StoneBucketMaterial(type);
            BucketMaterialHandler.addMaterial(type.name().toLowerCase(), type.material, type.ordinal());
        }
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event)
    {
        //TODO add crafting recipes for milk bucket
        ResourceLocation location = new ResourceLocation(DOMAIN, "woodenbucket");
        for (BucketTypes type : BucketTypes.values())
        {
            event.getRegistry().register(new ShapedOreRecipe(location, type.getBucket(),
                    " s ",
                    "wcw",
                    " w ",
                    'w', type.getCraftingMaterial(),
                    's', "stickWood", 'c', "dye")
                    .setRegistryName("bucket." + type.name().toLowerCase()));

        }
        for (ItemStack itemstack : OreDictionary.getOres("stone"))
        {
            if (itemstack != null && itemstack.getItem() != Item.getItemFromBlock(Blocks.STONE))
            {
                event.getRegistry().register(new ShapedOreRecipe(location, BucketTypes.STONE.getBucket(),
                        " s ",
                        "wcw",
                        " w ",
                        'w', itemstack,
                        's', "stickWood", 'c', "dye")
                        .setRegistryName("bucket.stone.z" + itemstack));
            }
        }
    }
}
