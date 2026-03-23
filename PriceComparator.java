/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{

    // TODO complete class
    public int compareOrders(TradeOrder order1, TradeOrder order2)
    {
        ; // not done, what is an ascending / descending comparator??? 
        

        if ((order1.isMarket() == true) && (order2.isMarket() == true))
        {
            return 0; 
        }
        if (order1.isMarket() == true)
        {
            return -1; 
        }

        if (order2.isMarket() == true)
        {
            return 1; 
        }

        double orderDifference = order1.getPrice() - order2.getPrice(); 
    }

}
