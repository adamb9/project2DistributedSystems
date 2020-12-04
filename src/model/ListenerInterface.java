package model;

import model.ships.Ship;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ListenerInterface extends Remote {
    public void shipSighted(Ship ship) throws RemoteException;
}
