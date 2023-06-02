package com.elewa.flikerphotos.modules.images.data.mapper

import com.elewa.flikerphotos.modules.images.data.model.api.ImageResponse
import com.elewa.flikerphotos.modules.images.data.model.dto.ImageDto
import com.elewa.flikerphotos.modules.images.domain.entity.ImageEntity


fun ImageDto.toEntity() = ImageEntity(
    id,
    previewUrl,
)

fun ImageResponse.toEntity() = ImageDto(
    id = id,
    previewUrl = "https://farm$farm.static.flickr.com/$server/${id}_$secret.jpg",
)

fun List<ImageResponse>.toEntity() = map { it.toEntity() }