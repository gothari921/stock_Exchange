public class MyTests_Stock {

        static String companyName = "FulkGPT";
        static String symbol = "FLK";
        static double initPrice = 100.00;
        static double sellLimit = 95.00; 
        static int numShares = 50; 

    public static void test() 
    {
        System.out.println("\n===== Stock Tests =====");
        testStockConstructor();
        test2();
    }

    public static void testStockConstructor() 
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        System.out.println(stock.toString());
        System.out.println("what the symbol should be: " + symbol);
        System.out.println("actual symbol:  " + stock.getStockSymbol());
        System.out.println("what the company name should be: " + companyName);
        System.out.println("actual company name:   " + stock.getCompanyName());
        System.out.println("what the low price should be: " + initPrice);
        System.out.println("what the low price is:  " + stock.getLoPrice());
        System.out.println("what the high price should be: " + initPrice);
        System.out.println("what the high price is:   " + stock.getHiPrice());
        System.out.println("what the last price should be: " + initPrice);
        System.out.println("what the last price is:   " + stock.getLastPrice());
        System.out.println("Actual volume:" + stock.getVolume());
    }

    public static void test2()
    {
        Stock s = new Stock(symbol, companyName, initPrice);

        System.out.println("Expected: contains 'Ask: none' and 'Bid: none'");
        String quote = s.getQuote();
        System.out.println("Actual: " + quote);
    }

    public static void test3()
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        Trader trader = new Trader(null, "trader", "password" );
        TradeOrder sellLimit = new TradeOrder(trader, symbol, false, true, 10, 10.00);
        stock.placeOrder(sellLimit);
        String quote = stock.getQuote();
        System.out.println("Really: Ask contains 10.00 and size 10");
        System.out.println("What we have" + quote);

    }



    




    

}