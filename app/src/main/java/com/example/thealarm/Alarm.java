package com.example.thealarm;

// Cho tính năng báo thức bình thường
public class Alarm extends AlarmBase{
    private boolean isOn; // Báo thức có đang bật hay đang tắt
    private boolean isRepeat; // = true: báo thức lặp lại, không thì là báo thức 1 lần
    private String time; // Kiểu cron
    private String sound; // File âm thanh khi báo thức kêu
    // Có thể có hoặc không - tính năng làm thêm
    private String groupId;

    public Alarm(boolean isActive, String name, byte accurateLevel, boolean isOn, boolean isRepeat, String time) {
        super(isActive, name, accurateLevel);
        this.isOn = isOn;
        this.isRepeat = isRepeat;
        this.time = time;
    }

    public Alarm(int id, boolean isActive, String name, byte accurateLevel, boolean isOn, boolean isRepeat, String time) {
        super(id, isActive, name, accurateLevel);
        this.isOn = isOn;
        this.isRepeat = isRepeat;
        this.time = time;
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isRepeat() {
        return isRepeat;
    }

    public String getTime() {
        return time;
    }

    public String getSound() {
        return sound;
    }

    public String getGroupId() {
        return groupId;
    }
}
