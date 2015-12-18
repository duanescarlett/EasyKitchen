package com.easykitchen.waterweb.root.easykitchen.Tools;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.easykitchen.waterweb.root.easykitchen.AddRecipeActivity;
import com.easykitchen.waterweb.root.easykitchen.Controllers.CatArrays;
import com.easykitchen.waterweb.root.easykitchen.Models.CatItem;
import com.easykitchen.waterweb.root.easykitchen.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryDialog extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences type;
    View view;
    private RecyclerView recyclerView;
    private CatAdapter catAdapter;
    private List<CatItem> list;
    private CatArrays cat;
    private Context c;
    private static int clickCounter;

    public CategoryDialog(){
        clickCounter = 0;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        catAdapter = new CatAdapter();
        cat = new CatArrays();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.view = inflater.inflate(R.layout.activity_category, container, false);
        return this.view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.nestly);

        // Create the storage for the selected category
        this.sharedPreferences = getActivity().getSharedPreferences("Category", Context.MODE_PRIVATE);

        recycle(getData());

        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        final Button button = (Button) getActivity().findViewById(R.id.CategoryDialogBackButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Reset the counter
                clickCounter = 0;
                // Perform action on click
                recycle(getData());
            }
        });

        final ImageButton close = (ImageButton) getActivity().findViewById(R.id.closeButton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = getActivity().getBaseContext();
                Intent intent = new Intent(c, AddRecipeActivity.class);
                startActivity(intent);
            }
        });

    }

    private static List<CatItem> getData(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Appetizers", "Desserts", "Seafood", "Meats",
                "Pastry", "Vegetarian", "Side Dish", "Drinks",
                "Alcoholic Drinks", "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
    }

    private void recycle(final List l){

        recyclerView.removeAllViews();
        Object shave = l.toArray();
        shave.toString();

        final String[] dessertItems = {
                "Cakes", "Cheese Cakes", "Cobblers",
                "Compotes", "Custards", "Fruit Desserts",
                "Ice Cream", "Jello Gelatin", "Meringues",
                "Parfaits", "Pastries", "Puddings",
                "Pies", "Sauces", "Sherbets",
                "Shortcakes", "Souffles", "Tortes",
                "Others"
        };

        final String[] appetizerItems = {
                "Fried", "Steamed", "Grilled", "Canapes",
                "Fruit", "Bread", "Soup", "Dip",
                "Meat", "Cheese", "Spread", "Spicy",
                "Seafood", "Snack", "Other"
        };

        final String[] seaFoodItems = {
                "Scallops", "Prawns", "Crab", "Clams",
                "Mussels", "Oysters", "Squid", "Pipis",
                "Calamari", "Octopus", "Yabbies", "Squid",
                "Fish", "Lobster", "Others"
        };

        final String[] meatItems = {
                "Beef", "Lamb", "Pork", "Chicken",
                "Turkey", "Quail", "Duck", "Sausages",
                "Rabbit", "Goose", "Kangaroo", "Goat",
                "Mince", "Offal", "Venison", "Steak"
        };

        final String[] pastryItems = {
                "Cake", "Muffins", "Pudding", "Chocolate",
                "Biscuits", "Cookies", "Other"
        };

        final String[] vegetarianItems = {
                "Vegan Appetizers", "Vegan Side Dish", "Vegan Soup", "Vegan Stew",
                "Vegan Pastry", "Vegan Main Dish", "Other"
        };

        final String[] sideDishItems = {
                "Salad", "Coleslaw", "Chip", "Wedges",
                "Dip", "Fries", "Bread", "Others"
        };

        final String[] drinkItems = {
                "Smoothies", "Punch", "Tea", "Shakes",
                "Floats", "Juice", "Others"
        };

        final String[] alcoholicDrinkItems = {
                "Vodka", "Martinis", "Rum", "Whiskey",
                "Tequila", "Margaritas", "Beer", "Wine",
                "Bourbon", "Brandy", "Cider", "Port",
                "Others"
        };

        catAdapter.CatAdapterInit(getActivity(), l);
        recyclerView.setAdapter(catAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickerListener() {

            @Override
            public void onClick(View view, int position) {

                int i = recyclerView.getChildAdapterPosition(view);

                type = getActivity().getSharedPreferences("DishType", Context.MODE_PRIVATE);
                SharedPreferences.Editor typeEditor = type.edit(); // Get the editor

                String x = menu(position);
                //Toast.makeText(getActivity(), x, Toast.LENGTH_SHORT).show();
                clickCounter++;

                switch (position){
                    case 0: // Appetizers

                        if(x == "appetizers" && clickCounter > 1){
                            String xx = appetizerItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 1: // Desserts
                        if(x == "desserts" && clickCounter > 1){
                            String xx = dessertItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 2:
                        if(x == "seafood" && clickCounter > 1){
                            String xx = seaFoodItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 3:
                        if(x == "meats" && clickCounter > 1){
                            String xx = meatItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 4:
                        if(x == "pastry" && clickCounter > 1){
                            String xx = pastryItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 5:
                        if(x == "vegetarian" && clickCounter > 1){
                            String xx = vegetarianItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 6:
                        if(x == "side dish" && clickCounter > 1){
                            String xx = sideDishItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 7:
                        if(x == "drinks" && clickCounter > 1){
                            String xx = drinkItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case 8:
                        if(x == "alcoholicDrinks" && clickCounter > 1){
                            String xx = alcoholicDrinkItems[position];
                            Toast.makeText(getActivity(), xx, Toast.LENGTH_SHORT).show();

                            // Collect data
                            typeEditor.putString(position+"", x);

                            // Close dialog and return to activity
                            c = getActivity().getBaseContext();
                            Intent intent = new Intent(c, AddRecipeActivity.class);
                            startActivity(intent);
                        }
                        break;
                    default:
                        String text = "Looking forward to the Weekend";
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDectector;
        private ClickerListener clickerListener;
        private RecyclerView rv;
        private Context context;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickerListener clickerListener){
            this.rv = recyclerView;
            this.clickerListener = clickerListener;
            this.context = context;
            gestureDectector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e){
                    Log.i("Dos", "onLongTapUp " + e);
                    //return super.onSingleTapUp(e);
                    return true;
                }
                @Override
                public void onLongPress(MotionEvent e){
                    Log.i("Dos", "onLongPress " + e);
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());

                    if(child != null && clickerListener != null){
                        clickerListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
            Log.d("Dos", "RecyclerTouchListener implemented");
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            this.rv = rv;
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if(child != null && clickerListener != null && gestureDectector.onTouchEvent(e)){
                clickerListener.onClick(child, rv.getChildAdapterPosition(child));

            }
            Log.d("Dos", "onInterceptTouchEvent " + gestureDectector.onTouchEvent(e) + " " + e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d("Dos", "RecyclerTouchListener implemented");
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            Log.d("Dos", "onRequestDisallowInterceptTouchEvent implemented");
        }

    }

    public interface ClickerListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    private String menu(int n){
        String category;
        switch (n) {
            case 0:
                list = cat.appetizers();
                recycle(list);
                category = "appetizers";
                break;
            case 1:
                list = cat.desserts();
                recycle(list);
                category = "desserts";
                break;
            case 2:
                list = cat.seaFood();
                recycle(list);
                category = "seafood";
                break;
            case 3:
                list = cat.meats();
                recycle(list);
                category = "meats";
                break;
            case 4:
                list = cat.pastry();
                recycle(list);
                category = "pastry";
                break;
            case 5:
                list = cat.vegetarian();
                recycle(list);
                category = "vegetarian";
                break;
            case 6:
                list = cat.sideDish();
                recycle(list);
                category = "side dish";
                break;
            case 7:
                list = cat.drinks();
                recycle(list);
                category = "drinks";
                break;
            case 8:
                list = cat.alcoholicDrinks();
                recycle(list);
                category = "alcoholic drinks";
                break;
            default:
                String text = "Looking forward to the Weekend";
                category = "null";
        }
        return category;
    }
}

