package testapp.justcleanassessment.views.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import testapp.justcleanassessment.domain.PostsItems
import dagger.hilt.android.scopes.FragmentScoped
import testapp.justcleanassessment.databinding.ItemPostsBinding
import javax.inject.Inject

@FragmentScoped
class PostsAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<PostsItems, PostsAdapter.ViewHolder>(PostsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemPostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostsItems, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPostsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<PostsItems>() {

    override fun areItemsTheSame(oldItem: PostsItems, newItem: PostsItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostsItems, newItem: PostsItems): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((PostsItems) -> Unit)? = null

    fun onClick(data: PostsItems) {
        onItemClick?.invoke(data)
    }
}