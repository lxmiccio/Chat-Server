package chat.server.gui;

import chat.engine.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class JConsole extends JFrame {

    private Server server;
    private JPanel pnlServices;
    private JScrollPane scrPnServices;
    private GridBagConstraints gridBagConstraints;

    public JConsole(Server server) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null");
        }
        this.server = server;
        this.pnlServices = new JPanel();
        this.pnlServices.setLayout(new GridBagLayout());
        this.scrPnServices = new JScrollPane();
        this.scrPnServices.setViewportView(this.pnlServices);
        this.gridBagConstraints = new GridBagConstraints();
        this.gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        this.gridBagConstraints.gridheight = 1;
        this.gridBagConstraints.gridwidth = 1;
        this.gridBagConstraints.gridx = 0;
        this.gridBagConstraints.gridy = -1;
        this.gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        this.gridBagConstraints.weightx = 1;
        this.gridBagConstraints.weighty = 0;
    }

    public Server getServer() {
        return server;
    }

    public void initialize() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(30, 30, 30, 30);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        this.add(this.scrPnServices, gridBagConstraints);
        this.setSize(new Dimension(750, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Server");
        this.setVisible(true);
    }

    public void addMessage(Message message) {
        AdjustmentListener adjustmentListener = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent adjustmentEvent) {
                adjustmentEvent.getAdjustable().setValue(adjustmentEvent.getAdjustable().getMaximum());
                scrPnServices.getVerticalScrollBar().removeAdjustmentListener(this);
            }
        };
        this.scrPnServices.getVerticalScrollBar().addAdjustmentListener(adjustmentListener);
        this.gridBagConstraints.gridy++;
        this.pnlServices.add(message.getJPanel(), this.gridBagConstraints);
        this.pnlServices.revalidate();
    }
}
