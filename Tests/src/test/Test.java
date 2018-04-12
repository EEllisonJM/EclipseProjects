package test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

/*LocalDate => A date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03. */
public class Test {

	static LocalDate asignar(String date) {
		return LocalDate.parse(date);
		// return localDate;
	}

	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		
		for (int i = 0; i < 10000; i++) {
			
		}
		System.out.println(Instant.now().toEpochMilli());
	}
	/*
	 * String date = "2016-08-16"; LocalDate ld = asignar(date); DateTimeFormatter
	 * formatter = DateTimeFormatter.ofPattern("yy");
	 * System.out.println(ld.format(DateTimeFormatter.ofPattern("yy")));
	 * 
	 * }
	 */
	/*
	 * String a = "1.342121"; String b = "1.3";
	 * System.out.println(String.format("%.2f", Float.parseFloat(a)));
	 * System.out.println(String.format("%.2f", Float.parseFloat(b)));
	 * 
	 * float af = Float.parseFloat(asignarPuntoFlotante(String.format("%.2f",
	 * Float.parseFloat(a)))); float bf =
	 * Float.parseFloat(asignarPuntoFlotante(String.format("%.2f",
	 * Float.parseFloat(b))));
	 * 
	 * System.out.println(af);
	 * System.out.println(Float.valueOf(Float.parseFloat(b))); }
	 * 
	 * static String asignarPuntoFlotante(String v) { String aux = ""; for (int i =
	 * 0; i < v.length(); i++) { if (v.charAt(i) == ',') { aux += '.'; } else { aux
	 * += v.charAt(i); }
	 * 
	 * } return aux; }
	 */
}
