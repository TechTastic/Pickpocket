package net.minecraft.world.inventory

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.Container
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity

class AccessibleArmorSlot(container: Container,
                          livingEntity: LivingEntity,
                          equipmentSlot: EquipmentSlot,
                          i: Int,
                          j: Int,
                          k: Int,
                          resourceLocation: ResourceLocation
) : ArmorSlot(container, livingEntity, equipmentSlot, i, j, k, resourceLocation)