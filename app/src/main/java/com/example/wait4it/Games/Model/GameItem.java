package com.example.wait4it.Games.Model;

public class GameItem {
    private final String name;
    private final int icon;
    private Class<?> activityClass;

    public GameItem(String name, int icon, Class<?> activityClass) {
        this.name = name;
        this.icon = icon;
        this.activityClass = activityClass;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public Class<?> getActivityClass() {
        return activityClass;
    }

    @Override
    public String toString() {
        return "GameItem{" +
                "name='" + name + '\'' +
                ", icon=" + icon +
                ", activityClass=" + activityClass +
                '}';
    }
}
