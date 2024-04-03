package es.uca.ParkingElSalvador;

import org.junit.Test;
import static org.junit.Assert.*;

public class BonoMensualTest {

    @Test
    public void testPrecioBono() {
        // Configuración
        long precio = 50;
        int nMeses = 2;
        BonoMensual bonoMensual = new BonoMensual(nMeses);
        bonoMensual.ponerPrecioBono(precio);

        // Ejecución
        double resultado = bonoMensual.precioBono();

        // Verificación
        assertEquals(nMeses * precio, resultado,0.0001); 
    }

    @Test
    public void testTipoBono() {
        // Configuración
        BonoMensual bonoMensual = new BonoMensual(1);

        // Ejecución
        String resultado = bonoMensual.tipoBono();

        // Verificación
        assertEquals("El bono es mensual", resultado);
    }

    @Test
    public void testPrecioMensual() {
        // Configuración
        long precio = 100;
        BonoMensual bonoMensual = new BonoMensual(1);
        bonoMensual.ponerPrecioBono(precio);

        // Ejecución
        long resultado = BonoMensual.precioMensual();

        // Verificación
        assertEquals(precio, resultado);
    }
}
