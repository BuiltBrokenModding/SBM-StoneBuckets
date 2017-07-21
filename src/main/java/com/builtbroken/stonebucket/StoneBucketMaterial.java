package com.builtbroken.stonebucket;

import com.builtbroken.mc.fluids.bucket.BucketMaterial;
import net.minecraft.util.ResourceLocation;

/**
 * @see <a href="https://github.com/BuiltBrokenModding/VoltzEngine/blob/development/license.md">License</a> for what you can and can't do with the code.
 * Created by Dark(DarkGuardsman, Robert) on 3/3/2017.
 */
public class StoneBucketMaterial extends BucketMaterial
{
    public StoneBucketMaterial(BucketTypes type)
    {
        super(StoneBucket.PREFIX + "StoneBucket." + type.name().toLowerCase(), new ResourceLocation(StoneBucket.DOMAIN, "items/bucket." + type.name().toLowerCase()));
        damageBucketWithHotFluid = false;
    }
}
