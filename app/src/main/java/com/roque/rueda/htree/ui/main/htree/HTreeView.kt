package com.roque.rueda.htree.ui.main.htree

import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.View
import kotlin.math.sqrt

class HTreeView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var _initialPoint: PointF? = null
    private var _length: Int? = null
    private var _depth: Int? = null

    private val blackPaint = Paint(ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
    }

    internal fun drawHTree(initialPoint: PointF, length: Int, depth: Int) {
        _initialPoint = initialPoint
        _length = length
        _depth = depth
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (_initialPoint != null) {
            val initialPoint = _initialPoint ?: return
            val length = _length ?: return
            val depth = _depth ?: return
            drawH(canvas, initialPoint, length, depth)
        } else {
            canvas?.drawText("Invalid HTree data", 0F, 0F, blackPaint)
        }
    }

    private fun drawH(canvas: Canvas?, initialPoint: PointF, length: Int, depth: Int) {
        if (depth < 0) { return }

        val side = length / 2F
        val leftCenter = initialPoint.x - side
        val rightCenter = initialPoint.x + side
        val topLeft = initialPoint.y - side
        val bottomRight = initialPoint.y + side

        // draw center H
        canvas?.drawLine(leftCenter, initialPoint.y, rightCenter, initialPoint.y, blackPaint)
        canvas?.drawLine(leftCenter, topLeft, leftCenter, bottomRight, blackPaint)
        canvas?.drawLine(rightCenter, topLeft, rightCenter, bottomRight, blackPaint)

        // draw tails H
        val tailLength = length / 2
        drawH(canvas, PointF(leftCenter, topLeft), tailLength, depth - 1)
        drawH(canvas, PointF(leftCenter, bottomRight), tailLength, depth - 1)
        drawH(canvas, PointF(rightCenter, topLeft), tailLength, depth - 1)
        drawH(canvas, PointF(rightCenter, bottomRight), tailLength, depth - 1)
    }
}