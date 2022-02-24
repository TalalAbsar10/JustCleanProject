package testapp.justcleanassessment.views.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.FragmentScoped
import testapp.justcleanassessment.databinding.ItemsCommentsBinding
import testapp.justcleanassessment.domain.CommentsItems
import javax.inject.Inject

@FragmentScoped
class CommentsAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<CommentsItems, CommentsAdapter.ViewHolder>(CommentsDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemsCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CommentsItems, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
            binding.clickListener = clickListener
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemsCommentsBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class CommentsDiffCallback : DiffUtil.ItemCallback<CommentsItems>() {

    override fun areItemsTheSame(oldItem: CommentsItems, newItem: CommentsItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentsItems, newItem: CommentsItems): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onbuttonItemClick: ((CommentsItems) -> Unit)? = null

    fun onbuttonClick(data: CommentsItems) {
        onbuttonItemClick?.invoke(data)
    }
}