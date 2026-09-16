package za.co.wethinkcode.courier;

public class ExpressDelivery extends DeliveryMethod {

    public static final double RATE_PER_KG = 45.00;
    private static final int TRANSIT_DAYS = 1;

    public ExpressDelivery(String trackingNumber, double weightKg) {
        super(trackingNumber, weightKg);
    }

    @Override
    public double deliveryFee() {
        return getWeightKg() * RATE_PER_KG;
    }

    @Override
    public int transitDays() {
        return TRANSIT_DAYS;
    }
}
