package dsos04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Asegurado {
	private String nombre, apellido_paterno, apellido_materno;
	private LocalDate fecha_nacimiento, fecha_ingreso_imss;
	private int subdelegacion_imss, consecutivo_inscripcion_imss;
	private NSS nss;
	private TarjetaCredito tarjeta_credito;

	public Asegurado(String _nombre, String _apellido_paterno, String _apellido_materno, String _fecha_nacimiento,
			String _fecha_ingreso_imss, String _subdelegacion_imss, String _consecutivo_inscripcion_imss,
			String _tarjeta_credito) {
		/* Formato Fechas */
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatoFechaNSS = DateTimeFormatter.ofPattern("yy");
		/* Datos persona */
		this.nombre = _nombre;
		this.apellido_paterno = _apellido_paterno;
		this.apellido_materno = _apellido_materno;
		this.fecha_nacimiento = LocalDate.parse(_fecha_nacimiento, formatoFecha);
		/* Datos IMMS */
		this.fecha_ingreso_imss = LocalDate.parse(_fecha_ingreso_imss, formatoFecha);
		this.subdelegacion_imss = Integer.parseInt(_subdelegacion_imss);
		this.consecutivo_inscripcion_imss = Integer.parseInt(_consecutivo_inscripcion_imss);
		/* NSS */
		this.nss = new NSS(//
				subdelegacion_imss, //
				Integer.parseInt(this.fecha_ingreso_imss.format(formatoFechaNSS)), //
				Integer.parseInt(this.fecha_nacimiento.format(formatoFechaNSS)), //
				consecutivo_inscripcion_imss);//
		/* Tarjeta de credito */
		this.tarjeta_credito = new TarjetaCredito(_tarjeta_credito);

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public LocalDate getFecha_ingreso_imss() {
		return fecha_ingreso_imss;
	}

	public void setFecha_ingreso_imss(LocalDate fecha_ingreso_imss) {
		this.fecha_ingreso_imss = fecha_ingreso_imss;
	}

	public int getSubdelegacion_imss() {
		return subdelegacion_imss;
	}

	public void setSubdelegacion_imss(int subdelegacion_imss) {
		this.subdelegacion_imss = subdelegacion_imss;
	}

	public int getConsecutivo_inscripcion_imss() {
		return consecutivo_inscripcion_imss;
	}

	public void setConsecutivo_inscripcion_imss(int consecutivo_inscripcion_imss) {
		this.consecutivo_inscripcion_imss = consecutivo_inscripcion_imss;
	}

	public String getNss() {
		return nss.toString();
	}

	public String getTarjetaCredito() {
		return tarjeta_credito.toString();
	}

	@Override
	public String toString() {
		return "--------------------------------\nNombre = " + nombre + "\nApellido_paterno = " + apellido_paterno
				+ "\nApellido_materno = " + apellido_materno + "\nFecha_nacimiento = " + fecha_nacimiento
				+ "\nFecha_ingreso_imss = " + fecha_ingreso_imss + "\nSubdelegacion_imss = " + subdelegacion_imss
				+ "\nConsecutivo_inscripcion_imss = " + consecutivo_inscripcion_imss + "\nNSS = " + nss
				+ "\nTarjeta_credito = " + tarjeta_credito;
	}

	/* Comparator for sorting the list by Name */
	public static Comparator<Asegurado> ordenarPorNombre = new Comparator<Asegurado>() {
		public int compare(Asegurado s1, Asegurado s2) {
			String nombreAsegurado1 = s1.getNombre().toUpperCase();
			String nombreAsegurado2 = s2.getNombre().toUpperCase();

			// ascending order
			return nombreAsegurado1.compareTo(nombreAsegurado2);
			// descending order
			// return StudentName2.compareTo(StudentName1);
		}
	};
	/* Comparator for sorting the list by Name */
	public static Comparator<Asegurado> ordenarPorApellidoPaterno = new Comparator<Asegurado>() {
		public int compare(Asegurado s1, Asegurado s2) {
			String apAsegurado1 = s1.getApellido_paterno().toUpperCase();
			String apAsegurado2 = s2.getApellido_paterno().toUpperCase();

			// ascending order
			return apAsegurado1.compareTo(apAsegurado2);
			// descending order
			// return StudentName2.compareTo(StudentName1);
		}
	};

}