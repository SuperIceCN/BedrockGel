package site.superice.bedrockgel;

import cn.nukkit.block.Block;
import cn.nukkit.block.customblock.CustomBlock;
import cn.nukkit.plugin.PluginBase;

import static site.superice.bedrockgel.util.RegistryManifestUtil.registryManifestOf;

public class Main extends PluginBase {
    public static Main INSTANCE = null;

    public Main() {
        INSTANCE = this;
    }

    @Override
    public void onLoad() {
        Block.registerCustomBlock(registryManifestOf(CustomBlock.class));
    }
}
