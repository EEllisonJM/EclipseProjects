package poo.java02.generadores;

import java.util.List;

import static j2html.TagCreator.*;
import poo.java02.pojos.Asegurado;

public class GeneradorHtmlAsegurados {

	private static AutoIncremental incremento = new AutoIncremental();

	public static String generar(List<Asegurado> asegurados) {
		String documentoHtml = html(/* HTML Format */
				head(title("Asegurados"), meta().attr("charset", "utf-8")), /* Header */
				body(table(thead(/* Groups the header content in a table */
						tr(/* Define a row in a table */
								th("#"), th("ID"), th("Nombre"), th("Apellido Paterno"), th(
										"Apellido Materno"),
								th("Fecha de Nacimiento"), th("Seguro Social"))),
						tbody(/* Groups the body content in a table */
								each(/* Each Row */
										asegurados,
										asegurado -> tr(td(incremento.siguiente().toString()), td(asegurado.getId()),
												td(asegurado.getNombre()), td(asegurado.getApellidoPaterno()),
												td(asegurado.getApellidoMaterno()),
												td(asegurado.getFechaNacimiento().toString()),
												td(asegurado.getSeguroSocial().toString()))))))).toString();
		return documentoHtml;
	}
}

final class AutoIncremental {
	private Long indice;

	public AutoIncremental() {
		this.indice = 0L;
	}

	public Long siguiente() {
		this.indice++;
		return this.indice;
	}
}