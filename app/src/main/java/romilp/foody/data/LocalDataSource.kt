package romilp.foody.data

import kotlinx.coroutines.flow.Flow
import romilp.foody.data.database.RecipesDAO
import romilp.foody.data.database.entities.FavoritesEntity
import romilp.foody.data.database.entities.FoodJokeEntity
import romilp.foody.data.database.entities.RecipesEntity
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO
) {
    /**
     * Read recipes from the local database.
     */
    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDAO.readRecipes()
    }

    /**
     * Read favorite recipes from the local database.
     */
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>> {
        return recipesDAO.readFavoriteRecipes()
    }

    /**
     * Read food jokes from the local database.
     */
    fun readFoodJoke(): Flow<List<FoodJokeEntity>> {
        return recipesDAO.readFoodJoke()
    }

    /**
     * Insert recipes into the local database.
     * @param recipesEntity The recipes entity to be inserted.
     */
    suspend fun insertRecipes(recipesEntity: RecipesEntity) {
        recipesDAO.insertRecipes(recipesEntity)
    }

    /**
     * Insert favorite recipes into the local database.
     * @param favoritesEntity The favorites entity to be inserted.
     */
    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity) {
        recipesDAO.insertFavoriteRecipe(favoritesEntity)
    }

    /**
     * Insert food joke into the local database.
     * @param foodJokeEntity The food joke entity to be inserted.
     */
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity) {
        recipesDAO.insertFoodJoke(foodJokeEntity)
    }

    /**
     * Delete a favorite recipe from the local database.
     * @param favoritesEntity The favorites entity to be deleted.
     */
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity) {
        recipesDAO.deleteFavoriteRecipe(favoritesEntity)
    }

    /**
     * Delete all favorite recipes from the local database.
     */
    suspend fun deleteAllFavoriteRecipes() {
        recipesDAO.deleteAllFavoriteRecipes()
    }
}
