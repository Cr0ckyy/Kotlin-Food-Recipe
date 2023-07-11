package romilp.foody.bindingAdapters

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import romilp.foody.data.database.entities.FoodJokeEntity
import romilp.foody.model.FoodJoke
import romilp.foody.util.NetworkResult

class FoodJokeBinding {

    companion object {

        /**
         * Custom BindingAdapter to set the visibility of card view and progress bar based on the API response and database.
         * @param view The view to set visibility for.
         * @param apiResponse The API response result.
         * @param database The list of food joke entities from the database.
         */
        @BindingAdapter("readApiResponse3", "readDatabase3", requireAll = false)
        @JvmStatic
        fun setCardAndProgressVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            when (apiResponse) {
                is NetworkResult.Loading -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.VISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.INVISIBLE
                        }
                    }
                }
                is NetworkResult.Error -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                            if (database != null) {
                                if (database.isEmpty()) {
                                    view.visibility = View.INVISIBLE
                                }
                            }
                        }
                    }
                }
                is NetworkResult.Success -> {
                    when (view) {
                        is ProgressBar -> {
                            view.visibility = View.INVISIBLE
                        }
                        is MaterialCardView -> {
                            view.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        /**
         * Custom BindingAdapter to set the visibility of error views based on the API response and database.
         * @param view The view to set visibility for.
         * @param apiResponse The API response result.
         * @param database The list of food joke entities from the database.
         */
        @BindingAdapter("readApiResponse4", "readDatabase4", requireAll = true)
        @JvmStatic
        fun setErrorViewsVisibility(
            view: View,
            apiResponse: NetworkResult<FoodJoke>?,
            database: List<FoodJokeEntity>?
        ) {
            if (database != null) {
                if (database.isEmpty()) {
                    view.visibility = View.VISIBLE
                    if (view is TextView) {
                        if (apiResponse != null) {
                            view.text = apiResponse.message.toString()
                        }
                    }
                }
            }
            if (apiResponse is NetworkResult.Success) {
                view.visibility = View.INVISIBLE
            }
        }

    }
}
