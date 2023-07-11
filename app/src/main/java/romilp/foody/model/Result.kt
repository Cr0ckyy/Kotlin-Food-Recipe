package romilp.foody.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Result(
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int, // The number of aggregate likes for the recipe
    @SerializedName("cheap")
    val cheap: Boolean, // Indicates if the recipe is cheap
    @SerializedName("dairyFree")
    val dairyFree: Boolean, // Indicates if the recipe is dairy-free
    @SerializedName("extendedIngredients")
    val extendedIngredients: @RawValue List<ExtendedIngredient>, // The list of extended ingredients for the recipe
    @SerializedName("glutenFree")
    val glutenFree: Boolean, // Indicates if the recipe is gluten-free
    @SerializedName("id")
    val recipeId: Int, // The ID of the recipe
    @SerializedName("image")
    val image: String, // The URL of the recipe image
    @SerializedName("readyInMinutes")
    val readyInMinutes: Int, // The cooking time in minutes for the recipe
    @SerializedName("sourceName")
    val sourceName: String?, // The name of the recipe source
    @SerializedName("sourceUrl")
    val sourceUrl: String, // The URL of the recipe source
    @SerializedName("summary")
    val summary: String, // The summary or description of the recipe
    @SerializedName("title")
    val title: String, // The title of the recipe
    @SerializedName("vegan")
    val vegan: Boolean, // Indicates if the recipe is vegan
    @SerializedName("vegetarian")
    val vegetarian: Boolean, // Indicates if the recipe is vegetarian
    @SerializedName("veryHealthy")
    val veryHealthy: Boolean, // Indicates if the recipe is very healthy
) : Parcelable
