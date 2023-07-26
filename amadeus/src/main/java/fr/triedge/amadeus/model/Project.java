package fr.triedge.amadeus.model;

import java.util.ArrayList;

public class Project {
    private int id;
    private String name, desc;
    private ArrayList<Task> tasks = new ArrayList<>();

    public int getTaskCount(){
        return getTasks().size();
    }

    public int getResourceCount(){
        int total = 0;
        for (Task t : getTasks()){
            total += t.getResourceCount();
        }
        return total;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
