package br.edu.unicapital.agenda.controller;

import java.util.List;

import br.edu.unicapital.agenda.model.Contato;
import br.edu.unicapital.agenda.view.AgendaTela;

public class Agenda {
	List<Contato> contatos;
	int crivo;
	static ContatoDao contatoDao;

	public static void main(String[] args) {
		Agenda agenda = new Agenda();
		contatoDao = new ContatoDao();
		agenda.contatos = contatoDao.getListar();
		agenda.crivo = 0;
		AgendaTela tela = new AgendaTela("Agenda", agenda);
	}

	public Contato gravar(Contato c) {
		for (Contato contato : contatos) {
			if (contato.getId() == c.getId()) {
				contato.setNome(c.getNome());
				contato.setTelefone(c.getTelefone());
				return contato;
			}
		}
		contatoDao.adicionar(c);
		contatos.add(c);
		crivo = contatos.size();
		return new Contato("" + contatos.size(), "", "");

	}

	public Contato editar(Contato tela) {
		Contato c = contatos.get(crivo);
		c.setNome(tela.getNome());
		c.setTelefone(tela.getTelefone());
		contatoDao.alterar(c);
		return c;
	}

	public Contato anterior() {
		crivo--;
		if (contatos.isEmpty()) {
			crivo = 0;
			return new Contato();
		}
		if (crivo <= 0)
			crivo = 0;
		return contatos.get(crivo);
	}

	public Contato proximo() {
		
		crivo++;
		if (contatos.isEmpty()) {
			crivo = 0;
			return new Contato();
		}
		if (crivo >= contatos.size())
			crivo = contatos.size() - 1;
		return contatos.get(crivo);
		
		
	}

	public Contato apagar() {
		if (!contatos.isEmpty()) {
			contatoDao.excluir(contatos.get(crivo));
			contatos.remove(crivo);
		}
		return anterior();
	}

	public Contato novo() {
		Contato c = new Contato("" + (maiorContato() + 1), "", "");
		return c;
	}

	private int maiorContato() {
		if (contatos.isEmpty())
			return 0;
		return contatos.get(contatos.size() - 1).getId();
	}

	public Contato buscar(String nome) {
		for (Contato c : contatos) {
			if (c.getNome().toLowerCase().contains(nome.toLowerCase()))
				return c;
		}
		return novo();
	}

}
