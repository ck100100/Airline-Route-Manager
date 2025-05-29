package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenu;
import Object.FoodMenuItem;

public class ControllerFoodMenu {
    private  List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();

    public ControllerFoodMenu() {
        createMockData();
    }

    public  void addMenu(FoodMenu menu){
        foodMenus.add(menu);
    }
    public List<FoodMenu> getFoodMenus(){return foodMenus;}
    public FoodMenu getMenuByID(int Id){
        for (FoodMenu menu : foodMenus){
            if (menu.getMenuID() == Id){
                return menu;
            }
        }
        return null;

    }
    public int getNextFoodMenuId(){
        if(foodMenus.isEmpty()){
            return 1;
        }
        int lastId = 0;
        for(FoodMenu menu : foodMenus){
            if(menu.getMenuID() > lastId){
                lastId = menu.getMenuID();
            }
        }
        return lastId + 1;
    }

    public void createMockData() {
        for (int i = 1; i <= 15; i++) {
            FoodMenu menu = new FoodMenu();
            menu.menuID = 100 + i;
            menu.name = "Menu " + i;
            menu.description = "Description for menu " + i;
            menu.menuPrice = 20 + i;

            menu.foodMenu = new ArrayList<>();
            menu.foodMenu.add(new FoodMenuItem("Item A" + i, 5.0 + i, 0.2 + (i * 0.01)));
            menu.foodMenu.add(new FoodMenuItem("Item B" + i, 6.0 + i, 0.3 + (i * 0.01)));
            menu.foodMenu.add(new FoodMenuItem("Item C" + i, 7.0 + i, 0.4 + (i * 0.01)));

            foodMenus.add(menu);
        }
    }
}
