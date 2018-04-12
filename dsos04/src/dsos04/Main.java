package dsos04;

/*Creacion de un archivo
 * */
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
//java -jar proyecto -a datos.csv

/*Cabecera/conversion
 * Menu de operaciones- listar asegurado y salir
 * ASegurado, Operaciones con los asegurados, listar,buscar(nombre,apellido,...), agregar y editar, eliminar
 * ====================
 * java -jar proyecto -a datos.csv
 * ----------------
 * Mostrar por paginacion
 * visualizar asegurado en especifico
 * Ordenar por atributos(nom...,ap...,etc)
 * ----------------
 * 
*/
public class Main {
	public static void main(String[] args) throws IOException {
		iniciar(args);
	}

	static void iniciar(String[] args) {
		if (tamParametrosValido(args) == true) {
			if (args[0].equals("-db") || args[0].equals("-a")) {
				procesar(args[1]);
			} else {

			}

		} else {
			System.out.println("\nFavor de ingresar los parametros correspondientes");
			System.out.println("java -jar proyecto.jar -i entrada.csv -o salida.csv");
			System.out.println("java -jar proyecto.jar -o salida.csv -i entrada.csv");
		}
	}

	static void procesar(String nombreArchivo) {

		if (nombreArchivo.isEmpty()) {
			System.out.println("Error: nombre de archivo no dado.");
			return;
		}

		Path ubicacionArchivoLeer = Paths.get(nombreArchivo);
		boolean existeArchivo = Files.exists(ubicacionArchivoLeer);

		if (!existeArchivo) {
			System.out.println("Error: el archivo no existe.");
			return;
		}
		try {
			// CSVHandler csvHandler = new CSVHandler();
			// csvHandler.leerContenidoArchivo(ubicacionArchivoLeer, '|');
			Menu m = new Menu();
			m.iniciarMenu(nombreArchivo);
		} catch (IOException e) {
			System.out.println("Error: el archivo no ha podido ser leído o escrito correctamente.");
			return;
		}

	}

	static boolean tamParametrosValido(String[] args) {
		if (args.length == 2) {
			return true;
		}
		return false;
	}
}