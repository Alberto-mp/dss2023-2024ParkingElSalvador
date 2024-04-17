package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

public class VehiculoTest {
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
    }

    @Test
    public void testCompraBono() {
        assertFalse("Al inicio el vehículo no debe tener bono", vehiculo.estancia().poseeBono());
        vehiculo.estancia().compraBono();
        assertTrue("Después de comprar el bono, el vehículo debe tenerlo", vehiculo.estancia().poseeBono());
    }

    @Test
    public void testSetFinBono() {
        LocalDateTime finBono = LocalDateTime.now().plusHours(1);
        vehiculo.estancia().setFinBono(finBono);
        assertEquals("El fin del bono debe ser igual al valor establecido", finBono, vehiculo.estancia().fechaFinBono());
    }

    @Test
    public void testMatricula() {
        assertEquals("La matrícula del vehículo debe ser la establecida en el constructor", "123ABC", vehiculo.matricula());
    }

    @Test
    public void testDineroPagado() {
        assertEquals("El dinero pagado por el vehículo debe ser 0 al inicio", 0.0, vehiculo.estancia().dineroPagado(), 0.001);
        vehiculo.estancia().setDineroPagado(10.5);
        assertEquals("El dinero pagado por el vehículo debe ser el valor establecido", 10.5, vehiculo.estancia().dineroPagado(), 0.001);
    }

    @Test
    public void testBonoValidoSinBono() {
        assertFalse("El vehículo no debería tener un bono al inicio", vehiculo.estancia().bonoValido());
    }

    @Test
    public void testBonoValidoConBonoVencido() {
        vehiculo.estancia().compraBono();
        vehiculo.estancia().setFinBono(LocalDateTime.now().minusHours(1)); // Un bono vencido desde hace una hora
        assertFalse("El bono no debería ser válido si ya está vencido", vehiculo.estancia().bonoValido());
    }

    @Test
    public void testBonoValidoConBonoVigente() {
        vehiculo.estancia().compraBono();
        vehiculo.estancia().setFinBono(LocalDateTime.now().plusHours(1)); // Un bono válido por una hora
        assertTrue("El bono debería ser válido si aún no ha vencido", vehiculo.estancia().bonoValido());
    }

    @Test
    public void testHaPagadoSinPagar() {
        assertFalse("El vehículo no debería haber pagado al inicio", vehiculo.estancia().haPagado());
    }

    @Test
    public void testHaPagadoDespuesDePagar() {
        vehiculo.estancia().pagarEstandar();
        assertTrue("El vehículo debería haber pagado después de llamar al método pagarEstandar()", vehiculo.estancia().haPagado());
    }
}
