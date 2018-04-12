package dsos02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//-a archivos.csv -p
//-a archivos.csv -i
//-a archivos.csv -d
//-a archivos.csv -c
//[-a archivos.csv] -p -d siempre junto
//-a archivos.csv -p -c
//-a archivos.csv -p -c
public class Main {

	public static void main(String[] args) {
		String nombreArchivo = "";
		if (args.length == 4 || args.length == 3 || args.length == 2) {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-a") && i < args.length) {
					nombreArchivo = args[i + 1];
				}
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
			try {
				LectorCsv lca = new LectorCsv();
				boolean resultado = lca.encuentra(ubicacionArchivo);

				if (resultado == true) {
					lca.parsear(ubicacionArchivo, args);
				}

			} catch (IOException e) {
				System.out.println("Error: el archivo no ha podido ser leído con exito.");
				return;
			}
		} else {
			System.out.println("Favor de ingresar los parametros correspondientes");
			System.out.println("Imprimir numeros pares e impares:");
			System.out.println("[-p|-i|-d|-c] -a archivos.csv");
			System.out.println("-p -a archivos.csv");
			System.out.println("-i -a archivos.csv");
		}
	}
}