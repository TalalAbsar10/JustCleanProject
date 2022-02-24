package testapp.justcleanassessment.views.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import testapp.justcleanassessment.domain.CommentsItems
import testapp.justcleanassessment.repository.CommentsRepository
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val commentsRepository: CommentsRepository
) : ViewModel() {

    fun getUserDetails(id: Int) = commentsRepository.getUserDetails(id)

    fun refreshUserDetails(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        commentsRepository.refreshComments(id)
    }

    fun getFavoriteItems() = commentsRepository.getFavoritesItems()

    fun refreshFavoriteItems(data: CommentsItems) = viewModelScope.launch(Dispatchers.IO) {
        commentsRepository.refreshFavoritesItems(data)
    }

}