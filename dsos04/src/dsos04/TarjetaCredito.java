package dsos04;

public class TarjetaCredito {
	private String tarjetaCredito;

	public TarjetaCredito(String tarjetaCredito) {
		this.tarjetaCredito = getTarjetaCredito(tarjetaCredito);
	}
	

	@Override
	public String toString() {
		return this.tarjetaCredito;
	}


	String getTarjetaCredito(String tarjetaCredito) {
		/*
		 * No se incluye el digito de verificacion(Si lo tiene mejor),separacion por -
		 * incluida
		 */
		if (tarjetaCredito.length() >= 18) {
			int arre[] = new int[16];
			/* Obtener 15 digitos */
			int count = 0;
			for (int i = 0; i < tarjetaCredito.length(); i++) {
				if (tarjetaCredito.charAt(i) != '-') {
					arre[count++] = Integer.parseInt(tarjetaCredito.charAt(i) + "");
				}
			}
			/* Multiplicar cada digito de posicion par */
			int multiplicar[] = { 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
			for (int i = 0; i < arre.length - 1; i++) {
				arre[i] *= multiplicar[i];
			}
			/* Sumar resultados de doble digito */
			for (int i = 0; i < arre.length; i++) {
				if (arre[i] > 9 && arre[i] < 19) {
					String sumaDosDigitos = arre[i] + "";
					arre[i] = (Integer.parseInt(sumaDosDigitos.charAt(0) + ""))
							+ (Integer.parseInt(sumaDosDigitos.charAt(1) + ""));
				}
			}
			/* Sumatoria global */
			int sumaGlobal = 0;
			for (int i = 0; i < arre.length; i++) {
				sumaGlobal += arre[i];
			}

			/* Digito de verificacion */
			arre[15] = Math.floorMod(sumaGlobal, 10);

			/* Validacion */
			int suma = 0;
			for (int i = 0; i < arre.length; i++) {
				suma += arre[i];
			}
			if (Math.floorMod(suma, 10) == 0) {
				return tarjetaCredito += arre[15];
			}
		}

		return "N/V";
	}
}