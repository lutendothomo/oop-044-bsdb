package za.co.wethinkcode.courier;

public abstract class DeliveryMethod {

    private final String trackingNumber;
    private final double weightKg;

    protected DeliveryMethod(String trackingNumber, double weightKg) {
        this.trackingNumber = trackingNumber;
        this.weightKg = weightKg;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public abstract double deliveryFee();

    public abstract int transitDays();

    @Override
    public String toString() {
        return String.format(
                "%s[trackingNumber=%s, weightKg=%.2f, fee=%.2f, transitDays=%d]",
                getClass().getSimpleName(), trackingNumber, weightKg, deliveryFee(), transitDays());
    }
}
