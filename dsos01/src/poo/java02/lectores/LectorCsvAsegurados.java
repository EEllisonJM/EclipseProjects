package poo.java02.lectores;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import poo.java02.pojos.Asegurado;

public class LectorCsvAsegurados {

	private static enum CabecerasArchivoCSV {
		id, nombre, apellido_paterno, apellido_materno, fecha_nacimiento, seguro_social
	}

	public static List<Asegurado> parsear(Path ubicacionArchivo) throws IOException {
		List<Asegurado> asegurados = new ArrayList<>();
		Reader lectorArchivo = new FileReader(ubicacionArchivo.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.withFirstRecordAsHeader()
				.withHeader(CabecerasArchivoCSV.class).parse(lectorArchivo);

		for (CSVRecord registro : registros) {
			String id = registro.get("id");
			String nombre = registro.get("nombre");
			String apellido_paterno = registro.get("apellido_paterno");
			String apellido_materno = registro.get("apellido_materno");

			ZonedDateTime fecha_nacimiento = ZonedDateTime.parse(registro.get("fecha_nacimiento"));
			Long numero_social = Long.parseLong(registro.get("seguro_social"));

			Asegurado asegurado = new Asegurado(id, nombre, apellido_paterno, apellido_materno, fecha_nacimiento,
					numero_social);
			
			asegurados.add(asegurado);
		}
		return asegurados;
	}
}