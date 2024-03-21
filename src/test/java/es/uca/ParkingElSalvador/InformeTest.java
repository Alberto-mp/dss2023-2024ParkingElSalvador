import org.junit.Test;
import static org.junit.Assert.*;

public class InformeTest {

    @Test
    public void testFechaInforme() {
        // Configuración
        Informe informe = new Informe();

        // Ejecución
        String fechaInforme = informe.fechaInforme();

        // Verificación
        assertNotNull(fechaInforme);
    }

    @Test
    public void testIngresoDiario() {
        // Configuración
        Informe informe = new Informe();

        // Verificación
        assertEquals(0, informe.ingresoDiario());
    }

    @Test
    public void testSumaDiaria() {
        // Configuración
        Informe informe = new Informe();
        long cantidad = 100;

        // Ejecución
        informe.sumaDiaria(cantidad);

        // Verificación
        assertEquals(cantidad, informe.ingresoDiario());
        assertEquals(cantidad, informe.ingresoSemanal());
        assertEquals(cantidad, informe.ingresoMensual());
    }

    @Test
    public void testIngresoSemanal() {
        // Configuración
        Informe informe = new Informe();

        // Verificación
        assertEquals(0, informe.ingresoSemanal());
    }

    @Test
    public void testSumaSemanal() {
        // Configuración
        Informe informe = new Informe();
        long cantidad = 200;

        // Ejecución
        informe.sumaSemanal(cantidad);

        // Verificación
        assertEquals(cantidad, informe.ingresoSemanal());
        assertEquals(cantidad, informe.ingresoMensual());
    }

    @Test
    public void testIngresoMensual() {
        // Configuración
        Informe informe = new Informe();

        // Verificación
        assertEquals(0, informe.ingresoMensual());
    }

    @Test
    public void testSumaMensual() {
        // Configuración
        Informe informe = new Informe();
        long cantidad = 300;

        // Ejecución
        informe.sumaMensual(cantidad);

        // Verificación
        assertEquals(cantidad, informe.ingresoMensual());
    }
}
