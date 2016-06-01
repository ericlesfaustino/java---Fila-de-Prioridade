package TrabalhoInterdisciplinar;

public class NoPrioridade {
	private NoPrioridade next;
	private Operacao operacao;
	private NoPrioridade prev;
	private int prioridade;
	private long tempoFim;
	private long tempoIni;		
	
	public NoPrioridade getNext() {
		return next;
	}
	public void setNext(NoPrioridade next) {
		this.next = next;
	}
	public Operacao getOperacao() {
		return operacao;
	}
	public void setOperacao(Operacao operacao) {
		this.operacao = operacao;
	}
	public NoPrioridade getPrev() {
		return prev;
	}
	public void setPrev(NoPrioridade prev) {
		this.prev = prev;
	}
	public int getPrioridade() {
		return prioridade;
	}	
	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}
	public long getTempoFim() {
		return tempoFim;
	}
	public void setTempoFim(long tempoFim) {
		this.tempoFim = tempoFim;
	}
	public long getTempoIni() {
		return tempoIni;
	}
	public void setTempoIni(long tempoIni) {
		this.tempoIni = tempoIni;
	}
}