package romilp.foody.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import romilp.foody.data.database.entities.FavoritesEntity
import romilp.foody.data.database.entities.FoodJokeEntity
import romilp.foody.data.database.entities.RecipesEntity
import romilp.foody.model.FoodJoke

/**
 * Data Access Object (DAO) interface for interacting with the recipes database.
 */
@Dao
interface RecipesDAO {

    /**
     * Insert a recipes entity into the recipes table.
     * @param recipesEntity The recipes entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipesEntity: RecipesEntity)

    /**
     * Insert a favorite recipe entity into the favorite recipes table.
     * @param favoritesEntity The favorite recipe entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteRecipe(favoritesEntity: FavoritesEntity)

    /**
     * Insert a food joke entity into the food joke table.
     * @param foodJokeEntity The food joke entity to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodJoke(foodJokeEntity: FoodJokeEntity)

    /**
     * Read all recipes from the recipes table.
     * @return Flow emitting a list of recipes entities.
     */
    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun readRecipes(): Flow<List<RecipesEntity>>

    /**
     * Read all favorite recipes from the favorite recipes table.
     * @return Flow emitting a list of favorite recipe entities.
     */
    @Query("SELECT * FROM favorite_recipes_table ORDER BY id ASC")
    fun readFavoriteRecipes(): Flow<List<FavoritesEntity>>

    /**
     * Read the food joke from the food joke table.
     * @return Flow emitting a list of food joke entities.
     */
    @Query("SELECT * FROM food_joke_table ORDER BY id ASC")
    fun readFoodJoke(): Flow<List<FoodJokeEntity>>

    /**
     * Delete a favorite recipe from the favorite recipes table.
     * @param favoritesEntity The favorite recipe entity to delete.
     */
    @Delete
    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity)

    /**
     * Delete all favorite recipes from the favorite recipes table.
     */
    @Query("DELETE FROM favorite_recipes_table")
    suspend fun deleteAllFavoriteRecipes()
}
