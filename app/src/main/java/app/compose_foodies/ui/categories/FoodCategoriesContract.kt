package app.compose_foodies.ui.categories

import app.compose_foodies.model.data.FoodItem

class FoodCategoriesContract {

    data class State(
        val categories: List<FoodItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        data object DataWasLoaded : Effect()
    }


}