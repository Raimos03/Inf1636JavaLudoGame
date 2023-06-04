package model;

class Round {

    //Variable that marks the current player's turn
	//Gerencia a rodada
	
    private int player_turn = 1;
    private int c;
    private static int turn = 1;
    

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
  
    public static int getRound() {
    	return Round.turn;
    	
    }
    
}