package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenuItem;

public class ControllerMenuItem {
    private  final List<FoodMenuItem> menuItems = new ArrayList<>();

    public List<FoodMenuItem> getMenuItems(){return menuItems;}
    public void addItem(FoodMenuItem item){
        menuItems.add(item);
    }
}
