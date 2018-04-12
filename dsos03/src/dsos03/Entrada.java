package dsos03;

public class Entrada {
	private String valor;
	private Object clase;

	Entrada(String v) {
		this.valor = v;
		this.clase = asignarClase();
	}

	String getValor() {/* conversion */
		System.out.println(clase.getClass());
		/*
		 * a(numeroEntero): Positivo > Negativo | Negativo > Positivo
		 */
		if (clase.getClass().toString().equals("class java.lang.Integer")) {
			return Integer.parseInt(valor) * (-1) + "";
		}
		/*
		 * b(numeroDoblePrecision): Trunar/completar a 2 decimales => 7.345 > 7.34 |||
		 * 7.3 > 7.30
		 */
		if (clase.getClass().toString().equals("class java.lang.Float")) {
			String aux = String.format("%.2f", Float.parseFloat(valor)) + "";
			
			return asignarPuntoFlotante(aux);
		}
		/*
		 * c(Boleanos) => true > false | false > true
		 */
		if (clase.getClass().toString().equals("class java.lang.Boolean")) {
			if (valor.equals("true") || valor.equals("TRUE")) {
				return "false";
			}
			if (valor.equals("false") || valor.equals("FALSE")) {
				return "true";
			}
		}
		/*
		 * d(caracter) => Siguiente caracter del alfabeto a,b,c,d,...,x,y,z n > ñ z > a
		 */
		if (clase.getClass().toString().equals("class java.lang.Character")) {
			String arreCadenas = "abcdefghijklmnñopqrstuvwxyz";// 27
			for (int i = 0; i < arreCadenas.length(); i++) {
				if (valor.charAt(0) == arreCadenas.charAt(i)) {
					if (i == arreCadenas.length() - 1) {
						return arreCadenas.charAt(0) + "";
					} else {
						return arreCadenas.charAt(i + 1) + "";
					}

				}
			}
		}
		/*
		 * e(cadenaCaracteres) => Conversion a minuscula/mayuscula de los siguientes
		 * caracetres a,á,e,é,i,í,o,ó,u,ú > A, Á,...,Ú Se respetan los demas caracteres
		 * "holÁ" >"hOlá"sE,ñ,trU
		 */
		if (clase.getClass().toString().equals("class java.lang.String")) {
			String arreCambio = "aáeéiíoóuúAÁEÉIÍOÓUÚ";// 20
			char[] cadAux = new char[valor.length()];

			for (int i = 0; i < valor.length(); i++) {

				cadAux[i] = valor.charAt(i);
				for (int j = 0; j < arreCambio.length(); j++) {
					if (valor.charAt(i) == arreCambio.charAt(j) && j >= 0 && j <= 10) {
						cadAux[i] = arreCambio.charAt(j + 10);
						break;
					}

					if (valor.charAt(i) == arreCambio.charAt(j) && j >= 10 && j <= 20) {
						cadAux[i] = arreCambio.charAt(j - 10);
						break;
					}
				}
			}
			String auxCadTexto = "";
			for (int i = 0; i < cadAux.length; i++) {
				auxCadTexto += cadAux[i];
			}
			return auxCadTexto;
		}
		return "NA";
	}

	Object asignarClase() {
		/* c(Boleanos) */
		if (valor.equals("true") || valor.equals("TRUE")) {
			return true;
		}
		if (valor.equals("false") || valor.equals("FALSE")) {
			return false;
		}
		try {
			if (puntoEncontrado(valor) == true) {
				/* b(numero de doble precision): */
				return Float.parseFloat(valor);
			}
			/* a(numeroEntero) */
			return Integer.parseInt(valor);

		} catch (NumberFormatException nfe) {// No Numero
			if (valor.length() == 1) {
				return valor.charAt(0);/* d(caracter) */
			} else {/* e(cadena de caracteres) */
				return valor;
			}
		}
	}

	boolean puntoEncontrado(String v) {
		for (int i = 0; i < v.length(); i++) {
			if (v.charAt(i) == '.') {
				return true;
			}
		}
		return false;
	}

	String asignarPuntoFlotante(String v) {
		String aux = "";
		for (int i = 0; i < v.length(); i++) {
			if (v.charAt(i) == ',') {
				aux += '.';
			} else {
				aux += v.charAt(i);
			}

		}
		return aux;
	}
}