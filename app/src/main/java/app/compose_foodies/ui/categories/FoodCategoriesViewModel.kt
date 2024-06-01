package app.compose_foodies.ui.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.compose_foodies.model.data.FoodMenuRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodCategoriesViewModel @Inject constructor(private val remoteSource: FoodMenuRemoteSource) :
    ViewModel() {

    var state by mutableStateOf(
        FoodCategoriesContract.State(
            categories = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<FoodCategoriesContract.Effect>(UNLIMITED)
        private set

    init {
        viewModelScope.launch {
            getFoodCategories()
        }
    }

     suspend fun getFoodCategories() {
        val categories = remoteSource.getFoodCategories()
        viewModelScope.launch {
            state = state.copy(categories = categories, isLoading = false)
            effects.send(FoodCategoriesContract.Effect.DataWasLoaded)
        }
    }


}