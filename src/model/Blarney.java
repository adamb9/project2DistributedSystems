//CENTRAL SITE

package model;

import model.bombs.*;
import model.ships.Ship;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Blarney implements Serializable {
    private ObjectOutputStream outputToFile;
    private ObjectInputStream inputFromClient;

    public static void main(String[] args){
        new Blarney();
    }

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

    @Override
    public String toString() {
        return "Blarney";
    }

}

