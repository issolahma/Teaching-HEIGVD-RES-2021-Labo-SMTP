package ch.heigvd.app.smtp;

import ch.heigvd.app.model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {
    public void sendMessage(Message message) throws IOException;
}
