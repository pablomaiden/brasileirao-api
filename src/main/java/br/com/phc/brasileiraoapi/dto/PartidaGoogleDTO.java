package br.com.phc.brasileiraoapi.dto;

import java.io.Serializable;

import br.com.phc.brasileiraoapi.util.StatusPartidasEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartidaGoogleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1563648995036816037L;
	
	private StatusPartidasEnum statusPartida;
	private String tempoPartida;	
	
	//Dados Time Casa
	private String nomeEquipeCasa;
	private String urlLogoEquipeCasa;
	private Integer placarTimeCasa;
	private Integer golsTimeCasa;
	private Integer placarEstendidoTimeCasa;
	
	//Dados Time Visitante
	private String nomeEquipeVisitante;
	private String urlLogoEquipeVisitante;
	private Integer placarTimeVisitante;
	private Integer golsTimeVisitante;
	private Integer placarEstendidoTimeVisitante;
	
}
