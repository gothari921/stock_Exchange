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
        test3();
        test4();
        test5(); 
        test6(); 
        test7();
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

    public static void test4()
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        Trader buyer = new Trader(null, "buyer", "password");
        TradeOrder buy = new TradeOrder(buyer, symbol, true, false, 15, 0.0);
        stock.placeOrder(buy);
        System.out.println("Expected order sizde is 1");
        System.out.println("Actual buy orders size is: " + stock.getBuyOrders().size());
        TradeOrder sell = new TradeOrder(buyer, symbol, false, false, numShares, initPrice);
        stock.placeOrder(sell);
        System.out.println("the real number of sell orders is 1");
        System.out.println("what we predicted is " + stock.getSellOrders().size());
    }

    public static void test5()
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        Trader seller = new Trader(null, "seller", "fulkonator");
        Trader buyer = new Trader (null, "buyer", "fulknado");
        TradeOrder bu = new TradeOrder(buyer, symbol, true, false, 15, 0.0);
        TradeOrder sell = new TradeOrder(buyer, symbol, true, false, 15, 0.0);
        stock.placeOrder(bu);
        stock.placeOrder(sell);
        System.out.println("buy orders are " + stock.getBuyOrders().isEmpty());
        System.out.println("sell order sare" + stock.getSellOrders().isEmpty());
        System.out.println("our last price is" + stock.getLastPrice());

    }

    public static void test6()
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        Trader seller = new Trader(null, "seller", "fulkonator");
        Trader buyer = new Trader (null, "buyer", "fulknado");
        TradeOrder bu = new TradeOrder(buyer, symbol, true, false, 50, 0.0);
        TradeOrder sell = new TradeOrder(buyer, symbol, true, false, 20, 0.0);
        stock.placeOrder(bu);
        stock.placeOrder(sell);
        System.out.println("actual sell orders are" + stock.getSellOrders().isEmpty());
        System.out.println("the actual remaining buy shares are " + stock.getBuyOrders().peek().getShares());
    }

    public static void test7()
    {
        Stock stock = new Stock(symbol, companyName, initPrice);
        Trader seller = new Trader(null, "seller", "fulkonator");
        TradeOrder sell = new TradeOrder(seller, symbol, false, false, 20, 0.0);
        stock.placeOrder(sell);
        String quote = stock.getQuote();
        System.out.println( "what we got + " + quote);

    }


    

    



    




    

}