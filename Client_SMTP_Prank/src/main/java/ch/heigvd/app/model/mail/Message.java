/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.model.mail;

/**
 * This class represent the structure of an email.
 */
public class Message {
    private String from, subject, body;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }
}
