package romilp.foody.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import romilp.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import romilp.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import romilp.foody.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import romilp.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE
import romilp.foody.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import romilp.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import romilp.foody.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import romilp.foody.util.Constants.Companion.PREFERENCES_NAME
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    // Define preference keys for accessing DataStore
    private object PreferenceKeys {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    // Access DataStore using preferencesDataStore delegate
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = PREFERENCES_NAME
    )

    /**
     * Save the selected meal type and diet type to DataStore.
     * @param mealType The selected meal type.
     * @param mealTypeId The ID of the selected meal type.
     * @param dietType The selected diet type.
     * @param dietTypeId The ID of the selected diet type.
     */
    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedDietType] = dietType
            preferences[PreferenceKeys.selectedDietTypeId] = dietTypeId
        }
    }

    /**
     * Save the back online status to DataStore.
     * @param backOnline The back online status.
     */
    suspend fun saveBackOnline(backOnline: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    /**
     * Read the selected meal type and diet type from DataStore.
     */
    val readMealAndDietType: Flow<MealAndDietType> = context.dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        val selectedMealType = preferences[PreferenceKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
        val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
        val selectedDietType = preferences[PreferenceKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
        val selectedDietTypeId = preferences[PreferenceKeys.selectedDietTypeId] ?: 0
        MealAndDietType(
            selectedMealType,
            selectedMealTypeId,
            selectedDietType,
            selectedDietTypeId
        )
    }

    /**
     * Read the back online status from DataStore.
     */
    val readBackOnline: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}

/**
 * Data class representing the selected meal type and diet type.
 * @param selectedMealType The selected meal type.
 * @param selectedMealTypeId The ID of the selected meal type.
 * @param selectedDietType The selected diet type.
 * @param selectedDietTypeId The ID of the selected diet type.
 */
data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)
