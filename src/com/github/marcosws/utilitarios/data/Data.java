package com.github.marcosws.utilitarios.data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Data {
	
	/**
	 * Verifica se a data é um feriado bancário
	 * Referências:
	 * 	http://www.profcardy.com/artigos/pascoa.php
	 * 	https://feriadosbancarios.febraban.org.br/
	 * @param localDate
	 * @return boolean
	 */
	public boolean verificarFeriado(LocalDate localDate) {
		
		int dia = localDate.getDayOfMonth();
		int mes = localDate.getMonthValue();
		int ano = localDate.getYear();
		int numeroDourado = (ano % 19) + 1; // calcula o numero dourado
		int[][] dataBase = { // Data Base para calcular os feriados
				{14, 4}, // Número dourado  1 Data Base: 14 de abril
				{3, 4},  // Número dourado  2 Data Base: 3 de abril
				{23, 3}, // Número dourado  3 Data Base: 23 de março
				{11, 4}, // Número dourado  4 Data Base: 11 de abril
				{31, 3}, // Número dourado  5 Data Base: 31 de março
				{18, 4}, // Número dourado  6 Data Base: 18 de abril
				{8, 4},  // Número dourado  7 Data Base: 8 de abril
				{28, 3}, // Número dourado  8 Data Base: 28 de março
				{16, 4}, // Número dourado  9 Data Base: 16 de abril
				{5, 4},  // Número dourado 10 Data Base: 5 de abril
				{25, 3}, // Número dourado 11 Data Base: 25 de março
				{13, 4}, // Número dourado 12 Data Base: 13 de abril
				{2, 4},  // Número dourado 13 Data Base: 2 de abril
				{22, 3}, // Número dourado 14 Data Base: 22 de março
				{10, 4}, // Número dourado 15 Data Base: 10 de abril
				{30, 3}, // Número dourado 16 Data Base: 30 de março
				{17, 4}, // Número dourado 17 Data Base: 17 de abril
				{7, 4},  // Número dourado 18 Data Base: 7 de abril
				{27, 3}  // Número dourado 19 Data Base: 27 de março
		};
		LocalDate ldDataBase = LocalDate.of(ano, dataBase[numeroDourado - 1][1], dataBase[numeroDourado - 1][0]);
		LocalDate ldSextaFeiraSanta, ldPascoa;
		ldSextaFeiraSanta = ldPascoa = ldDataBase;
		if(ldSextaFeiraSanta.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
			ldSextaFeiraSanta = ldSextaFeiraSanta.minusDays(1L); // Se a data base for sabado retrocede para sexta-feira
		}
		else {
	        while(!ldSextaFeiraSanta.getDayOfWeek().equals(DayOfWeek.FRIDAY))
	        	ldSextaFeiraSanta = ldSextaFeiraSanta.plusDays(1L); // Desloca a data base para a proxima sexta-feira
		}

		do { // Desloca a data base para o dia da Pascoa que serve para a verificação dos feriados de Carnaval e Corpus Christi
			ldPascoa = ldPascoa.plusDays(1L); 
		}
		while(!ldPascoa.getDayOfWeek().equals(DayOfWeek.SUNDAY));
		
		LocalDate ldCarnavalSegunda, ldCarnavalTerca, ldCorpusChrist;
		ldCarnavalSegunda = ldPascoa.minusDays(48L);
		ldCarnavalTerca = ldPascoa.minusDays(47L);
		ldCorpusChrist = ldPascoa.plusDays(60L);
		
		int[][] dataFeriado = { // Atribui os feriados fixos e móveis
				{ldSextaFeiraSanta.getDayOfMonth(), ldSextaFeiraSanta.getMonthValue()}, // Paixão de Cristo (feriado nacional) Sexta-Feira Santa
				{ldCarnavalSegunda.getDayOfMonth(), ldCarnavalSegunda.getMonthValue()}, // Carnaval (ponto facultativo)
				{ldCarnavalTerca.getDayOfMonth(), ldCarnavalTerca.getMonthValue()}, //     Carnaval (ponto facultativo)
				{ldCorpusChrist.getDayOfMonth(), ldCorpusChrist.getMonthValue()}, //       Corpus Christi (Ponto Facultativo)
				{1, 1}, //    Confraternização Universal (feriado nacional)
				{21, 4}, //   Dia de Tiradentes (feriado nacional)
				{1, 5}, //    Dia Mundial do Trabalho (feriado nacional)
				{7, 9}, //    Independência do Brasil (feriado nacional)
				{12, 10}, //  Nossa Senhora Aparecida (feriado nacional)
				{2, 11}, //   Finados (feriado nacional)
				{15 ,11}, //  Proclamação da República (feriado nacional)
				{25, 12}, //  Natal (feriado nacional)
		};

		for(int i = 0; i < dataFeriado.length; i++) // Verifica se a data é feriado
			if((dataFeriado[i][0] == dia) && (dataFeriado[i][1] == mes))
				return true;
		
		return false;
	}
	
	/**
	 * Verifica se a data é um feriado bancário
	 * @author Marcos Souza
	 * @param dia
	 * @param mes
	 * @param ano
	 * @return boolean
	 */
	public boolean verificarFeriado(int dia, int mes, int ano) {
		LocalDate localDate = LocalDate.of(ano, mes, dia);
		return verificarFeriado(localDate);
	}

	/**
	 * Verifica se a data é um dia útil
	 * @author Marcos Souza
	 * @param localDateTime
	 * @return boolean
	 */
	public boolean verificarDiaUtil(LocalDate localDate) {
		
		if(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY))
			return false;
		if(localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			return false;
		if(verificarFeriado(localDate))
			return false;
		
		return true;
		
	}
	
	/**
	 * Verifica se a data é um dia útil
	 * @author Marcos Souza
	 * @param dia
	 * @param mes
	 * @param ano
	 * @return
	 */
	public boolean verificarDiaUtil(int dia, int mes, int ano) {
		return verificarDiaUtil(LocalDate.of(ano, mes, dia));
	}
	
	/**
	 * Retorna a Data do primeiro dia Util do Mês
	 * @author Marcos Souza
	 * @param formato
	 * @return
	 */
	public String retornarDataPrimeiroDiaUtilDoMes(String formato) {
		
		LocalDate localDate = LocalDate.now();
		while(!(localDate.getDayOfMonth() == 1)) {
			localDate = localDate.minusDays(1L);
		}
		while(!verificarDiaUtil(localDate)) {
			localDate = localDate.plusDays(1L);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return formatter.format(localDate);
		
	}
	
	/**
	 * Retorna a Data do ultimo dia Util do Mês
	 * @author Marcos Souza
	 * @param formato
	 * @return
	 */
	public String retornarDataUltimoDiaUtilDoMes(String formato) {
		
		int ultimoDia = 0;
		LocalDate localDate = LocalDate.now();
		while(true) {
			ultimoDia = localDate.getDayOfMonth();
			localDate = localDate.plusDays(1L);
			if(localDate.getDayOfMonth() < ultimoDia) { 
				localDate = localDate.minusDays(1L);
				break;
			}
		}
		while(!verificarDiaUtil(localDate)) {
			localDate = localDate.minusDays(1L);
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return formatter.format(localDate);
		
	}
	
	/**
	 * Retornar uma data de um dia útil
	 * @author Marcos Souza
	 * @param diaAdSub
	 * @param formato
	 * @return String
	 */
	public String retornarDataDiaUtil(Long diaAdSub, String formato) {
		
		LocalDate localDate = null;
		if(diaAdSub < 0) {
			localDate = LocalDate.now().minusDays(Math.abs(diaAdSub));
			while(!verificarDiaUtil(localDate)) {
				localDate = localDate.minusDays(1L);
			}
		}
		else {
			localDate = LocalDate.now().plusDays(diaAdSub);
			while(!verificarDiaUtil(localDate)) {
				localDate = localDate.plusDays(1L);
			}
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
		return formatter.format(localDate);
		
	}

}
