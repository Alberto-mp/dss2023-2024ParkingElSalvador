import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import javax.naming.NameNotFoundException;

public class ParkingTest {
    private Parking parking;

    @Before
    public void setUp() {
        parking = new Parking("Parking Test", "Dirección Test", 100);
    }

    @Test
    public void testEntrada() throws IOException, NameNotFoundException {
        // Ejecución
        parking.entrada();

        // Verificación
        assertEquals(99, parking.getPlazasDisponibles());
        assertEquals(1, parking.getPlazasOcupadas());
    }

    @Test
    public void testSalida() throws IOException, NameNotFoundException {
        // Configuración
        parking.entrada(); 

        // Ejecución
        parking.salida();

        // Verificación
        assertEquals(99, parking.getPlazasDisponibles());
        assertEquals(0, parking.getPlazasOcupadas());
    }

    @Test
    public void testGetNombre() {
        assertEquals("Parking Test", parking.getNombre());
    }

    @Test
    public void testGetDireccionPostal() {
        assertEquals("Dirección Test", parking.getDireccionPostal());
    }

    @Test
    public void testGetCapacidadTotal() {
        assertEquals(100, parking.getCapacidadTotal());
    }

    @Test
    public void testGetPlazasDisponibles() {
        assertEquals(99, parking.getPlazasDisponibles());
    }

    @Test
    public void testGetPlazasOcupadas() {
        assertEquals(1, parking.getPlazasOcupadas());
    }

    @Test
    public void testGetBarrera() {
        assertNotNull(parking.getBarrera());
    }

    @Test
    public void testGetQR() {
        assertNotNull(parking.getQR());
    }

    @Test
    public void testGetVehiculos() {
        assertNotNull(parking.getVehiculos());
    }

    @Test
    public void testGetLibro() {
        assertNotNull(parking.getLibro());
    }

    @Test
    public void testGetInformeActual() {
        assertNotNull(parking.getInformeActual());
    }
}
