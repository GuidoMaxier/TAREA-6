import java.util.Random;

public class GeneradorJugador implements Runnable {
    private final SistemaJuego sistemaJuego;
    private final int jugadoresTotales;

    public GeneradorJugador(SistemaJuego sistemaJuego, int jugadoresTotales) {
        this.sistemaJuego = sistemaJuego;
        this.jugadoresTotales = jugadoresTotales;
    }

    @Override
    public void run(){
        Random random = new Random();
        for (int i = 1; i <= jugadoresTotales; i++) {

            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int ranking = random.nextInt(23001) + 1000;
            Jugador jugador = new Jugador(i, ranking);
            sistemaJuego.agregarJugador(jugador);

            //El Jugador [id] (Ranking: [ranking]) se unió a la cola de espera.
            System.out.println("El Jugador [id: "+ jugador.getId() +"] (Ranking: [" + jugador.getRanking() +"]) se unió a la cola de espera.");

        }
        sistemaJuego.setGenerandoJugadores(false);
        System.out.println("Se terminaron de generar los " + jugadoresTotales + " jugadores indicados.");
    }
    
}
