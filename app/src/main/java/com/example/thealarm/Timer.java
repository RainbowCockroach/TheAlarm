package com.example.thealarm;

// Cho tính năng hẹn giờ
public class Timer extends AlarmBase{
    private long timePassInMiliseconds; // Số giờ của hẹn giờ, tính bằng mili giây

    public Timer(boolean isActive, String name, byte accurateLevel, long timePassInMiliseconds) {
        super(isActive, name, accurateLevel);
        this.timePassInMiliseconds = timePassInMiliseconds;
    }

    public Timer(int id, boolean isActive, String name, byte accurateLevel, long timePassInMiliseconds) {
        super(id, isActive, name, accurateLevel);
        this.timePassInMiliseconds = timePassInMiliseconds;
    }

    public long getTimePassInMiliseconds() {
        return timePassInMiliseconds;
    }
}
