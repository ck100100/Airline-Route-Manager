package Controllers;

import java.util.ArrayList;
import java.util.List;
import Object.FoodMenu;

public class ControllerFoodMenu {
    private  final List<FoodMenu> foodMenus = new ArrayList<FoodMenu>();
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

}
