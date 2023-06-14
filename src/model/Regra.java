package model;
import java.util.List;
import java.util.Scanner;

class Regra {

	//Implementaçao inicial 
	//Modificações estruturais podem ocorrer
	//O codigo foca na logica principal das regras
	
	
//---- Regras Iniciais
		
	
	public int RegraI1( Peao p, int dado ) { //recebe um peao e coloca na saida 
		// coloca um peao na posicao de saida
			
		if (dado == 5 && p.isCasaInicial()) {
			
			//coloco casa de saida
						
			int rPeoesSaida =  VerificaPeoesSaida(p.getPlayerPai());
			int rPeoesAtivos =	 VerificaPeoesAtivos(p.getPlayerPai());
						
			if (rPeoesSaida == 1 && rPeoesAtivos ==1) {
						
				//atualizo o peao  // modificar
				
//				if (p.getIntCor()==0) { //vermelho				
//					p.setXY(Peao.getPosicaoCasaSaidaVermelho());
//				}
//				else if (p.getIntCor()==1) { //verde
//					p.setXY(Peao.getPosicaoCasaSaidaVerde());                 *** atualizar toda essa parte
//				}
//				else if (p.getIntCor()==2) {
//					p.setXY(Peao.getPosicaoCasaSaidaAmarelo());
//				}
//				else {
//					p.setXY(Peao.getPosicaoCasaSaidaAzul());
//					
//				}
				
				p.setCasaSaida(true);
				p.setCasaInicial(true);
				p.setPosicao(0);
				
				return 1;
			}
			
			// escolho outro peao para mover
			else {
				
				System.out.println("Digite o numero do peao que deseja mover:");
				Scanner sc= new Scanner(System.in);
				
				int opcaoPeao= sc.nextInt();
				sc.close();
				
				
				if (opcaoPeao>4 || opcaoPeao<0) {				
					return 0;
				}
				
					
				else {		
					
					Peao pEscolhido;
					
					Player pai = p.getPlayerPai();
					
					if (opcaoPeao==1) {
						pEscolhido = pai.getPeao1();
						
					}
					else if (opcaoPeao==2) {
											
						pEscolhido = pai.getPeao2();					
										}
					else if (opcaoPeao==3) {
											
						pEscolhido = pai.getPeao3();
					}
					
					else {
						pEscolhido = pai.getPeao4();
						
					}
					
					
					//pEscolhido.MovePeao(dado);	// move depois de escolhido		**atualizar	
				}					
			}			
		}	
		
		return 0;
	}
	
	public int VerificaPeoesSaida(Player pai) {
		
		// 0 para falhas , 1 para sucesso		
		 // verifica pelo pai do peao
		
		if (pai.getPeao1().isCasaSaida()==true) {			
			return 0;
		}
		
		else if (pai.getPeao2().isCasaSaida()==true) {			
			return 0;
		}
		
		else if (pai.getPeao3().isCasaSaida()==true) {					
			return 0;
			
		}
		
		else if (pai.getPeao4().isCasaSaida()==true) {		
			return 0;
			
		}	
		return 1;		
	}
	
	public int VerificaPeoesAtivos(Player pai) {
			
		if (pai.get_qtd_ativo()==4) {		
			return 0;
		}	
		return 1;	
	}
	

	
//---- Regras Básicas
	
	public int regraB1(Peao p, int x) { // verifica se o peao se desloca corretamente
		
		if (x<=0) { // erro de andar no sentido antihorario
			return 0;
		}
		
        if(p.isCasaFinal() == true) {
            return 1;
        }

        else {
            return 0;
        }
    }

    public int regraB2(Peao p) { // verifica se o peao esta na casa de saida ou na inicial
        if(p.isCasaSaida() == true ||  p.isCasaInicial() == true) {
            return 1;
        }

        else {
            return 0;
        }
    }

   public int regraB3(Peao p) { // Regra se o peao esta na barreira ou no abrigo
        if(p.isBarreira() == true ||  p.isAbrigo() == true) {
            return 1;
        }

        else {
            return 0;
        }
    }


// ----- Demais Regras 

   public int Regra6(Peao peao, Dado dado){ // regra de tirar 6 no dado
	
	int contador=1;
	
    for(int i = 0; i<3; i++){
    	// verifica se o número sorteado no dado é 6
    	if (dado.get_face() == 6){
    		dado.joga_dado(); // move o peão
    		// incrementa o contador de seis
    		
    		// verifica se o jogador obteve um 6 pela terceira vez consecutiva
    		if (contador == 3) {
    			peao.setPosicao(0); // retorna o peão para a casa inicial
    			contador = 0; // zera o contador de seis
    			return 1;
    		}
    	} else {
    		contador = 0;
    	}
    	
    }
    
    return 0;
}

	public int RegraBR(Peao peao, Dado dado, List<Peao> peoes) {
	    int posicaoFinal = peao.getPosicao() + dado.get_face();
	    for (Peao p : peoes) {
	        // verifica se o peão está na mesma pista e na casa à frente
	        if (p.getCorId() == peao.getCorId() && p.getPosicao() == posicaoFinal) {
	            return 1; // há um peão na frente
	        }
	    }
	    return 0; // não há peões na frente
	}

	public int RegraCA(Peao peao, Dado dado, List<Peao> peoes) {
	    int posicaoFinal = peao.getPosicao() + dado.get_face();
	    for (Peao p : peoes) {
	        if (p.getCorId() != peao.getCorId() && p.getPosicao() == posicaoFinal) {
	        	if (peao.isNoTabuleiro() && peao.isAbrigo() && peao.isCasaSaida() && peao.isCasaInicial() && peao.isCasaFinal()){
	                // movimenta o peão de volta para casa inicial
	                peao.setPosicao(0);
	                return 1; // retorna true para indicar que o peão foi movido de volta para casa inicial
	            
	        	} else {
	                return 0; // há um peão na frente, mas não é possível mover o peão de volta para casa inicial
	            }
	        }
	    }
	    return 0; // não há peões na frente
	}

	public int verificarCasaFinal(Peao peao, int valorDado, List<Peao> peoes) {
	    if (peao.getPosicao() >= 52) { // verifica se o peão já está na reta final
	        int casasRestantes = 58 - peao.getPosicao(); // calcula a quantidade de casas restantes na reta final
	        if (valorDado == casasRestantes) { // verifica se o valor do dado é igual à quantidade de casas restantes
	            // o jogador pode mover mais 6 casas com qualquer outro peão
	            for (Peao p : peoes) {
	                if (p.getCorId() == peao.getCorId() && !(p.isCasaFinal()) && p.getPosicao() != 0) {
	                    // movimenta o peão em 6 casas
	                    int novaPosicao = p.getPosicao() + 6;
	                    if (novaPosicao > 52 && novaPosicao <= 58) {
	                        p.setPosicao(novaPosicao);
	                        return 1; // retorna true para indicar que o peão foi movido
	                    }
	                }
	            }
	        } else {
	            return 0; // o valor do dado não é igual à quantidade de casas restantes
	        }
	    }
	    return 0; // o peão ainda não está na reta final
	}

	public int todosPeoesNaCasaFinal(List<Peao> peoes) {
		for (Peao p : peoes){
	        if (p.getPosicao() < 58) {
	            return 0;
	        }
	    }
	    return 1;
	}

	public int somaDistancias(List<Peao> peoes) {
	    int soma = 0;
	    for (Peao p : peoes){
	        soma += p.getPosicao();
	    }
	    return soma;
	}
}