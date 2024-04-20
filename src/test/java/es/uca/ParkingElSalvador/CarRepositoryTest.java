package es.uca.ParkingElSalvador;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CarRepositoryTest {
    private CarRepositoryInMemoryRepo carRepository;

    @Before
    public void setUp() {
        carRepository = new CarRepositoryInMemoryRepo();
    }

    @Test
    public void testMeter() {
        Vehiculo vehiculo = new Vehiculo("123ABC");
        carRepository.meter(vehiculo);
        assertEquals("El número de coches en el repositorio debe ser 1 después de agregar un vehículo", 1, carRepository.numCoches());
        assertEquals("El vehículo agregado debe poder ser obtenido del repositorio", vehiculo, carRepository.obtener("123ABC"));
    }

    @Test
    public void testSacar() {
        Vehiculo vehiculo = new Vehiculo("123ABC");
        carRepository.meter(vehiculo);
        carRepository.sacar(vehiculo);
        assertEquals("El número de coches en el repositorio debe ser 0 después de sacar el vehículo agregado", 0, carRepository.numCoches());
        assertNull("El vehículo sacado no debe estar presente en el repositorio", carRepository.obtener("123ABC"));
    }

    @Test
    public void testObtener() {
        Vehiculo vehiculo = new Vehiculo("123ABC");
        carRepository.meter(vehiculo);
        assertEquals("Se debería poder obtener el vehículo agregado al repositorio", vehiculo, carRepository.obtener("123ABC"));
        assertNull("No debería devolver ningún vehículo si la matrícula no está presente", carRepository.obtener("999ZZZ"));
    }

    @Test
    public void testNumCoches() {
        assertEquals("El número de coches en el repositorio debe ser 0 al inicio", 0, carRepository.numCoches());
        carRepository.meter(new Vehiculo("123ABC"));
        assertEquals("El número de coches en el repositorio debe ser 1 después de agregar un vehículo", 1, carRepository.numCoches());
    }
}
