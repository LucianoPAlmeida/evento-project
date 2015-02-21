package br.ucb.projeto.model.enuns;

public enum LocalEvento {
	FNAC("FNAC"),LA_URSA("La Ursa"),UCB("UCB");
	
	private String local;
	private LocalEvento(String local){
		this.local = local;
	}
	
	@Override
	public String toString() {
		return this.local;
	}
}