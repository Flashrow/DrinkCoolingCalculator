package pl.flashrow.dcc.core.mapper

import pl.flashrow.data.model.ContainerTypeDto
import pl.flashrow.dcc.core.enum.Material
import pl.flashrow.dcc.core.model.ContainerType

fun mapContainerTypeDtoToContainerType(containerType: ContainerTypeDto): ContainerType = ContainerType(
    name = containerType.name,
    capacityMl = containerType.capacityMl,
    material = mapMaterial(containerType.material),
    surfaceArea = containerType.surfaceArea,
)

private fun mapMaterial(materialName: String): Material {
 var material: Material? = null
    material = Material.entries.firstOrNull() { it.name == materialName.uppercase() }
    return material ?: Material.OTHER
}