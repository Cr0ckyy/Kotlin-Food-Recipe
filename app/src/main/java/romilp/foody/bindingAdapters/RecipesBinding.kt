package romilp.foody.bindingAdapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import romilp.foody.data.database.entities.RecipesEntity
import romilp.foody.model.FoodRecipe
import romilp.foody.util.NetworkResult

class RecipesBinding {

    companion object {

        /**
         * Custom BindingAdapter to handle errors when reading data from API and database.
         * @param view The view to handle errors for.
         * @param apiResponse The API response result.
         * @param database The list of recipes entities from the database.
         */
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        @JvmStatic
        fun handleReadDataErrors(
            view: View,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<RecipesEntity>?
        ) {
            when (view) {
                is ImageView -> {
                    // Show/hide the ImageView based on API response and empty database
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                }
                is TextView -> {
                    // Show/hide the TextView based on API response and empty database, and set the error message
                    view.isVisible = apiResponse is NetworkResult.Error && database.isNullOrEmpty()
                    view.text = apiResponse?.message.toString()
                }
            }
        }
    }
}
