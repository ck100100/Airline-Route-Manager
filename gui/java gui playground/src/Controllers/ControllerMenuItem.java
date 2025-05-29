package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenuItem;

public class ControllerMenuItem {
    private  final List<FoodMenuItem> menuItems = new ArrayList<>();
    public ControllerMenuItem(){
        createMockup();
    }

    public List<FoodMenuItem> getMenuItems(){return menuItems;}
    public void addItem(FoodMenuItem item){
        menuItems.add(item);
    }
    public void createMockup() {
        menuItems.add(new FoodMenuItem("Cheeseburger", 8.99, 250));
        menuItems.add(new FoodMenuItem("Margherita Pizza", 10.99, 400));
        menuItems.add(new FoodMenuItem("Caesar Salad", 6.49, 150));
        menuItems.add(new FoodMenuItem("Chocolate Cake", 5.99, 180));
        menuItems.add(new FoodMenuItem("Lemonade", 2.99, 300));
    }
}
