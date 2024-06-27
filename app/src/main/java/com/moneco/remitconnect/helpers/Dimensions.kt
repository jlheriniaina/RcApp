package com.moneco.remitconnect.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import com.moneco.remitconnect.R

object Dimensions {
    // Textes
    @Composable
    @ReadOnlyComposable
    fun text_xs() = dimensionResource(id = R.dimen.size_text_xs).value.sp

    @Composable
    @ReadOnlyComposable
    fun text_s() = dimensionResource(id = R.dimen.size_text_s).value.sp

    @Composable
    @ReadOnlyComposable
    fun text_m() = dimensionResource(id = R.dimen.size_text_m).value.sp

    @Composable
    @ReadOnlyComposable
    fun text_l() = dimensionResource(id = R.dimen.size_text_l).value.sp

    @Composable
    @ReadOnlyComposable
    fun text_xl() = dimensionResource(id = R.dimen.size_text_xl).value.sp

    // Margins
    @Composable
    @ReadOnlyComposable
    fun size_xs() = dimensionResource(id = R.dimen.size_xs)

    @Composable
    @ReadOnlyComposable
    fun size_s() = dimensionResource(id = R.dimen.size_s)

    @Composable
    @ReadOnlyComposable
    fun size_sm() = dimensionResource(id = R.dimen.size_sm)

    @Composable
    @ReadOnlyComposable
    fun size_m() = dimensionResource(id = R.dimen.size_m)

    @Composable
    @ReadOnlyComposable
    fun size_l() = dimensionResource(id = R.dimen.size_l)

    @Composable
    @ReadOnlyComposable
    fun size_xl() = dimensionResource(id = R.dimen.size_xl)

    @Composable
    @ReadOnlyComposable
    fun size_xxl() = dimensionResource(id = R.dimen.size_xxl)

    @Composable
    @ReadOnlyComposable
    fun size_3xl() = dimensionResource(id = R.dimen.size_3xl)
}

