package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

public class PagoTarjetaTest {

    private PagoTarjeta pagoTarjeta;
    
    @Before
    public void setUp() {
        pagoTarjeta = new PagoTarjeta();
    }

    @Test
    public void testProcesarPago() {
        // Probamos el método procesarPago
        BigDecimal cantidadEntregada = new BigDecimal("100.00");
        BigDecimal cantidadAPagar = new BigDecimal("50.00");
        boolean resultado = pagoTarjeta.procesarPago(cantidadEntregada, cantidadAPagar);
        
        // Verificamos que el pago haya sido procesado exitosamente
        assertTrue(resultado);
    }
}
