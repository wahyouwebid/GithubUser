package com.wahyouwebid.githubapp.features.detail.presentation

import com.wahyouwebid.githubapp.features.detail.domain.DetailItem

data class DetailState(
    val isLoading: Boolean = false,
    val data: DetailItem? = null,
    val error: String = ""
)