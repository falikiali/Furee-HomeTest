package com.example.furee

data class Payment(
    val id: Int,
    val methodName: String,
    val logo: List<LogoPayment>
)
