package org.example.classes;

import org.example.collection.CollectionLoaderSaver;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CollectionManager {
    public int currId = 1;
    public ArrayList<Human> collection = new ArrayList<>();
    public LocalDateTime initDate;
    private  final CollectionLoaderSaver cls;
    public CollectionManager(CollectionLoaderSaver cls){
        this.cls = cls;
    }
    public LocalDateTime getInitDate(){
        return initDate;
    }
    public ArrayList<Human> getCollection(){
        return collection;
    }
    public Human getById(int id){
        for (Human el: collection){
            if (el.getId()==id){
                return el;
            }
        }
        return null;
    }

    /**
     * Determines whether a Human instance is in a collection
     * @param h
     * @return boolean
     */
    public boolean isInCol(Human h){
                if(getById(h.getId())!=null){return true;} else {return false;}
        }
    public int getUnusedId(){
        while(getById(++currId)!=null); return currId;
    }

    /**
     * Adds a Human instance to the collection
     * @param h
     */
    public void add (Human h){
        if(!isInCol(h)){
            collection.add(h);
//            System.out.println("Added");
        } else {
//            System.out.println("Not added: object is already in the collection");
        }
    }
    public void insert (int index, Human h){
            collection.add(index, h);
//            System.out.println("Added");
    }
    public void updateEl(Human h){
        if(isInCol(h)){
            collection.remove(getById(h.getId()));
            collection.add(h);
//            System.out.println("Updated");
        } else {
//            System.out.println("Not updated: no such object in col");
        }
    }
    public void removeById(int id){
        if(isInCol(getById(id))){
            collection.remove(getById(id));
//            System.out.println("Element removed");
        } else {
//            System.out.println("No such element");
        }
    }
    public void initialaze(){
        collection.clear();
        collection = cls.readFromFile(cls.getFileName());
        initDate = LocalDateTime.now();
        if (!collection.isEmpty()) {
            for (Human el : collection) {
                if (el.getId()>currId)currId = el.getId();
            }
        } else currId=1;
    }
    public void saveToFile(){
        cls.writeToFile(collection);
    }
    @Override
    public String toString() {
        String colInfo = "";
        if(collection.isEmpty()){
            System.out.println("Empty collection");
        } else {
            for (Human el:collection){
                colInfo+=el+"\n";
            }
        }
        return colInfo;
    }
}
