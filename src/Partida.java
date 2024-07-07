import java.util.Random;

public class Partida implements Runnable {
    
    private final Jugador jugador1;
    private final Jugador jugador2;
    private final SistemaJuego sistemaJuego;

    public Partida(Jugador jugador1, Jugador jugador2, SistemaJuego sistemaJuego) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.sistemaJuego = sistemaJuego;
    }

    @Override
    public void run() {
        //  1. Aumentar la cantidad de partidas activas en el sistema (método a especificar en 
        // esta clase) 
        sistemaJuego.aumentarPartidasActivas();

        // 2. Indicar que empezó una partida entre los jugadores correspondientes.
        System.out.println("Empezó una partida entre Jugador [id: " + jugador1.getId() + "] y Jugador [id: " + jugador2.getId() + "].");
        // 3. Simular la partida con una espera de 3 segundos.
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 4. Generar un puntaje aleatorio para cada uno con un valor aleatorio entre 0 y 5.
        Random random = new Random();
        int puntosJugador1 = random.nextInt(6);
        int puntosJugador2 = random.nextInt(6);

        // 5. Calculamos quién fue el ganador según los resultados.
        String resultado;
        if (puntosJugador1 > puntosJugador2) {
            resultado = "Jugador [id: " + jugador1.getId() + "] WINS!"; 
        } else if (puntosJugador1 < puntosJugador2) {
            resultado = "Jugador [id: " + jugador2.getId() + "] WINS!"; 
        } else {
            resultado = "Empate";
        }

        // 6. Mostramos el resultado final de la partida con el siguiente formato:
           // a. Resultado de la partida: Jugador [id] + ( puntos ) + vs Jugador [id] + ( 
        // puntos ) – Resultado del cálculo en (5)
        System.out.println("\u001B[32m" + "Resultado de la partida: Jugador [id: " + jugador1.getId() + "] (puntos: " + puntosJugador1 + ") vs Jugador [id: " + jugador2.getId() + "] (puntos: " + puntosJugador2 + ") - Resultado: " + resultado + "\u001B[0m");
        
        // 7. Disminuir la cantidad de partidas activas en el sistema.¨
        sistemaJuego.disminuirPartidasActivas();
        
    }

}
