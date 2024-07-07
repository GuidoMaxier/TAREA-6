import java.util.ArrayList;

public class SistemaJuego implements Runnable{
    private final List<Jugador> jugadoresEspera = new ArrayList<>();
    private int partidasActivas = 0;
    private static final int RANGO_INICIAL = 500;
    private static final int AUMENTO_POR_SGUNDO = 100;
    private final object monitor = new Object();
    private boolean generandoJugadores = true;
    private boolean SistemaEnEjecucion = true;

    public synchronized void agregarJugador(Jugador jugador) {
        jugadoresEspera.add(jugador);
    }

    public void revisarEstadoSistema() {
        if (!generandoJugadores && jugadoresEspera.isEmpty() && partidasActivas == 0) {
            SistemaEnEjecucion = false;
        }
    }

    public synchronized void setGenerandoJugadores(boolean generandoJugadores) {
        this.generandoJugadores = generandoJugadores;
    }

    public synchronized void aumentarPartidasActivas() {
        partidasActivas++;
        System.out.println("Partidas activas: " + partidasActivas);
    }

    public synchronized void disminuirPartidasActivas() {
        partidasActivas--;
        System.out.println("Partidas activas: " + partidasActivas);
    }
    



    
}
