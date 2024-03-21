import org.junit.Test;
import static org.junit.Assert.*;

public class CarListTest {

    @Test
    public void testMeterYSacarVehiculo() {
        // Configuración
        CarList carList = new CarList();
        Vehiculo vehiculo1 = new Vehiculo("ABC123");
        Vehiculo vehiculo2 = new Vehiculo("XYZ789");

        // Ejecución
        carList.meter(vehiculo1);
        carList.meter(vehiculo2);

        // Verificación
        assertEquals(2, carList.numCoches());
        assertEquals(vehiculo1, carList.obtener("ABC123"));
        assertEquals(vehiculo2, carList.obtener("XYZ789"));

        // Sacar un vehículo
        carList.sacar(vehiculo1);

        // Verificación
        assertNull(carList.obtener("ABC123"));
        assertEquals(1, carList.numCoches());

        // Sacar el otro vehículo
        carList.sacar(vehiculo2);

        // Verificación
        assertNull(carList.obtener("XYZ789"));
        assertEquals(0, carList.numCoches());
    }

    @Test
    public void testObtenerVehiculoNoExistente() {
        // Configuración
        CarList carList = new CarList();

        // Ejecución
        Vehiculo vehiculo = carList.obtener("DEF456");

        // Verificación
        assertNull(vehiculo);
    }
}
