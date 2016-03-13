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

    public JConsole getjConsole() {
        return jConsole;
    }

    public void removeClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.clients.remove(client);
    }

    public void writeOnClients(Message message) {
        System.out.print("a");
        try {
            for (Client client : this.clients) {
                client.getOutToClient().writeObject(message);
                client.getOutToClient().flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.jConsole.addLog("Waiting for client on " + ChatServer.getIpAddress() + ":" + this.serverSocket.getLocalPort() + "...");
        while (true) {
            try {
                System.out.println("aaa");
                Socket socket = this.serverSocket.accept();
                Client client = new Client(this, socket);
                this.clients.add(client);
                new Thread(client).start();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
