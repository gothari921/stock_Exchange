import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a brokerage.
 */
public class Brokerage implements Login
{
    private Map<String, Trader> traders;
    private Set<Trader> loggedTraders;
    private StockExchange exchange;

    // TODO complete class

    public Brokerage(StockExchange exchange){}

    
    //
    // The following are for test purposes only
    //
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }

    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }

    protected StockExchange getExchange()
    {
        return exchange;
    }

    public int addUser(String name, String password){
        if(traders.containsKey(name))
            return -3;
        else if(!(4<=name.length()&&name.length()<=10))
            return -1;
        else if(!(2<=password.length()&&password.length()<=10))
            return -2;
        
        else{
            traders.put(name, new Trader(this, name, password ));
            return 0;
        }
        
    }
    
    public void getQuote(String symbol, Trader trader){
        String quote = exchange.getQuote(symbol);
        trader.recieveMessage(quote);
    }

    public int login(String name, String password){

        if(!traders.containsKey(name))
            return -1;
        else if(!traders.get(name).getPassword().equals(password))
            return -2;
        else if(loggedTraders.contains(traders.get(name)))
            return -3;
        else{
            loggedTraders.add(traders.get(name));
            if(!traders.get(name).hasMessages())
                traders.get(name).recieveMessage("Welcome to SafeTrade!");
            return 0;
        }

    }

    public void logout(Trader trader){
        loggedTraders.remove(trader);
    }

    public void placeOrder(TradeOrder order){
        
    }

    public boolean isLoggedIn(Trader trader){
        if(loggedTraders.contains(trader))
    }

    public boolean isRegistered(Trader trader){
        return traders.containsKey(trader.getName());
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
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
