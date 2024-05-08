package es.uca.ParkingElSalvador;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class PagoEstandar{
    private CarService coches;

    public PagoEstandar(CarService c){
        coches = c;
    }

    public double cantidad(int minutos, double pMinuto){
        return (double)minutos*pMinuto;
    }

    public void pagar(BigDecimal entregado, String mat, char F, double pMinuto) {
        Vehiculo vehiculo = coches.getVehiculo(mat);
        coches.delete(vehiculo);
        if(!vehiculo.estancia().haPagado()){
            TipoPago p = null;
            double pago = cantidad(vehiculo.estancia().duracion(),pMinuto);
            if(F == 'E')
                p = new PagoEfectivo();
            else 
                p = new PagoTarjeta();
            p.procesarPago(entregado);
            vehiculo.estancia().setDineroPagado(pago);
            vehiculo.estancia().pagarEstandar();   
        }
        coches.save(vehiculo);
    }
}