//SOCKET REMOTE SITE

package model;

import model.ships.*;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.Remote;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Kinsale implements  Serializable {
    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;

    public Kinsale() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("Server started ");
            Socket socket = serverSocket.accept();

            while(true) {

                Scanner sc = new Scanner(System.in);
                System.out.println("1.Destroyer");
                System.out.println("2.Aircraft Carrier");
                System.out.println("3.Sailing Ship");
                int selectedShip = sc.nextInt();

                Ship ship;

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


                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
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


