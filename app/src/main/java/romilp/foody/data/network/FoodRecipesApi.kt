package romilp.foody.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import romilp.foody.model.FoodJoke
import romilp.foody.model.FoodRecipe

/**
 * Retrofit API interface for food recipes.
 */
interface FoodRecipesApi {

    /**
     * Retrieve a list of recipes based on provided queries.
     * @param queries The queries used for searching recipes.
     * @return Response containing the FoodRecipe data.
     */
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String, String>
    ): Response<FoodRecipe>

    /**
     * Search for recipes based on the provided search query.
     * @param searchQuery The search query used for searching recipes.
     * @return Response containing the FoodRecipe data.
     */
    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @QueryMap searchQuery: Map<String, String>
    ): Response<FoodRecipe>

    /**
     * Retrieve a random food joke.
     * @param apiKey The API key for accessing the endpoint.
     * @return Response containing the FoodJoke data.
     */
    @GET("food/jokes/random")
    suspend fun getFoodJoke(
        @Query("apiKey") apiKey: String
    ): Response<FoodJoke>
}
