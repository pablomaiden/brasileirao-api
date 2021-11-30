package br.com.phc.brasileiraoapi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="partida")
public class Partida implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5296533622628774261L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	@Column(name="status_partida")
	private String statusPartida;
	
	@Column(name="time_casa")
	private String timeCasa;
	
	@Column(name="time_visitante")
	private String timeVisitante;
	
	@ManyToOne
	@JoinColumn(name="equipe_casa_id")
	private Equipe equipeCasa;
	
	@ManyToOne
	@JoinColumn(name="equipe_visitante_id")
	private Equipe equipeVisitante;
		
	@JsonFormat(pattern="dd/MM/yyyy HH:mm", timezone="America/Sao_Paulo")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora_partida")
	private Date dataHoraPartida;
	
	
	
	
	
	
	
	
	
	
	
	

}
