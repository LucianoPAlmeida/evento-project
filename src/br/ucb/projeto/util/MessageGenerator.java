package br.ucb.projeto.util;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Pessoa;

public class MessageGenerator {
	public static String generateText(Pessoa pessoa){
		@SuppressWarnings("unused")
		EventoDAO daoEvento = new EventoDAO();
		
		StringBuilder text = new StringBuilder("");
		
		return text.toString();
	}
}
