package Farkle;

public class Jugador {
    int puntos = 0;
    int puntosAcumulados = 0;
    Jugador()
    {


    }

    public void setPuntos(int nPuntos)
    {
        puntos = nPuntos;
    }

    public int getPuntos()
    {
        return puntos;
    }

    public void sumarAcumulados()
    {
        puntos += puntosAcumulados;
    }

    public int getAcumulados()
    {
        return puntosAcumulados;
    }

    public void setAcumulados(int nacum)
    {
        puntosAcumulados = nacum;
    }

}
