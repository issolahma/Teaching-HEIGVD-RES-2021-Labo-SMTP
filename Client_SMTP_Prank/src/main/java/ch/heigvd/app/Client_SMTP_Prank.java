package ch.heigvd.app;

import ch.heigvd.app.config.ConfigurationManager;
import ch.heigvd.app.model.prank.Prank;
import ch.heigvd.app.model.prank.PrankGenerator;
import ch.heigvd.app.smtp.SmtpClient;
//import org.graalvm.compiler.lir.alloc.lsra.LinearScan;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 *
 */
public class Client_SMTP_Prank
{
    /**
     *
     * @param args (fichier mails) (fichier messages)
     */
    public static void main( String[] args ) throws IOException {
        try {
            ConfigurationManager conf = new ConfigurationManager();
            PrankGenerator pg = new PrankGenerator(conf);
            List<Prank> prankList = pg.generatePranks();

            SmtpClient cli = new SmtpClient(conf.getSmtpServerAddress(), conf.getSmtpServerPort());

            for (Prank p : prankList) {
                cli.sendMessage(p.generateMailMessage());
            }
        }catch (Exception e)
        {
            System.out.println("Error : " + e.getMessage());
        }
    }


}
