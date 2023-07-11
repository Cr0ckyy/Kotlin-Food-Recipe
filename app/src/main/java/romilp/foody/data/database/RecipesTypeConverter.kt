package romilp.foody.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import romilp.foody.model.FoodRecipe
import romilp.foody.model.Result

/**
 * TypeConverter class for converting complex data types to and from String representation for Room database.
 */
class RecipesTypeConverter {
    private val gson = Gson()

    /**
     * Converts a FoodRecipe object to a JSON string.
     * @param foodRecipe The FoodRecipe object to convert.
     * @return The JSON string representation of the FoodRecipe object.
     */
    @TypeConverter
    fun foodRecipesToString(foodRecipe: FoodRecipe): String {
        return gson.toJson(foodRecipe)
    }

    /**
     * Converts a JSON string to a FoodRecipe object.
     * @param data The JSON string to convert.
     * @return The FoodRecipe object represented by the JSON string.
     */
    @TypeConverter
    fun stringToFoodRecipes(data: String): FoodRecipe {
        val listType = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(data, listType)
    }

    /**
     * Converts a Result object to a JSON string.
     * @param result The Result object to convert.
     * @return The JSON string representation of the Result object.
     */
    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    /**
     * Converts a JSON string to a Result object.
     * @param data The JSON string to convert.
     * @return The Result object represented by the JSON string.
     */
    @TypeConverter
    fun stringToResult(data: String): Result {
        val listType = object : TypeToken<Result>() {}.type
        return gson.fromJson(data, listType)
    }
}
