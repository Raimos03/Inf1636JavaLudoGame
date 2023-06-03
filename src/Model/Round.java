package Model;

class Round {

    //Variable that marks the current player's turn
    public int player_turn = 1;
    public int c;
    int turn = 1;

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
}