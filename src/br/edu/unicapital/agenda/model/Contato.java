package br.edu.unicapital.agenda.model;

import java.sql.Connection;

public class Contato {
	int id;
	String nome;
	String telefone;

	public Contato() {
	};

	public Contato(String id, String nome, String telefone) {
		this.id = new Integer(id);
		this.nome = nome;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Connection getConexao() {
		// TODO Auto-generated method stub
		return null;
	}

}
