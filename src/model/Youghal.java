//RMI REMOTE SITE

package model;

import model.ships.*;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Youghal implements Serializable {

    @Override
    public String toString() {
        return "Youghal";
    }

    public static void main(String[] args) {
        try {
            String host = "localhost";
            // Establish connection with the server
            Socket socket = new Socket(host, 8001);

            // Create an output stream to the server
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());

            // Create a Student object and send to the server
            AircraftCarrierFactory aircraftCarrierFactory = new AircraftCarrierFactory();
            AircraftCarrier aircraftCarrier = aircraftCarrierFactory.makeShip();
            toServer.writeObject(aircraftCarrier);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
