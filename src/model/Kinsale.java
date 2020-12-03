//SOCKET REMOTE SITE

package model;

import model.ships.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Kinsale implements  Serializable {

    @Override
    public String toString() {
        return "Kinsale";
    }

    public static void main(String[] args) {
        try {
            String host = "localhost";
            // Establish connection with the server
            Socket socket = new Socket(host, 8001);

            // Create an output stream to the server
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

            Scanner sc= new Scanner(System.in);
            System.out.println("1.Destroyer");
            System.out.println("2.Aircraft Carrier");
            System.out.println("3.Sailing Ship");
            int selectedShip = sc.nextInt();

            Ship ship;

            if(selectedShip==1){
                DestroyerFactory destroyerFactory = new DestroyerFactory();
                Destroyer destroyer = destroyerFactory.makeShip();
                ship = destroyer;
            }
            else if(selectedShip==2){
                AircraftCarrierFactory aircraftCarrierFactory = new AircraftCarrierFactory();
                AircraftCarrier aircraftCarrier = aircraftCarrierFactory.makeShip();
                ship = aircraftCarrier;
            }
            else{
                SailingShipFactory sailingShipFactory = new SailingShipFactory();
                SailingShip sailingShip = sailingShipFactory.makeShip();
                ship = sailingShip;
            }


            toServer.writeObject(ship);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
