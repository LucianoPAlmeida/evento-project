package br.ucb.projeto.util;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Pessoa;

public class MessageGenerator {
	public static String generateText(Pessoa pessoa){
		@SuppressWarnings("unused")
		EventoDAO daoEvento = new EventoDAO();
		
		StringBuilder text = new StringBuilder("");
		text.append("Olá "+pessoa.getNome()+",\nSua inscrição foi realizada com sucesso!\nPedimos que no dia da palestra você leve um documento com foto para confirmação da vaga.");
		return text.toString();
	}
}
