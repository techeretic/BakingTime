package com.example.android.bakingtime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

/**
 * Created by mihirnewalkar on 9/16/17.
 */

//public class RecipesAdapter extends ArrayAdapter<Recipes> {
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

//    private static final String LOG_TAG = RecipesAdapter.class.getSimpleName();
    private static final String LOG_TAG = "RecipesAdapter";

//    private List<Recipes> mRecipesList;
    private List<Recipes> mRecipesList = new ArrayList<>();

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param recipes A List of news objects to display in a list
     */
//    public RecipesAdapter(Activity context, ArrayList<Recipes> recipes) {
//        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
//        // the second argument is used when the ArrayAdapter is populating a single TextView.
//        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
//        // going to use this second argument, so it can be any value. Here, we used 0.
//        super(context, 0, recipes);
//        mRecipesList = recipes;
//    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // Check if the existing view is being reused, otherwise inflate the view
//        View listItemView = convertView;
//        if(listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.list_item, parent, false);
//        }
//
//        // Get the {@link AndroidFlavor} object located at this position in the list
//        Recipes currentRecipes = getItem(position);
//
//        TextView nameView = (TextView) listItemView.findViewById(R.id.recipe_name_tv);
//        String sName = currentRecipes.getName();
//        nameView.setText(sName);
//
//        TextView servingsView = (TextView) listItemView.findViewById(R.id.servings_tv);
//        int sServings = currentRecipes.getServings();
//        servingsView.setText(Integer.toString(sServings));
//
//        // Return the whole list item layout
//        // so that it can be shown in the ListView
//        return listItemView;
//    }

    private RecipeCardClickListener listener;

    void swapData(List<Recipes> recipes) {
        mRecipesList.clear();
        mRecipesList.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesAdapter.ViewHolder holder, int position) {
        holder.bind(mRecipesList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRecipesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView servings;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name_tv);
            servings = itemView.findViewById(R.id.servings_tv);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onRecipeCardClicked(view, getAdapterPosition());
            }
        }

        void bind(Recipes recipes) {
            name.setText(recipes.getName());
            String servingsText = servings.getContext().getString(R.string.servings, Integer.valueOf(recipes.getServings()));
            servings.setText(servingsText);
        }
    }

    @Nullable
    public Recipes getItem(int position) {
        if (position > mRecipesList.size() || position < 0) {
            return null;
        } else {
            return mRecipesList.get(position);
        }
    }

    public interface RecipeCardClickListener{
        /**
         * Method for when recipe is clicked
         *
         * @param view View of ViewHolder
         * @param position Position of item clicked in recycler view
         */
        void onRecipeCardClicked(View view, int position);
    }

    public void setOnItemClickListener(RecipeCardClickListener listener) {
        this.listener = listener;
    }
}