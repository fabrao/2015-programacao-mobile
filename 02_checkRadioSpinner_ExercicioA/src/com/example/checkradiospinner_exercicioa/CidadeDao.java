package com.example.checkradiospinner_exercicioa;

import java.util.ArrayList;

public class CidadeDao {
	public ArrayList<String> getCidadeByEstado(String estado) {
		ArrayList<String> ret = new ArrayList<String>();
		if(estado.startsWith("Paran")) {
			ret.add("Cornelio Procopio");
			ret.add("Londrina");
			ret.add("Bandeirantes");
		}
		
		//implementar aqui algumas cidades para cada estado
		
		return ret;
	}
}
