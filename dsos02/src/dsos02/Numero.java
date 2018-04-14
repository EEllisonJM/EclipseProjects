package dsos02;

public class Numero {
	private int valor;

	/* Constructor */
	public Numero(int valor) {
		super();
		this.valor = valor;
	}

	/* Getter and Setters */
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	/* Methods */
	boolean esPar() {
		if (Math.floorMod(valor, 2) == 0) {
			return true;
		}
		return false;
	}

	boolean esDecena() {
		if (valor > 9 && valor < 100) {
			return true;
		}
		return false;
	}

	boolean esCentena() {
		if (valor > 99 && valor < 1000) {
			return true;
		}
		return false;
	}
}
