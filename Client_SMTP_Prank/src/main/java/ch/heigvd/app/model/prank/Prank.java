/**
 * Author : Maude Issolah, Matthieu Godi
 * Date : 01.05.2021
 */
package ch.heigvd.app.model.prank;

import ch.heigvd.app.model.mail.Message;
import ch.heigvd.app.model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {
    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<>();
    private final List<Person> witnessRecipients = new ArrayList<>();
    private String body;
    private Message msg;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public String getMessage() {
        return body;
    }

    public void setMessage(String message) {
        this.body = message;
    }

    public void addVictimRecipient(List<Person> victims){
        victimRecipients.addAll(victims);
    }

    public void addWitnessRecipient(List<Person> witnesses){
        witnessRecipients.addAll(witnesses);
    }

    public List<Person> getVictimRecipients(){
        return new ArrayList<>(victimRecipients);
    }

    public List<Person> getWitnessRecipients(){
        return new ArrayList<>(witnessRecipients);
    }

    /**
     * Generate a Message from the body
     * @return
     */
    public Message generateMailMessage(){
        msg = new Message();

        String []splitMsg = this.body.split("\r\n");
        msg.setSubject(splitMsg[0].split(":")[1] + "\r\n");


        String body = this.body.replace(splitMsg[0] + "\r\n", "");
        msg.setBody(body + "\r\n" + victimSender.getFirstname());

        String[] to = victimRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setTo(to);

        String[] bcc = witnessRecipients
                .stream()
                .map(p -> p.getAddress())
                .collect(Collectors.toList())
                .toArray(new String[]{});
        msg.setBcc(bcc);

        msg.setFrom(victimSender.getAddress());

        return msg;
    }
}
