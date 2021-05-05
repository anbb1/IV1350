
package se.kth.iv1350.iv1350upg.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.iv1350upg.integration.Amount;


public class PaymentTest {
    
    public PaymentTest() {
    }
    private Payment pay;
    private double registerStart=1000;
    private double toPay=250;
    private CashRegister testCR;
    @BeforeEach
    public void setUp() {
        testCR=new CashRegister(new Amount(registerStart));
        pay= new Payment(new Amount(toPay),testCR);
    }
    
    @AfterEach
    public void tearDown() {
        testCR=null;
        pay=null;
    }
    
    
    @Test
    public void testPositivChange() {
        Amount paid = new Amount(toPay+100);
        Amount change = pay.handelPayment(paid);
        double expResult = registerStart+toPay;
        double result=testCR.getCashInRegister().getAmount();
        assertEquals(expResult,result,"didnt add cash to register when paid>ToPay");
        assertEquals(paid.getAmount()-toPay,change.getAmount(),"change not correct");
    }
    
    @Test
    public void testNegativChange() {
        Amount paid = new Amount(toPay-100);
        Amount change = pay.handelPayment(paid);
        double expResult = registerStart;
        double result=testCR.getCashInRegister().getAmount();
        assertEquals(expResult,result,"added cash to register when paid<ToPay");
        assertEquals(paid.getAmount()-toPay,change.getAmount(),"change not correct");
    }
    
    @Test
    public void testZeroChange() {
        Amount paid = new Amount(toPay);
        Amount change = pay.handelPayment(paid);
        double expResult = registerStart+toPay;
        double result=testCR.getCashInRegister().getAmount();
        assertEquals(expResult,result,"didnt add cash to register when paid==ToPay");
        assertEquals(paid.getAmount()-toPay,change.getAmount(),"change not correct");
    }
    
    @Test
    public void testMultiPayment()
    {
        double toPay2=100;
        double registerDifference= toPay+toPay2;
        pay.handelPayment(new Amount(toPay+50));
        pay.handelPayment(new Amount(50));

        pay=new Payment(new Amount(toPay2),testCR);
        pay.handelPayment(new Amount(toPay2-10));
        pay.handelPayment(new Amount(toPay2+10));
        
        double expResult = registerStart + registerDifference;
        double result = testCR.getCashInRegister().getAmount();
        assertEquals(expResult,result,"incorrect amount in register");
        
    }
    
    
}
