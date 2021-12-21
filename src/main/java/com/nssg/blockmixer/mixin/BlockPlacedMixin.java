package com.nssg.blockmixer.mixin;

import com.nssg.blockmixer.BlockMixer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.block.Block;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

///
//import net.minecraft.entity.player.PlayerInventory;
//import net.minecraft.entity.player.PlayerEntity;
///

@Mixin(Block.class)
public class BlockPlacedMixin {

  @Inject(at = @At ("HEAD"), method = "onPlaced")
  private void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo info)
  {
    BlockMixer.SwtichSlot(placer);
    //PlayerEntity self = (PlayerEntity) (Object) placer;
    //self.getInventory().selectedSlot = 5;
    //System.out.println("Block Placed: " + self);
  }
}