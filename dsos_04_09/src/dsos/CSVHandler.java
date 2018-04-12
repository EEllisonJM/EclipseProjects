package dsos;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class CSVHandler {
	List<Alumno> alumnos = new ArrayList<Alumno>();
	int count=0;

	int cantidadContenidoArchivo(Path ubicacionArchivoLeer, char delimitador) throws IOException {
		/* Accessing column values by index */
		Reader lectorArchivo = new FileReader(ubicacionArchivoLeer.toString());
		Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
		for (CSVRecord registro : registros) {
			/*Working with Headers*/
			String cabecera[] = new String[registro.size()];
			if (registro.get(0).equals("nombre")) {// First header value
				for (int i = 0; i < cabecera.length; i++) {
					cabecera[i] = registro.get(i);
				}
			} else {
				count++;//Ir contando			
			}
		}
		return count;
	}
	
	List<Alumno> leerContenidoArchivo(Path ubicacionArchivoLeer, char delimitador) throws IOException {
		/* Accessing column values by index */
		Reader lectorArchivo = new FileReader(ubicacionArchivoLeer.toString());
		Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
		for (CSVRecord registro : registros) {
			/*Working with Headers*/
			String cabecera[] = new String[registro.size()];
			if (registro.get(0).equals("nombre")) {// First header value
				for (int i = 0; i < cabecera.length; i++) {
					cabecera[i] = registro.get(i);
				}
			} else {
				alumnos.add(new Alumno(//
						registro.get(0), // Nombre
						registro.get(1), // Apellido Paterno
						registro.get(2))); // Apellido Materno			
			}
		}
		return alumnos;
	}

	void escrbirArchivoConvertido(String fileName) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withIgnoreHeaderCase());) {
			csvPrinter.printRecords("nombre", "apellido_paterno", "apellido_materno");
			for (int i = 0; i < alumnos.size(); i++) {
				csvPrinter.printRecord(alumnos.get(i).getNombre());
				csvPrinter.printRecord(alumnos.get(i).getApellidoPaterno());
				csvPrinter.printRecord(alumnos.get(i).getApellidoMaterno());
			}
			csvPrinter.flush();
			csvPrinter.close();
		}
	}
}