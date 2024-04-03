package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class BonoAnualTest {
    private BonoAnual bono;

    @Before
    public void setUp() {
        bono = new BonoAnual(1);
    }

    @Test
    public void testPrecioBono() {
    bono.ponerPrecioBono(50);
    assertEquals("El precio del bono debería ser igual al precio anual por el número de años", 50, bono.precioBono(), 0.001); // Aquí se establece un margen de error permitido
    }

    @Test
    public void testPonerPrecioBono() {
        bono.ponerPrecioBono(100);
        assertEquals("El precio anual del bono debería ser el valor establecido", 100, BonoAnual.precioAnual(), 0.001);
    }

 

    @Test
    public void testTipoBono() {
        assertEquals("El tipo de bono debería ser 'El bono es anual'", "El bono es anual", bono.tipoBono());
    }

    // Agrega más pruebas según sea necesario para cubrir otros métodos y casos de uso de la clase BonoAnual
}
