package model.ships;

public class DestroyerFactory implements ShipFactory {
    public Destroyer makeShip(){
        Destroyer destroyer = new Destroyer();
        return destroyer;
    }
}
