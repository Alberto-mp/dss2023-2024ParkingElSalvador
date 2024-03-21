import org.junit.Test;
import static org.junit.Assert.*;

public class BonoAnualTest {

    @Test
    public void testPrecioBono() {
        // Configuración
        long precio = 100;
        int nAnos = 3;
        BonoAnual bonoAnual = new BonoAnual(nAnos);
        bonoAnual.ponerPrecioBono(precio);

        // Ejecución
        double resultado = bonoAnual.precioBono();

        // Verificación
        assertEquals(nAnos * precio, resultado); 
    }

    @Test
    public void testTipoBono() {
        // Configuración
        BonoAnual bonoAnual = new BonoAnual(1);

        // Ejecución
        String resultado = bonoAnual.tipoBono();

        // Verificación
        assertEquals("El bono es anual", resultado);
    }

    @Test
    public void testPrecioAnual() {
        // Configuración
        long precio = 200;
        BonoAnual bonoAnual = new BonoAnual(1);
        bonoAnual.ponerPrecioBono(precio);

        // Ejecución
        long resultado = BonoAnual.precioAnual();

        // Verificación
        assertEquals(precio, resultado);
    }
}
