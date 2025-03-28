package Farkle;
//Cambio para prueba.
import java.awt.Font;

import javax.swing.*;

public class Menu {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JButton inicio = new JButton("Jugar");
        JLabel label = new JLabel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,250);
        frame.setResizable(false);

        label.setText("FARKLE");
        label.setFont(new Font("MV Boli",Font.PLAIN,50));
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        

        inicio.addActionListener(e -> IniciarJuego(frame));
        inicio.setBounds(200,150,100,25);
        inicio.setFocusable(false);
        inicio.setVerticalAlignment(JButton.BOTTOM);
        inicio.setHorizontalAlignment(JButton.CENTER);

        frame.add(inicio);
        frame.add(label);
        frame.setVisible(true);
    }

    public static void IniciarJuego(JFrame frame)
    {
        String a = JOptionPane.showInputDialog("Ingrese la cantidad de Jugadores: ");
        if(a == null)
        {
            JOptionPane.showMessageDialog(null, "Cancelaste la Operacion", "Cancelada", JOptionPane.WARNING_MESSAGE);
        } else {

            Integer jugadores = Integer.parseInt(a); 
            JuegoFarkle farkle = new JuegoFarkle(jugadores);  
        }
        frame.dispose();

    }

    
}
