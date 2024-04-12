package com.example.test.screens.characters

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.recycle.view.and.migrate.coroutines.databinding.FragmentCharactersBinding
import com.example.test.common.application.appComponent
import com.example.test.common.binding.fragment.BindingFragment
import com.example.test.common.extensions.observe
import com.example.test.common.generic.list.adapter.GenericListAdapter
import com.example.test.common.generic.list.adapter.Item
import com.example.test.common.navigation.safeNavigate
import com.example.test.common.view.model.factory.assistedViewModel
import com.example.test.screens.characters.adapter.CharacterHolderFactory
import com.example.test.screens.detail.character.adapter.CharacterDetailHolderFactory
import com.example.test.screens.characters.di.DaggerCharactersComponent
import com.example.test.screens.characters.domain.mapper.toItemList
import javax.inject.Inject

class CharactersFragment : BindingFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    @Inject
    lateinit var factory: CharactersViewModel.Factory
    private val viewModel by assistedViewModel { factory.create() }

    @Inject
    lateinit var characterHolderFactory: CharacterHolderFactory
    private val adapter by lazy(LazyThreadSafetyMode.NONE) {
        GenericListAdapter<Item>(characterHolderFactory)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharactersComponent.factory().create(context.appComponent).inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
        setupCharacters()
        setupNavigation()
    }

    private fun setupCharacters() {
        binding.character.adapter = adapter
        binding.character.itemAnimator = null

        viewModel.characters.observe(viewLifecycleOwner) {
            adapter.submitList(
                it.toItemList(
                    viewModel::navigateCharacterDetail
                )
            )
        }
    }

    private fun setupNavigation() {
        viewModel.navCommand.observe(
            viewLifecycleOwner,
            observer = findNavController()::safeNavigate
        )
    }

}