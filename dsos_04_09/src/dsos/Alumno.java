package dsos;

import java.util.Comparator;

public class Alumno {
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;

	public Alumno() {
	}

	public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno) {
		super();
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	@Override
	public String toString() {
		return "Alumno [" + nombre + " " + apellidoPaterno + " " + apellidoMaterno + "]";
	}

	public static Comparator<Alumno> ordenarPorApellidoPaterno = new Comparator<Alumno>() {
		public int compare(Alumno s1, Alumno s2) {
			String apAsegurado1 = s1.getApellidoPaterno().toUpperCase();
			String apAsegurado2 = s2.getApellidoPaterno().toUpperCase();
			// ascending order
			return apAsegurado1.compareTo(apAsegurado2);
			// descending order
			// return StudentName2.compareTo(StudentName1);
		}
	};
	public static Comparator<Alumno> ordenarPorNombre = new Comparator<Alumno>() {
		public int compare(Alumno s1, Alumno s2) {
			String apAsegurado1 = s1.getNombre().toUpperCase();
			String apAsegurado2 = s2.getNombre().toUpperCase();
			// ascending order
			return apAsegurado1.compareTo(apAsegurado2);
			// descending order
			// return StudentName2.compareTo(StudentName1);
		}
	};
}