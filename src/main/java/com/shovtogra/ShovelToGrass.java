package com.shovtogra;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShovelToGrass implements ModInitializer {
	public static final String MOD_ID = "shovel-to-grass";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		UseBlockCallback.EVENT.register((player,world,hand,hitResult)->{
			ItemStack stack = player.getStackInHand(hand);
			if (stack.getItem()instanceof ShovelItem){
				BlockPos pos = hitResult.getBlockPos();
				BlockState state = world.getBlockState(pos);
				if (state.getBlock()== Blocks.DIRT_PATH){
					world.setBlockState(pos,Blocks.GRASS_BLOCK.getDefaultState());
					world.playSound(player,pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS,1f,1f);
					return ActionResult.SUCCESS;
				}


			}
	return ActionResult.PASS;
			});

		LOGGER.info("Path to Grass");
	}
}