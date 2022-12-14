package com.alisha.data;

import java.util.Objects;

public class Coordinates {
    private final Double x; //Поле не может быть null
    private final Float y; //Поле не может быть null

    public Coordinates(Double x, Float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public String toString(){
        return "Coordinates{"
                + "x=" + x
                + ", y=" + y
                + '}';
    }
}
