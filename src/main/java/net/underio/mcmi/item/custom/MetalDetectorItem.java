package net.underio.mcmi.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class MetalDetectorItem extends Item {
  public MetalDetectorItem(Settings settings) {
    super(settings);
  }

  Set<BlockPos> checkedValuable = new HashSet<>();
  Set<BlockPos> checkedNormal = new HashSet<>();
  private int veinSize = 0;
  private BlockPos firstOrePos = null;
  private Block firstOreType = null;

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    if (!context.getWorld().isClient()) {
      BlockPos positionClicked = context.getBlockPos();
      PlayerEntity player = context.getPlayer();
      boolean foundBlock = false;

      for (int i = 0; positionClicked.getY() - i >= -64; i++) {
        BlockState state = context.getWorld().getBlockState(positionClicked.down(i));
        if (isValuableBlock(state)) {
          firstOrePos = positionClicked.down(i);
          firstOreType = state.getBlock();
          veinSize = 1;
          foundBlock = true;

          // Reset the checked sets
          checkedValuable.clear();
          checkedNormal.clear();

          // Initialize the BFS scan
          checkedValuable.add(firstOrePos);
          scanLayerFor(firstOrePos, context.getWorld());

          break;
        }
      }

      if (foundBlock) {
        outputVeinInfo(player);
      } else {
        player.sendMessage(Text.literal("No Valuables found!"));
      }
    }

    context.getStack().damage(1, context.getPlayer(), EquipmentSlot.MAINHAND);

    return ActionResult.SUCCESS;
  }

  private void scanLayerFor(BlockPos startPos, World world) {
    Queue<BlockPos> queue = new LinkedList<>();
    queue.add(startPos);

    while (!queue.isEmpty()) {
      BlockPos currentPos = queue.poll();
      Vec3i[] directions = {
              new Vec3i(1, 0, 0),  // +X direction
              new Vec3i(-1, 0, 0), // -X direction
              new Vec3i(0, 1, 0),  // +Y direction
              new Vec3i(0, -1, 0), // -Y direction
              new Vec3i(0, 0, 1),  // +Z direction
              new Vec3i(0, 0, -1)  // -Z direction
      };
      for (Vec3i direction : directions) {
        BlockPos neighborPos = currentPos.add(direction);
        if (!checkedValuable.contains(neighborPos) && !checkedNormal.contains(neighborPos)) {
          BlockState state = world.getBlockState(neighborPos);

          if (isValuableBlock(state)) {
            checkedValuable.add(neighborPos);
            queue.add(neighborPos);
            veinSize++;
          } else {
            checkedNormal.add(neighborPos);
          }
        }
      }
    }
  }

  private void outputVeinInfo(PlayerEntity player) {
    if (firstOrePos != null && firstOreType != null) {
      String oreName = firstOreType.asItem().getName().getString();
      player.sendMessage(Text.literal("Found " + oreName + " vein at " + firstOrePos.getX() + " " + firstOrePos.getY() + " " + firstOrePos.getZ() + " with size: " + veinSize), false);
    }
  }

  private boolean isValuableBlock(BlockState state) {
    return state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.DEEPSLATE_IRON_ORE) || state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE);
  }
}
