package dsos02;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
//shift + alt+ x
public class LectorCsv {
	String parametros[] = { "-a", "-p", "-i", "-d", "-c" };

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

	public void parsear(Path ubicacionArchivo, String args[]) throws IOException {
		Reader lectorArchivo = new FileReader(ubicacionArchivo.toString());
		Iterable<CSVRecord> registros = CSVFormat.RFC4180.parse(lectorArchivo);

		int linea = 0;

		for (CSVRecord registro : registros) {

			int arre[] = new int[registro.size()];
			for (int i = 0; i < registro.size(); i++) {
				try {
					arre[i] = Integer.parseInt(registro.get(i));
				} catch (Exception e) {
					System.out.println("Valor en linea " + (linea + 1) + " No es un numero, abortando mision...");
					System.out.println("Mision no cumplida");
				}
			}
			String aux="";
			for (int i = 0; i < parametros.length; i++) {
				for (int j = 0; j < args.length; j++) {
					if (parametros[i].equals(args[j])) {
						if(!aux.equals(parametros[i])) {
							caso(parametros[i], arre);
							aux=parametros[i];
						}						
						if (args.length == 2) {
							caso("todo", arre);
						}
					}
				}
			}
			linea++;
		}
	}

	void caso(String c, int arre[]) {
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
		case "todo":
			System.out.println("Sumatoria centenas: " + sumCentenas(arre));
			System.out.println("Sumatoria de pares: " + sumPares(arre));
			System.out.println("Sumatoria de impares: " + sumImpares(arre));
			System.out.println("Sumatoria decenas: " + sumDecenas(arre));
			System.out.println("Sumatoria centenas: " + sumCentenas(arre));
			System.out.println("");
			break;
		}
	}

	/*---------------------------------*/
	boolean esPar(int num) {
		if (Math.floorMod(num, 2) == 0) {
			return true;
		}
		return false;
	}

	boolean esDecena(int num) {
		if (num > 9 && num < 100) {
			return true;
		}
		return false;
	}

	boolean esCentena(int num) {
		if (num > 99 && num < 1000) {
			return true;
		}
		return false;
	}

	int sumPares(int arre[]) {
		int suma = 0;
		for (int i = 0; i < arre.length; i++) {
			if (esPar(arre[i])) {
				suma += arre[i];
			}
		}
		return suma;
	}

	int sumImpares(int arre[]) {
		int suma = 0;
		for (int i = 0; i < arre.length; i++) {
			if (!esPar(arre[i])) {
				suma += arre[i];
			}
		}
		return suma;
	}

	int sumDecenas(int arre[]) {
		int suma = 0;
		for (int i = 0; i < arre.length; i++) {
			if (esDecena(arre[i])) {
				suma += arre[i];
			}
		}
		return suma;
	}

	int sumCentenas(int arre[]) {
		int suma = 0;
		for (int i = 0; i < arre.length; i++) {
			if (esCentena(arre[i])) {
				suma += arre[i];
			}
		}
		return suma;
	}
}