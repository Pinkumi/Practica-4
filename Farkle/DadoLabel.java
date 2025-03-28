package Farkle;
import javax.swing.JLabel;

public class DadoLabel extends JLabel {
    DadoLabel(Dado dado)
    {
        this.setIcon(dado.getIcon());
        
    }
}
