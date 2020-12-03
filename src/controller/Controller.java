//Name: Adam Baldwin
//Class: SDH3-A
//Student Number: R00176025

package controller;

import model.*;
import model.ships.Ship;
import model.ships.ShipFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Controller {

    Youghal youghal = new Youghal();
    Kinsale kinsale = new Kinsale();
    Blarney blarney = new Blarney();
    int shipCount = 0;


    public void makeShip(ShipFactory factory, String location){
        Ship ship = factory.makeShip();
        String shipType = ship.function();
        shipCount++;
    }

}
