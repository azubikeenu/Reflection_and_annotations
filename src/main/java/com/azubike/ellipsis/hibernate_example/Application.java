package com.azubike.ellipsis.hibernate_example;

import org.h2.tools.Server;

public class Application {
    public static void main(String[] args) throws Exception {
        ///////////////////////////////
        // Starts the Database
        ////////////////////////////
        Server.main();
        var richard = new TransactionHistory(12390, "Richard", "Credit", 1200);
        var amaka = new TransactionHistory(11987, "Amaka", "Debit", 34578);
        var boma = new TransactionHistory(156780, "Boma", "Credit", 100000);

        Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
          hibernate.write(richard);
          hibernate.write(amaka);
          hibernate.write(boma);

        final TransactionHistory foundBoma = hibernate.read(TransactionHistory.class,1);
        System.out.println(foundBoma);
        System.out.println(hibernate.read(TransactionHistory.class ,2));
        System.out.println(hibernate.read(TransactionHistory.class ,3));
    }
}

