package poo.java02.pojos;

import java.time.ZonedDateTime;

public class Asegurado {

	private String id;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private ZonedDateTime fechaNacimiento;
	private Long seguroSocial;

	public Asegurado() {

		this.id = "";
		this.nombre = "";
		this.apellidoPaterno = "";
		this.apellidoMaterno = "";
		this.fechaNacimiento = ZonedDateTime.parse("1899-12-31T24:00:00-00:00");
		this.seguroSocial = 0L;

	}

	public Asegurado(String id, String nombre, String apellidoPaterno, String apellidoMaterno,
			ZonedDateTime fechaNacimiento, Long numeroSocial) {

		this.id = id;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.seguroSocial = numeroSocial;

	}

	public String toString() {
		StringBuffer representacion = new StringBuffer();
		representacion.append("Asegurado {");
		representacion.append("id : " + this.id).append(", ").append("nombre : " + this.nombre).append(", ")
				.append("apellidoPaterno : " + this.apellidoPaterno).append(", ")
				.append("apellidoMaterno : " + this.apellidoMaterno).append(", ")
				.append("fechaNacimiento : " + this.fechaNacimiento).append(", ")
				.append("seguroSocial : " + this.seguroSocial);
		representacion.append("}");

		return representacion.toString();
	}

	public String getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public ZonedDateTime getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public Long getSeguroSocial() {
		return this.seguroSocial;
	}
}
