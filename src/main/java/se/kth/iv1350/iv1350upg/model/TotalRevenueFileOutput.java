
package se.kth.iv1350.iv1350upg.model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * Prints sale revenue to file.
 * The log file will be in the current directory and will be called log.txt.
 * @author Anders
 */
public class TotalRevenueFileOutput implements PaymentObserver {
   private PrintWriter logStream;
   
   
   public TotalRevenueFileOutput()
   {
       try {
           logStream =new PrintWriter(new FileWriter("log.txt"),true);
       } catch (IOException ex) {
           System.out.println("         !ForErrorLog! CAN NOT LOG REVENUE !ForErrorLog!");
       }
   }

    @Override
    public void newPayment(Amount revenue) {
        logStream.println(String.format("%.2f",revenue.getAmount()));
    }
   
}