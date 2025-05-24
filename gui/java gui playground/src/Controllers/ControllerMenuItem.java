package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenuItem;

public class ControllerMenuItem {
    private static final List<FoodMenuItem> menuItems = new ArrayList<>();

    public static List<FoodMenuItem> getMenuItems(){return menuItems;}
    public static void addItem(FoodMenuItem item){
        menuItems.add(item);
    }
}
