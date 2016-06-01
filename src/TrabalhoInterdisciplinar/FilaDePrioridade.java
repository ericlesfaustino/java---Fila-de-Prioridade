package TrabalhoInterdisciplinar;

import javax.swing.JOptionPane;

public class FilaDePrioridade implements Fila, Imprimivel {	
	private NoPrioridade frente, cauda;
	private int tam;
	private static int multa;
	
	public FilaDePrioridade() {
	    frente = new NoPrioridade();  
	    cauda = new NoPrioridade();
	    cauda.setPrev(frente);
	    frente.setNext(cauda);
	    cauda.setPrioridade(5);
	    frente.setPrioridade(5);
	    tam = 0;
	    multa = 0;
	}
	public Operacao frente() {	
	    return frente.getNext().getOperacao();
	}	   
	public NoPrioridade noFrente(){
		return frente.getNext();
	}
	public Operacao cauda() {		
		return cauda.getPrev().getOperacao();
	}
	public boolean isEmpty() {		
		return tam == 0;
	}
	public int size() {
	    return tam;
	}
	public void definirTempoFinal(NoPrioridade np){
		 np.setTempoFim(System.currentTimeMillis());
	}
	public Operacao desenfileirar() {					
		if (((frente.getNext().getTempoFim() - frente.getNext().getTempoIni())/1000) > 15) {
		    	multa += 1;
		}
		Operacao operacao = frente.getNext().getOperacao(); 		
		JOptionPane.showMessageDialog(null, "Atendimento realizado com sucesso. Pessoa atendida: \nNome: " + frente.getNext().getOperacao().getDono() + "(" + frente.getNext().getPrioridade()+ ")");
	    frente.getNext().getNext().setPrev(frente);   
	    frente.setNext(frente.getNext().getNext()); 	   	    
	    tam--;	 	   
	    return operacao;			    
	}	
	public void enfileirar(Operacao op) throws FuraoException {
	    NoPrioridade novoNo = new NoPrioridade();
	    novoNo.setOperacao(op);
	    checarPrioridade(novoNo);
	    if (isEmpty()) {
	    	novoNo.setNext(cauda);
	    	novoNo.setPrev(frente);
	    	frente.setNext(novoNo);
	    	cauda.setPrev(novoNo);		    	
	    }else {
	    	NoPrioridade aux = frente.getNext();
	    	while (aux != cauda) {
	    		if (aux.getPrioridade() > novoNo.getPrioridade()) {
	    			novoNo.setNext(aux);
	    			novoNo.setPrev(aux.getPrev());
	    			aux.getPrev().setNext(novoNo);
	    			aux.setPrev(novoNo);	    			
	    			break;	       
	    		}else if (aux.getPrioridade() <= novoNo.getPrioridade() && novoNo.getPrioridade() < aux.getNext().getPrioridade() ) {
	    			novoNo.setNext(aux.getNext());
	    			novoNo.setPrev(aux);
	    			aux.getNext().setPrev(novoNo);
	    			aux.setNext(novoNo);	    			
	    			break;
	    		}    
	    		aux = aux.getNext();
	    	}
	    }  	
	    novoNo.setTempoIni(System.currentTimeMillis());
	    tam ++;
	    JOptionPane.showMessageDialog(null, "Pessoa adicionada a fila com sucesso.");
	}	
	public void enfileirarAtendimentosRealizados(NoPrioridade no) throws FuraoException {		
		NoPrioridade novoNo = new NoPrioridade();	
		novoNo.setOperacao(no.getOperacao());
		checarPrioridade(novoNo);	
		novoNo.setTempoIni(no.getTempoIni());
		novoNo.setTempoFim(no.getTempoFim());
		novoNo.setNext(cauda);
	    novoNo.setPrev(cauda.getPrev());
		cauda.getPrev().setNext(novoNo);
		cauda.setPrev(novoNo);	   
	    tam++;	 	   
	}	
	public void checarPrioridade(NoPrioridade np) throws FuraoException{	                 
		if (np.getOperacao().getClass() == Gestante.class) {
			np.setPrioridade(1);
		}else if (np.getOperacao().getClass() == Idoso.class) {
			np.setPrioridade(2);
		}else if (np.getOperacao().getClass() == CaixaRapido.class) {
			np.setPrioridade(3);
		}else if (np.getOperacao().getClass() == CaixaNormal.class) {
			np.setPrioridade(4);
	    }else{
	        throw new FuraoException();
	    }	
	}	
	public void imprimir() {
	    String pessoas = "";
	    NoPrioridade aux = frente.getNext();	    
	    while (aux != cauda) {
	    	pessoas += "Nome: " + aux.getOperacao().getDono() + "(" + aux.getPrioridade() + ")\n";
	    	aux = aux.getNext();
	    }
	    JOptionPane.showMessageDialog(null, "Ainda há " + size() + " pessoa(s) à ser(em) atendidada(s).\n" + pessoas);
	}	
	public void atendimentosRealizados() {
		 String pessoas = "";		
		 NoPrioridade aux = frente.getNext();		 
		 while (aux != cauda) {				
			 pessoas += "Nome: " + aux.getOperacao().getDono() + "(" + aux.getPrioridade() + "), tempo gasto: " + ((aux.getTempoFim() - aux.getTempoIni())/1000) + " segundos.\n";
			 aux = aux.getNext();
		 }
		 JOptionPane.showMessageDialog(null, "Atendimentos realizados:\n" + pessoas);
	}	 
	public void calcularMulta(){
		double multaTotal = 1000;
	    if (multa <= 0 ) {
	    	JOptionPane.showMessageDialog(null, "Não há multas!");
	    }else {
	    	JOptionPane.showMessageDialog(null, "A quantidade de multa(s) é: " + multa + ", e o valor a ser pago pela(s) multa(s) diária é de: R$ " + (multa*multaTotal));
	    }
	}		
}