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
import java.net.SocketException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Cliente {

	public static void main(String[] args) throws Exception {
		String nombreArchivoCSV = "nombres.csv";
		String[] alumnos = listaAlumnos();
		String puerto = "1234";
		String url = "";

		for (int i = 4; i < 5; i++) {
			url = "http://" + alumnos[i] + ":" + puerto + "/" + nombreArchivoCSV;
			System.out.println("NODO: "+alumnos[i]);
			getNombres(nombreArchivoCSV, url);
		}
		for (int i = 9; i < 13; i++) {
			url = "http://" + alumnos[i] + ":" + puerto + "/" + nombreArchivoCSV;
			System.out.println("NODO: "+alumnos[i]);
			getNombres(nombreArchivoCSV, url);
		}
		for (int i = 16; i < 19; i++) {
			url = "http://" + alumnos[i] + ":" + puerto + "/" + nombreArchivoCSV;
			System.out.println("NODO: "+alumnos[i]);
			getNombres(nombreArchivoCSV, url);
	
		}
	}

	public static void getNombres(String nombreArchivoCSV, String url) throws Exception {
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
				//System.out.println(inicio);

				System.out.println(response1.getStatusLine());

				HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				/*----*/
				BufferedInputStream bis = new BufferedInputStream(entity1.getContent());
				// String filePath = "sample.txt";
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
				System.out.println("Cantidad: "+numAlumnos);
				System.out.println("Duracion: " + millis);
			} catch (IOException e) {
				System.out.println(response1.getStatusLine());
				//return null;
			} catch (Exception e) {
				System.out.println(response1.getStatusLine());
				//return null;
			} finally {
				response1.close();
			}
		} finally {
			httpclient.close();
		}
		//return alumnos;
	}

	void push() throws Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost("http://httpbin.org/post");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("username", "vip"));
			nvps.add(new BasicNameValuePair("password", "secret"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			try {
				System.out.println(response2.getStatusLine());
				HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			} finally {
				response2.close();
			}
		} finally {
			httpclient.close();
		}
	}

	static String[] listaAlumnos() {
		String[] alumnos = new String[] { "altamiranonolascoadalididier.dsos.net",
				"antoniomoralesalfonsofabian.dsos.net", "bartoloosoriogabino.dsos.net",
				"chavezhernandezsergioivan.dsos.net", "cuevasortizemmanuelalejandro.dsos.net",
				"diazperezmarcos.dsos.net", "enriqueztoraljuancarlos.dsos.net", "garciagarciaguillermoricardo.dsos.net",
				"gonzalezcruzcarlosfrancisco.dsos.net", "hernandezalcantarabrenda.dsos.net",
				"hernandezgarciadavid.dsos.net", "jimenezmartinezerikellison.dsos.net", // 11
				"lopezhernandezrobertobenjamin.dsos.net", "martinezgarciajorgealejandro.dsos.net",
				"martinezmartinezjosemanuel.dsos.net", "matiasjacintogibran.dsos.net", "mendezramirezricardo.dsos.net", // 16
				"mirandamirandaedgaryoset.dsos.net", "perezsantiagoeduardo.dsos.net", "ramirezruizdaniel.dsos.net",
				"reyesoliverajocelyncitlali.dsos.net", "reyesvasquezdanielalejandro.dsos.net",
				"riossilvaguillermo.dsos.net", "sanchezlopezeduardogeovanni.dsos.net",
				"zaratehernandezjairomiguel.dsos.net", "zigalopezantonio.dsos.net" };
		return alumnos;
	}
}