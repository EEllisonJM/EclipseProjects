package dsos04;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private String mensaje = "1.Listar Asegurados\n2.Ordenar por Nombre\n3.Salir";
	private Scanner leer;
	private boolean salir = false;
	private Scanner aux;

	public Menu() {
	}

	void iniciarMenu(String ubicacionArchivo) throws IOException {
		CSVHandler csvHandler = new CSVHandler();
		Path ubicacionArchivoLeer = Paths.get(ubicacionArchivo);
		List<Asegurado> asegurados = csvHandler.leerContenidoArchivo(ubicacionArchivoLeer, '|');
		while (salir == false) {

			System.out.println(mensaje);
			leer = new Scanner(System.in);
			switch (leer.next()) {
			case "1":
				/* Mostrar Asegurado */
				int mostrar = 10;
				for (int i = 0; i < asegurados.size(); i++) {
					System.out.println(asegurados.get(i).toString());
					if (i == mostrar) {
						System.out.println("Next[n] - Esc[s]");
						aux = new Scanner(System.in);
						if (aux.next().equals("n")) {
							mostrar += 10;
						} else
							break;
					}

				}
				break;
			case "2":
				Collections.sort(asegurados, Asegurado.ordenarPorNombre);
				break;
			case "3":
				salir = true;
				System.out.println("Adios");
				break;
			default:
				System.out.println("Opcion no disponible, verifique");
			}
		}
	}
}