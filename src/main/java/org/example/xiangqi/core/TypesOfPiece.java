package org.example.xiangqi.core;

public enum TypesOfPiece {
    RED_CHARIOT("red", "chariot"),
    RED_HORSE("red", "horse"),
    RED_CANNON("red", "cannon"),
    RED_SOLDIER("red", "soldier"),
    RED_ADVISOR("red", "advisor"),
    RED_ELEPHANT("red", "elephant"),
    RED_GENERAL("red", "general"),
    BLACK_CHARIOT("black", "chariot"),
    BLACK_HORSE("black", "horse"),
    BLACK_CANNON("black", "cannon"),
    BLACK_SOLDIER("black", "soldier"),
    BLACK_ADVISOR("black", "advisor"),
    BLACK_ELEPHANT("black", "elephant"),
    BLACK_GENERAL("black", "general");

    private final String color;
    private final String name;
    private final char symbol;

    private TypesOfPiece(String color, String name) {
        this.name = name;
        this.color = color;
        this.symbol = getSymbolByNameAndColor(name, color);
    }

    public char getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public static char getSymbolByNameAndColor(String name, String color) {
        return switch (color + " " + name) {
            case "red chariot" -> '俥';
            case "red horse" -> '傌';
            case "red cannon" -> '炮';
            case "red soldier" -> '兵';
            case "red advisor" -> '仕';
            case "red elephant" -> '相';
            case "red general" -> '帥';

            case "black chariot" -> '車';
            case "black horse" -> '馬';
            case "black cannon" -> '砲';
            case "black soldier" -> '卒';
            case "black advisor" -> '士';
            case "black elephant" -> '象';
            case "black general" -> '將';

            default -> throw new IllegalArgumentException("Invalid color or name");
        };
    }
}
