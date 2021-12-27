package com.github.marcosws.utilitarios.doc;

import java.text.ParseException;

import javax.swing.text.MaskFormatter;

public class Doc {
	
	/**
	 * Gerador CPF Válido
	 * Método para gerar numero de CPF Válido.
	 * @author Marcos
	 * @return String - Numero do CPF Gerado.
	 */
	public String gerarCpf(){
		
		String numeroCpf = gerarNumeros(9);
		int[] pesoMultiplicador1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] pesoMultiplicador2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		int somaTotal = 0;
		int resto = 0;

		for(int i = 0; i < numeroCpf.length(); i++)
			 somaTotal += Integer.parseInt(String.valueOf(numeroCpf.charAt(i))) * pesoMultiplicador1[i];
		do{
			resto = somaTotal % 11;
			if(resto < 2)
				numeroCpf = numeroCpf.concat("0");
			else
				numeroCpf = numeroCpf.concat(String.valueOf((11 - resto)));
			somaTotal = 0;
			if(numeroCpf.length() == 11) break;
			for(int i = 0; i < numeroCpf.length(); i++)
				 somaTotal += Integer.parseInt(String.valueOf(numeroCpf.charAt(i))) * pesoMultiplicador2[i];
		}
		while(true);
		
		return numeroCpf;
	}
	/**
	 * Gerador CNPJ Válido
	 * @author Marcos
	 * @return
	 */
	public String gerarCnpj(){
		
		String numeroCnpj = gerarNumeros(8).concat("0001");
		int[] pesoMultiplicador1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] pesoMultiplicador2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int somaTotal = 0;
		int resto = 0;
		
		for(int i = 0; i < numeroCnpj.length(); i++)
			 somaTotal += Integer.parseInt(String.valueOf(numeroCnpj.charAt(i))) * pesoMultiplicador1[i];
		
		do{
			resto = somaTotal % 11;
			if(resto < 2)
				numeroCnpj = numeroCnpj.concat("0");
			else
				numeroCnpj = numeroCnpj.concat(String.valueOf((11 - resto)));
			somaTotal = 0;
			if(numeroCnpj.length() == 14) break;
			for(int i = 0; i < numeroCnpj.length(); i++)
				 somaTotal += Integer.parseInt(String.valueOf(numeroCnpj.charAt(i))) * pesoMultiplicador2[i];
		}
		while(true);
		
		return numeroCnpj;
	}
	
	
	/**
	 * @author Marcos
	 * @param cpf
	 * @return
	 */
	public boolean validarCpf(String cpf) {
		
		cpf = cpf.replaceAll("[^0-9]", "");
		if(cpf.length() != 11)
			return false;
		
		int[] pesoMultiplicador1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] pesoMultiplicador2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		int somaTotal = 0;
		int resto = 0;
		String cpfParc = cpf.substring(0, 9);
		
		for(int i = 0; i < 9; i++)
			somaTotal += Integer.parseInt(String.valueOf(cpfParc.charAt(i))) * pesoMultiplicador1[i];
			
		do {
			resto = somaTotal % 11;
			if(resto < 2)
				cpfParc += "0";
			else
				cpfParc += String.valueOf((11 - resto));
			somaTotal = 0;
			if(cpfParc.length() >= 11) break;
			for(int i = 0; i < 10; i++)
				somaTotal += Integer.parseInt(String.valueOf(cpfParc.charAt(i))) * pesoMultiplicador2[i];
		}
		while(true);
		
		if(cpfParc.substring(9).equals(cpf.substring(9)))
			return true;
		
		return false;
	}
	/**
	 * @author Marcos
	 * @param cnpj
	 * @return
	 */
	public boolean validarCnpj(String cnpj) {
		
		cnpj = cnpj.replaceAll("[^0-9]", "");
		if(cnpj.length() != 14)
			return false;
		
		int[] pesoMultiplicador1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int[] pesoMultiplicador2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
		int somaTotal = 0;
		int resto = 0;
		String cnpjParc = cnpj.substring(0, 12);
		
		for(int i = 0; i < 12; i++)
			somaTotal += Integer.parseInt(String.valueOf(cnpjParc.charAt(i))) * pesoMultiplicador1[i];
		
		do {
			resto = somaTotal % 11;
			if(resto < 2)
				cnpjParc += "0";
			else
				cnpjParc += String.valueOf((11 - resto));
			somaTotal = 0;
			if(cnpjParc.length() >= 14) break;
			for(int i = 0; i < 13; i++)
				somaTotal += Integer.parseInt(String.valueOf(cnpjParc.charAt(i))) * pesoMultiplicador2[i];
		}
		while(true);
		
		if(cnpjParc.substring(12).equals(cnpj.substring(12)))
			return true;
		
		return false;
	}
	
	/**
	 * Formata Mascara de CPF/CNPJ.
	 * @author Marcos
	 * @param numero
	 * @return
	 */
	public String mascaraCpfCnpj(String numero){
		
		try{
			String formato = "";
			if(numero.length() == 11)
				formato = "AAA.AAA.AAA-AA";
			else if(numero.length() == 14)
				formato = "AA.AAA.AAA/AAAA-AA";
			else
				return numero;
			MaskFormatter maskFormatter = new MaskFormatter(formato);
			maskFormatter.setValueContainsLiteralCharacters(false);
			return maskFormatter.valueToString(numero);
		}
		catch(ParseException e){}
		return numero;

	}
	
	/**
	 * Gerador de numeros aleatórios
	 * @param quantidadeDigitos
	 * @return
	 */
	private String gerarNumeros(int quantidadeDigitos) {
		String numero = "";
		for(int i = 0; i < quantidadeDigitos; i++)
			numero += String.valueOf(Math.round(Math.random() * 9));
		return numero;
	}


}
