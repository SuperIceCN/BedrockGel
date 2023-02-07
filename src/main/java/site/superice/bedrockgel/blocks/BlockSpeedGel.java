package site.superice.bedrockgel.blocks;

import cn.nukkit.api.Since;
import cn.nukkit.block.BlockTransparent;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.block.customblock.CustomBlockDefinition;
import cn.nukkit.block.customblock.data.Materials;
import cn.nukkit.math.AxisAlignedBB;
import cn.nukkit.math.Vector3f;
import cn.nukkit.utils.BlockColor;
import org.jetbrains.annotations.NotNull;
import site.superice.bedrockgel.annotation.AutoRegister;

@AutoRegister(CustomBlock.class)
public class BlockSpeedGel extends BlockTransparent implements CustomBlock {
    public BlockSpeedGel() {

    }

    public double getHardness() {
        return 0.0;
    }

    public double getResistance() {
        return 0.0;
    }

    public BlockColor getColor() {
        return BlockColor.ORANGE_BLOCK_COLOR;
    }

    @Override
    @NotNull
    public String getNamespaceId() {
        return "aperture:speed_gel";
    }

    @Override
    public CustomBlockDefinition getDefinition() {
        return CustomBlockDefinition.builder(this, Materials.builder()
                        .any(Materials.RenderMethod.OPAQUE, "bedrockgel-blocks-speed_gel"))
                .geometry("geometry.aperture.gel")
                .collisionBox(new Vector3f(-7.5f, 0.5f, -7.5f), new Vector3f(15f, 15f, 15f))
                .build();
    }

    @Override
    public boolean hasEntityCollision() {
        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return super.getCollisionBoundingBox();
    }

    @Override
    public double getFrictionFactor() {
        return 0.9999999;
    }

    @Since("1.19.60-r1")
    public boolean canSticksBlock() {
        return true;
    }

    @Override
    public String getName() {
        return CustomBlock.super.getName();
    }

    @Override
    public int getId() {
        return CustomBlock.super.getId();
    }

    public double getMinX() {
        return this.x + 0.0625;
    }

    public double getMinY() {
        return this.y + 0.0625;
    }

    public double getMinZ() {
        return this.z + 0.0625;
    }

    public double getMaxX() {
        return this.x + 0.9375;
    }

    public double getMaxY() {
            return this.y + 0.9375;
    }

    public double getMaxZ() {
        return this.z + 0.9375;
    }
}
