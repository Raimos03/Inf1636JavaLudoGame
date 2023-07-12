package model;
import java.util.List;
import java.util.Scanner;

class Regra {

	//Implementaçao inicial 
	//Modificações estruturais podem ocorrer
	//O codigo foca na logica principal das regras
	
	
//---- Regras Iniciais
		
	
	public int RegraI1( Peao p, int dado ) { //recebe um peao e verifica se esta ok ou nao 
		// coloca um peao na posicao de saida
		
		
			
		if (dado == 5) {
				
			int rPeoesSaida =  VerificaPeoesSaida(p.getPlayerPai()); // 1 se nenhum estiver na casa saida
			int rPeoesAtivos =	 VerificaPeoesAtivos(p.getPlayerPai());
						
			if (rPeoesSaida == 1 && rPeoesAtivos ==1) { // sucesso
				return 1;
			}
			
			// escolho outro peao para mover	
			else { // peao ativa < 4 ou casa de saida livre
				
				System.out.println(">> RegraI1 << - Escolha outro peao que deseja mover:");				

				if(rPeoesSaida==0) { // sem peao na casa de saida
					return 2;
				}
				else 
					return 3;	// peoes ativos	
									
			}			
		}		
		return 0; // dado nao e 5
	}
	
	public int VerificaPeoesSaida(Player pai) {
		
		// 0 para falhas , 1 para sucesso de ter alguem na casa de saida		
		 // verifica pelo pai do peao ,
		
		if (pai.getPeao1().isCasaSaida()==true) {			
			return 1;
		}
		
		else if (pai.getPeao2().isCasaSaida()==true) {			
			return 1;
		}
		
		else if (pai.getPeao3().isCasaSaida()==true) {					
			return 1;
			
		}
		
		else if (pai.getPeao4().isCasaSaida()==true) {		
			return 1;			
		}	
		return 0;		
	}
	
	public int VerificaPeoesAtivos(Player pai) {
			
		if (pai.get_qtd_ativo()<4) {		//retorna 0 se todos estao fora
			return 1;
		}	
		return 0;	
	}
	

	
//---- Regras Básicas
	
	public int RegraB1(Peao p) { // verifica se o peao se desloca corretamente

        if(p.isCasaFinal() == true) {
            return 1;
        }

        return 0;
    }

//	 public int RegraB2(Peao p, Casa c) { // verifica se o peao esta na casa de saida ou na inicial
//	        if(p.isCasaFinal() == true && c.getQtdPeao() > 1) {
//	            return 1;
//	        }
//
//	        else {
//	            return 0; //Retorna se tem menos de 2 peoes ou se a casa nao e final
//	        }
//	    }

	 public int RegraB3(Casa c) { // Regra se o peao esta na barreira ou no abrigo
		 
	        if(c.eAbrigo() == true) {
	            return 1;
	        }
	        else if (c.eCasaSaida() == true) {
	        	
	        	String sp1=c.getCor1();
	        	String sp2=c.getCor2();	        			
	            if(sp1 != null && sp2 != null) { // se tem dois peoes nela
	            	
	                if(!(sp1.equals(sp2))) {	                	
	                    if(c.getCorCasa().equals(sp1) || c.getCorCasa().equals(sp2)) { //pegar cor da casa
	                        return 2; // cores diferentes para barreira
	                    }
	                }
	            }
	            else {
	            	return 3; // nao e barreira com 2 peoes nela, casa saida com 1 peao
	            }
	        }
        
	        return 4; //barreira de qualquer outra cor em qualquer outra casa	        
	    }


// ----- Demais Regras 

	 public int r6_counter = 0;
     public int index = -1;
     public int Regra6(int d, int i) {
         if(d == 6 && r6_counter != 3 && index == -1) {
             this.r6_counter += 1;
             index = i;
             return 1;
         }

         else if (d == 6 && r6_counter != 3 && index == i) {
             this.r6_counter += 2;
             return 1;
         }

         else if(d == 6 && r6_counter == 3) {
             this.r6_counter = 0;
             index = -1;
             return 2;
         }

         else {
             this.r6_counter = 0;
             index = -1;
             return 3;
         }
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
	
	

	public int RegraCA(Peao peao, Object[] obj, int dado) {	
		
		
		int posfinal =  peao.getPosicao();
		if (posfinal==-1){
			return 0;
		}
		
		System.out.println(">>>>> Regra ca X: "+ posfinal);
//		if(posfinal>=52) {
//			posfinal=posfinal%52;
//		}
		
		String corpeao=peao.getCor();
		Casa casafinal = (Casa) obj[posfinal]; // vetor de peoes
		
		//&&casafinal.getCor1()!=null &&casafinal.getCor2()!=null
        if(casafinal.getQtdPeao()==2 ) {
   		 	
        	if(casafinal.getCor1().equals(corpeao)==false || casafinal.getCor2().equals(corpeao)==false) {
	              System.out.println("--- >>>>> Regra captura ativa: c1 "+ posfinal);
	              return 1;
	            
        	}
        	
        }      
        return -1;
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