//ADAM BALDWIN
//R00176025
//SDH3-A

//Listener/Client Interface

package model;

import model.ships.Ship;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListenerInterface extends Remote {
    public void shipSighted(Ship ship) throws RemoteException;
}
