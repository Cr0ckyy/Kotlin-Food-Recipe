package romilp.foody.bindingAdapters

import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import romilp.foody.adapters.FavoriteRecipesAdapter
import romilp.foody.data.database.entities.FavoritesEntity

class FavoriteRecipesBinding {
    companion object {

        /**
         * Custom BindingAdapter to set visibility of a view based on the data.
         * @param view The view to set visibility for.
         * @param favoritesEntity The list of favorite entities.
         * @param mAdapter The adapter to update the data for.
         */
        @BindingAdapter("setVisibility", "setData", requireAll = false)
        @JvmStatic
        fun setVisibility(
            view: View,
            favoritesEntity: List<FavoritesEntity>?,
            mAdapter: FavoriteRecipesAdapter?
        ) {
            when (view) {
                is RecyclerView -> {
                    val dataCheck = favoritesEntity.isNullOrEmpty()
                    view.isInvisible = dataCheck
                    if (!dataCheck) {
                        favoritesEntity?.let { mAdapter?.setData(it) }
                    }
                }
                else -> view.isVisible = favoritesEntity.isNullOrEmpty()
            }
        }
    }
}
