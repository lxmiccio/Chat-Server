package chat.server.gui;

import chat.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class JForm extends JFrame {

    private JLabel lblIp;
    private JLabel lblPort;
    private JComboBox cmbPort;
    private JButton btnListen;

    public JForm() {
        this.lblIp = new JLabel("IP Address: " + ChatServer.getIpAddress());
        this.lblIp.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblPort = new JLabel("Select the Port");
        this.lblPort.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultComboBoxModel modelCmbPort = new DefaultComboBoxModel();
        for (int iterator = 0; iterator < 65536; iterator++) {
            modelCmbPort.addElement(iterator);
        }
        this.cmbPort = new JComboBox(modelCmbPort);
        this.cmbPort.setSelectedItem(9999);
        this.btnListen = new JButton("Listen");
    }

    public void initialize() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(30, 30, 15, 30);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        this.add(this.lblIp, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(15, 30, 15, 30);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        this.add(this.lblPort, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(15, 30, 15, 30);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        this.add(this.cmbPort, gridBagConstraints);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(15, 30, 30, 30);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        this.add(this.btnListen, gridBagConstraints);
        this.setSize(new Dimension(350, 300));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Server");
        this.setVisible(true);
        this.btnListen.addActionListener(new JFormListener(this));
    }

    public JComboBox getCmbPort() {
        return cmbPort;
    }

    public JButton getBtnListen() {
        return btnListen;
    }
}
