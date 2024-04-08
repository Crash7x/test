package com.example.test.screens.detail.character

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.example.recycle.view.and.migrate.coroutines.databinding.FragmentCharactersBinding
import com.example.test.common.application.appComponent
import com.example.test.common.binding.fragment.BindingFragment
import com.example.test.common.view.model.factory.assistedViewModel
import com.example.test.screens.detail.character.di.DaggerCharacterDetailComponent
import javax.inject.Inject

class CharactersDetailFragment : BindingFragment<FragmentCharactersBinding>(FragmentCharactersBinding::inflate) {

    private val args by navArgs<CharactersDetailFragmentArgs>()

    @Inject
    lateinit var factory: CharactersDetailViewModel.Factory
    private val viewModel by assistedViewModel { factory.create(args.characterId) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCharacterDetailComponent.factory().create(context.appComponent).inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadData()
    }

}