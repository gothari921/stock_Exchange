public class MyTests_StockExchange {

    public static void test() {

        System.out.println("\n===== StockExchange Tests =====");
        
        StockExchange exchange = new StockExchange();

        String result = exchange.getQuote("Fulk");
        System.out.println("Test 1 - getQuote not listed: " + 
            (result.equals("AAPL not found.") ? "PASS" : "FAIL: " + result));

        
        exchange.listStock("SIGG", "SIggy Inc.", 150.00);
        result = exchange.getQuote("SIGG");
        System.out.println("Test 2 - getQuote after: " + 
            (result.contains("SIGG") ? "pass" : "fail: " + result));


        exchange.listStock("FULK", "Fulkgpt.com", 200.00);
        exchange.listStock("BTD6", "GoonTD6", 300.00);
        System.out.println("Test 3 - list multiple stocks: " + 
            (exchange.getListedStocks().size() == 3 ? "pass" : "fail: size=" + exchange.getListedStocks().size()));

        
        result = exchange.getQuote("GOOG");
        System.out.println("Test 4 - getQuote GOOG: " + 
            (result.contains("GOOG") ? "PASS" : "FAIL: " + result));

        result = exchange.getQuote("XYZ");
        System.out.println("Test 5 - getQuote unknown symbol: " + 
            (result.equals("XYZ not found.") ? "PASS" : "FAIL: " + result));


    }
}