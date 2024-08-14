import os
import platform
from jugador import Jugador
from tablero import Tablero
from minimax_algoritmo import Minimax

def limpiar_pantalla():
    # Limpia la pantalla dependiendo del sistema operativo
    if platform.system() == "Windows":
        os.system("cls")
    else:
        os.system("clear")

def mostrar_tablero(tablero):
    print("  0 1 2")
    print("  _ _ _")
    for i in range(3):
        print(f"{i}|", end="")
        for j in range(3):
            print(f"{tablero[i][j]}|", end="")
        print()
        print("  _ _ _")

def iniciar_juego():
    tablero = Tablero()
    jugador1 = Jugador('X', False)
    jugador2 = Jugador('O', True)
    minimax = Minimax()

    turno_jugador = True

    while not tablero.hay_ganador() and not tablero.es_empate():
        limpiar_pantalla()  # Limpiar la pantalla antes de mostrar el tablero
        mostrar_tablero(tablero.get_tablero())

        if turno_jugador:
            fila, columna = map(int, input("Ingresa tu movimiento (fila y columna con un espacio, ejemplo: 1 0, 0 2): ").split())
            if not tablero.realizar_movimiento(fila, columna, jugador1.simbolo):
                print("Movimiento no válido. Intenta de nuevo.")
                continue
        else:
            movimiento = minimax.calc_mejor_mov(tablero.get_tablero(), jugador2.simbolo)
            tablero.realizar_movimiento(movimiento[0], movimiento[1], jugador2.simbolo)

        turno_jugador = not turno_jugador

    limpiar_pantalla()  # Limpiar la pantalla antes de mostrar el tablero final
    mostrar_tablero(tablero.get_tablero())
    if tablero.hay_ganador():
        if not turno_jugador:
            print(f"{jugador1.simbolo} ha ganado!")
        else:
            print(f"{jugador2.simbolo} ha ganado!")
    else:
        print("¡Empate!")

if __name__ == "__main__":
    iniciar_juego()
