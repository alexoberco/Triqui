import java.util.Scanner;

public class Main {
    private static final Tablero tablero = new Tablero();
    private static Jugador jugador1;
    private static Jugador jugador2;
    private static Minimax minimax;

    public static void main(String[] args) {
        jugador1 = new Jugador('X', false);  // Humano
        jugador2 = new Jugador('O', true);   // IA

        minimax = new Minimax();
        iniciarJuego();
    }

    private static void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        boolean turnoJugador = true;

        while (!juegoTerminado()) {
            mostrarTablero();

            if (turnoJugador) {
                realizarMovimientoJugador(scanner);
            } else {
                realizarMovimientoIa();
            }

            turnoJugador = !turnoJugador;
        }

        mostrarTablero();
        if (tablero.hayGanador()) {
            if (!turnoJugador) {
            System.out.println(jugador1.getSimbolo() + " ha ganado!");
            } else {
                System.out.println(jugador2.getSimbolo() + " ha ganado!");
            }
        } else {
            System.out.println("¡Empate!");
        }

        scanner.close();
    }

    private static void mostrarTablero() {
        System.out.println("\033c");
        char[][] estadoTablero = tablero.getTablero();
        System.out.println("  0 1 2");
        System.out.println("  _ _ _");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + "|");
            for (int j = 0; j < 3; j++) {
                System.out.print(estadoTablero[i][j] + "|");
            }
            System.out.println();
            System.out.println("  _ _ _");
        }
    }

    private static void realizarMovimientoJugador(Scanner scanner) {
        int fila, columna;
        boolean movimientoValido;

        do {
            System.out.println("Ingresa tu movimiento (fila y columna): ");
            fila = scanner.nextInt();
            columna = scanner.nextInt();
            movimientoValido = tablero.realizarMovimiento(fila, columna, jugador1.getSimbolo());
            if (!movimientoValido) {
                System.out.println("Movimiento no válido. Intenta de nuevo.");
            }
        } while (!movimientoValido);
    }

    private static void realizarMovimientoIa() {
        int[] movimiento = minimax.calcMejorMov(tablero.getTablero(), jugador2.getSimbolo());
        tablero.realizarMovimiento(movimiento[0], movimiento[1], jugador2.getSimbolo());
    }

    private static boolean juegoTerminado() {
        return tablero.hayGanador() || tablero.esEmpate();
    }
}