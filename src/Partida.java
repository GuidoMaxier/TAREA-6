public class Partida implements Runnable {
    
    private final Jugador jugador1;
    private final Jugador jugador2;
    // private final SistemaJuego sistemaJuego;

    public Partida(Jugador jugador1, Jugador jugador2/*, SistemaJuego sistemaJuego*/) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        // this.sistemaJuego = sistemaJuego;
    }

    @Override
    public void run() {
        //  1. Aumentar la cantidad de partidas activas en el sistema (método a especificar en 
        // esta clase) 
        // 2. Indicar que empezó una partida entre los jugadores correspondientes.
        // 3. Simular la partida con una espera de 3 segundos.
        // 4. Generar un puntaje aleatorio para cada uno con un valor aleatorio entre 0 y 5.
        // 5. Calculamos quién fue el ganador según los resultados.
        // 6. Mostramos el resultado final de la partida con el siguiente formato:
        // a. Resultado de la partida: Jugador [id] + ( puntos ) + vs Jugador [id] + ( 
        // puntos ) – Resultado del cálculo en (5)
        // 7. Disminuir la cantidad de partidas activas en el sistema.
        
    }



}
