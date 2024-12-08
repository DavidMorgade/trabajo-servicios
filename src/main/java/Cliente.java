import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

class Cliente extends Thread {
    private String nombreCliente;
    private int numeroVehiculo;
    private Semaphore semaforo;
    private static final Set<Integer> vehiculosEnUso = new HashSet<>();
    private static final Object lock = new Object(); // Para sincronizar acceso a vehiculosEnUso

    public Cliente(String nombreCliente, Semaphore semaforo) {
        this.nombreCliente = nombreCliente;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();

            synchronized (lock) {
                // Buscar un vehículo disponible
                for (int i = 1; i <= 4; i++) {
                    if (!vehiculosEnUso.contains(i)) {
                        numeroVehiculo = i;
                        vehiculosEnUso.add(i);
                        break;
                    }
                }
            }

            System.out.println(nombreCliente + " probando vehículo " + numeroVehiculo);

            Thread.sleep(3000);

            System.out.println(nombreCliente + " terminó de probar el vehículo " + numeroVehiculo);

            synchronized (lock) {
                vehiculosEnUso.remove(numeroVehiculo); // Liberar el vehículo
            }

            semaforo.release();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

