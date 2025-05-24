package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenu;

public class ControllerFoodMenu {
    private List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
    public void addMenu(FoodMenu menu){
        foodMenus.add(menu);
    }

    public ArrayList<FoodMenu> getAllFoodMenus() {
        return (ArrayList<FoodMenu>) foodMenus;
    }
}
