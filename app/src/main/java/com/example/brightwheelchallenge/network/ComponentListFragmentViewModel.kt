package com.example.brightwheelchallenge.network

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.brightwheelchallenge.ui.RepoListFragment
import kotlin.reflect.KClass

/**
 * View model to store list of [ComponentModel]
 */
class ComponentListFragmentViewModel: ViewModel() {

    // list of available components in the app
    val componentList = listOf<ComponentModel<*>> (
        ComponentModel("Repo List", RepoListFragment::class)
    )
}

data class ComponentModel<T>(
        val name: String,
        val fragment: KClass<T>
) where T: Fragment {
    override fun toString(): String {
        return name
    }
}