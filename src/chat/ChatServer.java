package chat;

import chat.server.gui.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception exception) {
        }
        JForm jForm = new JForm();
        jForm.initialize();
    }

    public static String getIpAddress() {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            InetAddress[] inetAddresses = InetAddress.getAllByName(inetAddress.getCanonicalHostName());
            if (inetAddresses != null) {
                ip = inetAddresses[0].toString().substring(inetAddresses[0].toString().lastIndexOf("/") + 1);
            }
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
        }
        return ip;
    }
}
