package pl.pacinho.model;

import lombok.Getter;

import java.awt.event.KeyEvent;
import java.util.Arrays;

public enum MoveType {

    UP(KeyEvent.VK_UP),
    DOWN(KeyEvent.VK_DOWN),
    LEFT(KeyEvent.VK_LEFT),
    RIGHT(KeyEvent.VK_RIGHT);

    @Getter
    private int vk;

    MoveType(int vk) {
        this.vk = vk;
    }

    public static MoveType findByVK(int vk) {
       return Arrays.stream(MoveType.values()).filter(mt -> mt.getVk() == vk).findFirst().orElse(null);
    }
}