package testapp.justcleanproject.view.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import testapp.justcleanassessment.R
import testapp.justcleanassessment.databinding.FragmentFavoritesBinding
import testapp.justcleanassessment.views.favorites.FavoriteItemsAdapter
import testapp.justcleanassessment.views.comments.CommentsViewModel
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private val viewModel: CommentsViewModel by activityViewModels()
    @Inject
    lateinit var adapter: FavoriteItemsAdapter
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorites, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteItems().observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }
}