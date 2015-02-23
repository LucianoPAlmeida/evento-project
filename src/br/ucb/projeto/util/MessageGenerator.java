package br.ucb.projeto.util;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Pessoa;

public class MessageGenerator {
	public static String generateText(Pessoa pessoa){
		@SuppressWarnings("unused")
		EventoDAO daoEvento = new EventoDAO();
		
		StringBuilder text = new StringBuilder("");
		text.append("Ol� "+pessoa.getNome()+"\nSua inscri��o foi realizada com sucesso.\nLevar documento com foto para confirma��o das palestras escolhidas que ser� realizada no dia do evento.\nObs: Durante o evento haver� arrecada��o de alimentos n�o perec�veis, que ser�o doados para institui��es de caridade. Ao doar voc� concorre a dois ingressos para a TWB's Party, no La Ursa, dia 20/03.");
		return text.toString();
	}
}
