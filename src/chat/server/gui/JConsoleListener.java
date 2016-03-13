package chat.server.gui;

import chat.engine.*;
import java.awt.event.*;

/**
 *
 * @author Alex
 */
public class JConsoleListener implements WindowListener {

    private JConsole jConsole;

    public JConsoleListener(JConsole jConsole) {
        if (jConsole == null) {
            throw new IllegalArgumentException("JConsole cannot be null");
        }
        this.jConsole = jConsole;
    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        this.jConsole.getServer().writeOnClients(new Message("Server", "Exit"));
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {
    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {
    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {
    }
}
