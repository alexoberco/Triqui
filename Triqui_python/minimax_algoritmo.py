class Minimax:
    def __init__(self):
        self.MAX_PROFUNDIDAD = 2

    def eval_tablero(self, tablero):
        puntuacion = 0
        for i in range(3):
            puntuacion += self.evaluar_linea(tablero[i][0], tablero[i][1], tablero[i][2])
            puntuacion += self.evaluar_linea(tablero[0][i], tablero[1][i], tablero[2][i])
        puntuacion += self.evaluar_linea(tablero[0][0], tablero[1][1], tablero[2][2])
        puntuacion += self.evaluar_linea(tablero[0][2], tablero[1][1], tablero[2][0])
        return puntuacion

    def evaluar_linea(self, c1, c2, c3):
        puntuacion = 0
        if c1 == c2 == c3:
            if c1 == 'O':
                puntuacion = 100
            elif c1 == 'X':
                puntuacion = -100
        elif c1 == c2 or c2 == c3 or c1 == c3:
            if c1 == 'O' or c2 == 'O' or c3 == 'O':
                puntuacion += 10
            if c1 == 'X' or c2 == 'X' or c3 == 'X':
                puntuacion -= 10
        if c1 == 'O' or c2 == 'O' or c3 == 'O':
            puntuacion += 1
        if c1 == 'X' or c2 == 'X' or c3 == 'X':
            puntuacion -= 1
        return puntuacion

    def minimax(self, tablero, profundidad, es_ia):
        puntaje = self.eval_tablero(tablero)
        if puntaje == 100 or puntaje == -100 or profundidad == 0 or not self.hay_movimientos(tablero):
            return puntaje

        if es_ia:
            mejor = -float('inf')
            for i in range(3):
                for j in range(3):
                    if tablero[i][j] == ' ':
                        tablero[i][j] = 'O'
                        mejor = max(mejor, self.minimax(tablero, profundidad - 1, False))
                        tablero[i][j] = ' '
            return mejor
        else:
            peor = float('inf')
            for i in range(3):
                for j in range(3):
                    if tablero[i][j] == ' ':
                        tablero[i][j] = 'X'
                        peor = min(peor, self.minimax(tablero, profundidad - 1, True))
                        tablero[i][j] = ' '
            return peor

    def calc_mejor_mov(self, tablero, jugador):
        mejor_valor = -float('inf')
        mejor_movimiento = [-1, -1]

        for i in range(3):
            for j in range(3):
                if tablero[i][j] == ' ':
                    tablero[i][j] = jugador
                    if jugador == 'O':
                        movimiento_valor = self.minimax(tablero, self.MAX_PROFUNDIDAD, False)
                    else:
                        movimiento_valor = self.minimax(tablero, self.MAX_PROFUNDIDAD, True)
                    tablero[i][j] = ' '
                    if movimiento_valor > mejor_valor:
                        mejor_movimiento = [i, j]
                        mejor_valor = movimiento_valor
        return mejor_movimiento

    def hay_movimientos(self, tablero):
        return any(tablero[i][j] == ' ' for i in range(3) for j in range(3))
