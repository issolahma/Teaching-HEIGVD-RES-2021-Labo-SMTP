/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.smtp;

import ch.heigvd.app.model.mail.Message;

import java.io.IOException;

public interface ISmtpClient {
    /**
     * Send a mail via SMTP protocol
     * @param message
     * @throws IOException
     */
    public void sendMessage(Message message) throws IOException;
}
