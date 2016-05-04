package nl.marijnvanzelst.axon.rummikub.game.model.tile;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Tile {

    ONE_RED(Color.RED, 1, false, 0),
    TWO_RED(Color.RED, 2, false, 1),
    THREE_RED(Color.RED, 3, false, 2),
    FOUR_RED(Color.RED, 4, false, 3),
    FIVE_RED(Color.RED, 5, false, 4),
    SIX_RED(Color.RED, 6, false, 5),
    SEVEN_RED(Color.RED, 7, false, 6),
    EIGHT_RED(Color.RED, 8, false, 7),
    NINE_RED(Color.RED, 9, false, 8),
    TEN_RED(Color.RED, 10, false, 9),
    ELEVEN_RED(Color.RED, 11, false, 10),
    TWELVE_RED(Color.RED, 12, false, 11),
    THIRTEEN_RED(Color.RED, 13, false, 12),
    ONE_BLACK(Color.BLACK, 1, false, 13),
    TWO_BLACK(Color.BLACK, 2, false, 14),
    THREE_BLACK(Color.BLACK, 3, false, 15),
    FOUR_BLACK(Color.BLACK, 4, false, 16),
    FIVE_BLACK(Color.BLACK, 5, false, 17),
    SIX_BLACK(Color.BLACK, 6, false, 18),
    SEVEN_BLACK(Color.BLACK, 7, false, 19),
    EIGHT_BLACK(Color.BLACK, 8, false, 20),
    NINE_BLACK(Color.BLACK, 9, false, 21),
    TEN_BLACK(Color.BLACK, 10, false, 22),
    ELEVEN_BLACK(Color.BLACK, 11, false, 23),
    TWELVE_BLACK(Color.BLACK, 12, false, 24),
    THIRTEEN_BLACK(Color.BLACK, 13, false, 25),
    ONE_YELLOW(Color.YELLOW, 1, false, 26),
    TWO_YELLOW(Color.YELLOW, 2, false, 27),
    THREE_YELLOW(Color.YELLOW, 3, false, 28),
    FOUR_YELLOW(Color.YELLOW, 4, false, 29),
    FIVE_YELLOW(Color.YELLOW, 5, false, 30),
    SIX_YELLOW(Color.YELLOW, 6, false, 31),
    SEVEN_YELLOW(Color.YELLOW, 7, false, 32),
    EIGHT_YELLOW(Color.YELLOW, 8, false, 33),
    NINE_YELLOW(Color.YELLOW, 9, false, 34),
    TEN_YELLOW(Color.YELLOW, 10, false, 35),
    ELEVEN_YELLOW(Color.YELLOW, 11, false, 36),
    TWELVE_YELLOW(Color.YELLOW, 12, false, 37),
    THIRTEEN_YELLOW(Color.YELLOW, 13, false, 38),
    ONE_BLUE(Color.BLUE, 1, false, 39),
    TWO_BLUE(Color.BLUE, 2, false, 40),
    THREE_BLUE(Color.BLUE, 3, false, 41),
    FOUR_BLUE(Color.BLUE, 4, false, 42),
    FIVE_BLUE(Color.BLUE, 5, false, 43),
    SIX_BLUE(Color.BLUE, 6, false, 44),
    SEVEN_BLUE(Color.BLUE, 7, false, 45),
    EIGHT_BLUE(Color.BLUE, 8, false, 46),
    NINE_BLUE(Color.BLUE, 9, false, 47),
    TEN_BLUE(Color.BLUE, 10, false, 48),
    ELEVEN_BLUE(Color.BLUE, 11, false, 49),
    TWELVE_BLUE(Color.BLUE, 12, false, 50),
    THIRTEEN_BLUE(Color.BLUE, 13, false, 51),
    JOKER(null, null, true, 52);

    Tile(Color color, Integer number, boolean joker, int order) {
        this.color = color;
        this.number = number;
        this.joker = joker;
        this.order = order;
    }

    private Color color;

    private Integer number;
    
    private boolean joker;

    private int order;

    public Color getColor() {
        return color;
    }

    public Integer getNumber() {
        return number;
    }

    public boolean isJoker() {
        return joker;
    }

    public Optional<Tile> getNext(){
        return Tile.getTile(this.color, this.number + 1);
    }

    public static List<Tile> sort(List<Tile> tiles){
        return tiles.stream()
                .sorted((t1, t2) -> t1.order - t2.order)
                .collect(Collectors.toList());
    }

    public boolean atSamePosition(Tile tile){
        if(tile.isJoker() || this.isJoker()){
            return true;
        }
        if(tile.getColor() == this.getColor() && tile.getNumber().equals(this.getNumber())){
            return true;
        }
        return false;
    }

    public static Optional<Tile> getTile(Color color, Integer number){
        return Arrays.asList(Tile.values()).stream()
                .filter(tile -> !tile.isJoker())
                .filter(tile -> tile.getNumber().equals(number))
                .filter(tile -> tile.getColor() == color)
                .findFirst();

    }
}
