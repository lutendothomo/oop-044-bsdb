package za.co.wethinkcode.courier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExpressDeliveryTest {

    @Test
    void deliveryFee_isRatePerKgTimesWeight() {
        ExpressDelivery delivery = new ExpressDelivery("TRK-001", 4.0);
        assertEquals(4.0 * ExpressDelivery.RATE_PER_KG, delivery.deliveryFee(), 0.001);
    }

    @Test
    void deliveryFee_scalesWithWeight() {
        ExpressDelivery light = new ExpressDelivery("TRK-002", 1.0);
        ExpressDelivery heavy = new ExpressDelivery("TRK-003", 10.0);
        assertTrue(heavy.deliveryFee() > light.deliveryFee());
    }

    @Test
    void transitDays_isOneDay() {
        ExpressDelivery delivery = new ExpressDelivery("TRK-004", 2.0);
        assertEquals(1, delivery.transitDays());
    }

    @Test
    void getTrackingNumber_returnsTrackingNumberProvided() {
        ExpressDelivery delivery = new ExpressDelivery("TRK-005", 2.5);
        assertEquals("TRK-005", delivery.getTrackingNumber());
    }

    @Test
    void getWeightKg_returnsWeightProvided() {
        ExpressDelivery delivery = new ExpressDelivery("TRK-006", 7.5);
        assertEquals(7.5, delivery.getWeightKg(), 0.001);
    }
}
