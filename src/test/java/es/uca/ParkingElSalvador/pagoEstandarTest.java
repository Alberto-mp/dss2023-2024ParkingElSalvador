package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class pagoEstandarTest {
    private Vehiculo vehiculo;
    private PagoEstandar pago;
    private Cajero cajero;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
        cajero = new Cajero();
        cajero.meterDinero(new BigDecimal(40));
        pago = new PagoEstandar(cajero,vehiculo, 0.5); // Precio por minuto
    }

    @Test
    public void testCantidad() {
        assertEquals("La cantidad debe ser 30 si se han usado 60 minutos y el precio por minuto es 0.5", 30, pago.cantidad(60), 0);
    }

    @Test
    public void testPagar() {
        try {
            pago.pagar(new BigDecimal(10),'E'); // Pago de tarifa estándar válido
            assertTrue("El vehículo debería haber pagado la tarifa estándar", vehiculo.estancia().haPagado());
            assertNotNull("El vehículo debería tener una cantidad pagada", vehiculo.estancia().dineroPagado());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
}
