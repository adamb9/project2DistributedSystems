package model.ships;

public class AircraftCarrierFactory implements ShipFactory {
    public AircraftCarrier makeShip(){
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        return aircraftCarrier;
    }
}
