package br.ucb.projeto.util;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Pessoa;

public class MessageGenerator {
	public static String generateText(Pessoa pessoa){
		@SuppressWarnings("unused")
		EventoDAO daoEvento = new EventoDAO();
		
		StringBuilder text = new StringBuilder("");
		text.append("Ol� "+pessoa.getNome()+",\nSua inscri��o foi realizada com sucesso!\nPedimos que no dia da palestra voc� leve um documento com foto para confirma��o da vaga.");
		return text.toString();
	}
}
