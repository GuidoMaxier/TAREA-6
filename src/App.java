public class App {
    public static void main(String[] args) throws Exception {
        SistemaJuego sistemaJuego = new SistemaJuego();
        Thread hiloEmparejamiento = new Thread(sistemaJuego);
        hiloEmparejamiento.start();

        int cantJugadores = 20;
        GeneradorJugador generadorJugador = new GeneradorJugador(sistemaJuego, cantJugadores);

        Thread hiloGenerador = new Thread(generadorJugador);
        hiloGenerador.start();

        try {
            hiloGenerador.join();
            hiloEmparejamiento.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El programa ha finalizado");

    }
}
