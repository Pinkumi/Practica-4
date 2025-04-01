package Farkle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Dado {

        ImageIcon icon6 = new ImageIcon("Farkle/ForFarkle/Sprite-0001.png");
        ImageIcon icon5 = new ImageIcon("Farkle/ForFarkle/Sprite-0002.png");
        ImageIcon icon4 = new ImageIcon("Farkle/ForFarkle/Sprite-0003.png");
        ImageIcon icon3 = new ImageIcon("Farkle/ForFarkle/Sprite-0004.png");
        ImageIcon icon2 = new ImageIcon("Farkle/ForFarkle/Sprite-0005.png");
        ImageIcon icon1 = new ImageIcon("Farkle/ForFarkle/Sprite-0006.png");
        ImageIcon actualIcon = icon1;
        int valor = 1;
        Random rnd = new Random();
        boolean locked = false;
        boolean validPlay = false;
        boolean used = false;
    Dado()
    {
        
        
    }

    public void lanzar()
    {
        valor = rnd.nextInt(6)+1;
        actualizarIcon();
    }

    public void actualizarIcon()
    {
        switch(valor)
        {
            case 1:
                actualIcon = icon1;
                break;

            case 2:
                actualIcon = icon2;
                break;

            case 3:
                actualIcon = icon3;
                break;
            case 4:
                actualIcon = icon4;
                break;
            
            case 5:
                actualIcon = icon5;
                break;
             
            case 6:
                actualIcon = icon6;
                break;

        }
    }

    public ImageIcon getIcon()
    {
        return actualIcon;
    }

    public Integer getValor()
    {
        return valor;
    }

    public boolean isLocked()
    {
        return locked;
    }

    public void setLocked(boolean estado)
    {
        locked = estado;
    }

    public boolean isAValidPlay()
    {
        // puedes poner aqui la logica para saber si es una jugada valida (es 1 o 5 o pertenece a una serie)
        return validPlay;
    }

    public void valid(boolean set)
    {
        validPlay = set;
    }

    public void setUsed(boolean nUsed)
    {
        used = nUsed;
    }

}
