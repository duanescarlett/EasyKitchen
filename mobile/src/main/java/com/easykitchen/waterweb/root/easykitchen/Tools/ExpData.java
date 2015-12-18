package com.easykitchen.waterweb.root.easykitchen.Tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by root on 5/16/15.
 */
public class ExpData {

    public static HashMap<String, List<String>> getInfo(){
        HashMap<String, List<String>> catDetails = new HashMap<String, List<String>>();
        List<String> appetizers = new ArrayList<String>();
        appetizers.add("Bread Sticks");
        appetizers.add("Bun and Cheese");

        List<String> condiments = new ArrayList<String>();

        List<String> comfort = new ArrayList<String>();

        List<String> desserts = new ArrayList<String>();

        List<String> dressing = new ArrayList<String>();

        List<String> drinks = new ArrayList<String>();

        List<String> sides = new ArrayList<String>();

        List<String> sauces = new ArrayList<String>();

        List<String> seasonings = new ArrayList<String>();

        List<String> meats = new ArrayList<String>();
        meats.add("Beef");
        meats.add("Seafood");
        meats.add("Chicken");
        meats.add("Turkey");
        meats.add("Lamb");

        catDetails.put("Appetizers", appetizers);
        catDetails.put("Condiments", condiments);
        catDetails.put("Comfort", comfort);
        catDetails.put("Desserts", desserts);
        catDetails.put("Dressing", dressing);
        catDetails.put("Drinks", drinks);
        catDetails.put("Sides", sides);
        catDetails.put("Sauces", sauces);
        catDetails.put("Seasonings", seasonings);
        catDetails.put("Meats", meats);

        return catDetails;

    }
}
