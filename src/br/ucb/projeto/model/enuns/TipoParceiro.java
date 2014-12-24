package br.ucb.projeto.model.enuns;

public enum TipoParceiro {
	APOIO("Apoio"),PATROCINADOR("Patrocinador");
	
	private String tipo;
	
	private TipoParceiro(String tipo){
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return this.tipo;
	}
}
