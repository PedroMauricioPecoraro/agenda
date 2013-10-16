package br.edu.unicapital.agenda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicapital.agenda.model.Contato;

public class ContatoDao {
	private Connection connection;
	
	public ContatoDao() {
		this.connection = ConnectionFactory.getConnection();
	}

	public void alterar(Contato contato) {
		String sql = "update contato set nome = ?, telefone = ? where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
			stmt.setInt(3, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void excluir(Contato contato) {
		String sql = "delete from contato where id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void adicionar(Contato contato) {
		String sql = "insert into contato " +
				"(nome, telefone) " +
				"values (?, ?)";
//				"(nome, email, endereco, dataNascimento) " +
//				"values (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getTelefone());
//			stmt.setString(2, contato.getEmail());
//			stmt.setString(3, contato.getEndereco());
//			stmt.setDate(4,  new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));
			
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> getListar() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = connection.prepareStatement("select * from contato");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Contato contato = new Contato();
//				contato.setId(rs.getLong("id"));
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setTelefone(rs.getString("telefone"));
//				contato.setEmail(rs.getString("email"));
//				contato.setEndereco(rs.getString("endereco"));
//				Calendar data = Calendar.getInstance();
//				data.setTime(rs.getDate("dataNascimento"));
//				contato.setDataNascimento(data);
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
