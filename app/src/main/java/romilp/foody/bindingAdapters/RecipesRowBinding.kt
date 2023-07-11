package romilp.foody.bindingAdapters

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import org.jsoup.Jsoup
import romilp.foody.R
import romilp.foody.model.Result
import romilp.foody.ui.fragments.recipes.RecipesFragmentDirections
import java.lang.Exception

class RecipesRowBinding {

    companion object {

        /**
         * Custom BindingAdapter to handle recipe item click listener.
         * @param recipeRowLayout The recipe row layout view.
         * @param result The recipe result data.
         */
        @BindingAdapter("onRecipeClickListener")
        @JvmStatic
        fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
            recipeRowLayout.setOnClickListener {
                Log.d("onRecipeClickListener", result.title)
                try {
                    val action =
                        RecipesFragmentDirections.actionRecipesFragmentToDetailsActivity(result)
                    recipeRowLayout.findNavController().navigate(action)
                } catch (e: Exception) {
                    Log.d("onRecipeClickListener", e.toString())
                }
            }
        }

        /**
         * Custom BindingAdapter to load images from a URL using Coil library.
         * @param imageView The ImageView to load the image into.
         * @param imageUrl The URL of the image to load.
         */
        @BindingAdapter("loadImagesFromUrl")
        @JvmStatic
        fun loadImagesFromUrl(imageView: ImageView, imageUrl: String) {
            imageView.load(imageUrl) {
                crossfade(600)
                error(R.drawable.ic_error_placeholder)
            }
        }

        /**
         * Custom BindingAdapter to apply vegan color to views based on the vegan flag.
         * @param view The view to apply the vegan color to.
         * @param vegan The boolean flag indicating whether the recipe is vegan.
         */
        @BindingAdapter("applyVeganColor")
        @JvmStatic
        fun applyVeganColor(view: View, vegan: Boolean) {
            if (vegan) {
                when (view) {
                    is TextView -> {
                        view.setTextColor(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                    is ImageView -> {
                        view.setColorFilter(
                            ContextCompat.getColor(
                                view.context,
                                R.color.green
                            )
                        )
                    }
                }
            }
        }

        /**
         * Custom BindingAdapter to parse HTML content and set it to a TextView.
         * @param textView The TextView to set the parsed HTML content to.
         * @param description The HTML content to parse and set.
         */
        @BindingAdapter("parseHtml")
        @JvmStatic
        fun parseHtml(textView: TextView, description: String?) {
            if (description != null) {
                val desc = Jsoup.parse(description).text()
                textView.text = desc
            }
        }

    }
}
