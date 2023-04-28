package com.cryptocapital.utils

import android.content.Context
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.cryptocapital.R
import com.github.mikephil.charting.components.IMarker
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF


class CustomMarkerView(private val context: Context, private val layoutResource: Int) :
    IMarker {
    private var tvContent: TextView? = null
    private var view: View? = null
    init {
        view = LayoutInflater.from(context).inflate(layoutResource, null)
        setTvContent(view?.findViewById(R.id.tvContent))
    }
    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2f), -height.toFloat())
    }

    override fun getOffsetForDrawingAtPoint(posX: Float, posY: Float): MPPointF {
        return offset
    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tvContent!!.text = "$ " + e!!.y
    }

    fun refreshContent(e: Map.Entry<*, *>, highlight: Highlight?) {

    }

    override fun draw(canvas: Canvas, posX: Float, posY: Float) {
        setTvContent(view!!.findViewById(R.id.tvContent))
        view!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        view!!.layout(0, 0, view!!.measuredWidth, view!!.measuredHeight)
        canvas.save()
        canvas.translate(posX + offset.getX(), posY + offset.getY())
        view!!.draw(canvas)
        canvas.restore()
    }

    val width: Int
        get() {
            view!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            return view!!.measuredWidth
        }
    val height: Int
        get() {
            view!!.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
            return view!!.measuredHeight
        }

    fun setTvContent(tvContent: TextView?) {
        this.tvContent = tvContent
    }
}