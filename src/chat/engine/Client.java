package chat.engine;

import java.io.*;
import java.net.*;

/**
 *
 * @author Alex
 */
public class Client implements Runnable {

    private Server server;
    private Socket socket;
    private String username;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;

    public Client(Server server, Socket socket) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        this.server = server;

        if (socket == null) {
            throw new IllegalArgumentException("Socket cannot be null");
        }
        this.socket = socket;

        this.username = null;
    }

    @Override
    public void run() {
        try {
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.objectOutputStream.flush();
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            System.out.print("sdasf");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        try {
            this.username = this.objectInputStream.readUTF();
            this.server.getjConsole().addLog(username + " has connected");
            boolean listen = true;
            while (listen) {
                System.out.println("prima");
                String fromClient = this.objectInputStream.readUTF();
                System.out.println(fromClient);
                if (fromClient.equalsIgnoreCase("Exit")) {
                    System.out.println("abcdef");
                    listen = false;
                    this.server.getjConsole().addLog(username + " has disconnected");
                    this.server.removeClient(this);
                    break;
                } else {
                    this.server.writeOnClients(new Message(this.username, fromClient));
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ObjectOutputStream getOutToClient() {
        return this.objectOutputStream;
    }

    public String getUsername() {
        return this.username;
    }
}
