package br.ucb.projeto.util;

import br.ucb.projeto.model.DAOS.EventoDAO;
import br.ucb.projeto.model.beans.Pessoa;

public class MessageGenerator {
	public static String generateText(Pessoa pessoa){
		@SuppressWarnings("unused")
		EventoDAO daoEvento = new EventoDAO();
		
		StringBuilder text = new StringBuilder("");
		text.append("Olá "+pessoa.getNome()+"\nSua inscrição foi realizada com sucesso.\nLevar documento com foto para confirmação das palestras escolhidas que será realizada no dia do evento.\nObs: Durante o evento haverá arrecadação de alimentos não perecíveis, que serão doados para instituições de caridade. Ao doar você concorre a dois ingressos para a TWB's Party, no La Ursa, dia 20/03.");
		return text.toString();
	}
}
