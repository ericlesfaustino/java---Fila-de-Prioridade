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
			int opcao = Integer.parseInt(JOptionPane.showInputDialog("Escolha uma opção: "
						+ "\n1 - Enfileirar"
						+ "\n2 - Desenfileirar"
						+ "\n3 - Exibir o primeiro da fila"
						+ "\n4 - Exibir o último da fila"
						+ "\n5 - Mostrar a fila completa"
		                + "\n6 - Verficar se a fila está vazia"
		                + "\n7 - Relatório de multas"
		                + "\n8 - Relatório de atendimentos"
		                + "\n9 - Verificar o tamanho da fila"
		                + "\n10 - Sair "));
			
			if (opcao < 1 || opcao > 10) {
				throw new Exception("Opção inválida! Digite uma opção de 1 a 10.");
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
					JOptionPane.showMessageDialog(null, "Não há ninguém a ser atendido.");
					menu();
				}
				break;				     
			case 3:
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}else {
						JOptionPane.showMessageDialog(null, "A pessoa da frente é: " + fila1.frente().getDono() + "(" + fila1.noFrente().getPrioridade() + ").");
						menu();
					}				
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "Não há ninguém na fila para ser o primeiro.");
					menu();
				}				
				break;	     
			case 4:
				try {
					if (fila1.isEmpty()) {
						throw new FilaVaziaException();
					}
					JOptionPane.showMessageDialog(null, "A última pessoa da fila é: " + fila1.cauda().getDono());
					menu();
				} catch (FilaVaziaException e) {
					JOptionPane.showMessageDialog(null, "Não há ninguém na fila para ser o último.");
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
					JOptionPane.showMessageDialog(null, "Não há ninguém na fila.");
					menu();
				}				
				break;	     
			case 6:				
				if (fila1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fila vazia. Não há atendimento(s) a ser(em) realizado(s)!"); 
				}else {
					JOptionPane.showMessageDialog(null, "Ainda há " + fila1.size() + " atendimento(s) pendente(s)!");
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
					JOptionPane.showMessageDialog(null, "Ningúem foi atendido ainda.");
					menu();
				}		
				break;	  
			case 9:
				JOptionPane.showMessageDialog(null, "Há " + fila1.size() + " pessoa(s) na fila.");
				menu();
				break;
			case 10:
				JOptionPane.showMessageDialog(null, "Você saiu!");				
				break;	      
			}
		}catch(FilaVaziaException e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			menu();
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Digite um valor numérico inteiro!");
			menu();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
			menu();
		}
	}
	  
	public static void menuInsercao(){		
		try {
			int opcao1 = Integer.parseInt(JOptionPane.showInputDialog("Digite a operação a ser realizada!"
					+ "\n1 - Inserir Gestante"
					+ "\n2 - Inserir Idoso"
					+ "\n3 - Inserir Caixa Rápido"
					+ "\n4 - Inserir Caixa Normal"
					+ "\n5 - Tentar furar fila"
					+ "\n6 - Regressar ao menu principal"));	   
	      
			if (opcao1 < 1 || opcao1 > 6) {
				throw new Exception("Digite uma opção válida de 1 á 6");
			}			
			if (opcao1 == 1) {
				fila1.enfileirar(new Gestante (JOptionPane.showInputDialog("Digite o nome da usuária para atendimento de gestantes.")));
			}else if (opcao1 == 2) {
				fila1.enfileirar(new Idoso (JOptionPane.showInputDialog("Digite o nome do usuário para o atendimento de idosos.")));
			}else if (opcao1 == 3) {
				fila1.enfileirar(new CaixaRapido (JOptionPane.showInputDialog("Digite o nome do usuário para o atendimento de caixa rápido.")));
			}else if(opcao1 == 4){
				fila1.enfileirar(new CaixaNormal (JOptionPane.showInputDialog("Digite o nome do usuário para o atendimento de caixa normal.")));
			}else if(opcao1 == 5){
				throw new FuraoException(); 
			}else {
				
			}			
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Digite um valor numérico inteiro");
			menuInsercao();
		}catch(FuraoException e){
			JOptionPane.showMessageDialog(null, "Você foi barrado. Que deselegante, não fure a fila!");
			menuInsercao();	
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			menuInsercao();
		}
	}		
}