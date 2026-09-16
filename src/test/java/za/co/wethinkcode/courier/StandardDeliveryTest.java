package za.co.wethinkcode.courier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StandardDeliveryTest {

    @Test
    void deliveryFee_isFlatFee() {
        StandardDelivery delivery = new StandardDelivery("TRK-101", 6.0);
        assertEquals(StandardDelivery.FLAT_FEE, delivery.deliveryFee(), 0.001);
    }

    @Test
    void deliveryFee_doesNotChangeWithWeight() {
        StandardDelivery light = new StandardDelivery("TRK-102", 1.0);
        StandardDelivery heavy = new StandardDelivery("TRK-103", 20.0);
        assertEquals(light.deliveryFee(), heavy.deliveryFee(), 0.001);
    }

    @Test
    void transitDays_isFiveDays() {
        StandardDelivery delivery = new StandardDelivery("TRK-104", 3.0);
        assertEquals(5, delivery.transitDays());
    }

    @Test
    void getTrackingNumber_returnsTrackingNumberProvided() {
        StandardDelivery delivery = new StandardDelivery("TRK-105", 4.5);
        assertEquals("TRK-105", delivery.getTrackingNumber());
    }

    @Test
    void getWeightKg_returnsWeightProvided() {
        StandardDelivery delivery = new StandardDelivery("TRK-106", 9.5);
        assertEquals(9.5, delivery.getWeightKg(), 0.001);
    }
}
