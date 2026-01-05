package com.fastgo.blockchain.blockchain.domain;

public enum BadgeLevel {
    
   BRONZE(500, "Novice Rider", "CD7F32"), 
    SILVER(1000, "Expert Rider", "A8A9AD"), 
    GOLD(1500, "Legendary Rider", "D4AF37"); 

    private final int threshold;
    private final String label;
    private final String hexColor;

    BadgeLevel(int threshold, String label, String hexColor) {
        this.threshold = threshold;
        this.label = label;
        this.hexColor = hexColor;
    }

    public int getThreshold() { return threshold; }
    public String getLabel() { return label; }
    public String getHexColor() { return hexColor; }
    
    // Metodo utile per capire qual è il prossimo livello
    public BadgeLevel next() {
        return switch (this) {
            case BRONZE -> SILVER;
            case SILVER -> GOLD;
            case GOLD -> null; // Max level
        };
    }
}