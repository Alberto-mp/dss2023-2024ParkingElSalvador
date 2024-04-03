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
        assertFalse("Al inicio el vehículo no debe tener bono", vehiculo.poseeBono());
        vehiculo.compraBono();
        assertTrue("Después de comprar el bono, el vehículo debe tenerlo", vehiculo.poseeBono());
    }

    @Test
    public void testSetFinBono() {
        LocalDateTime finBono = LocalDateTime.now().plusHours(1);
        vehiculo.setFinBono(finBono);
        assertEquals("El fin del bono debe ser igual al valor establecido", finBono, vehiculo.fechaFinBono());
    }

    @Test
    public void testMatricula() {
        assertEquals("La matrícula del vehículo debe ser la establecida en el constructor", "123ABC", vehiculo.matricula());
    }

    @Test
    public void testDineroPagado() {
        assertEquals("El dinero pagado por el vehículo debe ser 0 al inicio", 0.0, vehiculo.dineroPagado(), 0.001);
        vehiculo.setDineroPagado(10.5);
        assertEquals("El dinero pagado por el vehículo debe ser el valor establecido", 10.5, vehiculo.dineroPagado(), 0.001);
    }

    @Test
    public void testToString() {
        assertEquals("La representación en cadena del vehículo debe ser 'Matricula -> 123ABC'", "Matricula -> 123ABC", vehiculo.toString());
    }

    @Test
    public void testBonoValidoSinBono() {
        assertFalse("El vehículo no debería tener un bono al inicio", vehiculo.bonoValido());
    }

    @Test
    public void testBonoValidoConBonoVencido() {
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().minusHours(1)); // Un bono vencido desde hace una hora
        assertFalse("El bono no debería ser válido si ya está vencido", vehiculo.bonoValido());
    }

    @Test
    public void testBonoValidoConBonoVigente() {
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusHours(1)); // Un bono válido por una hora
        assertTrue("El bono debería ser válido si aún no ha vencido", vehiculo.bonoValido());
    }

    @Test
    public void testHaPagadoSinPagar() {
        assertFalse("El vehículo no debería haber pagado al inicio", vehiculo.haPagado());
    }

    @Test
    public void testHaPagadoDespuesDePagar() {
        vehiculo.pagarEstandar();
        assertTrue("El vehículo debería haber pagado después de llamar al método pagarEstandar()", vehiculo.haPagado());
    }
}
