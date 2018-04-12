package test;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Object o=2.1;
		System.out.println(String.format("%.2f", o));
		System.out.println(o.getClass().getName());
		System.out.println(o.toString());
		o=false;
		System.out.println(o.getClass().getName());
		System.out.println(o.toString());
		o='h';
		System.out.println(o.getClass().getName());
		System.out.println(o.toString());
		o="Hola";
		System.out.println(o.getClass().getName());
		System.out.println(o.toString());

		// CSVHandler csvHandler = new CSVHandler();
		// String hola="ads";
		// Object a=true;
		// System.out.println(a.getClass());
		// csvHandler.write("archivos.csv");
		/*
		 * Path path=Paths.get("datos.csv"); if(csvHandler.archivoContieneNumeros(path)
		 * == true) { System.out.println("Numeros");
		 * 
		 * }
		 */

	}

}