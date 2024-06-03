package common;

import java.io.Serializable;

public class Coordinates implements Serializable {
    public Double x;
    public Double y;
    public Coordinates(Double x, Double y){
        this.x=x;
        this.y=y;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }
}
