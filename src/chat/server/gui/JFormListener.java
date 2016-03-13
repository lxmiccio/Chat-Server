package chat.server.gui;

import chat.engine.*;
import java.awt.event.*;

/**
 *
 * @author Alex
 */
public class JFormListener implements ActionListener {

    private JForm jForm;

    public JFormListener(JForm jForm) {
        if (jForm == null) {
            throw new IllegalArgumentException("JForm cannot be null");
        }
        this.jForm = jForm;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.jForm.dispose();
        new Thread(new Server((int) this.jForm.getCmbPort().getSelectedItem())).start();
    }
}
