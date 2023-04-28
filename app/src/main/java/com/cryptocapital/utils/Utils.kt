package com.cryptocapital.utils

class Utils {
    companion object{
        val Double.monetaryDescription: String
            get() {
                val num = Math.abs(this)
                val sign = if (this < 0) "-" else ""

                return when {
                    num >= 1_000_000_000_000 -> {
                        val formatted = num / 1_000_000_000_000
                        val stringFormatted = String.format("%.2f", formatted.toFloat())
                        "$$sign$stringFormatted Tr"
                    }
                    num >= 1_000_000_000 -> {
                        val formatted = num / 1_000_000_000
                        val stringFormatted = String.format("%.2f", formatted.toFloat())
                        "$$sign$stringFormatted Bn"
                    }
                    num >= 1_000_000 -> {
                        val formatted = num / 1_000_000
                        val stringFormatted = String.format("%.2f", formatted.toFloat())
                        "$$sign$stringFormatted M"
                    }
                    num >= 1_000 -> {
                        val formatted = num / 1_000
                        val stringFormatted = String.format("%.2f", formatted.toFloat())
                        "$$sign$stringFormatted K"
                    }
                    else -> "$${String.format("%.2f", this.toFloat())}"
                }
            }
    }
}