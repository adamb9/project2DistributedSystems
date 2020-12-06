//ADAM BALDWIN
//R00176025
//SDH3-A

//SOCKET REMOTE SITE

package model;

import model.ships.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Kinsale implements  Serializable {
    private ServerSocket serverSocket;
    private Socket socket;
    private Ship ship;
    private ObjectOutputStream outputStream;


    public Kinsale() {
        try {
            // Create a server socket
            serverSocket = new ServerSocket(8001);
            System.out.println("Kinsale Server started ");
            socket = serverSocket.accept();
            //accepting socket connection with blarney

            while(true) {

                Scanner sc = new Scanner(System.in);
                System.out.println("1.Destroyer");
                System.out.println("2.Aircraft Carrier");
                System.out.println("3.Sailing Ship");
                int selectedShip = sc.nextInt();

                //User input determines which ship is spotted at remote site

                if (selectedShip == 1) {
                    DestroyerFactory destroyerFactory = new DestroyerFactory();
                    Destroyer destroyer = destroyerFactory.makeShip();
                    ship = destroyer;
                } else if (selectedShip == 2) {
                    AircraftCarrierFactory aircraftCarrierFactory = new AircraftCarrierFactory();
                    AircraftCarrier aircraftCarrier = aircraftCarrierFactory.makeShip();
                    ship = aircraftCarrier;
                } else {
                    SailingShipFactory sailingShipFactory = new SailingShipFactory();
                    SailingShip sailingShip = sailingShipFactory.makeShip();
                    ship = sailingShip;
                }

                //Creating socket object output stream and writing the object to it
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                outputStream.writeObject(ship);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        public static void main(String[] args){
            Kinsale kinsale = new Kinsale();
        }

    }


