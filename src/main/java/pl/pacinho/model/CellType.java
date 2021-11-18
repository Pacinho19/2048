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
    _2048(2048, Color.YELLOW),
    _NONE(0, null);


    private int number;
    private Color color;
    private CellType next;

    CellType(int number, Color color ) {
        this.color = color;
        this.number = number;
    }

    static{
        _2.next=_4;
        _4.next=_8;
        _8.next=_16;
        _16.next=_32;
        _32.next=_64;
        _64.next=_128;
        _128.next=_256;
        _256.next=_512;
        _512.next=_1024;
        _1024.next=_2048;
        _2048.next=null;
    }

    public String getCellText() {
        if (this == _EMPTY) {
            return "";
        }
        return String.valueOf(number);
    }
}