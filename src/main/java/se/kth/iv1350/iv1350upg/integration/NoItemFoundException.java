package se.kth.iv1350.iv1350upg.integration;

/**
 * Thrown when the program fails to find a matching item.
 * @author Anders
 */
public class NoItemFoundException extends Exception{

    
    /**
     * Constructs an instance of <code>NoItemFoundException</code> with the
     * specified detail message.
     *
     * @param itemID entered item id.
     */
    public NoItemFoundException(ItemID itemID) {
        super("unable to find item with the id " + itemID.getID());
    }
}
