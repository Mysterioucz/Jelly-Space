package entities.Player;

import Items.Base.Base_Item;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Base_Item> Items;
    private final int MAX_ITEMS = 5;

    public Inventory() {
        Items = new ArrayList<>();
    }

    public void addItem(Base_Item item){
        if (Items.size() < MAX_ITEMS){
            Items.add(item);
        }else {
            System.out.println("Your inventory is full");
        }
    }

    public void deleteUsedItem(){
        int i = 0;
        while (i<Items.size()){
            if (Items.get(i).isUsed()){
                Items.remove(i);
            }else {
                i ++;
            }
        }
    }

    public ArrayList<Base_Item> getItems() {
        return Items;
    }
}
