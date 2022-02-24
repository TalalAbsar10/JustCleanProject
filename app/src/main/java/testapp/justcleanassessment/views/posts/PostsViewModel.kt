package testapp.justcleanassessment.views.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import testapp.justcleanassessment.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val postsRepository: PostsRepository
) : ViewModel() {

    val data = postsRepository.users

    init {
        viewModelScope.launch(Dispatchers.IO) {
            postsRepository.refreshPosts()
        }
    }

}