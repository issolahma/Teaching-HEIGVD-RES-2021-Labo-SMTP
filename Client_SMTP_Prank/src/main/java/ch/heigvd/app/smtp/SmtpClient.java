/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.smtp;

import ch.heigvd.app.model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

/**
 *  Class that manage the smtp connexion, and send messages.
 */
public class SmtpClient implements ISmtpClient {
    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private  String smtpServerAddress;
    private int smtpServerPort = 25;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerAddress, int smtpServerPort) {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        LOG.info("Sending message via SMTP");
        socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
        String line = reader.readLine();
        LOG.info(line);
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);
        if(!line.startsWith("250")){
            throw new IOException("SMTP error: " + line);
        }
        while (line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM:");
        writer.write(message.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        for(String to : message.getTo()){
            writer.write("RCPT TO:");
            writer.write(to);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        if (message.getCc().length != 0) {
            for (String cc : message.getCc()) {
                writer.write("RCPT TO:");
                writer.write(cc);
                writer.write("\r\n");
                writer.flush();
                line = reader.readLine();
                LOG.info(line);
            }
        }

        if (message.getBcc().length != 0) {
            for (String bcc : message.getBcc()) {
                writer.write("RCPT TO:");
                writer.write(bcc);
                writer.write("\r\n");
                writer.flush();
                line = reader.readLine();
                LOG.info(line);
            }
        }

        writer.write("DATA");
        writer.write("From: " + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo()[0]);
        for(int i=1; i < message.getTo().length; ++i){
            writer.write(", " + message.getTo()[i]);
        }
        writer.write("\r\n");
        writer.flush();

        if(message.getCc().length != 0) {
            writer.write("Cc: " + message.getCc()[0]);
            for (int i = 1; i < message.getCc().length; ++i) {
                writer.write(", " + message.getCc()[i]);
            }
            writer.write("\r\n");

            writer.flush();
        }

        writer.write("Content-Type: text/plain; charset=UTF-8\r\n");
        writer.write("Content-Transfer-Encoding: base64\r\n");

        LOG.info(message.getSubject());
        writer.write("Subject: =?UTF-8?B?" + Base64.getEncoder().encodeToString(message.getSubject().getBytes(StandardCharsets.UTF_8)) + "?=");
        writer.write("\r\n");
        writer.write("\r\n");
        LOG.info(message.getBody());
        writer.write(Base64.getEncoder().encodeToString(message.getBody().getBytes(StandardCharsets.UTF_8)));
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();
    }

}
