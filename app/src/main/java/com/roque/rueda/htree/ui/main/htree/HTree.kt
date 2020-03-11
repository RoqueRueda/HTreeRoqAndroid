package com.roque.rueda.htree.ui.main.htree

import android.graphics.PointF

data class HTree(
    val point: PointF = PointF(0F, 0F),
    val length: Int = 100,
    val depth: Int = 0
)