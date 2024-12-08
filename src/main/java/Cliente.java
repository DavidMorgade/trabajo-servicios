import java.util.concurrent.Semaphore;

class Cliente extends Thread {
    private String nombreCliente;
    private int numeroVehiculo;
    private int vehiculosDisponibles;
    private Semaphore semaforo;

    public Cliente(String nombreCliente, Semaphore semaforo, int vehiculosDisponibles) {
        this.nombreCliente = nombreCliente;
        this.semaforo = semaforo;
        this.vehiculosDisponibles = vehiculosDisponibles;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire();

            // Probamos un vehiculo al azar entre el numero de vehiculos disponibles
            numeroVehiculo = (int) (Math.random() * vehiculosDisponibles) + 1;

            System.out.println(nombreCliente + " probando vehículo " + numeroVehiculo);

            Thread.sleep(3000);

            System.out.println(nombreCliente + " terminó de probar el vehículo " + numeroVehiculo);

            semaforo.release();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}