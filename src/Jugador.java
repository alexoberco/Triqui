class Jugador {
    private final char simbolo;
    private final boolean esIa;

    public Jugador(char simbolo, boolean esIa) {
        this.simbolo = simbolo;
        this.esIa = esIa;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public boolean esIa() {
        return esIa;
    }
}