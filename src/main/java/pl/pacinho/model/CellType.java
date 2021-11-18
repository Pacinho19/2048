package pl.pacinho.model;

import lombok.Getter;

import java.awt.*;

@Getter
public enum CellType {

    _EMPTY(0, Color.WHITE),
    _2(2, Color.BLUE),
    _4(4, Color.CYAN),
    _8(8, Color.DARK_GRAY),
    _16(16, Color.GREEN),
    _32(32, Color.LIGHT_GRAY),
    _64(64, Color.MAGENTA),
    _128(128, Color.PINK),
    _256(256, Color.ORANGE),
    _512(512, Color.RED),
    _1024(1024, Color.GRAY),
    _2048(2048, Color.YELLOW);


    CellType(int number, Color color) {
        this.color = color;
        this.number = number;
    }

    private int number;
    private Color color;

    public  String getCellText(){
        if(this==_EMPTY){
            return "";
        }
        return String.valueOf(number);
    }
}
