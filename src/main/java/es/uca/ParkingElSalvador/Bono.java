import java.time.LocalDateTime;

public abstract class Bono {
    private LocalDateTime inicio,fin;

    // Metodos observadores
    public LocalDateTime fechaInicio() {
        return inicio;
    }
    public LocalDateTime fechaFin() {
        return fin;
    }
    public boolean activo() {
        return LocalDateTime.now().isBefore(fin);
    }

    // Definimos metodos a sobreescribir en las subclases
    // segun  el tipo
    abstract double precioBono();
    abstract void ponerPrecioBono(long x);
    abstract String tipoBono();
}

