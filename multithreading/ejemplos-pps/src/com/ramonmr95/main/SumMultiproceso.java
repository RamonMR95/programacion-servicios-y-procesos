package com.ramonmr95.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumMultiproceso {

	public static void main(String[] args) {
		String f1 = "r1.txt";
		String f2 = "r2.txt";
		
		long n1 = Long.parseLong(args[0]);
		long n4 = Long.parseLong(args[1]);
		
		long n2 = 0;
		long n3 = 0;
		long r1 =0;
		long r2 = 0;
		
		Process p1;
		Process p2;
		
		/* Valores intermedios */
		n2 = n4 / 2;
		n3 = n2 + 1;
		
		/* Imprimir intervalos */
		System.out.println("N1 -> " + n1);
		System.out.println("N2 -> " + n2);
		System.out.println("N3 -> " + n3);
		System.out.println("N4 -> " + n4);
		
		/* Lanzar procesos */
		try {
			p1 = lanzarSumador(n1, n2, f1);
			p2 = lanzarSumador(n1, n2, f2);
			p1.waitFor();
			p2.waitFor();
		} 
		catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
		try {
			r1 = obtenerContenido(f1);
			r2 = obtenerContenido(f2);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static long obtenerContenido(String f1) throws IOException {
		long contenido = 0;
		String line;
		FileReader fr = new FileReader(f1);
		BufferedReader br = new BufferedReader(fr);
		line = br.readLine();
		br.close();
		contenido = Long.parseLong(line);
		return contenido;
	}

	private static Process lanzarSumador(long n1, long n2, String f1) throws IOException {
		String[] comando = {"java", "Sumador", String.valueOf(n1), String.valueOf(n2)};
		Process p = null;
		ProcessBuilder pb = new ProcessBuilder(comando);
		System.out.println("Llamando " + comando[0] + " " + comando[1] + " " + comando[2]);
		pb.redirectError(new File(f1 + "-err.txt"));
		pb.redirectOutput(new File(f1));
		
		/* Inicio de proceso concurrente */
		p = pb.start();
		return p;
	}

}
