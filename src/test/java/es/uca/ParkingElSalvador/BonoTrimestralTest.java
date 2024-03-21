import org.junit.Test;
import static org.junit.Assert.*;

public class BonoTrimestralTest {

    @Test
    public void testPrecioBono() {
        // Configuración
        long precio = 150;
        int nTrimestres = 2;
        BonoTrimestral bonoTrimestral = new BonoTrimestral(nTrimestres);
        bonoTrimestral.ponerPrecioBono(precio);

        // Ejecución
        double resultado = bonoTrimestral.precioBono();

        // Verificación
        assertEquals(nTrimestres * precio, resultado); 
    }

    @Test
    public void testTipoBono() {
        // Configuración
        BonoTrimestral bonoTrimestral = new BonoTrimestral(1);

        // Ejecución
        String resultado = bonoTrimestral.tipoBono();

        // Verificación
        assertEquals("El bono es trimestral", resultado);
    }

    @Test
    public void testPrecioTrimestral() {
        // Configuración
        long precio = 200;
        BonoTrimestral bonoTrimestral = new BonoTrimestral(1);
        bonoTrimestral.ponerPrecioBono(precio);

        // Ejecución
        long resultado = BonoTrimestral.precioTrimestral();

        // Verificación
        assertEquals(precio, resultado);
    }
}
