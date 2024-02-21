package pl.flashrow.core.common.extension

fun Float.getPercentageString():String = String.format("%.1f", this*100)+"%"