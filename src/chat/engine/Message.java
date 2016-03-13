package chat.engine;

import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class Message implements Serializable {

    private String time;
    private String username;
    private String text;

    public Message(String username, String text) {
        if (username == null || username.length() <= 0) {
            throw new IllegalArgumentException("Username cannot be null");
        }
        this.username = username;

        if (text == null || text.length() <= 0) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.text = text;

        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public JPanel getJPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridBagLayout());

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel lblTime = new JLabel(this.time);
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblUsername = new JLabel(this.username);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setPreferredSize(new Dimension(150, 14));

        JLabel lblText = new JLabel(this.text);
        lblText.setHorizontalAlignment(SwingConstants.CENTER);
        
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 15, 5, 15);
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        jPanel.add(lblTime, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 15, 5, 15);
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        jPanel.add(lblUsername, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 15, 5, 15);
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        jPanel.add(lblText, gridBagConstraints);

        return jPanel;
    }

    public String getText() {
        return text;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }
}
