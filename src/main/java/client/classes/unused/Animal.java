package client.classes.unused;

abstract class Animal {
    String howIsNamed;
    boolean isAlive;
    Animal(String howIsNamed,boolean isAlive){
        this.howIsNamed = howIsNamed;
        this.isAlive = isAlive;
    }
    boolean getIsAlive(){
        return isAlive;
    }
    String getHowIsNamed(){
        return howIsNamed;
    }
}
