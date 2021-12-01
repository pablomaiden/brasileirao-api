package br.com.phc.brasileiraoapi.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.phc.brasileiraoapi.dto.PartidaResponseDTO;
import br.com.phc.brasileiraoapi.entity.Partida;
import br.com.phc.brasileiraoapi.repository.PartidaRepository;
import br.com.phc.brasileiraoapi.exception.NotFoundException;

@Service
public class PartidaService {	
	
	@Autowired
	private PartidaRepository partidaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
		
	public Partida buscarPartidaPorId(Long id) {
		return partidaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Nenhuma partida foi encontrado com o id informado: " + id));
	}
	
	public PartidaResponseDTO listarPartidas() {
		PartidaResponseDTO partidas = new PartidaResponseDTO();
		partidas.setPartidas(partidaRepository.findAll());		
		return partidas;
	}
	
	public List<Partida> listarPartidasPeriodo() {
		return partidaRepository.listarPartidasPeriodo();
	}

	public Integer buscarQuantidadePartidasPeriodo() {
		return partidaRepository.buscarQuantidadePartidasPeriodo();
	}

}
