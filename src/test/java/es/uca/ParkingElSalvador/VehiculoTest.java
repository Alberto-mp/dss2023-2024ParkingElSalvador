import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

public class VehiculoTest {
    private Vehiculo vehiculo;
    private LocalDateTime llegada;

    @Before
    public void setUp() {
        vehiculo = new Vehiculo("123ABC");
        llegada = LocalDateTime.of(2024, 3, 19, 10, 30); // Establecer una hora de llegada fija para las pruebas
        vehiculo.llega();
    }

    @Test
    public void testLlegada() {
        assertNotNull("La hora de llegada no debería ser nula", vehiculo.horaLlegada());
        assertEquals("La hora de llegada debe ser igual a la establecida", "2024-03-19 10:30:00", vehiculo.horaLlegada());
    }

    @Test
    public void testDuracion() {
        // Supongamos que el vehículo sale una hora después de llegar
        LocalDateTime salida = llegada.plusHours(1);
        vehiculo.sale();
        assertEquals("La duración debe ser de 60 minutos", 60, vehiculo.duracion());
    }

    @Test
    public void testCompraBono() {
        vehiculo.compraBono();
        assertTrue("El vehículo debería tener un bono", vehiculo.poseeBono());
    }

    @Test
    public void testBonoValido() {
        // El vehículo no tiene bono
        assertFalse("El vehículo no debería tener un bono", vehiculo.bonoValido());

        // El vehículo tiene un bono válido
        vehiculo.compraBono();
        vehiculo.setFinBono(LocalDateTime.now().plusHours(1)); // Un bono válido por una hora
        assertTrue("El bono debería ser válido", vehiculo.bonoValido());

        // El vehículo tiene un bono expirado
        vehiculo.setFinBono(LocalDateTime.now().minusHours(1)); // Un bono expirado desde hace una hora
        assertFalse("El bono no debería ser válido", vehiculo.bonoValido());
    }

    @Test
    public void testPagarEstandar() {
        vehiculo.pagarEstandar();
        assertTrue("El vehículo debería haber pagado la tarifa estándar", vehiculo.haPagado());
    }

    @Test
    public void testSalida() {
        // Supongamos que el vehículo sale una hora después de llegar
        LocalDateTime salida = llegada.plusHours(1);
        vehiculo.sale();
        assertEquals("La hora de salida debe ser igual a la establecida", salida.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), vehiculo.horaSalida());
    }
}
