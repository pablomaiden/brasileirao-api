package br.com.phc.brasileiraoapi.util;

import br.com.phc.brasileiraoapi.dto.PartidaGoogleDTO;

public class ScrapingUtil {
	
	
	private static final String BASE_URL_GOOGLE="http://google.com.br/search?q=";
	private static final String COMPLEMENTO_GOOGLE="&hl=pt-BR";
	
	public static void main(String[] args) {
		
		PartidaGoogleDTO dto = new PartidaGoogleDTO();
		dto.getGolsTimeCasa();
		
		
	}

}
