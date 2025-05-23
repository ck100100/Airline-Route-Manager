package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenu;

public class ControllerFoodMenu {
    private static final List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
    public static void addMenu(FoodMenu menu){
        foodMenus.add(menu);
    }

}
