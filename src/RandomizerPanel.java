import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.*;

public class RandomizerPanel extends JPanel implements RandomizerView{

    private DefaultListModel<String> playerNames = new DefaultListModel<>();
    private DefaultListModel<String> temp = new DefaultListModel<>();
    private JTextArea playerText;
    private JTextArea team1Text;
    private JTextArea team2Text;
    Randomizer ra = new Randomizer();
    RandomizerController rac = new RandomizerController(ra);

    public RandomizerPanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(450, 500));


        ra.addRandomizerView(this);


        JTabbedPane pane = new JTabbedPane();


        ImageIcon tab0Icon = new ImageIcon(
                this.getClass().getResource(""));
//        ImageIcon tab1Icon = new ImageIcon(
//                this.getClass().getResource(""));
        ImageIcon tab1Icon = new ImageIcon(
                this.getClass().getResource(""));
        ImageIcon tab2Icon = new ImageIcon(
                this.getClass().getResource(""));

        JPanel content0 = new JPanel(new GridBagLayout());
        GridBagConstraints main = new GridBagConstraints();
//        JPanel content1 = new JPanel();
        JPanel content1 = new JPanel();
        JPanel content2 = new JPanel();

        main.insets = new Insets(5,5, 5, 5);

//        content0.setLayout(new FlowLayout(FlowLayout.CENTER));

        pane.addTab("Main", tab0Icon, content0, "Randomizer");
        pane.setMnemonicAt(0, KeyEvent.VK_1);
//        pane.addTab("Teams", tab1Icon, content1, "Random Number Picker");
//        pane.setMnemonicAt(1, KeyEvent.VK_2);
        pane.addTab("Weapons", tab1Icon, content1, "Random Weapon Picker");
        pane.setMnemonicAt(1, KeyEvent.VK_3);
        pane.addTab("Map", tab2Icon, content2, "Random Map Picker");
        pane.setMnemonicAt(2, KeyEvent.VK_4);

        this.add(pane, BorderLayout.CENTER);

        content0.setBackground(Color.WHITE);

        JLabel mLabel = new JLabel("<html><body>" +
                "<h2>Welcome to Destiny 2 Scrims Randomizer!</h2><br>");

        mLabel.setHorizontalAlignment(SwingUtilities.CENTER);
        main.anchor = GridBagConstraints.NORTHWEST;
        main.gridx = 0;
        main.gridy = 0;
        main.gridwidth = 5;
        main.fill = GridBagConstraints.HORIZONTAL;
        content0.add(mLabel, main);

        JButton addPlayerBtn = new JButton("Add Players");
        addPlayerBtn.addActionListener(rac);
        //addPlayerBtn.setActionCommand("ADD");
        main.gridx = 0;
        main.gridy = 1;
        main.gridwidth = 1;
        main.gridheight = 1;
        content0.add(addPlayerBtn, main);

        JButton removePlayerBtn = new JButton("Remove Player");
        main.gridx = 1;
        main.gridy = 1;
        main.gridwidth = 1;
        main.gridheight = 1;
        content0.add(removePlayerBtn, main);

        JButton clearAll = new JButton("Clear All");
        main.gridx = 2;
        main.gridy = 1;
        main.gridwidth = 1;
        main.gridheight = 1;
        content0.add(clearAll, main);

        JButton randomizeBtn = new JButton("Randomize");
        randomizeBtn.addActionListener(rac);
        main.gridx = 3;
        main.gridy = 1;
        main.gridwidth = 1;
        main.gridheight = 1;
        content0.add(randomizeBtn, main);

        playerText = new JTextArea(10, 20);
        playerText.setEditable(false);
        JScrollPane playerPane = new JScrollPane(playerText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playerPane.setBorder(BorderFactory.createTitledBorder("Players"));
        main.gridx = 0;
        main.gridy = 3;
        main.gridheight = 10;
        main.gridwidth = 40;
        main.fill = GridBagConstraints.VERTICAL;
        content0.add(playerPane, main);

        team1Text = new JTextArea(4, 15);
        team1Text.setEditable(false);
        JScrollPane team1Pane = new JScrollPane(team1Text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        team1Pane.setBorder(BorderFactory.createTitledBorder("Team 1"));
        main.gridx = 2;
        main.gridy = 3;
        main.gridheight = 4;
        main.gridwidth = 40;
        main.fill = GridBagConstraints.VERTICAL;
        content0.add(team1Pane, main);

        team2Text = new JTextArea(4, 15);
        team2Text.setEditable(false);
        JScrollPane team2Pane = new JScrollPane(team2Text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        team2Pane.setBorder(BorderFactory.createTitledBorder("Team 2"));
        main.gridx = 2;
        main.gridy = 7;
        main.gridheight = 4;
        main.gridwidth = 40;
        main.fill = GridBagConstraints.VERTICAL;
        content0.add(team2Pane, main);

//        list.setBounds(200,200,75,75);
//        list.setLayoutOrientation(JList.VERTICAL);
//        list.setVisibleRowCount(-1);
        JList list = new JList(playerNames);

    }

    private static void RandomizerFrame() {

        JPanel panel = new RandomizerPanel();
        panel.setOpaque(true);

        JFrame frame = new JFrame("Destiny 2 Scrims");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

    }


    @Override
    public void handleRandomizerUpdate(RandomizerEvent e) {
        int rnum = e.getRnum();
        int players = e.getPlayers();
        Randomizer ra = (Randomizer) e.getSource();
        if(rac.getPlayerAdded() == false) {
            playerNames.addElement(e.getPlayerName());
            playerText.append(e.getPlayerName() + "\n");
            if(playerNames.getSize() == players) {
                rac.togglePlayerAdded();
            }
        }

        if(rac.getRandom() == true) {
            temp = playerNames;
            double tempSize = Math.ceil(temp.getSize());
            for(int i = 0; i < 2; i++) {
                for (int j = 0; j < tempSize; j++) {   // Browsing through each player
                    if(tempSize == 2) {
                        break;
                    } else if(i == 0) {    // i = 0 indicates team 1
                        team1Text.append(temp.remove(j) + "\n");
                    } else if(i == 1) {
                        team2Text.append(temp.remove(j) + "\n");
                    }
                }
            }
            rac.toggleRandom();
        }
    }

    @Override
    public void handleRandomizeWeaponsUpdate(RandomizerEvent e) {

    }

    public static void main(String[] args) {
        new RandomizerPanel();
        RandomizerPanel.RandomizerFrame();
    }

}

