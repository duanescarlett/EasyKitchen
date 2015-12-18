package com.easykitchen.waterweb.root.easykitchen.Controllers;

import com.easykitchen.waterweb.root.easykitchen.Models.CatItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 9/2/15.
 */
public class CatArrays {

    public static List<CatItem> appetizers(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Fried", "Steamed", "Grilled", "Canapes",
                "Fruit", "Bread", "Soup", "Dip",
                "Meat", "Cheese", "Spread", "Spicy",
                "Seafood", "Snack", "Other"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> desserts(){
        
        List<CatItem> data = new ArrayList<CatItem>();

        String[] items = {
            "Cakes", "Cheese Cakes", "Cobblers",
            "Compotes", "Custards", "Fruit Desserts",
            "Ice Cream", "Jello Gelatin", "Meringues",
            "Parfaits", "Pastries", "Puddings",
            "Pies", "Sauces", "Sherbets",
            "Shortcakes", "Souffles", "Tortes",
            "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> seaFood(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Scallops", "Prawns", "Crab", "Clams",
                "Mussels", "Oysters", "Squid", "Pipis",
                "Calamari", "Octopus", "Yabbies", "Squid",
                "Fish", "Lobster", "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> meats(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Beef", "Lamb", "Pork", "Chicken",
                "Turkey", "Quail", "Duck", "Sausages",
                "Rabbit", "Goose", "Kangaroo", "Goat",
                "Mince", "Offal", "Venison", "Steak"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> pastry(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Cake", "Muffins", "Pudding", "Chocolate",
                "Biscuits", "Cookies", "Other"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> vegetarian(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Vegan Appetizers", "Vegan Side Dish", "Vegan Soup", "Vegan Stew",
                "Vegan Pastry", "Vegan Main Dish", "Other"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }


    public static List<CatItem> sideDish(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Salad", "Coleslaw", "Chip", "Wedges",
                "Dip", "Fries", "Bread", "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> drinks(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Smoothies", "Punch", "Tea", "Shakes",
                "Floats", "Juice", "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

    public static List<CatItem> alcoholicDrinks(){
        List<CatItem> data = new ArrayList<CatItem>();
        String[] items = {
                "Vodka", "Martinis", "Rum", "Whiskey",
                "Tequila", "Margaritas", "Beer", "Wine",
                "Bourbon", "Brandy", "Cider", "Port",
                "Others"
        };
        for(int i = 0; i < items.length; i++){
            CatItem current = new CatItem(items[i]);
            data.add(current);
        }
        return data;
    }

}
