//CENTRAL SITE

package model;

import model.ships.Ship;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class Blarney extends UnicastRemoteObject implements ListenerInterface {
    //private ObjectOutputStream outputToFile;
    //private ObjectInputStream inputFromClient;

    protected Blarney() throws RemoteException{
    };


    @Override
    public void shipSighted(Ship ship) throws RemoteException {
        System.out.println("Ship sighting event: " + ship.function());
    }


    public static void main(String[] args){
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


    /*
    public Blarney() {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(8001);
            System.out.println("Server started ");

            // Create an object ouput stream
            outputToFile = new ObjectOutputStream(
                    new FileOutputStream("blarney.dat", true));

            while (true) {
                // Listen for a new connection request
                Socket socket = serverSocket.accept();

                // Create an input stream from the socket
                inputFromClient =
                        new ObjectInputStream(socket.getInputStream());

                // Read from input
                Object object = inputFromClient.readObject();
                System.out.println(object.toString());

                // Write to the file
                outputToFile.writeObject(object);
                System.out.println("A new Ship object is stored");
            }
        }
        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                inputFromClient.close();
                outputToFile.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    */


    @Override
    public String toString() {
        return "Blarney";
    }

}

