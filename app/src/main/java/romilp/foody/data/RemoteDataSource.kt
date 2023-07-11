package romilp.foody.data

import retrofit2.Response
import romilp.foody.data.network.FoodRecipesApi
import romilp.foody.model.FoodJoke
import romilp.foody.model.FoodRecipe
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    /**
     * Get recipes from the remote API.
     * @param queries The query parameters for the API request.
     * @return The API response containing the food recipes.
     */
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    /**
     * Search recipes from the remote API.
     * @param searchQuery The search query parameters for the API request.
     * @return The API response containing the search results.
     */
    suspend fun searchRecipes(searchQuery: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQuery)
    }

    /**
     * Get a random food joke from the remote API.
     * @param apiKey The API key required for the request.
     * @return The API response containing the food joke.
     */
    suspend fun getFoodJoke(apiKey: String): Response<FoodJoke> {
        return foodRecipesApi.getFoodJoke(apiKey)
    }

}
