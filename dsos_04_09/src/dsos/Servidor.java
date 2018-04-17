package dsos;

public class Servidor {
	private int id;
	private String url;
	private String puerto;

	public Servidor() {

	}

	public Servidor(int id, String url, String puerto) {
		super();
		this.id = id;
		this.url = url;
		this.puerto = puerto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPuerto() {
		return puerto;
	}

	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}

}
