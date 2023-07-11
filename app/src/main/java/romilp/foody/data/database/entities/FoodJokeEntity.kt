package romilp.foody.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import romilp.foody.model.FoodJoke
import romilp.foody.util.Constants.Companion.FOOD_JOKE_TABLE

/**
 * Entity class representing a food joke stored in the database.
 *
 * @param foodJoke The FoodJoke object associated with the entity.
 */
@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke: FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
