package net.underio.mcmi.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.underio.mcmi.MCMI;

public class ModBlocks {

  public static final Block RUBY_BLOCK = registerBlock("ruby_block",
      new Block(AbstractBlock.Settings.copy(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));

  private static Block registerBlock(String name, Block block) {
    registerBlockItem(name, block);
    return Registry.register(Registries.BLOCK, new Identifier(MCMI.MOD_ID, name), block);
  }

  private static Item registerBlockItem(String name, Block block) {
    return Registry.register(Registries.ITEM, new Identifier(MCMI.MOD_ID, name),
        new BlockItem(block, new Item.Settings()));
  }

  public static void registerModBlocks() {
    MCMI.LOGGER.info("Registering ModBlocks for " + MCMI.MOD_ID);

  }
}
