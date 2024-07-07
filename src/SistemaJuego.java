import java.util.ArrayList;
import java.util.List;

public class SistemaJuego implements Runnable {
    private final List<Jugador> jugadoresEspera = new ArrayList<>();
    private int partidasActivas = 0;
    private static final int RANGO_INICIAL = 500;
    private static final int AUMENTO_POR_SGUNDO = 100;
    private final Object monitor = new Object();
    private boolean generandoJugadores = true;
    private boolean SistemaEnEjecucion = true;

    public void agregarJugador(Jugador jugador) {
        synchronized (monitor) {
            jugadoresEspera.add(jugador);
            monitor.notifyAll();
        }
    }

    public void revisarEstadoSistema() {
        synchronized (monitor) {
            if (!generandoJugadores && jugadoresEspera.isEmpty() && partidasActivas == 0) {
                SistemaEnEjecucion = false;
                monitor.notifyAll();
            }
        }
  
    }

    public synchronized void setGenerandoJugadores(boolean generandoJugadores) {
        synchronized (monitor) {
            this.generandoJugadores = generandoJugadores;
            monitor.notifyAll();
        }
    }

    public synchronized void aumentarPartidasActivas() {
        synchronized (monitor) {
            partidasActivas++;
            System.out.println("Partidas activas: " + partidasActivas);
            monitor.notifyAll();
        }
    }

    public synchronized void disminuirPartidasActivas() {
        synchronized (monitor) {
            partidasActivas--;
            System.out.println("Partidas activas: " + partidasActivas);
            monitor.notifyAll();
        }
    }

    private Jugador encontrarPartida(Jugador jugador) {
        long tiempoEsperado = (System.currentTimeMillis() - jugador.getTiempoEspera()) / 1000;

        int rangoPermitido = RANGO_INICIAL + (int) (tiempoEsperado * AUMENTO_POR_SGUNDO);

        for (Jugador oponente : jugadoresEspera) {
            if (oponente != jugador && Math.abs(oponente.getRanking() - jugador.getRanking()) <= rangoPermitido) {
                return oponente;
            }
        }
        return null;

    }

    @Override
    public void run() {
        while (SistemaEnEjecucion) {
            Jugador jugador1 = null;
            Jugador jugador2 = null;

            synchronized (monitor) {

                while (jugadoresEspera.size() < 2 && SistemaEnEjecucion) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                if (jugadoresEspera.size() >= 2) {
                    jugador1 = jugadoresEspera.get(0);
                    jugador2 = encontrarPartida(jugador1);
                    
                    if (jugador2 != null) {
                        jugadoresEspera.remove(jugador1);
                        jugadoresEspera.remove(jugador2);
                    }

                }

            }

            if (jugador2 != null) {
                Partida partida = new Partida(jugador1, jugador2, this);
                new Thread(partida).start();
            } else {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            revisarEstadoSistema();

        }
        System.out.println("Finalizó el sistema del juego");
    }

}
