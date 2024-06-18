package io.github.techtastic.pickpocket.screen

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.*
import net.minecraft.world.item.ItemStack

class PickpocketingMenu(syncId: Int, val target: Player) : AbstractContainerMenu(null, syncId) {
    val SLOT_IDS = arrayOf(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
    val TEXTURE_EMPTY_SLOTS = mapOf(
        Pair(EquipmentSlot.FEET, InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS),
        Pair(EquipmentSlot.LEGS, InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS),
        Pair(EquipmentSlot.CHEST, InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE),
        Pair(EquipmentSlot.HEAD, InventoryMenu.EMPTY_ARMOR_SLOT_HELMET)
    )

    init {
        this.addInventorySlots(target.inventory, 7, 17)
        this.addHotbarSlots(target.inventory, 7, 75)
        this.addArmorSlots(target.inventory)
        this.addOffhandSlot(target.inventory)


    }

    override fun quickMoveStack(player: Player, i: Int): ItemStack {
        TODO("Not yet implemented")
    }

    override fun stillValid(player: Player) = target.distanceToSqr(player) < player.blockInteractionRange() / 2

    fun addInventorySlots(inventory: Inventory, startX: Int, startY: Int) {
        for (y in 0 .. 2) {
            for (x in 0 .. 8) {
                this.addSlot(Slot(inventory, x + y * 9 + 9, startX + x * 18, startY + y * 18))
            }
        }
    }

    fun addHotbarSlots(inventory: Inventory, startX: Int, startY: Int) {
        for (i in 0 .. 8) {
            this.addSlot(Slot(inventory, i, startX + i * 18, startY))
        }
    }

    fun addArmorSlots(inventory: Inventory) {
        for (i in 0 .. 3) {
            val equipmentSlot = SLOT_IDS[i]
            this.addSlot(AccessibleArmorSlot(
                inventory,
                inventory.player,
                equipmentSlot,
                39 - i,
                7 + i * 16,
                97,
                TEXTURE_EMPTY_SLOTS[equipmentSlot]
                    ?: TEXTURE_EMPTY_SLOTS[EquipmentSlot.HEAD]!!))
        }
    }

    fun addOffhandSlot(inventory: Inventory) {
        this.addSlot(object: Slot(inventory, 40, 97, 97) {
            override fun setByPlayer(itemStack: ItemStack, itemStack2: ItemStack) {
                target.onEquipItem(EquipmentSlot.OFFHAND, itemStack2, itemStack)
                super.setByPlayer(itemStack, itemStack2)
            }

            override fun getNoItemIcon() = com.mojang.datafixers.util.Pair.of(InventoryMenu.BLOCK_ATLAS, InventoryMenu.EMPTY_ARMOR_SLOT_SHIELD)
        })
    }
}