package app.compose_foodies.ui.categories_details

import app.compose_foodies.model.data.FoodItem

class FoodCategoryDetailsContract {

    data class State(
        val category: FoodItem?,
        val categoryFoodItems: List<FoodItem>
    )

}