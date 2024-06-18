package com.moneco.remitconnect.application.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

data class ChildLayout(
    val contentType: String = "",
    val content: @Composable (item: Any?) -> Unit = {},
    val items: List<Any> = emptyList()
)

@Composable
fun VerticalScrollLayout(
    modifier: Modifier,
    vararg childLayouts: ChildLayout
) {
    LazyColumn(modifier = modifier) {
        childLayouts.forEach { child ->
            if (child.items.isEmpty()) loadItem(child) else loadItems(child)
        }
    }
}

private fun LazyListScope.loadItem(childLayout: ChildLayout) {
    item(contentType = childLayout.contentType) {
        childLayout.content(null)
    }
}

private fun LazyListScope.loadItems(childLayout: ChildLayout) {
    items(items = childLayout.items) { item ->
        childLayout.content(item)
    }
}

@Suppress("UNCHECKED_CAST")
@Composable
fun <T : Any> LoadItemAfterSafeCast(
    generalItem: Any?,
    composeWithSafeItem: @Composable (item: T) -> Unit
) {
    (generalItem as? T)?.let { safeItem ->
        composeWithSafeItem(safeItem)
    }
}