package romilp.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import romilp.foody.databinding.RecipesRowLayoutBinding
import romilp.foody.model.FoodRecipe
import romilp.foody.model.Result
import romilp.foody.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()

    class MyViewHolder(private val binding: RecipesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                // Inflate the layout for each item view
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

        fun bind(result: Result) {
            // Bind data to the views of the item view
            binding.result = result
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Create a ViewHolder instance for each item view
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data to the ViewHolder at the specified position
        val currentRecipe = recipes[position]
        holder.bind(currentRecipe)
    }

    override fun getItemCount(): Int {
        // Return the total number of recipes
        return recipes.size
    }

    fun setData(newData: FoodRecipe) {
        // Calculate the diff between the old and new recipe lists
        val recipesDiffUtil =
            RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        recipes = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
