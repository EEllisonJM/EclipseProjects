package dsos02;

import java.util.List;

public class Main {

	/* Find an argument inside valids arguments */
	static boolean existeParametro(String[] parametros, String parametro) {
		for (int i = 0; i < parametros.length; i++) {
			if (parametros[i].equals(parametro)) {
				return true;
			}
		}
		return false;
	}

	/* get argument position */
	static int posicionParametro(String[] args, String parametro) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals(parametro)) {
				return i;
			}
		}
		return -1;/* Not found */
	}

	/* Array reduced */
	static String[] getNewArray(String[] arreglo, int skipPosition) {
		String[] newArray = new String[arreglo.length - 1];
		int position = 0;
		for (int i = 0; i < arreglo.length; i++) {
			if (i != skipPosition) {
				newArray[position++] = arreglo[i];
			}
		}
		return newArray;
	}

	/* Argument size = 4 */
	static String[] getLastArgument(String[] parametros, String aux) {
		/* { "-a", "-p", "-i", "-d", "-c" }; */
		for (int i = 1; i < parametros.length; i++) {
			if (parametros[i] == aux) {
				if (Math.floorMod(i, 2) != 0) {
					if (i == 1) {/* [-p] */
						return getNewArray(getNewArray(getNewArray(parametros, 0), 0), 0);/**/
					}
					if (i == 3) {/* [-d] */
						return getNewArray(getNewArray(getNewArray(parametros, 0), 2), 2);/**/
					}
				} else {
					if (i == 2) {/*-i*/
						return getNewArray(getNewArray(getNewArray(parametros, 0), 0), 0);/**/
					}
					if (i == 4) {/*-c*/
						return getNewArray(getNewArray(getNewArray(parametros, 0), 2), 2);/**/
					}
				}
			}
		}
		System.out.println("Paso");
		return null;
	}

	static String[] valido_3_argumentos(String[] aux, String[] parametros) {
		/*
		 * [-a archivos.csv -i] [-a archivos.csv -p][-a archivos.csv -d] [-a
		 * archivos.csv -c]
		 */
		String parametro_a_utilizar[] = new String[1];
		if (existeParametro(parametros, aux[0])) {
			parametro_a_utilizar[0] = aux[0];
			return parametro_a_utilizar;
		}
		return null;

	}

	static String[] valido_4_argumentos(String[] parametros, String[] aux) {
		/*
		 * [-a archivos.csv -p -c] [-a archivos.csv -i -c] [-p -a archivos.csv -i]
		 * etc...[Divide y venceras]
		 */
		String parametros_a_utilizar[] = new String[2];
		if (existeParametro(parametros, aux[0])) {
			parametros_a_utilizar[0] = aux[0];
			parametros = getLastArgument(parametros, aux[0]);
			if (existeParametro(parametros, aux[1])) {
				parametros_a_utilizar[1] = aux[1];
				return parametros_a_utilizar;
			}
		}
		return null;
	}

	static String[] validarParametros(String[] args, String[] parametros) {
		int posicionParametro = posicionParametro(args, parametros[0]/* [-a] */);
		if (posicionParametro != -1) {/* [-a] Found ([-a data.csv] always together) */
			/* Rest of the array */
			String[] aux =
					/* Delete [data.csv] */
					getNewArray(
							/* Delete [-a] */
							getNewArray(args, posicionParametro), posicionParametro);
			if (aux.length == 2) {/* Size = 4 */
				if (valido_4_argumentos(parametros, aux) != null) {
					return valido_4_argumentos(parametros, aux);
				}

			} else {/* (aux.length == 1) Size =3 */
				if (valido_3_argumentos(aux, parametros) != null) {
					return valido_3_argumentos(aux, parametros);
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		try {
			/* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
			LectorCsv lCSV = new LectorCsv();
			String nombreArchivo = "";
			String argss[] = { "-p", "-a", "datos.csv", "-a" };
			String parametros[] = { "-a", "-p", "-i", "-d", "-c" };

			/* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
			if (argss.length == 4 || argss.length == 3 || argss.length == 2) {
				List<Numero[]> numeros;
				if (argss.length == 2) {/* 2 Arguments */
					/* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
					numeros = lCSV.archivo(nombreArchivo);
					nombreArchivo = argss[1];
					for (int i = 0; i < numeros.size(); i++) {
						lCSV.caso("-all", numeros.get(i));
					}

					/* - - - - - - - - - - - - - - - - - - - - - - - - - - - */
				} else {/* [3|4] Parameters */
					if (validarParametros(argss, parametros) != null) {
						numeros = lCSV.archivo(argss[posicionParametro(argss, "-a") + 1]);
						String p[] = validarParametros(argss, parametros);
						for (int i = 0; i < p.length; i++) {
							for (int j = 0; j < numeros.size(); j++) {
								lCSV.caso(p[i].toString(), numeros.get(j));
							}
						}
					} else {
						System.out.println(mensajeParametrosNoValidos());
					}
				}
			} else {
				System.out.println(mensajeParametrosNoValidos());
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(mensajeParametrosNoValidos());
		}
	}

	static String mensajeParametrosNoValidos() {
		return "Parametros no validos, verifique.\n" + //
				"-a archivos.csv \n" + // 2

				"[-p|-i] -a archivo \n" + // 3
				"[-d|-c] -a archivo \n" + // 3
				"-a archivo [-p|-i] \n" + // 3
				"-a archivo [-d|-c] \n" + // 3

				"-a archivo [-d|-c] [-p|-i] \n" + // 4
				"-a archivo [-p|-i] [-d|-c] \n" + // 4

				"[-d|-c] [-p|-i] -a archivo \n" + // 4
				"[-p|-i] [-d|-c] -a archivo \n" + // 4

				"[-d|-c]  -a archivo [-p|-i] \n" + // 4
				"[-p|-i] -a archivo [-d|-c] \n"; // 4
	}
}