package com.plcoding.jetpackcomposepokedex.data.remote.responses

data class HeldItem(
    val item: Item,
    val version_details: List<VersionDetail>
)