package Player;

import Items.Base_Item;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Base_Item> Items;
    private final int MAX_ITEMS = 5;

    public Inventory() {
        Items = new ArrayList<>();
    }

    public void addItem(Base_Item item){
        this.Items.add(item);
    }
}
