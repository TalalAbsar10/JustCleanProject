package testapp.justcleanassessment.views.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import testapp.justcleanassessment.R
import testapp.justcleanassessment.databinding.FragmentCommentsListBinding
import javax.inject.Inject


@AndroidEntryPoint
class CommentsFragment : Fragment() {
    private val viewModel: CommentsViewModel by activityViewModels()
    private val args: CommentsFragmentArgs by navArgs()
    @Inject
    lateinit var adapter: CommentsAdapter
    private var _binding: FragmentCommentsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_comments_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshUserDetails(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserDetails(args.id).observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        adapter.clickListener.onbuttonItemClick = {
            viewModel.refreshFavoriteItems(it)
            Toast.makeText(context, "Item with Id: ${it.id} Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }

}