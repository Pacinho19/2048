package pl.pacinho.model;

import lombok.Getter;

import java.awt.*;

@Getter
public enum CellType {

    _EMPTY(0, Color.WHITE),
    _2(2, new Color(0,255,0)),
    _4(4, new Color(0,0,255)),
    _8(8, new Color(255,255,0)),
    _16(16, new Color(0,255,255)),
    _32(32, new Color(255,0,255)),
    _64(64, new Color(192,192,192)),
    _128(128, new Color(128,128,128)),
    _256(256, new Color(128,0,0)),
    _512(512, new Color(128,128,0)),
    _1024(1024, new Color(0,128,0)),
    _2048(2048, new Color(128,0,128)),
    _4096(4096, new Color(0,128,128)),
    _8192(8192, new Color(0,0,128)),
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
        _2048.next=_4096;
        _4096.next=_8192;
        _8192.next=null;
    }

    public String getCellText() {
        if (this == _EMPTY) {
            return "";
        }
        return String.valueOf(number);
    }
}