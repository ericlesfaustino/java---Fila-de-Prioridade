package TrabalhoInterdisciplinar;

import javax.swing.JOptionPane;

public class UsaFilaDePrioridade {

	static FilaDePrioridade fila1;
	static FilaDePrioridade fila2;	
	static CentralDeImpressao centralDeImpressao;
		 
	public static void main(String[] args) throws FuraoException {	  
		centralDeImpressao = new CentralDeImpressao();
		fila1 = new FilaDePrioridade();
		fila2 = new FilaDePrioridade();	
		menu();
	}
	 
	public static void menu(){
		try{
			int opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma op��o: "
						+ "\n1 - Enfileirar"
						+ "\n2 - Desenfileirar"
						+ "\n3 - Exibir o primeiro da fila"
						+ "\n4 - Exibir o �ltimo da fila"
						+ "\n5 - Mostrar a fila completa"
		                + "\n6 - Verficar se a fila est� vazia"
		                + "\n7 - Relat�rio de multas"
		                + "\n8 - Relat�rio de atendimentos"
		                + "\n9 - Verificar o tamanho da fila"
		                + "\n10 - Sair "));
			
			if (opcao < 1 || opcao > 10) {
				throw new Exception("Op��o inv�lida! Digite uma op��o de 1 a 10.");
			}
			switch (opcao) {
			case 1:				
				menuInsercao();					
				menu();
				break;
			case 2:
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}else {	
						fila1.definirTempoFinal(fila1.noFrente());
						fila2.enfileirarAtendimentosRealizados(fila1.noFrente());						
						fila1.desenfileirar();						
						menu();						
					}				
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "N�o h� ningu�m a ser atendido.");
					menu();
				}
				break;				     
			case 3:
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}else {
						JOptionPane.showMessageDialog(null, "A pessoa da frente �: " + fila1.frente().getDono() + "(" + fila1.noFrente().getPrioridade() + ").");
						menu();
					}				
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "N�o h� ningu�m na fila para ser o primeiro.");
					menu();
				}				
				break;	     
			case 4:
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}
					JOptionPane.showMessageDialog(null, "A �ltima pessoa da fila �: " + fila1.cauda().getDono());
					menu();
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "N�o h� ningu�m na fila para ser o �ltimo.");
					menu();
				}				
	    	 	break;	      
			case 5:				
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}
					centralDeImpressao.imprimirFila(fila1);
					menu();
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "N�o h� ningu�m na fila.");
					menu();
				}				
				break;	     
			case 6:				
				if (fila1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fila vazia. N�o h� atendimento(s) a ser(em) realizado(s)!"); 
				}else {
					JOptionPane.showMessageDialog(null, "Ainda h� " + fila1.size() + " atendimento(s) pendente(s)!");
				}				
				menu();
				break;	     
			case 7:
				fila1.calcularMulta();
				menu();
				break;	     
			case 8:
				try {
					if (fila2.isEmpty()) {
						throw new FilaVaziaException();
					}else {
						fila2.atendimentosRealizados();
						menu();
					}
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "Ning�em foi atendido ainda.");
					menu();
				}		
				break;	  
			case 9:
				JOptionPane.showMessageDialog(null, "H� " + fila1.size() + " pessoa(s) na fila.");
				menu();
				break;
			case 10:
				JOptionPane.showMessageDialog(null, "Voc� saiu!");				
				break;	      
			}
		}catch(FilaVaziaException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			menu();
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Digite um valor num�rico inteiro!");
			menu();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			menu();
		}
	}
	  
	public static void menuInsercao(){		
		try {
			int opcao1 = Integer.parseInt(JOptionPane.showInputDialog("Digite a opera��o a ser realizada!"
					+ "\n1 - Inserir Gestante"
					+ "\n2 - Inserir Idoso"
					+ "\n3 - Inserir Caixa R�pido"
					+ "\n4 - Inserir Caixa Normal"
					+ "\n5 - Tentar furar fila"
					+ "\n6 - Regressar ao menu principal"));	   
	      
			if (opcao1 < 1 || opcao1 > 6) {
				throw new Exception("Digite uma op��o v�lida de 1 � 6");
			}			
			if (opcao1 == 1) {
				fila1.enfileirar(new Gestante (JOptionPane.showInputDialog("Digite o nome da usu�ria para atendimento de gestantes.")));
			}else if (opcao1 == 2) {
				fila1.enfileirar(new Idoso (JOptionPane.showInputDialog("Digite o nome do usu�rio para o atendimento de idosos.")));
			}else if (opcao1 == 3) {
				fila1.enfileirar(new CaixaRapido (JOptionPane.showInputDialog("Digite o nome do usu�rio para o atendimento de caixa r�pido.")));
			}else if(opcao1 == 4){
				fila1.enfileirar(new CaixaNormal (JOptionPane.showInputDialog("Digite o nome do usu�rio para o atendimento de caixa normal.")));
			}else if(opcao1 == 5){
				throw new FuraoException(); 
			}else {
				
			}			
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Digite um valor num�rico inteiro");
			menuInsercao();
		}catch(FuraoException e){
			JOptionPane.showMessageDialog(null, "Voc� foi barrado. Que deselegante, n�o fure a fila!");
			menuInsercao();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			menuInsercao();
		}
	}		
}