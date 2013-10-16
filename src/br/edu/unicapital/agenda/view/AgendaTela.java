package br.edu.unicapital.agenda.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.unicapital.agenda.controller.Agenda;
import br.edu.unicapital.agenda.model.Contato;

public class AgendaTela extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	Agenda controller;
	
	
	JPanel painel;
	JLabel idLb;
	JTextField idTf;
	JLabel nomeLb;//texto fixo nao da para mudar
	JTextField nomeTf;
	JLabel telefoneLb;
	JTextField telefoneTf;//campo para escrever ou digitar
	JButton anteriorBt;
	JButton proximoBt;
	JButton gravarBt;//botao
	JButton apagarBt;
	JButton novoBt;
	JButton buscarBt;
	JButton editarBt;
	
	
	public AgendaTela(String titulo, Agenda controller) {
		super(titulo);
		this.controller = controller;
		painel = new JPanel();
		painel.setLayout(new GridLayout(7, 2));
		idLb = new JLabel("Id:");
		idTf = new JTextField();
		idTf.setEnabled(false);
		idTf.setText("0");
		nomeLb = new JLabel("Nome:");
		nomeTf = new JTextField();
		telefoneLb = new JLabel("Telefone:");
		telefoneTf = new JTextField();
		anteriorBt = new JButton("<");
		proximoBt = new JButton(">");
		gravarBt = new JButton("gravar");
		apagarBt = new JButton("apagar");
		novoBt = new JButton("novo");
		buscarBt = new JButton("buscar");
		editarBt = new JButton("editar");
		
		Escutador escutador = new Escutador();
		
		anteriorBt.addActionListener(escutador);
		proximoBt.addActionListener(escutador);
		gravarBt.addActionListener(escutador);
		apagarBt.addActionListener(escutador);
		novoBt.addActionListener(escutador);
		buscarBt.addActionListener(escutador);
		editarBt.addActionListener(escutador);
		
		this.add(painel);
		painel.add(idLb);
		painel.add(idTf);
		painel.add(nomeLb);
		painel.add(nomeTf);
		painel.add(telefoneLb);
		painel.add(telefoneTf);
		painel.add(anteriorBt);
		painel.add(proximoBt);
		painel.add(gravarBt);
		painel.add(apagarBt);
		painel.add(novoBt);
		painel.add(buscarBt);
		painel.add(editarBt);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(300, 300);
		this.setVisible(true);
	}
	
	public class Escutador implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(anteriorBt)) {
				anterior();
			} else if (e.getSource().equals(proximoBt)) {
				proximo();
			} else if (e.getSource().equals(gravarBt)) {
				gravar();
			} else if (e.getSource().equals(apagarBt)) {
				apagar();
			} else if (e.getSource().equals(novoBt)) {
				novo();
			} else if (e.getSource().equals(buscarBt)) {
				buscar();
			} else if (e.getSource().equals(editarBt)) {
				editar();
			}
		}

		private void gravar() {
			Contato c = new Contato(idTf.getText(), nomeTf.getText(), telefoneTf.getText());
			desenhar(controller.gravar(c));
		}
		
		private void editar() {
			Contato c = new Contato(idTf.getText(), nomeTf.getText(), telefoneTf.getText());
			desenhar(controller.editar(c));
		}

		private void novo() {
			desenhar(controller.novo());
		}

		private void apagar() {
			desenhar(controller.apagar());
		}

		private void proximo() {
			desenhar(controller.proximo());
		}

		private void anterior() {
			desenhar(controller.anterior());
			
		}
		
		private void buscar() {
			String nome = JOptionPane.showInputDialog("Digite o nome");
			desenhar(controller.buscar(nome));
		}

	}
	
	public void desenhar(Contato c) {
		idTf.setText("" + c.getId());
		nomeTf.setText(c.getNome());
		telefoneTf.setText(c.getTelefone());
	}
	
}

