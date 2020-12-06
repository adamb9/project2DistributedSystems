//ADAM BALDWIN
//R00176025
//SDH3-A

//CENTRAL SITE

package model;

import model.ships.Ship;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class Blarney extends UnicastRemoteObject implements ListenerInterface {
    //List that contains all ships sent from remote sites
    private List<Ship> list = Collections.synchronizedList(new ArrayList<Ship>());

    protected Blarney() throws RemoteException{
    }


    public static void socketImpl(List<Ship> list){
        //Create object input and output streams
        ObjectOutputStream outputToFile;
        ObjectInputStream inputFromClient;
        try {
            String host = "localhost";
            // Establish connection with the server
            Socket socket = new Socket(host, 8001);

            // Create an object ouput stream
            outputToFile = new ObjectOutputStream(new FileOutputStream("blarney.dat", true));

            while (true) {
                // Create an input stream from the socket
                inputFromClient = new ObjectInputStream(socket.getInputStream());

                // Read from input
                Object object = inputFromClient.readObject();
                System.out.println("Ship sighting event: " + object.toString());

                // Write to the file and add to list
                Ship ship = (Ship) object;
                list.add(ship);

                outputToFile.writeObject(object);
                System.out.println("Socket: Ship stored");

            }
        }
        catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void rmiImpl(){
        try
        {
            //Lookup for the service
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Ship";
            Remote lRemote = Naming.lookup(url);
            Sentry lRemoteServer = (Sentry) lRemote;

            //Create Blarney and register it as a Listener
            Blarney blarney = new Blarney();
            lRemoteServer.addListener(blarney);
        }
        catch (Exception aInE)
        {
            System.out.println(aInE);
        }
    }

    @Override
    public void shipSighted(Ship ship) throws RemoteException {
        System.out.println("Ship sighting event: " + ship.function());
        storeShip(ship);
    }

    public void storeShip(Ship ship){
        try {
            //Stores the ship as a serialised object into blarney.dat
            FileOutputStream fileOut = new FileOutputStream("./blarney.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ship);
            out.close();
            fileOut.close();
            System.out.println("RMI: Ship stored");
            //Adding ship to the shared list of ship objects
            list.add(ship);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static void main(String[] args){
        List<Ship> list = Collections.synchronizedList(new ArrayList<Ship>());
        rmiImpl();
        socketImpl(list);

    }


}

