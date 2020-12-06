//ADAM BALDWIN
//R00176025
//SDH3-A

//Server interface for RMI implementation

package model;

import model.ships.Ship;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sentry extends Remote {
    public void addListener(ListenerInterface listener) throws RemoteException;
    public void removeListener(ListenerInterface listener) throws RemoteException;

}
