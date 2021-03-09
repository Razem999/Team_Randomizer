import java.util.*;
import java.util.Scanner;

public class Randomizer {

    public enum primWeapons {
        HAND_CANNON,
        AUTO_RIFLE,
        PULSE_RIFLE,
        SCOUT_RIFLE,
        SIDEARM,
        BOWS
    }

    public enum secondWeapons {
        SNIPER,
        SHOTGUN,
        FUSION,
        LINEAR_FUSION,
        GRENADE_LAUNCHER,
        BOWS
    }

    public enum heavyWeapons {
        MACHINE_GUN,
        ROCKETS,
        GRENADE_LAUNCHER,
        SWORD,
        LINEAR
    }

    public enum teams {
        team1,
        team2
    }

    private int rnum;
    private int players;
    private String playerName;
    private List<String> playerList;
    private String PrimaryWeapon;
    private static final List<primWeapons> PW = Collections.unmodifiableList(Arrays.asList(primWeapons.values()));
    private static final List<secondWeapons> SW = Collections.unmodifiableList(Arrays.asList(secondWeapons.values()));
    private static final List<heavyWeapons> HW = Collections.unmodifiableList(Arrays.asList(heavyWeapons.values()));
    private static int SIZE;
    private primWeapons pweapon;
    private secondWeapons sweapon;
    private heavyWeapons hweapon;
    private static final Random RANDOM = new Random();
    private int max;
    private List<RandomizerView> randomizerViews;

    public Randomizer() {
        this.rnum = 6;
        this.players = 6;
        this.max = 6;
        playerList = new ArrayList<>();
        randomizerViews = new ArrayList<>();
    }

    public void randomizeWeapons() {
        SIZE = PW.size();
        SIZE = SW.size();
        SIZE = HW.size();
        pweapon = PW.get(RANDOM.nextInt(SIZE));
        sweapon = SW.get(RANDOM.nextInt(SIZE));
        hweapon = HW.get(RANDOM.nextInt(SIZE));
        //for(RandomizerView rv: randomizerViews) rv.handleRandomizeWeaponsUpdate(new RandomizerEvent(this, pweapon, sweapon, hweapon));
    }

//    public static primWeapons randomizePrimary() {
//        SIZE = PW.size();
//        return PW.get(RANDOM.nextInt(SIZE));
//    }
//
//    public static secondWeapons randomizeSecondary() {
//        SIZE = SW.size();
//        return SW.get(RANDOM.nextInt(SIZE));
//    }
//
//    public static heavyWeapons randomizeHeavy() {
//        SIZE = HW.size();
//        return HW.get(RANDOM.nextInt(SIZE));
//    }

    public void addPlayer(String pn) {
        playerName = pn;
        playerList.add(playerName);
        updateRandomizer();
    }

    public void setPlayerCount(int i) {
        players = i;
    }

    public void randomizePlayers() {
        Collections.shuffle(playerList);
        updateRandomizer();
    }

    public void randomNumber() {
        rnum = (int)(Math.random() * ((max - 1) + 1)) + 1;
    }

    public void addRandomizerView(RandomizerView rv) {
        randomizerViews.add(rv);
    }

    public void updateRandomizer() {
        for(RandomizerView rv: randomizerViews) rv.handleRandomizerUpdate(new RandomizerEvent(this, rnum, players, playerName));
    }





}
