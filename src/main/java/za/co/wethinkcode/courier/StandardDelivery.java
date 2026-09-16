package za.co.wethinkcode.courier;

public class StandardDelivery extends DeliveryMethod {

    public static final double FLAT_FEE = 60.00;
    private static final int TRANSIT_DAYS = 5;

    public StandardDelivery(String trackingNumber, double weightKg) {
        super(trackingNumber, weightKg);
    }

    @Override
    public double deliveryFee() {
        return FLAT_FEE;
    }

    @Override
    public int transitDays() {
        return TRANSIT_DAYS;
    }
}
