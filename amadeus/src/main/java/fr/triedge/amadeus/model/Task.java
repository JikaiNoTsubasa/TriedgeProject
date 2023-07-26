package fr.triedge.amadeus.model;

import java.util.ArrayList;

public class Task {
    private int id;
    private String name, desc;
    private ArrayList<Resource> resources = new ArrayList<>();

    public int getResourceCount(){
        return getResources().size();
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
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
