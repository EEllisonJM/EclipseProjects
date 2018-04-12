package dsos04;

public class NSS {
	int subdelegacion, anio_fecha_ingreso, anio_fecha_nacimiento, consecutivo_inscripcion, digitoVerificador;

	public NSS(int subdelegacion, int anio_fecha_ingreso, int anio_fecha_nacimiento, int consecutivo_inscripcion) {
		super();
		this.subdelegacion = subdelegacion;
		this.anio_fecha_ingreso = anio_fecha_ingreso;
		this.anio_fecha_nacimiento = anio_fecha_nacimiento;
		this.consecutivo_inscripcion = consecutivo_inscripcion;
		this.digitoVerificador = getDigitoVerificador(
				subdelegacion + anio_fecha_ingreso + anio_fecha_nacimiento + consecutivo_inscripcion + "");
	}

	/* Getter and Setter Methods */
	public int getAnio_fecha_ingreso() {
		return anio_fecha_ingreso;
	}

	public void setAnio_fecha_ingreso(int anio_fecha_ingreso) {
		this.anio_fecha_ingreso = anio_fecha_ingreso;
	}

	public int getAnio_fecha_nacimiento() {
		return anio_fecha_nacimiento;
	}

	public void setAnio_fecha_nacimiento(int anio_fecha_nacimiento) {
		this.anio_fecha_nacimiento = anio_fecha_nacimiento;
	}

	public int getConsecutivo_inscripcion() {
		return consecutivo_inscripcion;
	}

	public void setConsecutivo_inscripcion(int consecutivo_inscripcion) {
		this.consecutivo_inscripcion = consecutivo_inscripcion;
	}

	public int getDigitoVerificador() {
		return digitoVerificador;
	}

	@Override
	public String toString() {
		return subdelegacion + "" + anio_fecha_ingreso + "" + anio_fecha_nacimiento + "" + "" + consecutivo_inscripcion
				+ "";
	}

	int getDigitoVerificador(String nssIncompleto) {
		int arre[] = new int[nssIncompleto.length()];
		/* Separar NSS */
		for (int i = 0; i < nssIncompleto.length(); i++) {
			arre[i] = Integer.parseInt(nssIncompleto.charAt(i) + "");
		}
		/* Multiplicar 1 2 1 2 1 2 1 2 1 2 */
		int multiplicar[] = { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		for (int i = 0; i < arre.length; i++) {
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

		/* Digito decena */
		int sigDecena = 0;
		if (sumaGlobal > 9 && sumaGlobal < 100) {
			String siguienteDecena = sumaGlobal + "";
			/* Decena no cerrada */
			if (Integer.parseInt((siguienteDecena.charAt(1) + "")) != 0) {
				sigDecena = ((Integer.parseInt(siguienteDecena.charAt(0) + "") + 1) * 10);
				return (sigDecena - sumaGlobal);
			} else {
				return 0;// Decena cerrada
			}
		}
		return -1;
	}
}