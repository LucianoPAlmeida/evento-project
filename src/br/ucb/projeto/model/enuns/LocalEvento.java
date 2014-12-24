package br.ucb.projeto.model.enuns;

public enum LocalEvento {
	FNAC("FNAC"),LIVRARIA_CULTURA("Livraria Cultura"),UCB("UCB");
	
	private String local;
	private LocalEvento(String local){
		this.local = local;
	}
	
	@Override
	public String toString() {
		return this.local;
	}
}