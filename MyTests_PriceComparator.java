public class MyTests_PriceComparator {

    public static void test() {

        System.out.println("\n===== PriceComparator Tests =====");
        int pass = 0;
        int fail = 0;

        PriceComparator defaultComp = new PriceComparator();
        TradeOrder limLow  = new TradeOrder(null, "FULK", true, true, 10, 1.00);
        TradeOrder limHigh = new TradeOrder(null, "FULK", true, true, 10, 5.00);
        int result = defaultComp.compare(limLow, limHigh);
        if (result < 0){

            System.out.println("pass");
            pass++;
        } 
        else {

            System.out.println("pass ");
            fail++;
        }

        PriceComparator temp = new PriceComparator(true);
        result = temp.compare(limLow, limHigh);
        if (result < 0){

            System.out.println("pass");
            pass++;
        } 
        else{

            System.out.println("fail");
            fail++;
        }

       
        PriceComparator temp1 = new PriceComparator(false);
        result = temp1.compare(limLow, limHigh);
        if (result > 0) {

            System.out.println("pass");
            pass++;
        } 
        else {

            System.out.println("fail");
            fail++;
        }

     
        TradeOrder market1 = new TradeOrder(null, "AAPL", true, false, 10, 0);
        TradeOrder market2 = new TradeOrder(null, "AAPL", true, false, 10, 0);
        result = defaultComp.compare(market1, market2);
        if (result == 0) {

            System.out.println("pass");
        } 
        else {

            System.out.println("fail ");
        }

        
        result = defaultComp.compare(market1, limLow);
        if (result == -1) {

            System.out.println("pass");
        } 
        else {

            System.out.println("fail");
        }

    
        result = temp1.compare(market1, limLow);
        if (result == -1) {

            System.out.println("pass");
        } 
        else {

            System.out.println("fail");
        }

        result = defaultComp.compare(limLow, market1);
        if (result == 1) {
            System.out.println("pass");
        } 
        else {

            System.out.println("fail");
        }


        result = temp1.compare(limLow, market1);
        if (result == 1) {

            System.out.println("pass");
        } 
        else {
            
            System.out.println("fail");
        }
    }
}