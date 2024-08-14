class Minimax {
    private static final int MAX_PROFUNDIDAD = 2;

    // Función heurística para evaluar el estado del tablero
    private int evalTablero(char[][] tablero) {
        int puntuacion = 0;
    
        // Verificar filas y columnas
        for (int i = 0; i < 3; i++) {
            puntuacion += evaluarLinea(tablero[i][0], tablero[i][1], tablero[i][2]);
            puntuacion += evaluarLinea(tablero[0][i], tablero[1][i], tablero[2][i]);
        }
    
        // Verificar diagonales
        puntuacion += evaluarLinea(tablero[0][0], tablero[1][1], tablero[2][2]);
        puntuacion += evaluarLinea(tablero[0][2], tablero[1][1], tablero[2][0]);
    
        return puntuacion;
    }

    private int miniMax(char[][] tablero, int profundidad, boolean esIa) {
        int puntaje = evalTablero(tablero);

        if (puntaje == 10 || puntaje == -10 || profundidad == 0 || !hayMovimientos(tablero)) {
            return puntaje;
        }

        if (esIa) {
            int mejor = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == ' ') {
                        tablero[i][j] = 'O';
                        mejor = Math.max(mejor, miniMax(tablero, profundidad - 1, false));
                        tablero[i][j] = ' ';
                    }
                }
            }
            return mejor;
        } else {
            int peor = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tablero[i][j] == ' ') {
                        tablero[i][j] = 'X';
                        peor = Math.min(peor, miniMax(tablero, profundidad - 1, true));
                        tablero[i][j] = ' ';
                    }
                }
            }
            return peor;
        }
    }

    public int[] calcMejorMov(char[][] tablero, char jugador) {
        int mejorValor = Integer.MIN_VALUE;
        int[] mejorMovimiento = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    tablero[i][j] = jugador;
                    int movimientoValor;
                    if (jugador == 'O') {
                        movimientoValor = miniMax(tablero, MAX_PROFUNDIDAD, false);
                    } else {
                        movimientoValor = miniMax(tablero, MAX_PROFUNDIDAD, true);
                    }
                    tablero[i][j] = ' ';

                    if (movimientoValor > mejorValor) {
                        mejorMovimiento[0] = i;
                        mejorMovimiento[1] = j;
                        mejorValor = movimientoValor;
                    }
                }
            }
        }
        return mejorMovimiento;
    }

    private boolean hayMovimientos(char[][] tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    
    private int evaluarLinea(char c1, char c2, char c3) {
        int puntuacion = 0;
    
        // Caso 3 en línea
        if (c1 == c2 && c2 == c3) {
            if (c1 == 'O') {
                puntuacion = 100;
            } else if (c1 == 'X') {
                puntuacion = -100;
            }
        } else if (c1 == c2 || c2 == c3 || c1 == c3) { // Caso 2 en línea
            if (c1 == 'O' || c2 == 'O' || c3 == 'O') {
                puntuacion += 10;
            }
            if (c1 == 'X' || c2 == 'X' || c3 == 'X') {
                puntuacion -= 10;
            }
        }
    
        // Caso 1 en línea
        if (c1 == 'O' || c2 == 'O' || c3 == 'O') {
            puntuacion += 1;
        }
        if (c1 == 'X' || c2 == 'X' || c3 == 'X') {
            puntuacion -= 1;
        }
    
        return puntuacion;
    }
}
