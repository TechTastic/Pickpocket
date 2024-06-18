package io.github.techtastic.pickpocket

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents
import net.fabricmc.fabric.api.event.player.UseEntityCallback
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntityEvent
import net.minecraft.world.entity.SlotAccess
import net.minecraft.world.entity.player.Player
import org.slf4j.LoggerFactory

object Pickpocket : ModInitializer {
	const val MOD_ID = "pickpocket"
    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		UseEntityCallback.EVENT.register { player, world, hand, entity, hitResult ->
			if (player.isSpectator)
				return@register InteractionResult.PASS
			if (entity !is Player)
				return@register InteractionResult.PASS
			//TODO: Test for player position relative to other player
			return@register InteractionResult.SUCCESS_NO_ITEM_USED
		}
	}

	object Client: ClientModInitializer {
		override fun onInitializeClient() {
		}
	}
}