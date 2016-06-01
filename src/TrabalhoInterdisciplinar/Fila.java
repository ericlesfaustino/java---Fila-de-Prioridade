package TrabalhoInterdisciplinar;

public interface Fila {
	
	Operacao desenfileirar() throws FuraoException;
	void enfileirar(Operacao op) throws FuraoException;
	Operacao frente();
	boolean isEmpty();
	int size();
}
