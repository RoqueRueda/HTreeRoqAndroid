package com.roque.rueda.htree.ui.main

import android.graphics.PointF
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roque.rueda.htree.ui.main.htree.HTree

class MainViewModel : ViewModel() {

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    internal val viewState: LiveData<ViewState> get() = _viewState

    fun createHTree(x: Float, y: Float, length: Int, depth: Int) {
        _viewState.value = ViewState(
            HTree(
                point = PointF(x, y),
                length = length,
                depth = depth
            )
        )
    }

}

data class ViewState (
    val hTree: HTree = HTree()
)
