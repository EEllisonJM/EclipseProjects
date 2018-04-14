package dsos02;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/*[shift] + [alt] + [x] [j]*/
public class LectorCsv {
	List<Numero[]> numeros = new ArrayList<>();

	List<Numero[]> archivo(String nombreArchivo) {
		if (nombreArchivo.isEmpty()) {
			System.out.println("Error: nombre de archivo no dado.");
			return null;
		}
		Path ubicacionArchivo = Paths.get(nombreArchivo);
		boolean existeArchivo = Files.exists(ubicacionArchivo);

		if (!existeArchivo) {
			System.out.println("Error: el archivo no existe.");
			return null;
		}
		try {
			LectorCsv lca = new LectorCsv();
			boolean resultado = lca.encuentra(ubicacionArchivo);
			if (resultado == true) {
				return lca.parsear(ubicacionArchivo);
			}
		} catch (IOException e) {
			System.out.println("Error: el archivo no ha podido ser leído con exito.");
		}
		return null;
	}

	boolean encuentra(Path ubicacionArchivo) throws IOException {
		Reader lectorArchivo = new FileReader(ubicacionArchivo.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.parse(lectorArchivo);
		int linea = 0;
		for (CSVRecord registro : registros) {
			int arre[] = new int[registro.size()];
			for (int i = 0; i < registro.size(); i++) {
				try {
					arre[i] = Integer.parseInt(registro.get(i));
				} catch (Exception e) {
					System.out.println("Valor en linea " + (linea + 1) + " No es un numero");
					return false;
				}
			}
			linea++;
		}
		return true;
	}

	public List<Numero[]> parsear(Path ubicacionArchivo) throws IOException {
		Reader lectorArchivo = new FileReader(ubicacionArchivo.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.parse(lectorArchivo);

		for (CSVRecord registro : registros) {
			Numero arregloNumeros[] = new Numero[registro.size()];
			for (int i = 0; i < registro.size(); i++) {
				try {
					arregloNumeros[i] = new Numero(Integer.parseInt(registro.get(i)));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			numeros.add(arregloNumeros);
		}
		return numeros;
	}

	void caso(String c, Numero[] arre) {
		switch (c) {
		case "-p":
			System.out.println("Sumatoria de pares: " + sumPares(arre));
			break;

		case "-i":
			System.out.println("Sumatoria de impares: " + sumImpares(arre));
			break;
		case "-d":
			System.out.println("Sumatoria decenas: " + sumDecenas(arre));
			break;
		case "-c":
			System.out.println("Sumatoria centenas: " + sumCentenas(arre) + "\n");
			break;
		case "-all": // System.out.println(p[j].toString());

			System.out.println("Sumatoria centenas: " + sumCentenas(arre));
			System.out.println("Sumatoria de pares: " + sumPares(arre));
			System.out.println("Sumatoria de impares: " + sumImpares(arre));
			System.out.println("Sumatoria decenas: " + sumDecenas(arre));
			System.out.println("Sumatoria centenas: " + sumCentenas(arre));
			System.out.println("");
			break;
		default:
			System.out.println(Main.mensajeParametrosNoValidos());
			break;
		}
	}

	int sumPares(Numero[] numeros) {
		int suma = 0;
		for (int j = 0; j < numeros.length; j++) {
			if (Math.floorMod(numeros[j].getValor(), 2) == 0) {/* Par */
				suma += numeros[j].getValor();
			}
		}
		return suma;
	}

	int sumImpares(Numero[] numeros) {
		int suma = 0;
		for (int j = 0; j < numeros.length; j++) {
			if (Math.floorMod(numeros[j].getValor(), 2) != 0) {/* Impar */
				suma += numeros[j].getValor();
			}
		}
		return suma;
	}

	int sumDecenas(Numero numeros[]) {
		int suma = 0;
		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i].esDecena()) {
				suma += numeros[i].getValor();
			}
		}
		return suma;
	}

	int sumCentenas(Numero numeros[]) {
		int suma = 0;
		for (int i = 0; i < numeros.length; i++) {
			if (numeros[i].esCentena()) {
				suma += numeros[i].getValor();
			}
		}
		return suma;
	}
}