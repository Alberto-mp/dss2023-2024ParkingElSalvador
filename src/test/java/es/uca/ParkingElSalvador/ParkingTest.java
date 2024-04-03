package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;

public class ParkingTest {
    private Parking parking;
    private Vehiculo vehiculo;

    @Before
    public void setUp() {
        parking = new Parking("Parking Prueba", "Calle Prueba, 123", 50);
        vehiculo = new Vehiculo("123ABC");
    }


    @Test
    public void testPonerPrecioBonos() {
        parking.ponerPrecioBonos(30, 90, 365); // Precios para bonos
        assertEquals("El precio mensual de bono debe ser 30", 30, BonoMensual.pMes,0.001);
        assertEquals("El precio trimestral de bono debe ser 90", 90, BonoTrimestral.pTrimestre,0.001);
        assertEquals("El precio anual de bono debe ser 365", 365, BonoAnual.pAnual,0.0001);
    }

    @Test
    public void testEntrada() throws Exception {
        parking.entrada();
        assertEquals("Debería haber una plaza ocupada", 1, parking.getPlazasOcupadas());
    }

    @Test
    public void testSalida() throws Exception {
        parking.entrada();
        parking.salida();
        assertEquals("Debería haber ninguna plaza ocupada", 0, parking.getPlazasOcupadas());
    }

    @Test
    public void testVehiculoPagaEstandar() throws Exception {
        parking.precioEstandar(1); // Precio por minuto
        parking.entrada();
        parking.vehiculoPagaEstandar("123ABC");
        assertTrue("El vehículo debería haber pagado", vehiculo.haPagado());
    }

    @Test
    public void testVehiculoPagaBonoMensual() throws Exception {
        parking.entrada();
        parking.vehiculoPagaBonoMensual(1, "123ABC");
        assertTrue("El vehículo debería haber comprado un bono mensual", vehiculo.poseeBono());
    }

    @Test
    public void testVehiculoPagaBonoTrimestral() throws Exception {
        parking.entrada();
        parking.vehiculoPagaBonoTrimestral(1, "123ABC");
        assertTrue("El vehículo debería haber comprado un bono trimestral", vehiculo.poseeBono());
    }

    @Test
    public void testVehiculoPagaBonoAnual() throws Exception {
        parking.entrada();
        parking.vehiculoPagaBonoAnual(1, "123ABC");
        assertTrue("El vehículo debería haber comprado un bono anual", vehiculo.poseeBono());
    }

}
