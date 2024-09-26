package com.pestControlDrugs.bnet.model.dataclass

data class Field(
    val flags: Flags,
    val group: Int,
    val image: String,
    val name: String,
    val show: Int,
    val type: String,
    val types_id: Int,
    val value: String
)