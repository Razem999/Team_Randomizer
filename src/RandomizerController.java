import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomizerController implements ActionListener{

    private Randomizer ra;
    private boolean random = false;
    private boolean randomized = false;
    private boolean playerAdded = false;

    public RandomizerController(Randomizer ra) {
        this.ra = ra;
    }

    public boolean getPlayerAdded() {
        return playerAdded;
    }

    public void togglePlayerAdded() {
        playerAdded = !playerAdded;
    }

    public void toggleRandom() {
        random = !random;
    }

    public boolean getRandom() {
        return random;
    }

    public void toggleRandomized() {
        randomized = !randomized;
    }

    public boolean getRandomized() {
        return randomized;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Add Players") {
            int number = Integer.parseInt(JOptionPane.showInputDialog("Enter number of players:"));
            ra.setPlayerCount(number);
            for(int i=1; i < number + 1; i++) {
                String name = JOptionPane.showInputDialog("Enter name for Player " + i);
                ra.addPlayer(name);
            }
        }
        else if(e.getActionCommand() == "Randomize") {
            toggleRandom();
            randomized = true;
            ra.randomizePlayers();
        }

    }
}
