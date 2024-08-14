class Tablero {
    private final char[][] tablero = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    public char[][] getTablero() {
        return this.tablero;
    }

    public boolean realizarMovimiento(int fila, int columna, char jugador) {
        if (tablero[fila][columna] == ' ') {
            tablero[fila][columna] = jugador;
            return true;
        }
        return false;
    }

    public boolean hayGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] && tablero[i][0] != ' ') {
                return true;
            }
            if (tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] && tablero[0][i] != ' ') {
                return true;
            }
        }

        if (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] && tablero[0][0] != ' ') {
            return true;
        }
        return tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0] && tablero[0][2] != ' ';
    }

    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return !hayGanador();
    }
}