public class MyTests_Brokerage {

    public static void test() {

        System.out.println("\n===== Brokerage Tests =====");
        // TODO - add tests for Brokerage class
        StockExchange s = new StockExchange();
        Brokerage brk = new Brokerage(s);
        testOutput(brk.addUser("valid", "password"),0, "valid addUser");
        testOutput(brk.addUser(null, null),-1,"double null user");


    }

    private static void testOutput(int a, int b, String test){
        System.out.println("Testing " + test+"\nExcepcted: "+a+"\t was"+b+"\t\t"+(a==b));
    }
}