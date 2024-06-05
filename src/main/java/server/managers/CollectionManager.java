package server.managers;

import common.Human;
import common.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class CollectionManager implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private final ReentrantLock lock = new ReentrantLock();
    public Human getById(int id){
        lock.lock();
        for (Human el: collection){
            if (el.getId()==id){
                lock.unlock();
                return el;
            }
        }
        lock.unlock();
        return null;
    }
    public boolean checkByNameAndOwner(Human human, User user){
        lock.lock();
        for (Human el: collection){
            if (el.getName().equals(human.name) && el.getOwner().equals(user.getName())){
                return true;
            }
        }
        lock.unlock();
        return false;
    }

    /**
     * Determines whether a Human instance is in a collection
     * @param h
     * @return boolean
     */
    public boolean isInCol(Human h){
                lock.lock();
                if(getById(h.getId())!=null){
                    lock.unlock();
                    return true;
                } else {
                    lock.unlock();
                    return false;
                }
        }
    public int getUnusedId(){
        return DataConnector.getLatestId();
    }
    /**
     * Adds a Human instance to the collection
     * @param h
     */
    public void add (Human h, User user){
        lock.lock();
//        if(!isInCol(h)){
            if(h.getOwner()!=null) {
                if(checkByNameAndOwner(h, user)){return;}
                h.setOwner(user.getName());
                h.setId(DataConnector.getLatestId());
                DataConnector.addHumanInfo(h.getId(),h.getName(),h.getStatus(),h.getColor(),h.statsToString(), h.getIsAlive(), h.getRarity(),h.getOwner(),h.getCoordinates());
                collection.add(h);
//                String[] arr = h.toCsvStr().split(",");
//            }
//            System.out.println("Added");
        } else {
//            System.out.println("Not added: object is already in the collection");
        }
        lock.unlock();
    }
    /**
     * Inserts a Human instance into the entered index
     * @param h
     * @param index
     */
    public void insert (int index, Human h){
            collection.add(index, h);
//            System.out.println("Added");
    }
    /**
     * Updates a Human instance with the entered index
     * @param h
     */
    public void updateEl(int id, Human h){
        if(isInCol(getById(id))){
            lock.lock();
//            if(h.getOwner().equals(user.getName())) {
                collection.remove(getById(id));
                collection.add(h);
//            }
            lock.unlock();
//            System.out.println("Updated");
        } else {
            System.out.println("Not updated: no such object in col");
        }
    }

    public void setCollection(ArrayList<Human> collection) {
        this.collection = collection;
    }

    /**
     * Removes a Human instance with the entered id
     * @param id
     */
    public void removeById(int id){
        if(isInCol(getById(id))){
            lock.lock();
//            if(getById(id).getOwner().equals(userData.getName())) {
                collection.remove(getById(id));
                DataConnector.removeCollectionInfo(id);
//            } else {
//                System.out.println("not enough rights");
//            }
            lock.unlock();
//            System.out.println("Element removed");
        } else {
//            System.out.println("No such element");
        }
    }
    /**
     * Initializes the collection - loads and sets initialization date
     */
    public void initialaze(){
        lock.lock();
        collection.clear();
        ArrayList<String> colFromDB = DataConnector.getCollectionInfo();
//        System.out.println(colFromDB);
//        var s = colFromDB.size();
//        cls.writeToFile(colFromDB, s);
        cls.writeToFile(colFromDB);
        collection = cls.readFromFile(cls.getFileName());

        initDate = LocalDateTime.now();
//        if (!collection.isEmpty()) {
//            for (Human el : collection) {
//                if (el.getId()>currId)currId = el.getId();
//            }
//        } else currId=1;
        lock.unlock();
    }
    /**
     * Saves the collection to a file
     */
    public void saveToFile(){
        lock.lock();
        cls.writeToFile(collection);
        lock.unlock();
    }
//    public static void ()
    @Override
    public String toString() {
        String colInfo = "";
        if(collection.isEmpty()){
            System.out.println(">Empty collection");
        } else {
            for (Human el:collection){
                colInfo+=el+"\n";
            }
        }
        return colInfo;
    }
}
