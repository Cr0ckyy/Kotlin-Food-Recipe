package romilp.foody.ui.fragments.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import romilp.foody.R
import romilp.foody.adapters.FavoriteRecipesAdapter
import romilp.foody.databinding.FragmentFavoriteRecipesBinding
import romilp.foody.viewModels.MainViewModel

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    // ViewModels
    private val mainViewModel: MainViewModel by viewModels()

    // Adapter for favorite recipes
    private val mAdapter: FavoriteRecipesAdapter by lazy {
        FavoriteRecipesAdapter(
            requireActivity(),
            mainViewModel
        )
    }

    // View binding
    private var _binding: FragmentFavoriteRecipesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        // Enable options menu
        setHasOptionsMenu(true)

        // Set up the RecyclerView
        setUpRecyclerView(binding.favoriteRecipesRecyclerView)

        return binding.root
    }

    // Inflate the options menu for this fragment
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu, menu)
    }

    // Handle options menu item selections
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteAll_favorite_recipes_menu) {
            // Delete all favorite recipes
            mainViewModel.deleteAllFavoriteRecipes()

            // Show snackbar to indicate that all recipes have been removed
            Snackbar.make(binding.root, "All Recipes removed", Snackbar.LENGTH_SHORT)
                .setAction("Okay") {}.show()
        }
        return super.onOptionsItemSelected(item)
    }

    // Set up the RecyclerView with the adapter and layout manager
    private fun setUpRecyclerView(recyclerView: RecyclerView) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    // Clear the context action mode and clean up the view binding
    override fun onDestroyView() {
        super.onDestroyView()
        mAdapter.clearContextualActionMode()
        _binding = null
    }
}
