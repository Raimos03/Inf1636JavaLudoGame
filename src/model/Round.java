package model;

class Round {

    //Variable that marks the current player's turn
	//Gerencia o jogador da vez
	
    private int player_turn = 1;
    private int c;
    private int turn = 1;

    public void New_round() {
	
        if(turn !=0 && turn <= 4) {
            player_turn = turn;
        }
        else {
            c = turn % 4;

            if(c == 0) {
                player_turn = 1;
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
    
}