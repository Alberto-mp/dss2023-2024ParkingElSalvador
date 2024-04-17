package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

public class PagoEfectivoTest {

    private PagoEfectivo pagoEfectivo;
    private Cajero cajero;
    
    @Before
    public void setUp() {
        // Creamos una instancia real de Cajero
        cajero = new Cajero();
        
        // Creamos una instancia de PagoEfectivo con la instancia real de Cajero
        pagoEfectivo = new PagoEfectivo(cajero);
    }

    @Test
    public void testProcesarPagoConCambioSuficiente() {
        // Agregamos una cantidad de dinero al cajero
        BigDecimal cantidadInicial = new BigDecimal("100.00");
        BigDecimal cantidadAPagar = new BigDecimal("50.00");
        cajero.meterDinero(cantidadInicial);
        
        // Probamos el método procesarPago
        BigDecimal cantidadEntregada = new BigDecimal("100.00");
        boolean resultado = pagoEfectivo.procesarPago(cantidadEntregada, cantidadAPagar);
        
        // Verificamos que el cambio se haya calculado correctamente y que el pago haya sido procesado exitosamente
        assertTrue(resultado);
        assertEquals(new BigDecimal("100.00"), cajero.getDinero());
    }

    @Test
    public void testProcesarPagoSinCambioSuficiente() {
        // Agregamos una cantidad de dinero al cajero (menor a la cantidad a pagar)
        BigDecimal cantidadInicial = new BigDecimal("30.00");
        BigDecimal cantidadAPagar = new BigDecimal("50.00");
        cajero.meterDinero(cantidadInicial);
        
        // Probamos el método procesarPago
        BigDecimal cantidadEntregada = new BigDecimal("30.00");
        boolean resultado = pagoEfectivo.procesarPago(cantidadEntregada, cantidadAPagar);
        
        // Verificamos que el pago haya sido rechazado y que el cajero no haya sido modificado
        assertFalse(resultado);
        assertEquals(cantidadInicial, cajero.getDinero());
    }
}
