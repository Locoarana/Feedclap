package com.example.easi641.services;

import java.util.ArrayList;
import java.util.List;

import com.example.easi641.common.UserValidator;
import com.example.easi641.dto.DesarrolladorDto;
import com.example.easi641.dto.ProyectoDto;
import com.example.easi641.dto.RegistroDto;
import com.example.easi641.entities.*;
import com.example.easi641.exception.ExceptionMessageEnum;
import com.example.easi641.exception.NotFoundException;
import com.example.easi641.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DesarrolladorService {
	@Autowired
	private ReviewerRepository reviewerRepository;

	@Autowired
	private RegistroRepository registroRepository;

	@Autowired
	private JuegoRepository juegoRepository;

	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	private DesarrolladorRepository desarrolladorRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Desarrollador createDesarrollador(DesarrolladorDto desarrolladorDto) {
		UserValidator.validateDesarrollador(desarrolladorDto);
		Desarrollador desarrollador = Desarrollador.builder().nombre(desarrolladorDto.getNombre()).build();

		return desarrolladorRepository.save(desarrollador);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Desarrollador createDesarrollador(User user) {
		Desarrollador desarrollador = new Desarrollador();
		desarrollador.setId(user.getId());
		desarrollador.setNombre(user.getName());
		desarrollador.setProyectos(new ArrayList<>());
		desarrollador.setRating(2.5f);
		return desarrolladorRepository.save(desarrollador);
	}

	@Transactional(readOnly = true)
	public List<User> findAllDesarrolladores() {
		return userRepository.getDevelopers();
	}

	@Transactional
	public void deleteDesarrollador(Long desarrolladorId) {
		Desarrollador desarrollador = desarrolladorRepository.findById(desarrolladorId)
				.orElseThrow(() -> new NotFoundException(ExceptionMessageEnum.NOT_FOUND.getMessage()));
		desarrolladorRepository.delete(desarrollador);
	}

	@Transactional
	public Proyecto createProyecto(ProyectoDto proyectoDto) {
		Desarrollador desarrollador = desarrolladorRepository.findById(proyectoDto.getDesarrolladorId())
				.orElseThrow(() -> new NotFoundException("Desarrollador not found."));

		Juego juego = juegoRepository.findById(proyectoDto.getJuegoId())
				.orElseThrow(() -> new NotFoundException("Juego not found."));

		Proyecto proyecto = new Proyecto();
		proyecto.setJuego(juego);
		proyecto.setDesarrollador(desarrollador);
		proyecto.setPuesto(proyectoDto.getPuesto());
		return proyectoRepository.save(proyecto);
	}

	@Transactional
	public Registro createRegistro(RegistroDto registroDto) {
		Desarrollador desarrollador = desarrolladorRepository.findById(registroDto.getDesarrolladorId())
				.orElseThrow(() -> new NotFoundException("Desarrollador not found."));

		Reviewer reviewer = reviewerRepository.findById(registroDto.getReviewerId())
				.orElseThrow(() -> new NotFoundException("Reviewer not found."));

		Registro registro = new Registro();
		registro.setDesarrollador(desarrollador);
		registro.setReviewer(reviewer);
		registro.setMonto(registroDto.getMonto());
		registro.setLocalDateTime(registroDto.getLocalDateTime());
		return registroRepository.save(registro);
	}
}
