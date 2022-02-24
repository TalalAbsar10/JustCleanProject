package testapp.justcleanassessment.views.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.FragmentScoped
import testapp.justcleanassessment.database.DatabaseFavoriteItems
import testapp.justcleanassessment.databinding.FavoriteItemsListBinding
import javax.inject.Inject

@FragmentScoped
class FavoriteItemsAdapter @Inject constructor(val clickListener: ClickListener) :
    ListAdapter<DatabaseFavoriteItems, FavoriteItemsAdapter.ViewHolder>(UserDetailsDiffCallback()) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: FavoriteItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DatabaseFavoriteItems, clickListener: ClickListener) {
            binding.data = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemsListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class UserDetailsDiffCallback : DiffUtil.ItemCallback<DatabaseFavoriteItems>() {

    override fun areItemsTheSame(oldItem: DatabaseFavoriteItems, newItem: DatabaseFavoriteItems): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DatabaseFavoriteItems, newItem: DatabaseFavoriteItems): Boolean {
        return oldItem == newItem
    }

}

class ClickListener @Inject constructor() {

    var onItemClick: ((DatabaseFavoriteItems) -> Unit)? = null

    fun onClick(data: DatabaseFavoriteItems) {
        onItemClick?.invoke(data)
    }

    var onbuttonItemClick: ((DatabaseFavoriteItems) -> Unit)? = null

    fun onbuttonClick(data: DatabaseFavoriteItems) {
        onbuttonItemClick?.invoke(data)
    }
}