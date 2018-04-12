package dsos04;

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
	List<Asegurado> asegurados = new ArrayList<Asegurado>();

	List<Asegurado> leerContenidoArchivo(Path ubicacionArchivoLeer, char delimitador) throws IOException {
		/* Accessing column values by index */
		Reader lectorArchivo = new FileReader(ubicacionArchivoLeer.toString());
		Iterable<CSVRecord> registros = CSVFormat.newFormat(delimitador).parse(lectorArchivo);
		for (CSVRecord registro : registros) {
			String cabecera[] = new String[registro.size()];
			if (registro.get(0).equals("nombre")) {// Pasar la cabecera
				for (int i = 0; i < cabecera.length; i++) {
					cabecera[i] = registro.get(i);
				}
			} else {
				asegurados.add(new Asegurado(//
						registro.get(0), // Nombre
						registro.get(1), // Apellido Paterno
						registro.get(2), // Apellido Materno
						registro.get(3), // Fecha Nacimiento
						registro.get(4), // Fecha ingreso
						registro.get(5), // Subdelegacion
						registro.get(6), // Consecutivo
						/* registro.get(7) -> NSS -> Se ha de generar */
						registro.get(8)));// TarjetaCredito
			}
		}
		return asegurados;
	}

	void escrbirArchivoConvertido(String fileName) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withIgnoreHeaderCase());) {
			csvPrinter.printRecords("nombre", "apellido_paterno", "apellido_materno", "fecha_nacimiento",
					"fecha_ingreso_imss", "subdelegacion_imss", "consecutivo_inscripcion_imss", "nss",
					"tarjeta_credito");
			for (int i = 0; i < asegurados.size(); i++) {
				csvPrinter.printRecord(asegurados.get(i).getNombre());
				csvPrinter.printRecord(asegurados.get(i).getApellido_paterno());
				csvPrinter.printRecord(asegurados.get(i).getApellido_materno());
				csvPrinter.printRecord(asegurados.get(i).getFecha_nacimiento());
				csvPrinter.printRecord(asegurados.get(i).getFecha_ingreso_imss());
				csvPrinter.printRecord(asegurados.get(i).getSubdelegacion_imss());
				csvPrinter.printRecord(asegurados.get(i).getConsecutivo_inscripcion_imss());
				csvPrinter.printRecord(asegurados.get(i).getNss());
				csvPrinter.printRecord(asegurados.get(i).getTarjetaCredito());
			}

			csvPrinter.flush();
			csvPrinter.close();
		}
	}
}