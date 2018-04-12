package test;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVHandler {
	String texto = "";
	private int errorFila;

	public void write(String fileName) throws IOException {
		String str = "Hello";
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		writer.write(str);
		writer.close();
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
				/* = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = = */
			}
			errorFila++;
		}
		return true;
	}

	Object asignarClase(String valor) {
		/* c(Boleanos) */
		if (valor.toLowerCase() == "true") {
			return true;
		}
		if (valor.toLowerCase() == "false") {
			return false;
		}
		try {			
			Integer.parseInt(valor);			
			if(puntoEncontrado(valor)) {
				/* b(numero de doble precision): */
				return Float.parseFloat(valor);
			}
			/* a(numeroEntero) */			
			return Integer.parseInt(valor); 

		} catch (NumberFormatException nfe) {// No Numero
			if (valor.length() == 1) {
				return valor.charAt(0);/* d(caracter) */
			} else {/* e(cadena de caracteres) */
				return valor;
			}
		}
	}

	boolean puntoEncontrado(String v) {
		for (int i = 0; i < v.length(); i++) {
			if (v.charAt(i) == '.') {
				return true;
			}
		}
		return false;
	}
}
