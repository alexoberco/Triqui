class Tablero:
    def __init__(self):
        self.tablero = [[' ' for _ in range(3)] for _ in range(3)]

    def get_tablero(self):
        return self.tablero

    def realizar_movimiento(self, fila, columna, jugador):
        if self.tablero[fila][columna] == ' ':
            self.tablero[fila][columna] = jugador
            return True
        return False

    def hay_ganador(self):
        for i in range(3):
            if self.tablero[i][0] == self.tablero[i][1] == self.tablero[i][2] != ' ':
                return True
            if self.tablero[0][i] == self.tablero[1][i] == self.tablero[2][i] != ' ':
                return True
        if self.tablero[0][0] == self.tablero[1][1] == self.tablero[2][2] != ' ':
            return True
        if self.tablero[0][2] == self.tablero[1][1] == self.tablero[2][0] != ' ':
            return True
        return False

    def es_empate(self):
        return all(self.tablero[i][j] != ' ' for i in range(3) for j in range(3)) and not self.hay_ganador()
