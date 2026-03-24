/**
 * A price comparator for trade orders.
 */
public class PriceComparator implements java.util.Comparator<TradeOrder>
{

    // TODO complete class
     boolean ascending;
    public PriceComparator(){
        ascending = true;
    }
    public PriceComparator(boolean asc){
        ascending = asc;
    }
    public int compare(TradeOrder order1, TradeOrder order2){
        if(order1.isMarket() && order2.isMarket()){
            return 0;
       }

        if(order1.isMarket() && order2.isLimit()){
            return -1;
       }

       if(order1.isLimit() && order2.isMarket()){
            return 1;
       }
       
        double cent1 = order1.getPrice() % 1 * 100;
        double cent2 = order2.getPrice() % 1 * 100;
        
       if(ascending){
        return (int)cent1 - (int)cent2;
       }
       else{
        return (int)cent2-(int)cent1;
       }
    }


}
