package com.roque.rueda.htree.ui.main

import android.graphics.PointF
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.roque.rueda.htree.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeHTree()
        h_tree_view.viewTreeObserver.addOnGlobalLayoutListener {
            viewModel.createHTree(
                x = h_tree_view.width / 2F,
                y = h_tree_view.height / 2F,
                length = 300,
                depth = 2
            )
        }
    }

    private fun observeHTree() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            render(it)
        })
    }

    private fun render(viewState: ViewState?) {
        requireNotNull(viewState)
        h_tree_view.drawHTree(
            viewState.hTree.point,
            viewState.hTree.length,
            viewState.hTree.depth
        )
    }
}
