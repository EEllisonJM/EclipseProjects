package poo.java02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import poo.java02.generadores.GeneradorHtmlAsegurados;
import poo.java02.lectores.LectorCsvAsegurados;
import poo.java02.pojos.Asegurado;
import poo.java02.visualizadores.VisualizadorSwingAsegurados;

public class Main {

	public static void main(String[] args) {

		String nombreArchivo = "archivos-csv/asegurados.csv";

		if (args.length == 1) {
			nombreArchivo = args[0];
		}

		if (nombreArchivo.isEmpty()) {
			System.out.println("Error: nombre de archivo no dado.");
			return;
		}

		Path ubicacionArchivo = Paths.get(nombreArchivo);
		boolean existeArchivo = Files.exists(ubicacionArchivo);

		if (!existeArchivo) {
			System.out.println("Error: el archivo no existe.");
			return;
		}

		List<Asegurado> asegurados;
		try {
			asegurados = LectorCsvAsegurados.parsear(ubicacionArchivo);
		} catch (IOException e) {
			System.out.println("Error: el archivo no ha podido ser leído con exito.");
			return;
		}

		String documentoHtml = GeneradorHtmlAsegurados.generar(asegurados);
		VisualizadorSwingAsegurados.visualizar(documentoHtml);

	}

}
