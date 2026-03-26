import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a stock trader.
 */
public class Trader implements Comparable<Trader>
{
    private Brokerage brokerage;
    private String screenName, password;
    private TraderView myView;
    private Queue<String> mailbox;

    public Trader (Brokerage brokerage, String screenName, String password)
    {
        this.brokerage = brokerage; 
        this.screenName = screenName; 
        this.password = password; 
        //this.myView = new TraderWindow(this);
        this.mailbox = new LinkedList<String>(); 

    }



// big fulk 
    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }


    public String getName()
    {
        return screenName; 
    }

    public String getPassword()
    {
        return password;     
    }

    public int compareTo(Trader other)
    {
        return screenName.compareToIgnoreCase(other.getName());
    }

    public boolean equals(Object other)
    {   
        if (other.getClass() != this.getClass())
        {
            throw new ClassCastException();
        }  
        
        Trader foreign = (Trader) other; 

        int compare = screenName.compareToIgnoreCase(foreign.getName());
        if (compare == 0)
        {
            return true; 
        }
        else
        {
            return false; 
        }
    }

    public void setView(TraderView x)
    {
        myView = x; 
    }

    public boolean hasMessages()
    {
        return !mailbox.isEmpty(); 
    }
    
    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }

    public void receiveMessage(String msg){
        mailbox.add(msg);
        if(brokerage.isLoggedIn(this)){
            while(!mailbox.isEmpty()){
                myView.showMessage(mailbox.peek());
                mailbox.remove();
            }
        }
        
    }

    public void placeOrder(TradeOrder order)
    {
        brokerage.placeOrder(order);
    }

    public void quit()
    {
        brokerage.logout(this);
        myView = null; 
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
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
                if ( field.getType().getName().equals( "Brokerage" ) )
                    str += separator + field.getType().getName() + " "
                        + field.getName();
                else
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
