package dsos03;

/*Creacion de un archivo
 * */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*Cabecera/conversion
 * ====================
 * java -jar proyecto.jar -i entrada.csv -o salida.csv
 * java -jar proyecto.jar -o salida.csv -i entrada.csv
 * ''''''''''''''
 * [java -jar programa.jar -i entrada.csv -o salida.csv]
	positivo negativo
	redondear	
	siguiente caracter
	cambio cadana de caracteres - Mayuscula minuscula 
*/
public class Main {
	public static void main(String[] args) {
		String nombreArchivo = "";
		String salidaArchivo = "";
		if (parametrosValidos(args) == true) {
			if (args[0].equals("-i")) {
				nombreArchivo = args[1];
				salidaArchivo = args[3];

			} else {
				nombreArchivo = args[3];
				salidaArchivo = args[1];
			}

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
				CSVHandler csvHandler = new CSVHandler();
				csvHandler.leerContenidoArchivo(ubicacionArchivoLeer);
				csvHandler.escrbirArchivoConvertido(salidaArchivo);

			} catch (IOException e) {
				System.out.println("Error: el archivo no ha podido ser leído o escrito correctamente.");
				return;
			}
		} else {
			System.out.println("\nFavor de ingresar los parametros correspondientes");
			System.out.println("java -jar proyecto.jar -i entrada.csv -o salida.csv");
			System.out.println("java -jar proyecto.jar -o salida.csv -i entrada.csv");
		}
	}

	static boolean parametrosValidos(String[] args) {
		if (args.length == 4) {
			return true;
		}
		return false;
	}
}