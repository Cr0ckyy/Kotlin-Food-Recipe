package romilp.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import romilp.foody.model.FoodRecipe
import romilp.foody.util.Constants.Companion.RECIPES_TABLE

/**
 * Entity class representing a recipe stored in the database.
 *
 * @param foodRecipe The FoodRecipe object associated with the entity.
 */
@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(var foodRecipe: FoodRecipe) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
