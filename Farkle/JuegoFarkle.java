package Farkle;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.*;

public class JuegoFarkle {
    //frame
    JFrame frame = new JFrame();
    JLabel label;
    ArrayList<Dado> dados = new ArrayList<Dado>();
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    //paneles
    JPanel panelTurno = new JPanel();
    JPanel panelPuntos = new JPanel();
    JPanel panelAcumulados = new JPanel();
    JPanel panelCentral = new JPanel();

    //botones
    JButton botonLanzar = new JButton();
    JButton botonBanca = new JButton();

    //labels Dados
    JLabel labelDado1 = new JLabel();
    JLabel labelDado2 = new JLabel();
    JLabel labelDado3 = new JLabel();
    JLabel labelDado4 = new JLabel();
    JLabel labelDado5 = new JLabel();
    JLabel labelDado6 = new JLabel();
    ArrayList<JLabel> labelsDados = new ArrayList<JLabel>();

    // Checkboxes para loquear dados
    /* 
    JCheckBox dado1Check = new JCheckBox();
    JCheckBox dado2Check = new JCheckBox();
    JCheckBox dado3Check = new JCheckBox();
    JCheckBox dado4Check = new JCheckBox();
    JCheckBox dado5Check = new JCheckBox();
    JCheckBox dado6Check = new JCheckBox();
    */
    ArrayList<JCheckBox> checkBoxs = new ArrayList<>();


    // Labels para la informacion Actual
    JLabel turnoJugador = new JLabel();
    JLabel puntosJugador = new JLabel();
    JLabel puntosAcumulados = new JLabel();

    Integer turnoQuien = 0;

    JuegoFarkle(Integer NPlayers)
    {
        
        for(int i=0;i<NPlayers; i++)
        {
            jugadores.add(new Jugador());
        }
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(768,480);
        frame.setLayout(null);


        // creacion de dados
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
        Dado dado3 = new Dado();
        Dado dado4 = new Dado();
        Dado dado5 = new Dado();
        Dado dado6 = new Dado();

        
        dados.add(dado1);
        dados.add(dado2);
        dados.add(dado3);
        dados.add(dado4);
        dados.add(dado5);
        dados.add(dado6);


        // poner las cosas en orden y mostrarlas en pantalla
        panelTurno.setBounds(0,0,192,60);
        panelTurno.setBackground(Color.DARK_GRAY);

        panelPuntos.setBounds(0,60,192,210);
        panelPuntos.setBackground(Color.GRAY);

        panelAcumulados.setBounds(0, 270, 192, 210);
        panelAcumulados.setBackground(Color.LIGHT_GRAY);

        panelCentral.setLayout(null);
        panelCentral.setBounds(192,0,576,480);
        panelCentral.setBackground(Color.green);

        // boton de lanzar dados
        botonLanzar.addActionListener(a -> rollDados());
        botonLanzar.setText("Lanzar Dados");
        botonLanzar.setBounds(163,340,125,20);
        panelCentral.add(botonLanzar);

        // boton de sumar puntos
        botonBanca.addActionListener(a -> sumarPuntos());
        botonBanca.setText("Sumar Puntos");
        botonBanca.setBounds(360,170,135,50);
        panelCentral.add(botonBanca);

        // labels de los dados
        labelsDados.add(labelDado1);
        labelsDados.add(labelDado2);
        labelsDados.add(labelDado3);
        labelsDados.add(labelDado4);
        labelsDados.add(labelDado5);
        labelsDados.add(labelDado6);

        
        labelDado1.setIcon(dados.get(0).getIcon());
        labelDado2.setIcon(dados.get(1).getIcon());
        labelDado3.setIcon(dados.get(2).getIcon());
        labelDado4.setIcon(dados.get(3).getIcon());
        labelDado5.setIcon(dados.get(4).getIcon());
        labelDado6.setIcon(dados.get(5).getIcon());

        //poner su ubicacion
        int index = 0;
        for(int i = 0;i<2;i++)
        {
            for(int j = 0;j<3;j++)
            {
                labelsDados.get(index).setBounds(64 + 200*i,64 + 100*j,64,64);
                index ++;
            }
        }
        
        labelsDados.stream().forEach(a -> panelCentral.add(a));

        // colocar las checkboxes
        int crear = 0;
        for(int i = 0;i<2;i++)
        {
            for(int j = 0;j<3;j++)
            {
                checkBoxs.add(new JCheckBox());
                checkBoxs.get(crear).setBounds(128+(200*i),64+(100*j),25,25);
                crear ++;
            }
        }
        /* 
        dado1Check.setBounds(128,64,25,25);
        dado2Check.setBounds(128,164,25,25);
        dado3Check.setBounds(128,264,25,25);
        dado4Check.setBounds(328,64,25,25);
        dado5Check.setBounds(328,164,25,25);
        dado6Check.setBounds(328,264,25,25);
        */
        
        for(int i = 0;i<checkBoxs.size();i++)
        {
            panelCentral.add(checkBoxs.get(i));
        }
        /* 
        panelCentral.add(dado1Check);
        panelCentral.add(dado2Check);
        panelCentral.add(dado3Check);
        panelCentral.add(dado4Check);
        panelCentral.add(dado5Check);
        panelCentral.add(dado6Check);
        */

        //Labels de informacion
        turnoJugador.setText("Jugador: ");
        panelTurno.add(turnoJugador);

        puntosJugador.setText("");
        panelPuntos.add(puntosJugador);

        puntosAcumulados.setText("");
        panelAcumulados.add(puntosAcumulados);


        frame.add(panelTurno);
        frame.add(panelPuntos);
        frame.add(panelAcumulados);
        frame.add(panelCentral);
        frame.setVisible(true);

    }



    public void rollDados()
    {
        actualizarCheckboxes();
        dados.stream().filter(a -> !a.isLocked()).forEach(a -> a.lanzar());
        dados.stream().forEach(a -> System.out.println(a.getValor()));
        labelDado1.setIcon(dados.get(0).getIcon());
        labelDado2.setIcon(dados.get(1).getIcon());
        labelDado3.setIcon(dados.get(2).getIcon());
        labelDado4.setIcon(dados.get(3).getIcon());
        labelDado5.setIcon(dados.get(4).getIcon());
        labelDado6.setIcon(dados.get(5).getIcon());
        actualizarInformacion();
        frame.setVisible(true);

    }

    // lambdas goes Brrrrrr!!!
    public void sumarPuntos()
    {
        actualizarCheckboxes();
        desactivarCheckboxInvalidas();
        actualizarCheckboxes();
        jugadores.get(turnoQuien).setPuntos(dados.stream().
        filter(a -> a.isLocked()).filter(a -> a.isAValidPlay()).map(a -> a.getValor()).
        reduce(0,(a,b) -> a+b)); 
        disableCheckBoxes();
    }

    public void actualizarCheckboxes()
    {
        /* 
        dados.get(0).setLocked(dado1Check.isSelected());
        dados.get(1).setLocked(dado2Check.isSelected());
        dados.get(2).setLocked(dado3Check.isSelected());
        dados.get(3).setLocked(dado4Check.isSelected());
        dados.get(4).setLocked(dado5Check.isSelected());
        dados.get(5).setLocked(dado6Check.isSelected());
        */
        for(int i = 0;i<dados.size();i++)
        {
            dados.get(i).setLocked(checkBoxs.get(i).isSelected());
        }
    }

    public void desactivarCheckboxInvalidas()
    {
        /* 
        dado1Check.setSelected(dados.get(0).isAValidPlay());
        dado2Check.setSelected(dados.get(1).isAValidPlay());
        dado3Check.setSelected(dados.get(2).isAValidPlay());
        dado4Check.setSelected(dados.get(3).isAValidPlay());
        dado5Check.setSelected(dados.get(4).isAValidPlay());
        dado6Check.setSelected(dados.get(5).isAValidPlay());
        */
        // revisar que sea jugada valida y no este seleccionado
        for(int i = 0; i<checkBoxs.size();i++)
        {
            checkBoxs.get(i).setSelected(dados.get(i).isAValidPlay() && dados.get(i).isLocked());
        }
    }

    public void turnos()
    {
            //actualizar la informacion al Jugador actual
            boolean jugadaTerminada = false;
            botonLanzar.setEnabled(false);
            botonLanzar.setEnabled(true);
            turnoQuien += 1;


    }

    public void disableCheckBoxes()
    {
        /* 
        dado1Check.setEnabled(!dado1Check.isSelected());
        dado2Check.setEnabled(!dado2Check.isSelected());
        dado3Check.setEnabled(!dado3Check.isSelected());
        dado4Check.setEnabled(!dado4Check.isSelected());
        dado5Check.setEnabled(!dado5Check.isSelected());
        dado6Check.setEnabled(!dado6Check.isSelected());
        */
        for(int i = 0;i<checkBoxs.size();i++)
        {
            checkBoxs.get(i).setEnabled(!checkBoxs.get(i).isSelected());
        }
    }


    

    public void actualizarInformacion()
    {
        turnoJugador.setText("Jugador: "+(turnoQuien+1));
        puntosJugador.setText("El jugador tiene: "+jugadores.get(turnoQuien).getPuntos()+" puntos");
        puntosAcumulados.setText("En esta ronda lleva "+jugadores.get(turnoQuien).getAcumulados()+" puntos");
        frame.setVisible(true);

    }

    // esta no hace nada
    public void controladorTurno()
    {
        int puntajeMasAlto = 0;
        while (puntajeMasAlto < 10000)
        {
            turnos();
        }
    }

    public void encontrarElPuntajeMasAlto()
    {

    }

}
