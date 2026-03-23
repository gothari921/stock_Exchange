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

    public Stock(String symbol, String company, double p){
        stockSymbol = symbol;
        companyName = company;
        hiPrice = p;
        lastPrice = p;
        loPrice = p;
        volume = 0;

    }

    protected void executeOrders(){
    
        while(!(buyOrders.isEmpty()||sellOrders.isEmpty())){
            TradeOrder topBuy = buyOrders.peek();
            TradeOrder topSell = sellOrders.peek();
            PriceComparator c = new PriceComparator();
            boolean bothLimit = topBuy.isLimit()&&topSell.isLimit();
            boolean bothMarket = topBuy.isMarket()&&topSell.isMarket();
            boolean bhs = topBuy.getPrice()>=topSell.getPrice();
            int shares = Math.min(topBuy.getShares(), topSell.getShares());

            if(bothMarket){
                moveShares(topBuy, topSell, shares, lastPrice);

            }
            else if(bothLimit){
                if(bhs){
                    moveShares(topBuy, topSell, shares, topSell.getPrice());
                }
                else{
                    break;
                }
            }
            else if(!(bothLimit||bothMarket)){
                if(topBuy.isLimit())
                    moveShares(topBuy, topSell, shares, topBuy.getPrice());
                else
                    moveShares(topBuy, topSell, shares, topSell.getPrice());
            }
        }
        

        


    }

    private void moveShares(TradeOrder buy, TradeOrder sell, int shares, double price ){
        buy.subtractShares(shares);
        sell.subtractShares(shares);
        if(buy.getShares()==0)
            buyOrders.remove();
        if(sell.getShares()==0)
            sellOrders.remove();
        updatePrice(price);

    }

    private void updatePrice(double price){
        lastPrice = price;
        if(price>hiPrice)
            hiPrice = price;
        else if(price<loPrice)
            loPrice = price;
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
