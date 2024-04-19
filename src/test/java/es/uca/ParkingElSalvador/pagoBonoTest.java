package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class PagoBonoTest {
    private Vehiculo vehiculo;
    private PagoBono pago;
    private Cajero cajero;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
        cajero = new Cajero();
        cajero.setDinero(new BigDecimal(500000));
        pago = new PagoBono(cajero,vehiculo);
    }

    @Test
    public void testComprarBonoMensual() {
        try {
            pago.comprarBonoMensual(new BigDecimal(1),1,'E'); // Compra de bono mensual válido
            assertTrue("El vehículo debería haber comprado un bono mensual", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testComprarBonoTrimestral() {
        try {
            pago.comprarBonoTrimestral(new BigDecimal(1),1,'T'); // Compra de bono trimestral válido
            assertTrue("El vehículo debería haber comprado un bono trimestral", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testComprarBonoAnual() {
        try {
            pago.comprarBonoAnual(new BigDecimal(1),1,'E'); // Compra de bono anual válido
            assertTrue("El vehículo debería haber comprado un bono anual", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
}
