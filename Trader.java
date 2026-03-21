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
        this.myView = null;
        this.mailbox = new LinkedList<String>(); 

    }

    public void recieveMessage(String msg){
        mailbox.add(msg);
        if(brokerage.isLoggedIn(this)){
            while(!mailbox.isEmpty()){
                String msg2 = mailbox.
                myView.showMessage(mailbox.peek());
                mailbox.remove();
            }
        }
        
    }


// big fulk 
    //
    // The following are for test purposes only
    //
    protected Queue<String> mailbox()
    {
        return mailbox;
    }

    public boolean equals()
    {

    }

    public String getName()
    {
        return screenName; 
    }

    public String getPassword()
    {
        return password;     
    }

    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }

     public boolean hasMessages()
    {
        return !mailbox.isEmpty(); 
    }

    public void setView(TraderView x)
    {
        myView = x; 
    }

    public void placeOrder()
    {
        
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
