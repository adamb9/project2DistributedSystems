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
    Thread blarneyThread = new Thread(blarney);
    int shipCount = 0;

    //This function is called when a button is clicked.
    //It takes a sentry location and a ship factory which produces a ship at that location.
    //This is then used to create new sentry threads to communicate and update blarney of this information.
    //Blarney will then produce the bombs to destroy the ships.
    public void makeShip(ShipFactory factory, String location){
        Ship ship = factory.makeShip();
        String shipType = ship.function();
        shipCount++;
    }



    //When the program is started, a blarney thread is created and the kinsale and youghal sentries are registered to it.
    public void startApp(){
        blarneyThread.start();
        youghal.registerObserver(blarney);
        kinsale.registerObserver(blarney);
    }
}
