package model.ships;

public class SailingShipFactory implements ShipFactory {
    public SailingShip makeShip(){
        SailingShip sailingShip = new SailingShip();
        return sailingShip;
    }
}
