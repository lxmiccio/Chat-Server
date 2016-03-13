package chat.engine;

import java.io.*;
import java.net.*;

/**
 *
 * @author Alex
 */
public class Client implements Runnable {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private Server server;
    private Socket socket;
    private String username;

    public Client(Server server, Socket socket) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        this.server = server;

        if (socket == null) {
            throw new IllegalArgumentException("Socket cannot be null");
        }
        this.socket = socket;
        try {
            this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());
            this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        new Thread(this).start();
    }

    @Override
    public void run() {
        this.readUsername();
        this.readMessages();
    }

    private void readUsername() {
        try {
            this.username = this.objectInputStream.readUTF();
            this.server.getjConsole().addMessage(new Message(this.username, this.username + " has connected"));
            this.server.writeToClients(new Message(this.username, this.username + " has connected"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void readMessages() {
        boolean listen = true;
        try {
            while (listen) {
                String fromClient = this.objectInputStream.readUTF();
                if (!fromClient.equalsIgnoreCase("Exit")) {
                    this.server.getjConsole().addMessage(new Message(this.username, fromClient));
                    this.server.writeToClients(new Message(this.username, fromClient));
                } else {
                    listen = false;
                    this.server.getjConsole().addMessage(new Message(this.username, this.username + " has disconnected"));
                    this.server.writeToClients(new Message(this.username, this.username + " has disconnected"));
                    this.server.removeClient(this);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getUsername() {
        return username;
    }
}
