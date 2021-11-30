package br.com.phc.brasileiraoapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="equipe")
public class Equipe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2669313135810099976L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Long id;
	
	private String nomeEquipe;
	
	private String urlLogoEquipe;
	
	
	
	
	
	
	
	
	

}