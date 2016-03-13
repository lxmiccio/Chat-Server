package chat.engine;

import chat.*;
import chat.server.gui.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Alex
 */
public class Server implements Runnable {

    private ArrayList<Client> clients;
    private JConsole jConsole;
    private ServerSocket serverSocket;

    public Server(int port) {
        if (port < 0 || port > 65535) {
            throw new IllegalArgumentException("Port Must be Between 0 and 65535, Inclusive");
        }
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        this.clients = new ArrayList<>();
        this.jConsole = new JConsole(this);
        this.jConsole.initialize();
    }

    @Override
    public void run() {
        this.jConsole.addMessage(new Message("Server", "Waiting for Client on " + ChatServer.getIpAddress() + " : " + this.serverSocket.getLocalPort()));
        while (true) {
            try {
                this.clients.add(new Client(this, this.serverSocket.accept()));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public JConsole getjConsole() {
        return jConsole;
    }

    public void removeClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.clients.remove(client);
    }

    public void writeToClients(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }
        try {
            for (Client client : this.clients) {
                client.getObjectOutputStream().writeObject(message);
                client.getObjectOutputStream().flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
