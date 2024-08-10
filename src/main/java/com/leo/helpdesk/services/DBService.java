package com.leo.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leo.helpdesk.domain.Chamado;
import com.leo.helpdesk.domain.Cliente;
import com.leo.helpdesk.domain.Tecnico;
import com.leo.helpdesk.domain.enums.Perfil;
import com.leo.helpdesk.domain.enums.Prioridade;
import com.leo.helpdesk.domain.enums.Status;
import com.leo.helpdesk.repositories.ChamadoRepository;
import com.leo.helpdesk.repositories.ClienteRepository;
import com.leo.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

    @Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir cezar", "63653230268", "valdir@mail.com", "123");
		tec1.addPerfil(Perfil.ADMIN);

		Tecnico tec2 = new Tecnico(null, "Lucas de Souza", "55913428765", "lucas@mail.com", "321");
		tec2.addPerfil(Perfil.ADMIN);

		Tecnico tec3 = new Tecnico(null, "Manoel Marques", "14952337816", "manoel@mail.com", "221");
		tec3.addPerfil(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Mateus Lima", "80527954780", "torvalds@mail.com", "123");

		Cliente cli2 = new Cliente(null, "Andre Silva", "22476831941", "andre@mail.com", "332 ");

		Cliente cli3 = new Cliente(null, "Pedro Gabriel", "35567114982", "pedro@mail.com", "112");

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primerio chamado", tec1, cli1);
		Chamado c2 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec2, cli2);
		Chamado c3 = new Chamado(null, Prioridade.ALTA, Status.ENCERRADO, "Chamado 03", "Terceiro chamado", tec3, cli2);
		Chamado c4 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 04", "Quarto chamado", tec2, cli3);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		tecnicoRepository.saveAll(Arrays.asList(tec2));
		tecnicoRepository.saveAll(Arrays.asList(tec3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		clienteRepository.saveAll(Arrays.asList(cli2));
		clienteRepository.saveAll(Arrays.asList(cli3));
		chamadoRepository.saveAll(Arrays.asList(c1));
		chamadoRepository.saveAll(Arrays.asList(c2));
		chamadoRepository.saveAll(Arrays.asList(c3));
		chamadoRepository.saveAll(Arrays.asList(c4));
    }
}