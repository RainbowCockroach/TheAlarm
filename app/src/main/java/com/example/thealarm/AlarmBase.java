package com.example.thealarm;

public class AlarmBase {
    private int id;
    private boolean isActive; // Để người dùng có thể bật - tắt báo thức trong list
    private String name; // Tên báo thức
    private byte accurateLevel; // Cái này cho chế độ tiết kiệm pin

    public AlarmBase(boolean isActive, String name, byte accurateLevel) {
        this.isActive = isActive;
        this.name = name;
        this.accurateLevel = accurateLevel;
    }

    public AlarmBase(int id, boolean isActive, String name, byte accurateLevel) {
        this.id = id;
        this.isActive = isActive;
        this.name = name;
        this.accurateLevel = accurateLevel;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public String getName() {
        return name;
    }

    public byte getAccurateLevel() {
        return accurateLevel;
    }
}
