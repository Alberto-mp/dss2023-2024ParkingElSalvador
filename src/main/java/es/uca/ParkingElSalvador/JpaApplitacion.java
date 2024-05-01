package es.uca.ParkingElSalvador;

@SpringBootApplication
@EnableJpaRepositories
public class JpaApplitacion {
    public static void main(String[] args){
        SpringApplication.run(JpaApplitacion.class, args);
    }
}