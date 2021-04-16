package ch.heigvd.app;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 *
 */
public class App 
{
    /**
     *
     * @param args (fichier mails) (fichier messages)
     */
    public static void main( String[] args )
    {
        if (args.length != 2) {
            System.err.println("Usage: app.jar mails.txt messages.txt");
            return;
        }

        Group g = new Group(Paths.get(args[1]));


    }


}
