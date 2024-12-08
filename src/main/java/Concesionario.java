import javax.swing.*;
import java.util.concurrent.Semaphore;

public class Concesionario {

    private final static int VEHICULOS_DISPONIBLES = 4;
    private final static int NUMERO_CLIENTES = 9;
    private static DefaultListModel<String> listaEsperaModel;
    private static DefaultListModel<String> listaProbandoModel;
    private static JTextArea logArea;
    private static Semaphore semaforo;

    public static void main(String[] args) {

        // Semáforo que permite hasta 4 clientes al mismo tiempo
        Semaphore semaforo = new Semaphore(VEHICULOS_DISPONIBLES);

        // Creamos los 9 clientes
        for(int i = 1; i <= NUMERO_CLIENTES; i++) {
            Cliente cliente = new Cliente("Cliente " + i, semaforo);
            cliente.start();
        }

    }
}
