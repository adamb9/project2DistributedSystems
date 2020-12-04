//RMI REMOTE SITE

package model;

import model.ships.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Youghal extends UnicastRemoteObject implements Sentry, Runnable {

    private List<ListenerInterface> listeners = new ArrayList<>();
    private Ship ship;

    private Youghal() throws RemoteException{
        ship = new Destroyer();
    }

    @Override
    public void run() {
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1.Destroyer");
            System.out.println("2.Aircraft Carrier");
            System.out.println("3.Sailing Ship");
            int selectedShip = sc.nextInt();

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

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyListeners(ship);
        }
    }


    private void notifyListeners(Ship sightedShip)
    {
        for (ListenerInterface lListener : listeners)
        {
            try
            {
                lListener.shipSighted(sightedShip);
            }
            catch (RemoteException aInE)
            {
                listeners.remove(lListener);
            }
        }
    }

    @Override
    public void addListener(ListenerInterface listener) throws RemoteException {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ListenerInterface listener) throws RemoteException {
        listeners.remove(listener);
    }

    @Override
    public Ship getShip() throws RemoteException {
        return ship;
    }


    public static void main(String[] args) {
        try
        {
            Youghal lServer = new Youghal();
            // Binding the remote object (stub) in the registry
            Registry reg = LocateRegistry.createRegistry(52369);
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/Hello";

            Naming.rebind(url, lServer);

            //Create the thread and change the temperature
            Thread lThread = new Thread(lServer);
            lThread.start();

        }
        catch (Exception aInE)
        {
            System.out.println("Remote error- " + aInE);
        }

    }



    @Override
    public String toString() {
        return "Youghal";
    }


}
