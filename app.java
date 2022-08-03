import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class app{
	public static void main(String[] args){
		// hacemos la ventana y la animacion
		JFrame ventana = new JFrame("Test animation");
		Dibujo dibujo = new Dibujo();

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container contenedor = ventana.getContentPane();
		// contenedor.setBackground(Color.BLACK);
		contenedor.add(dibujo);

		ventana.pack(); // hacemos la ventana lo suficientemente grande para el GUI
		ventana.setVisible(true);
		// ventana.getContentPane().setBackground(Color.YELLOW);
		dibujo.initialAnimation();

	}
}
