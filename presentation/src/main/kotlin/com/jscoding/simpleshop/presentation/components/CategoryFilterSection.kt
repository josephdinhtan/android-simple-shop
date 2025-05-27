package com.jscoding.simpleshop.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jddev.simpletouch.ui.utils.StUiPreview
import com.jddev.simpletouch.ui.utils.StUiPreviewWrapper
import com.jscoding.simpleshop.domain.model.ProductCategory

data class CategoryFilterColor(
    val backgroundColor: Color,
    val surfaceColor: Color,
    val surfaceSelectedColor: Color,
    val textColor: Color,
    val textSelectedColor: Color,
) {
    companion object {
        fun default() = CategoryFilterColor(
            backgroundColor = Color.White,
            surfaceColor = Color.LightGray,
            surfaceSelectedColor = Color.Black,
            textColor = Color.Black,
            textSelectedColor = Color.White
        )
    }
}

@Composable
fun CategoryFilterSection(
    modifier: Modifier = Modifier,
    categories: List<ProductCategory>,
    selectedCategories: Set<ProductCategory>,
    color: CategoryFilterColor = CategoryFilterColor.default(),
    itemShape: RoundedCornerShape = RoundedCornerShape(100),
    onCategoryToggle: (ProductCategory) -> Unit,
) {
    LazyRow(
        modifier = modifier.background(color = color.backgroundColor),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            val isSelected = selectedCategories.contains(category)
            CategoryChip(
                text = category.name,
                isSelected = isSelected,
                color = color,
                itemShape = itemShape,
                onClick = { onCategoryToggle(category) }
            )
        }
    }
}

@Composable
private fun CategoryChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    color: CategoryFilterColor,
    itemShape: RoundedCornerShape,
    onClick: () -> Unit,
) {

    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) color.surfaceSelectedColor else color.surfaceColor,
        animationSpec = tween(durationMillis = 300),
        label = "ChipBackground"
    )

    val contentColor by animateColorAsState(
        targetValue = if (isSelected) color.textSelectedColor else color.textColor,
        animationSpec = tween(durationMillis = 300),
        label = "ChipTextColor"
    )

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.05f else 1f,
        animationSpec = tween(300),
        label = "ChipScale"
    )

    Surface(
        shape = itemShape,
        color = backgroundColor,
        modifier = modifier
            .clip(itemShape)
            .scale(scale)
            .clickable(
                onClick = onClick
            ),
//        tonalElevation = if (isSelected) 4.dp else 0.dp
    ) {
        Text(
            text = text,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
@StUiPreview
private fun Preview() {
    StUiPreviewWrapper {
        val allCategories = listOf(
            ProductCategory("all-product", "All products", ""),
            ProductCategory("men", "Men", ""),
            ProductCategory("women", "Women", ""),
            ProductCategory("accessories", "Accessories", ""),
        )
        var selectedCategories by remember { mutableStateOf(setOf(ProductCategory("all-product", "All products", ""))) }

        CategoryFilterSection(
            modifier = Modifier,
            categories = allCategories,
            selectedCategories = selectedCategories,
            onCategoryToggle = {
                selectedCategories = if (selectedCategories.contains(it)) {
                    selectedCategories.minus(it)
                } else {
                    selectedCategories.plus(it)
                }
            },
        )
    }
}
