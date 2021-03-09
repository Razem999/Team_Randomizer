import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class RandomizerEvent extends EventObject {

    private int rnum;
    private int players;
    private String name;

    public RandomizerEvent(Randomizer randomizer, int rnum, int players, String playerName) {
        super(randomizer);
        this.rnum = rnum;
        this.players = players;
        this.name = playerName;
    }

    public int getRnum() {return rnum;}
    public int getPlayers() {return players;}
    public String getPlayerName() {return name;}



}
