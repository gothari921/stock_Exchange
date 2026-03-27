public class MyTests_Brokerage {

    private static StockExchange exchange = new StockExchange();
    private static Brokerage brokerage = new Brokerage(exchange);

    public static void test() {
        testAddUserValid();
        testAddUserNullName();
        testAddUserNameTooShort();
        testAddUserNameTooLong();
        testAddUserPasswordTooShort();
        testAddUserDuplicate();
        testLoginValid();
        testLoginNameNotFound();
        testLoginWrongPassword();
        testLoginAlreadyLoggedIn();
        testLogout();
        testGetQuote();
        testPlaceOrder();
        System.out.println(brokerage);
        System.out.println(exchange);
    }

    private static void testAddUserValid() {
        System.out.println("Testing valid addUser");
        int result = brokerage.addUser("pass", "word");
        System.out.println("Expected: 0     was" + result + "        " + (result == 0));
    }

    private static void testAddUserNullName() {
        System.out.println("Testing null name addUser");
        int result = brokerage.addUser(null, null);
        System.out.println("Expected: -1     was" + result + "        " + (result == -1));
    }

    private static void testAddUserNameTooShort() {
        System.out.println("Testing name too short");
        int result = brokerage.addUser("flk", "berg");
        System.out.println("Expected: -1     was" + result + "        " + (result == -1));
    }

    private static void testAddUserNameTooLong() {
        System.out.println("Testing name too long");
        int result = brokerage.addUser("fulkenberg", "berg");
        System.out.println("Expected: -1     was" + result + "        " + (result == -1));
    }

    private static void testAddUserPasswordTooShort() {
        System.out.println("Testing password too short");
        int result = brokerage.addUser("fulk", "s");
        System.out.println("Expected: -2     was" + result + "        " + (result == -2));
    }

    private static void testAddUserDuplicate() {
        System.out.println("Testing duplicate name");
        int result = brokerage.addUser("pass", "word");
        System.out.println("Expected: -3     was" + result + "        " + (result == -3));
    }

    private static void testLoginValid() {
        System.out.println("Testing valid login");
        int result = brokerage.login("pass", "word");
        System.out.println("Expected: 0     was" + result + "        " + (result == 0));
    }

    private static void testLoginNameNotFound() {
        System.out.println("Testing name not found");
        int result = brokerage.login("nico", "rsbg");
        System.out.println("Expected: -1     was" + result + "        " + (result == -1));
    }

    private static void testLoginWrongPassword() {
        System.out.println("Testing wrong password");
        int result = brokerage.login("pass", "num");
        System.out.println("Expected: -2     was" + result + "        " + (result == -2));
    }

    private static void testLoginAlreadyLoggedIn() {
        System.out.println("Testing already logged in");
        int result = brokerage.login("pass", "word");
        System.out.println("Expected: -3     was" + result + "        " + (result == -3));
    }

    private static void testLogout() {
        System.out.println("Testing logout");
        Trader trader = brokerage.getTraders().get("pass");
        brokerage.logout(trader);
        System.out.println("Expected: false     was" + brokerage.isLoggedIn(trader) + "        " + (!brokerage.isLoggedIn(trader)));
    }

    private static void testGetQuote() {
        System.out.println("Testing getQuote");
        exchange.listStock("FULK", "fulkgpt.com", 100.0);
        Trader trader = brokerage.getTraders().get("pass");
        brokerage.login("pass", "word");
        brokerage.getQuote("FULK", trader);
        System.out.println("Has message: " + trader.hasMessages());
    }

    private static void testPlaceOrder() {
        System.out.println("Testing placeOrder");
        Trader trader = brokerage.getTraders().get("pass");
        TradeOrder order = new TradeOrder(trader, "FULK", true, true, 100, 100.0);
        brokerage.placeOrder(order);
        System.out.println("Has message: " + trader.hasMessages());
    }

    
}