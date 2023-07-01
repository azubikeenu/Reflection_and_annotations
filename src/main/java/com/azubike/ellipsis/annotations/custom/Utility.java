package com.azubike.ellipsis.annotations.custom;

@MostUsed
public class Utility {
    public void doStuff() {
        System.out.println("Perform stuffs");
    }

    @MostUsed(".NET")
    public void doStuff(String arg) {
        System.out.println("Operating on " + arg);
    }

    public void doStuff(int arg) {
        System.out.println("Operating on " + arg);
    }
}

class SubUtil extends Utility {

}
