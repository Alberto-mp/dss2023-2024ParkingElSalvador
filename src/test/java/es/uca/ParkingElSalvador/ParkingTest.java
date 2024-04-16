package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        BonoMensual bm = new BonoMensual(vehiculo);
        BonoTrimestral bt = new BonoTrimestral(vehiculo);
        BonoAnual ba = new BonoAnual(vehiculo);

        assertEquals("El precio trimestral de bono debe ser 90", 90, bt.getPrecio().doubleValue(),0.001);
        assertEquals("El precio anual de bono debe ser 365", 365, ba.getPrecio().doubleValue(),0.0001);
        assertEquals("El precio mensual de bono debe ser 30", 30, bm.getPrecio().doubleValue(),0.001);

    }

   @Test
    public void testEntrada() throws Exception {
        parking.entrada(vehiculo.matricula());
        assertEquals("Debería haber una plaza ocupada", 1, parking.getPlazasOcupadas());
    }
    

    @Test
    public void testSalida() throws Exception {
        parking.entrada(vehiculo.matricula());
        // Simulamos que el coche paga
        parking.vehiculoPagaEstandar(vehiculo.matricula(), 'E');
        parking.salida(vehiculo.matricula());
        assertEquals("Debería haber ninguna plaza ocupada", 0, parking.getPlazasOcupadas());
    }
    
    @Test
    public void testVehiculoPagaEstandar() throws Exception {
        parking.precioEstandar(1); // Precio por minuto
        parking.entrada(vehiculo.matricula());
        parking.vehiculoPagaEstandar(vehiculo.matricula(),'T');
        assertTrue("El vehículo debería haber pagado", parking.getVehiculos().obtener(vehiculo.matricula()).estancia().haPagado());
    }

    @Test
    public void testVehiculoPagaBonoMensual() throws Exception {
        parking.entrada(vehiculo.matricula());
        parking.vehiculoPagaBonoMensual(1, vehiculo.matricula(),'T');
        assertTrue("El vehículo debería haber comprado un bono mensual", parking.getVehiculos().obtener(vehiculo.matricula()).estancia().poseeBono());
    }

    @Test
    public void testVehiculoPagaBonoTrimestral() throws Exception {
        parking.entrada(vehiculo.matricula());
        parking.vehiculoPagaBonoTrimestral(1, vehiculo.matricula(),'E');
        assertTrue("El vehículo debería haber comprado un bono trimestral", parking.getVehiculos().obtener(vehiculo.matricula()).estancia().poseeBono());
    }

    @Test
    public void testVehiculoPagaBonoAnual() throws Exception {
        parking.entrada(vehiculo.matricula());
        parking.vehiculoPagaBonoAnual(1, vehiculo.matricula(),'E');
        assertTrue("El vehículo debería haber comprado un bono anual", parking.getVehiculos().obtener(vehiculo.matricula()).estancia().poseeBono());
    }
     

}
