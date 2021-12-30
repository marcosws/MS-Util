package com.github.marcosws.utilitarios;

import com.github.marcosws.utilitarios.data.Data;
import com.github.marcosws.utilitarios.doc.Doc;
import com.github.marcosws.utilitarios.json.JsonParser;

public class Utilitarios {
	
	public static final Data data = new Data();
	public static final Doc doc = new Doc();
	public static final JsonParser json = new JsonParser();
	
	public static String removeAcentos(String texto) {
		
		String letrasComAcento = "ÁÃÂÀÄÅÉÊËÈÏÍÌÎÓÕÔÖÒÚÙÛÜÇÑÝŸŠáãâàäåéêëèïíìîóõôöòúùûüçñýÿš";
		String letrasSemAcento = "AAAAAAEEEEIIIIOOOOOUUUUCNYYSaaaaaaeeeeiiiiooooouuuucnyys";
		for(int i = 0; i < letrasComAcento.length(); i++) 
			texto = texto.replace(letrasComAcento.charAt(i), letrasSemAcento.charAt(i));
		return texto;
		
	}
	
	/**
	 * Gerador de numeros aleatórios
	 * @param quantidadeDigitos
	 * @return
	 */
	public static String gerarNumeros(int quantidadeDigitos) {
		String numero = "";
		for(int i = 0; i < quantidadeDigitos; i++)
			numero += String.valueOf(Math.round(Math.random() * 9));
		return numero;
	}
	
	/**
	 * 
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static String gerarNumeros(int minimo, int maximo) {
		
		String numero = String.valueOf(Math.round((Math.random() * ((maximo - minimo) + 1)) + minimo));
		String zeros = "";
		for(int i = 0; i < Math.abs((numero.length() - String.valueOf(maximo).length())); i++)
			zeros += "0";
		return zeros.concat(numero);
		
	}

}
