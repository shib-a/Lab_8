package org.example.classes;

public class Item {
    public String itemName;
    private boolean isPickable = true;

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
