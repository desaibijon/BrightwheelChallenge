package com.example.brightwheelchallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.brightwheelchallenge.R
import com.example.brightwheelchallenge.network.ComponentListFragmentViewModel

/**
 * Displays a list of [ComponentListFragmentViewModel.Components] our app supports
 */
class ComponentListFragment: Fragment() {

    companion object {
        fun newInstance() = ComponentListFragment()
    }

    // variable declaration
    private lateinit var viewModel: ComponentListFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // setup view model
        viewModel = ViewModelProvider(this).get(ComponentListFragmentViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val context = context ?: return null
        val rootView = inflater.inflate(
                R.layout.component_list_fragment,
                container,
                false
        )

        // Setup list view
        val listView = rootView.findViewById<ListView>(R.id.list_view)
        listView.adapter = ArrayAdapter(
                context,
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, viewModel.componentList
        )

        // Open fragment when item is clicked
        listView.onItemClickListener = AdapterView.OnItemClickListener {
            _: AdapterView<*>, _: View, pos: Int, _: Long ->

            val componentModel = viewModel.componentList[pos]

            parentFragmentManager.beginTransaction()
                    .add(
                            R.id.container,
                            componentModel.fragment.java.newInstance(),
                            componentModel.name
                    )
                    .addToBackStack(componentModel.name)
                    .commit()
        }

        return rootView;
    }
}