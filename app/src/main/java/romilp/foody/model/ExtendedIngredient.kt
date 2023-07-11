package romilp.foody.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExtendedIngredient(
    @SerializedName("amount")
    val amount: Double?, // The amount of the ingredient
    @SerializedName("consistency")
    val consistency: String?, // The consistency of the ingredient
    @SerializedName("image")
    val image: String?, // The URL of the ingredient image
    @SerializedName("name")
    val name: String?, // The name of the ingredient
    @SerializedName("original")
    val original: String?, // The original text describing the ingredient
    @SerializedName("unit")
    val unit: String? // The unit of measurement for the ingredient
) : Parcelable
