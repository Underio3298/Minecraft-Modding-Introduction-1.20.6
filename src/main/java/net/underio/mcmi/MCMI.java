package net.underio.mcmi;

import net.fabricmc.api.ModInitializer;
import net.underio.mcmi.block.ModBlocks;
import net.underio.mcmi.item.ModItemGroups;
import net.underio.mcmi.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCMI implements ModInitializer {
  public static final String MOD_ID = "mcmi";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModItems.registerModItems();
    ModBlocks.registerModBlocks();
    ModItemGroups.registerItemGroups();
    LOGGER.info(MOD_ID + " has been initialized!");
  }
}
