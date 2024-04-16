package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class pagoBonoTest {
    private Vehiculo vehiculo;
    private PagoBono pago;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
        pago = new PagoBono(vehiculo);
    }

    @Test
    public void testComprarBonoMensual() {
        try {
            pago.comprarBonoMensual(1,'E'); // Compra de bono mensual válido
            assertTrue("El vehículo debería haber comprado un bono mensual", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testComprarBonoTrimestral() {
        try {
            pago.comprarBonoTrimestral(1,'T'); // Compra de bono trimestral válido
            assertTrue("El vehículo debería haber comprado un bono trimestral", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testComprarBonoAnual() {
        try {
            pago.comprarBonoAnual(1,'E'); // Compra de bono anual válido
            assertTrue("El vehículo debería haber comprado un bono anual", vehiculo.estancia().poseeBono());
            assertNotNull("El vehículo debería tener una fecha de fin de bono", vehiculo.estancia().fechaFinBono());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
}
