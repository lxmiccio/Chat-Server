package chat.engine;

import java.io.*;
import java.text.*;
import java.util.*;

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

    public String getUsername() {
        return this.username;
    }

    public String getTime() {
        return time;
    }

    public String getText() {
        return this.text;
    }
}
