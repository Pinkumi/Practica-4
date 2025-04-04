package Farkle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;

public class JuegoFarkle {
    //frame
    JFrame frame = new JFrame();
    JLabel label;
    ArrayList<Dado> dados = new ArrayList<Dado>();
    ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    ArrayList<JLabel> datosJugadores = new ArrayList<>();

    //paneles
    JPanel panelTurno = new JPanel();
    JPanel panelPuntos = new JPanel();
    JPanel panelAcumulados = new JPanel();
    JPanel panelCentral = new JPanel();

    //botones
    JButton botonLanzar = new JButton();
    JButton botonBanca = new JButton();
    JButton botonBloquear = new JButton();

    //labels Dados
    JLabel labelDado1 = new JLabel();
    JLabel labelDado2 = new JLabel();
    JLabel labelDado3 = new JLabel();
    JLabel labelDado4 = new JLabel();
    JLabel labelDado5 = new JLabel();
    JLabel labelDado6 = new JLabel();
    JLabel tablaDValores = new JLabel();
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
        // todos los dados tienen un lambda dentro del addActionListener(),
        // de esta manera, al ser presionados, se llama a la funcion que la lambda apunta
        botonLanzar.addActionListener(a -> rollDados());
        botonLanzar.setText("Lanzar Dados");
        botonLanzar.setBounds(163,340,125,20);
        panelCentral.add(botonLanzar);
        botonLanzar.setEnabled(true);

        // boton de sumar puntos
        botonBanca.addActionListener(a -> AñadirAcumulados());
        botonBanca.setText("Sumar Puntos");
        botonBanca.setBounds(360,170,135,50);
        panelCentral.add(botonBanca);
        botonBanca.setEnabled(false);
        //boton de bloquear dados (tambien hace las comparaciones)
        botonBloquear.addActionListener(a -> bloquearDados());
        botonBloquear.setText("Bloquear Dados");
        botonBloquear.setBounds(360,70,135,50);
        panelCentral.add(botonBloquear);
        botonBloquear.setEnabled(false);

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
        // ImageIcon iconTabla = new ImageIcon("Farkle/ForFarkle/Sprite-0001.png");

        //poner su ubicacion
        int index = 0;
        for(int i = 0;i<2;i++){
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

        
        // ponerl la informacion de los jugadores
        for(int i = 0; i< jugadores.size();i++)
        {
           datosJugadores.add(new JLabel("El jugador "+ (i+1)+" tiene "+ jugadores.get(i).getPuntos()+" puntos")); 
        }
        for(int i = 0; i< datosJugadores.size();i++)
        {
            panelPuntos.add(datosJugadores.get(i));
        }


        //rollDados();
        for(int i = 0; i< checkBoxs.size();i++)
        {
            checkBoxs.get(i).setEnabled(false);
        }
        //Labels de informacion
        turnoJugador.setText("Jugador: ");
        turnoJugador.setForeground(Color.WHITE);
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


    // esta funcion lanza los dados y revisa si se obtuvo escalera o Farkle
    public void rollDados()
    {
        actualizarCheckboxes();
        //activar checks q se puedan activar y no estan bloqueados
        checkBoxs.stream().filter( a-> !a.isEnabled() && !a.isSelected()).
                forEach(a-> a.setEnabled(true));
        // este lambda lanza todos los dados que no esten bloqueados
        dados.stream().filter(a -> !a.isLocked()).forEach(a -> a.lanzar());

        //region Codigo para forzar escalera
//        for(int i = 0; i<dados.size();i++){
//            dados.get(i).setValor(i+1);
//        }
        //endregion

        // esta lambda es para debug
        dados.stream().forEach(a -> System.out.println(a.getValor()));
        labelDado1.setIcon(dados.get(0).getIcon());
        labelDado2.setIcon(dados.get(1).getIcon());
        labelDado3.setIcon(dados.get(2).getIcon());
        labelDado4.setIcon(dados.get(3).getIcon());
        labelDado5.setIcon(dados.get(4).getIcon());
        labelDado6.setIcon(dados.get(5).getIcon());
        botonLanzar.setEnabled(false);
        botonBloquear.setEnabled(true);
        botonBanca.setEnabled(true);

        if(Escalera() == 1500)
        {
            JOptionPane.showMessageDialog(null, "ESCALERA!!", "Farkle",JOptionPane.INFORMATION_MESSAGE);
            jugadores.get(turnoQuien).setAcumulados(jugadores.get(turnoQuien).getAcumulados()+1500);
            rollDados();
            botonLanzar.setEnabled(true);
        }

        if(Farkled())
        {
            JOptionPane.showMessageDialog(null, "FARKLE!!!", "Farkle",JOptionPane.INFORMATION_MESSAGE);
            jugadores.get(turnoQuien).setAcumulados(0);
            botonLanzar.setEnabled(false);
            botonBloquear.setEnabled(false);
        }
        actualizarInformacion();
        frame.setVisible(true);
    }

        // Este añade los puntos acumulados al puntaje real del jugador actual
    public void AñadirAcumulados()
    {
        if(jugadores.get(turnoQuien).getAcumulados() == 0)
        {
            JOptionPane.showMessageDialog(null,"No gano puntos :(","Farkle",JOptionPane.INFORMATION_MESSAGE);
        } else {
            jugadores.get(turnoQuien).sumarAcumulados();
            jugadores.get(turnoQuien).setAcumulados(0);
        }
        actualizarInformacion();
        if(jugadores.get(turnoQuien).getPuntos() >= 10000)
        {   int botonSalir = JOptionPane.YES_NO_OPTION;
            JOptionPane.showMessageDialog(null, "El Jugador "+ (turnoQuien+1) +" ha ganado!","Farkle", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(null, "Desea seguir jugando? ","Farkle",botonSalir) == JOptionPane.YES_OPTION){
                //Salir o seguir jugando
                reiniciarPartida();
                return;
            }else{
                System.exit(0);
            }
            frame.dispose();
        }

        turnoQuien += 1;
        if(turnoQuien > jugadores.size()-1)
        {
            turnoQuien = 0;
        }

        actualizarInformacion();


        for(int i = 0; i<dados.size();i++)
        {
            dados.get(i).setLocked(false);
            dados.get(i).valid(false);
            checkBoxs.get(i).setEnabled(true);
            checkBoxs.get(i).setSelected(false);
            botonLanzar.setEnabled(true);
        }
        botonBloquear.setEnabled(false);
        rollDados();
    }

    // este suma los puntos de las jugadas validas dentro de los dados seleccionados
    // al puntaje acumulado deñ jugador y bloquea los dados validos para no volver a ser usados
    public void bloquearDados()
    {
        actualizarCheckboxes();
        jugadores.get(turnoQuien).setAcumulados(jugadores.get(turnoQuien).getAcumulados() + tresDeUnTipo());
        jugadores.get(turnoQuien).setAcumulados(jugadores.get(turnoQuien).getAcumulados() + cincosYUnos());

        actualizarInformacion();
        desactivarCheckboxInvalidas();
        disableCheckBoxes();
        actualizarCheckboxes();

        //revisar si no se obtuvo un hotDice
        int checkHot = 0;
        for(int i = 0; i < dados.size();i++)
        {
            if(checkBoxs.get(i).isSelected())
            {
                checkHot ++;
            }
        }

        if(checkHot == 6)
        {
            JOptionPane.showMessageDialog(null, "HOTDICE!!","Farkle",JOptionPane.INFORMATION_MESSAGE);
            jugadores.get(turnoQuien).setAcumulados(jugadores.get(turnoQuien).getAcumulados() + 1000);
            botonBloquear.setEnabled(false);
            for(int i = 0; i< checkBoxs.size();i++)
            {
                checkBoxs.get(i).setEnabled(false);
                checkBoxs.get(i).setSelected(false);
            }
        }


        botonLanzar.setEnabled(true);
    }


    public void actualizarCheckboxes()
    {
        //region checkboxes sin arraylist
//        dados.get(0).setLocked(dado1Check.isSelected());
//        dados.get(1).setLocked(dado2Check.isSelected());
//        dados.get(2).setLocked(dado3Check.isSelected());
//        dados.get(3).setLocked(dado4Check.isSelected());
//        dados.get(4).setLocked(dado5Check.isSelected());
//        dados.get(5).setLocked(dado6Check.isSelected());
        //endregion

        for(int i = 0;i<dados.size();i++)
        {
            dados.get(i).setLocked(checkBoxs.get(i).isSelected());
        }
    }

    public void desactivarCheckboxInvalidas() //Esta tambien se tiene que cambiar por la funcion.
    {
        //region check validaciones sin array
//        dado1Check.setSelected(dados.get(0).isAValidPlay());
//        dado2Check.setSelected(dados.get(1).isAValidPlay());
//        dado3Check.setSelected(dados.get(2).isAValidPlay());
//        dado4Check.setSelected(dados.get(3).isAValidPlay());
//        dado5Check.setSelected(dados.get(4).isAValidPlay());
//        dado6Check.setSelected(dados.get(5).isAValidPlay());
        // endregion

        // revisar que sea jugada valida y no este seleccionado

        // cambiar este metodo por otro
        for(int i = 0; i<checkBoxs.size();i++)
        {
            checkBoxs.get(i).setSelected(dados.get(i).isAValidPlay() && dados.get(i).isLocked());
        }

    }

    // este metodo es llamado una vez las chechboxes que no son jugadas fueron desSeleccionadas 
    // automaticamente para evitar errores, luego bloquea las checkboxes para no ser deseleccionadas
    public void disableCheckBoxes()
    {
        for(int i = 0;i<checkBoxs.size();i++)
        {
            checkBoxs.get(i).setEnabled(!checkBoxs.get(i).isSelected());
        }
    }



    // actaulizar la informacion en pantalla
    public void actualizarInformacion()
    {

        turnoJugador.setText("Jugador: "+(turnoQuien+1));
        puntosJugador.setText("El jugador tiene: "+jugadores.get(turnoQuien).getPuntos()+" puntos");
        puntosAcumulados.setText("En esta ronda lleva "+jugadores.get(turnoQuien).getAcumulados()+" puntos");
        
        // aqui se pone la informacion de los puntos de los jugadores:
        for(int i = 0; i<jugadores.size();i++)
        {
            datosJugadores.get(i).setText("El jugador "+(i+1)+" tiene "+jugadores.get(i).getPuntos()+" puntos");
            
        }
        frame.setVisible(true);

    }

    // Revisar la jugada 3 of a kind de las reglas estandar de Farkle
    public int tresDeUnTipo() {
        int contador = 0;
        int valorRepetido = -1;  // Para almacenar el valor de los dados repetidos

        // Primero, vamos a buscar si hay tres dados del mismo valor
        for (int i = 0; i < dados.size(); i++) {
            if (dados.get(i).isLocked() && !dados.get(i).isAValidPlay()) {
                for (int j = i + 1; j < dados.size(); j++) {
                    if (dados.get(i).getValor() == dados.get(j).getValor() && dados.get(j).isLocked() && !dados.get(j).isAValidPlay()) {
                        for (int k = j + 1; k < dados.size(); k++) {
                            if (dados.get(i).getValor() == dados.get(k).getValor() && dados.get(k).isLocked() && !dados.get(k).isAValidPlay()) {
                                // Se encontramos tres dados con el mismo valor
                                valorRepetido = dados.get(i).getValor();
                                contador = 3;  // Marcamos que hemos encontrado tres dados

                                // Ahora, marcamos los tres dados como válidos
                                dados.get(i).valid(true);
                                dados.get(j).valid(true);
                                dados.get(k).valid(true);
                                break;
                            }
                        }
                    }
                    if (contador == 3) break; // Si ya hemos encontrado los tres dados, rompemos el ciclo
                }
            }
            if (contador == 3) break; // Si ya hemos encontrado los tres dados, rompemos el ciclo
        }

        // Si encontramos tres dados del mismo valor, devolvemos los puntos
        if (contador == 3 && valorRepetido == 1) {
            return 1000;
        } else if (contador == 3) {
            return valorRepetido * 100;
        }

        // Si no encontramos tres dados, devolvemos 0
        return 0;
    }




    // esta funcion asigna los puntos si seleccionaste un cinco o un uno (3 of a kind tiene prioridad)
    public int cincosYUnos()
    {
        int puntos = 0;
        for(int i = 0; i< dados.size();i++)
        {
            if(dados.get(i).getValor() == 5 && dados.get(i).isLocked() && !dados.get(i).isAValidPlay())
            {
                dados.get(i).valid(true);
                checkBoxs.get(i).setEnabled(false);
                puntos += 50;

            } else if(dados.get(i).getValor() == 1 && dados.get(i).isLocked() && !dados.get(i).isAValidPlay())
            {
                dados.get(i).valid(true);
                checkBoxs.get(i).setEnabled(false);
                puntos += 100;

            }

        }

        return puntos;
    }

    // esta es llamada solo al lanzar los dados
    public int Escalera()
    {
        // esto solo es posible con todos los dados, por lo tanto primero revisamos eso
        for(int i = 0; i<dados.size();i++)
        {
            if(checkBoxs.get(i).isSelected() == true)
            {
                return 0;
            }
        }

        // una vez descartamos eso, podemos pasar a revisar la escalera
        // filta los dados no bloqueados y obtener un Stream de sus valores
        List<Integer> valores = dados.stream()
                .map(dado -> dado.valor)       // Extrae el valor de cada dado
                .collect(Collectors.toList());  // Recoge los resultados en una lista

        // Si hay menos de 6 dados no bloqueados, no puede haber escalera
        if (valores.size() != 6) {
            return 0;
        }

        // Ordenar los valores
        valores.sort(Comparator.naturalOrder());

        // <Comprobar> si los valores son consecutivos
        for (int i = 1; i < valores.size(); i++) {
            if (valores.get(i) != valores.get(i - 1) + 1) {
                return 0;  // Si no son consecutivos, no es una escalera
            }
        }

        // Si pasamos todo lo anterior, si es una escalera

        return 1500;
    }

    // revisa si no hay nada de los anteriores (sin afectar los dados como las otras si hacen)
    public boolean Farkled()
    {
        boolean farkled = true;
        // hay almenos algun 1 o un 5
        for(int i = 0; i<dados.size();i++)
        {
            if(dados.get(i).getValor() == 1 && !dados.get(i).isLocked())
            {
                farkled = false;
            } else if (dados.get(i).getValor() == 5 && !dados.get(i).isLocked()) {
                farkled = false;
            }
        }

        // hay almenos una secuencia de 3
        // Usamos un HashMap para contar las veces que aparece cada valor (sin contar los dados bloqueados)
        HashMap<Integer, Integer> contadorValores = new HashMap<>();

        // Recorremos todos los dados
        for (Dado dado : dados) {
            // Si el dado no está bloqueado
            if (!dado.isLocked()) {
                // Incrementamos el contador para el valor del dado
                contadorValores.put(dado.getValor(), contadorValores.getOrDefault(dado.getValor(), 0) + 1);

                // Si encontramos que algún valor se repite al menos 3 veces
                if (contadorValores.get(dado.getValor()) >= 3) {
                    farkled = false;  // Si se encuentra, retornamos true
                }
            }
        }

        return farkled;
    }
    public void reiniciarPartida(){
        for(int i =0; i<jugadores.size();i++){
            jugadores.get(i).setPuntos(0);
            jugadores.get(i).setAcumulados(0);
            turnoQuien = 0;
        }

        botonBloquear.setEnabled(false);
        botonBanca.setEnabled(false);
        for(int i = 0; i<checkBoxs.size();i++){
            checkBoxs.get(i).setSelected(false);
            checkBoxs.get(i).setEnabled(false);
        }
//        for(int i = 0; i<dados.size();i++){
//            dados.get(i).setValor(1);
//            dados.get(i).actualizarIcon();
//        }
//        labelDado1.setIcon(dados.get(0).getIcon());
//        labelDado2.setIcon(dados.get(1).getIcon());
//        labelDado3.setIcon(dados.get(2).getIcon());
//        labelDado4.setIcon(dados.get(3).getIcon());
//        labelDado5.setIcon(dados.get(4).getIcon());
//        labelDado6.setIcon(dados.get(5).getIcon());

        actualizarInformacion();

    }

}