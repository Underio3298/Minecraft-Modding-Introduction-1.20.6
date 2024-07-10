package net.underio.mcmi.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.underio.mcmi.MCMI;
import net.underio.mcmi.block.ModBlocks;

public class ModItemGroups {

  public static final ItemGroup RUBY_GROUP = FabricItemGroup.builder()
      .icon(() -> new ItemStack(ModItems.RAW_RUBY))
      .displayName(Text.translatable("itemgroup.raw_ruby"))
      .entries((context, entries) -> {
        entries.add(ModItems.RUBY);
        entries.add(ModItems.RAW_RUBY);

        entries.add(ModItems.METAL_DETECTOR);

        entries.add(ModBlocks.RUBY_BLOCK);
        entries.add(ModBlocks.RAW_RUBY_BLOCK);

        entries.add(ModBlocks.RUBY_ORE);
        entries.add(ModBlocks.DEEPSLATE_RUBY_ORE);
        entries.add(ModBlocks.NETHER_RUBY_ORE);
        entries.add(ModBlocks.END_STONE_RUBY_ORE);

      })
      .build();

  public static void registerItemGroups() {
    MCMI.LOGGER.info("Registering Item Groups for " + MCMI.MOD_ID);
    Registry.register(Registries.ITEM_GROUP, new Identifier("mcmi", "ruby_group"), RUBY_GROUP);
    MCMI.LOGGER.info("Done Registering Item Groups for " + MCMI.MOD_ID);
  }
}
