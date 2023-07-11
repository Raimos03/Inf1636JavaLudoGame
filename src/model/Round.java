package model;

class Round {

    //Variable that marks the current player's turn
	//Gerencia o jogador da vez
	
    private int player_turn = 0;
    private int c;
    private int turn = 0;

    public void New_round() {	
        if(turn >=0 && turn <= 3) {
            player_turn = turn;
        }
        else {
            c = turn % 4;

            if(c == 0) {
                player_turn = 0;
            }

            else {
                player_turn = c;
            }
        }       
        
    }
    
    
    
    public void nextRound() {
    	turn++;
    }

    public int getIntPlayerVez() {
    	return this.player_turn;
    }
    public void setIntPlayerVez(int n) {
    	this.player_turn=n;
    	this.turn=n;
    	
    }
    
}