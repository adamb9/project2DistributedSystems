//CENTRAL SITE

package model;

import model.ships.Ship;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Blarney extends UnicastRemoteObject implements ListenerInterface {


    protected Blarney() throws RemoteException{
    }


    public static void socketImpl(){
        ObjectOutputStream outputToFile;
        ObjectInputStream inputFromClient;
        try {
            String host = "localhost";
            // Establish connection with the server
            Socket socket = new Socket(host, 8001);

            // Create an object ouput stream
            outputToFile = new ObjectOutputStream(
                    new FileOutputStream("blarney.dat", true));

            while (true) {
                // Create an input stream from the socket
                inputFromClient = new ObjectInputStream(socket.getInputStream());

                // Read from input
                Object object = inputFromClient.readObject();
                System.out.println("Ship sighting event: " + object.toString());

                // Write to the file
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
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";
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
            FileOutputStream fileOut =
                    new FileOutputStream("./blarney.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ship);
            out.close();
            fileOut.close();
            System.out.println("RMI: Ship stored");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }


    public static void main(String[] args){
        rmiImpl();
        socketImpl();

        /*
        Scanner sc = new Scanner(System.in);
        System.out.println("1.RMI\n2.Sockets");
        int method = sc.nextInt();

        if(method==1) {
            rmiImpl();
        }
        else if(method==2) {
            socketImpl();
        }
        else{
            System.out.println("Error. Enter either 1 or 2 please!");
        }*/
    }


}

