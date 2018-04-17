package dsos;

/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Cliente {

	public static void main(String[] args) throws Exception {
		/* java -jar Cabrera nombres.csv */
 
		String nombreArchivoCSV = "nombres.csv";
		String buscar = "Cabrera";
		// nombreArchivoCSV = args[0];
		// args[1] = archivoCSV;

		List<Servidor> listaServidores = getServerNames(
				"http://jimenezmartinezerikellison.dsos.net:1234/servidores.csv", "servidores.csv");

		String puerto = "1234";
		String url = "";
		List<Alumno> listaAlumnos = new ArrayList<>();
		/**/
		/**/
		Instant inicio = Instant.now();
		
		int count = 0;

		for (int i = 0; i < listaServidores.size(); i++) {/* 26 */
			url = "http://" + listaServidores.get(i).getUrl() + ":" + puerto + "/" + nombreArchivoCSV;
			if (existeServidor(url)) {
				System.out.print(
						"[OK] NODO: " + (i + 1) + " " + listaServidores.get(i).getUrl() + " => Servidor encontrado\n");
				listaAlumnos = getNombreAlumnos(nombreArchivoCSV, url);
				List<Alumno> listaAgrupar = new ArrayList<>();
				Instant inicioBusqueda = Instant.now();
				for (int j = 0; j < listaAlumnos.size(); j++) {
					// System.out.println("....");

					if (listaAlumnos.get(j).getApellidoPaterno().equals(buscar)) {
						listaAgrupar.add(listaAlumnos.get(j));
						count++;
					}
				}
				System.out.println("[" + buscar + "] . Encontrado . " + count + " Veces");
				count = 0;
				Instant finBusqueda = Instant.now();
				Duration durationBusqueda = Duration.between(inicioBusqueda, finBusqueda); // For
																							// days-hours-minutes-seconds
																							// scale.
				long millisB = durationBusqueda.toMillis(); // Possible data-loss in truncating nanoseconds to
															// milliseconds.
				System.out.println("Duracion Busqueda: " + millisB + " en milisegundos");
				/**/

				Collections.sort(listaAgrupar, Alumno.ordenarPorNombre);
				for (int j = 0; j < listaAgrupar.size(); j++) {
					System.out.println(listaAgrupar.get(j).toString());
				}
				System.out.println("\n");
				/**/
			} else {
				System.out.print(
						"NODO: " + (i + 1) + " " + listaServidores.get(i).getUrl() + " => Servidor No encontrado\n");
			}
		}

		Instant fin = Instant.now();
		Duration duration = Duration.between(inicio, fin); // For days-hours-minutes-seconds scale.
		long millis = duration.toMillis(); // Possible data-loss in truncating nanoseconds to milliseconds.
		System.out.println("Duracion aplicación: " + millis + " en milisegundos");
	} 

	public static List<Alumno> getNombreAlumnos(String nombreArchivoCSV, String url) throws Exception {
		int numAlumnos;

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network
			// socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.
			try {
				Instant inicio = Instant.now();
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				/*----*/
				BufferedInputStream bis = new BufferedInputStream(entity1.getContent());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(nombreArchivoCSV)));
				int inByte;
				while ((inByte = bis.read()) != -1)
					bos.write(inByte);
				bis.close();
				bos.close();
				/*----*/
				Path ubicacionArchivoLeer = Paths.get(nombreArchivoCSV);
				CSVHandler csvHandler = new CSVHandler();
				numAlumnos = csvHandler.cantidadContenidoArchivo(ubicacionArchivoLeer, '|');

				EntityUtils.consume(entity1);
				Instant fin = Instant.now();
				Duration duration = Duration.between(inicio, fin); // For days-hours-minutes-seconds scale.
				long millis = duration.toMillis(); // Possible data-loss in truncating nanoseconds to milliseconds.

				response1.close();
				httpclient.close();
				return csvHandler.readStudentsNames(ubicacionArchivoLeer, '|');
			} catch (Exception e) {
				System.out.println("Que paso amiguito");
				response1.close();
			} finally {
				response1.close();
			}

		} catch (IOException e) {
			System.out.println("Error amigo");
			e.getMessage();

		} finally {
			httpclient.close();
		}
		return null;
	}

	static List<Servidor> getServerNames(String url, String nombreArchivoCSV) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);

			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network
			// socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.
			try {
				Instant inicio = Instant.now();
				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				/*----*/
				BufferedInputStream bis = new BufferedInputStream(entity1.getContent());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(nombreArchivoCSV)));
				int inByte;
				while ((inByte = bis.read()) != -1)
					bos.write(inByte);
				bis.close();
				bos.close();
				/*----*/
				Path ubicacionArchivoLeer = Paths.get(nombreArchivoCSV);
				CSVHandler csvHandler = new CSVHandler();

				EntityUtils.consume(entity1);
				Instant fin = Instant.now();
				Duration duration = Duration.between(inicio, fin); // For days-hours-minutes-seconds scale.
				long millis = duration.toMillis(); // Possible data-loss in truncating nanoseconds to milliseconds.

				response1.close();
				httpclient.close();
				return csvHandler.readServersNames(ubicacionArchivoLeer, ',');
			} catch (Exception e) {
				System.out.println("Que paso amiguito");
				response1.close();
			} finally {
				response1.close();
			}

		} catch (IOException e) {
			System.out.println("Error amigo");
			e.getMessage();
		} finally {
			httpclient.close();
		}
		return null;
	}

	/* Using core Java. */
	static boolean existeServidor(String url) throws ClientProtocolException, IOException {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("GET");// con.setRequestMethod("HEAD")
			con.setConnectTimeout(2000); // set timeout to 3 seconds
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);

		} catch (java.net.SocketTimeoutException e) {
			return false;
		} catch (java.io.IOException e) {
			return false;
		}
	}
}