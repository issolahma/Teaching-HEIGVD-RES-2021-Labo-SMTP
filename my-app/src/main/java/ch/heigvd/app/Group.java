package ch.heigvd.app;

import java.nio.file.Path;
import java.util.List;

public class Group {
    private String sender;
    private List<String> recipients;

    public Group(Path file) {
        // lire le fichier
        // verif si mail
        // sender
        // recipients
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }
}
