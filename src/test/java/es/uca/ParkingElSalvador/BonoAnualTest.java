package es.uca.ParkingElSalvador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class BonoAnualTest {
    private Vehiculo vehiculo;
    private BonoAnual bono;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("ABC123");
        bono = new BonoAnual(vehiculo);
    }

    @Test
    public void testConstructor() {
        assertNotNull(bono);
        assertEquals(vehiculo, bono.getVehiculo());
    }

    @Test
    public void testSetPrecio() {
        BigDecimal precio = new BigDecimal("100.00");
        BonoAnual.setPrecio(precio);
        assertEquals(precio, bono.getPrecio());
    }

    @Test
    public void testTipoBono() {
        assertEquals("Anual", bono.tipoBono());
    }
}
