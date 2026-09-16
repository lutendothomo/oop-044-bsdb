package za.co.wethinkcode.courier;

public class Main {

    public static void main(String[] args) {
        DeliveryMethod express = new ExpressDelivery("TRK-1001", 3.5);
        DeliveryMethod standard = new StandardDelivery("TRK-2001", 3.5);

        System.out.println(express);
        System.out.println(standard);
    }
}
