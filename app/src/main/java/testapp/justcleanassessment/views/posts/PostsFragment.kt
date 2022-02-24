package testapp.justcleanassessment.views.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import testapp.justcleanassessment.R
import dagger.hilt.android.AndroidEntryPoint
import testapp.justcleanassessment.databinding.FragmentPostsListBinding
import javax.inject.Inject

@AndroidEntryPoint
class PostsFragment : Fragment() {
    private val viewModel: PostsViewModel by viewModels()

    @Inject
    lateinit var adapter: PostsAdapter

    private var _binding: FragmentPostsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_posts_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        adapter.clickListener.onItemClick = {
            findNavController().navigate(PostsFragmentDirections.actionUsersListToUserDetails(it.id))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}