import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 */
public class Stock
{
    public static DecimalFormat money = new DecimalFormat( "0.00" );

    private String stockSymbol;
    private String companyName;
    private double loPrice, hiPrice, lastPrice;
    private int volume;
    private PriorityQueue<TradeOrder> buyOrders, sellOrders;

    // TODO complete class
    protected void executeOrders(){
        
    }

    public String getQuote(){
        TradeOrder t = sellOrders.peek();
         String msg = companyName+" ("+stockSymbol+")\n"+"  Price: "+lastPrice+"  hi: "+hiPrice+"  lo: "+loPrice+"  vol: "+volume+"\n"; 
         if(sellOrders.isEmpty()||t==null){
            msg+="Ask: none  ";
         }
         else if(t.isLimit()){
            msg+="Ask: "+t.getPrice()+" size: "+t.getShares()+"  ";
         }
         else if(t.isMarket()){
            msg+="Ask: market  size: "+t.getShares()+"  ";
         }

         if(buyOrders.isEmpty()||t==null){
            msg+="Bid: none  ";
         }
         else if(t.isLimit()){
            msg+="Bid: "+t.getPrice()+" size: "+t.getShares();
         }
         else if(t.isMarket()){
            msg+="Bid: market  size: "+t.getShares();
         }
         return msg;
}

         
    
    

    public void placeOrder(TradeOrder order){
        String msg = "New Order:  ";
        
        if(order.isBuy()){
            buyOrders.add(order);
            msg+="Buy "+order.getSymbol()+"(+"+companyName+")"+"\n"+order.getShares()+" shares at "+order.getPrice();
            order.getTrader().recieveMessage(msg);

        }
        else if(order.isSell()){
            sellOrders.add(order);
            msg+="Sell "+order.getSymbol()+"(+"+companyName+")"+"\n"+order.getShares()+" shares at ";
            if(order.isMarket())
                msg+="market.";
            else
                msg+="limit.";
            order.getTrader().recieveMessage(msg);
        }
        executeOrders();
    }

    //
    // The following are for test purposes only
    //
    
    protected String getStockSymbol()
    {
        return stockSymbol;
    }
    
    protected String getCompanyName()
    {
        return companyName;
    }
    
    protected double getLoPrice()
    {
        return loPrice;
    }
    
    protected double getHiPrice()
    {
        return hiPrice;
    }

    protected double getLastPrice()
    {
        return lastPrice;
    }
    
    protected int getVolume()
    {
        return volume;
    }

    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }
    
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }
    



    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for ( Field field : fields )
        {
            try
            {
                str += separator + field.getType().getName() + " "
                    + field.getName() + ":" + field.get( this );
            }
            catch ( IllegalAccessException ex )
            {
                System.out.println( ex );
            }

            separator = ", ";
        }

        return str + "]";
    }
}
