package net.underio.mcmi.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.underio.mcmi.MCMI;

public class ModItems {

  public static final Item RUBY = registerItem("ruby", new Item(new Item.Settings()));
  public static final Item RAW_RUBY = registerItem("raw_ruby", new Item(new Item.Settings()));

  private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
    entries.add(RUBY);
    entries.add(RAW_RUBY);
  }

  public static Item registerItem(String name, Item item) {
    return Registry.register(Registries.ITEM, new Identifier(MCMI.MOD_ID, name), item);
  }

  public static void registerModItems() {
    MCMI.LOGGER.info("Registering mod items for " + MCMI.MOD_ID);

    ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
  }
}
