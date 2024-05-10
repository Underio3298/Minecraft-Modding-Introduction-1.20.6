package net.underio.mcmi;

import net.fabricmc.api.ModInitializer;
import net.underio.mcmi.item.ModItems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCMI implements ModInitializer {
  public static final String MOD_ID = "mcmi";
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitialize() {
    ModItems.RegisterModItems();
    LOGGER.info(MOD_ID + " has been initialized!");
  }
}
