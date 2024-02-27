package org.example.classes;

public class Item {
    public String itemName;
    private boolean isPickable = true;

    public void addToInventory(Item this, Human human){
        int count = 0;
        for(int i = 0; i < 4; i++){
            if (human.inventory[i] == null){
                human.inventory[i] = this;
                break;
            }else{
                count++;
            }
        }
        if (count==4){
            System.out.println("Unable to take this: Inventory is full!");
        }
    }
    public Item(String itemName){
        this.itemName = itemName;
    }

    //setters

    public void setState(boolean n){
        isPickable = n;
    }

    //getters

    public boolean getState(){
        return isPickable;
    }

    @Override
    public String toString() {
        return itemName;
    }
    public boolean validate(){
        if (itemName==null||itemName.isEmpty()||itemName.isBlank()){return false;}
        return true;
    }
}
