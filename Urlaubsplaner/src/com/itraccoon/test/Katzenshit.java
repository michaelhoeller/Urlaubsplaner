package com.itraccoon.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Katzenshit {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Saskia und Eves shitty Mathe aufgabe:");
		System.out.println("----------------------------");

		System.out.println("4 Hunde fangen 5 Katzen in 3 Stunden");
		System.out.println("zeit berechnen für x Hunde und x Katzen. Bitte eingeben:");
		System.out.print("Hunde    	");
		int Hunde = Integer.parseInt(br.readLine());
		System.out.print("Katzen	");
		int Katzen = Integer.parseInt(br.readLine());
		double const3 = (4 * 180) / 5;

		double time = (const3 * Katzen) / Hunde;

		System.out.println("Ergebnis in Minuten: " + time);
		System.out.println("Ergebnis in Stunden: " + time / 60);
		System.out.println("Übrigens solltet ihr zusammen mal meinen Penis lutschen :)");
	}

}
