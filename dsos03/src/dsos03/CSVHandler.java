package dsos03;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVHandler {
	ArrayList<String[]> entradas = new ArrayList<String[]>();

	String texto = "";
	private int errorFila;

	void leerContenidoArchivo(Path ubicacionArchivoLeer) throws IOException {
		/* Accessing column values by index */
		Reader lectorArchivo = new FileReader(ubicacionArchivoLeer.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.parse(lectorArchivo);
		for (CSVRecord registro : registros) {
			String e[] = new String[registro.size()];
			for (int i = 0; i < registro.size(); i++) {
				/* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
				e[i] = (new Entrada(registro.get(i)).getValor());
				/* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
			}
			entradas.add(e);
		}
	}

	void escrbirArchivoConvertido(String fileName) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withIgnoreHeaderCase());) {
			for (int i = 0; i < entradas.size(); i++) {
				csvPrinter.printRecord((Object)entradas.get(i));
			}
			csvPrinter.flush();
		}
	}

	boolean archivoContieneNumeros(Path ubicacionArchivo) throws IOException {
		/* Accessing column values by index */
		Reader lectorArchivo = new FileReader(ubicacionArchivo.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.parse(lectorArchivo);		

		errorFila = 0;
		for (CSVRecord registro : registros) {
			int arre[] = new int[registro.size()];
			for (int i = 0; i < registro.size(); i++) {
				/* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
				try {
					arre[i] = Integer.parseInt(registro.get(i));
				} catch (NumberFormatException e) {
					System.out.println("Valor en linea " + (errorFila + 1) + " No es un numero");
					return false;
				}
				/* = = = = = = = = = = = = = = = = = = TarjetaCredito( = = = = = = = = = = = = = = = = */
			}
			errorFila++;
		}
		return true;
	}
}
