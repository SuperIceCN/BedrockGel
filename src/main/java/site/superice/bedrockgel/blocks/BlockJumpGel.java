package site.superice.bedrockgel.blocks;

import cn.nukkit.api.Since;
import cn.nukkit.block.BlockTransparent;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.block.customblock.CustomBlockDefinition;
import cn.nukkit.block.customblock.data.Materials;
import cn.nukkit.entity.Entity;
import cn.nukkit.math.BlockFace;
import cn.nukkit.math.Vector3;
import cn.nukkit.math.Vector3f;
import cn.nukkit.utils.BlockColor;
import org.jetbrains.annotations.NotNull;
import site.superice.bedrockgel.annotation.AutoRegister;

import java.util.concurrent.ConcurrentHashMap;

@AutoRegister(CustomBlock.class)
public class BlockJumpGel extends BlockTransparent implements CustomBlock {
    public static ConcurrentHashMap<Entity, Long> lastJumpTimeMap = new ConcurrentHashMap<>();
    public static Vector3 JUMP_VEC = new Vector3(0, 2, 0);

    public BlockJumpGel() {

    }

    public double getHardness() {
        return 0.0;
    }

    public double getResistance() {
        return 0.0;
    }

    public BlockColor getColor() {
        return BlockColor.BLUE_BLOCK_COLOR;
    }

    @Override
    @NotNull
    public String getNamespaceId() {
        return "aperture:jump_gel";
    }

    @Override
    public CustomBlockDefinition getDefinition() {
        return CustomBlockDefinition.builder(this, Materials.builder()
                        .any(Materials.RenderMethod.OPAQUE, "bedrockgel-blocks-jump_gel"))
                .geometry("geometry.aperture.gel")
                .collisionBox(new Vector3f(-7.5f, 0.5f, -7.5f), new Vector3f(15f, 15f, 15f))
                .build();
    }

    @Override
    public double getFrictionFactor() {
        return 0.98;
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

    @Override
    public void onEntityFallOn(Entity entity, float fallDistance) {
        var lastJumpTime = lastJumpTimeMap.getOrDefault(entity, 0L);
        if (System.currentTimeMillis() - lastJumpTime > 200) {
            entity.setMotion(new Vector3(0, Math.max(JUMP_VEC.y, fallDistance * 0.05), 0));
            lastJumpTimeMap.put(entity, System.currentTimeMillis());
        }
    }

    @Override
    public void onEntityCollide(Entity entity) {
        var lastJumpTime = lastJumpTimeMap.getOrDefault(entity, 0L);
        var collidedBlocks = entity.getTickCachedCollisionBlocks().stream().filter(block -> block.getId() == getId() && block.y > entity.y).count();
        if (System.currentTimeMillis() - lastJumpTime > 200) {
            if (collidedBlocks == 0 && entity.y % 1 >= 0.875) {
                entity.setMotion(JUMP_VEC);
            } else {
                // which face does the entity collide with?
                // These lines of code calculate it.
                BlockFace face = null;
                var minDistance = Double.MAX_VALUE;
                for (var blockFace : BlockFace.values()) {
                    if (blockFace == BlockFace.UP || blockFace == BlockFace.DOWN) {
                        continue;
                    }
                    var distance = entity.distanceSquared(blockFace.getXOffset() + this.x + 0.5, blockFace.getYOffset() + this.y + 0.5, blockFace.getZOffset() + this.z + 0.5);
                    if (distance < minDistance) {
                        face = blockFace;
                        minDistance = distance;
                    }
                }
                if (face != null) {
                    entity.setMotion(new Vector3(face.getXOffset(), JUMP_VEC.y * 0.5, face.getZOffset()));
                }
            }
            lastJumpTimeMap.put(entity, System.currentTimeMillis());
        }
    }
}
