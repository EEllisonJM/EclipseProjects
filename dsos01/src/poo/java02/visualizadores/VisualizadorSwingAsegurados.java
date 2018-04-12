package poo.java02.visualizadores;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

public class VisualizadorSwingAsegurados {

	public static void visualizar(String documentoHtml) {
		JFrame ventanaPrincipal = new JFrame("Asegurados");
		ventanaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		JTextPane visualizador = new JTextPane();
		visualizador.setContentType("text/html");
		visualizador.setEditable(false);
		visualizador.setText(documentoHtml);

		JScrollPane scroll = new JScrollPane(visualizador);
		ventanaPrincipal.getContentPane().add(scroll);
		ventanaPrincipal.pack();
		ventanaPrincipal.setVisible(true);
	}
}
