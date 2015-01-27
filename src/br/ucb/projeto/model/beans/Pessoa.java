package br.ucb.projeto.model.beans;

import javax.persistence.Id;

public class Pessoa {
	private String nome;
	private String sobrenome;
	@Id
	private String rg;
	private String telefone;
	private String universidade;
	private String curso;
	private Integer semestre;
	private String emprego;
	private String localTrabalho;
//	@ManyToMany
//	@JoinTable(name = "pessoa_eventos",joinColumns={@JoinColumn(name ="id_pessoa")},inverseJoinColumns={@JoinColumn(name="id_evento")})
//	private List<Evento> eventos;
}
