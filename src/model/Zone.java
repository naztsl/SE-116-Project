package model;

public abstract class Zone extends Cell {
    protected int level;
    protected int electricity;
    protected int water;
    protected int internet;
    protected int population;
    protected int goods;
    protected int lifestyle;
    protected boolean security;
    protected boolean health;
    protected boolean education;

    protected int output;

    public Zone(int row, int col, char symbol) {
        super(row, col, symbol);
        level=0;
    }


    public int getLevel() {
        return level;
    }

    public int getElectricity() {
        return electricity;
    }

    public int getWater() {
        return water;
    }

    public int getInternet() {
        return internet;
    }

    public int getPopulation() {
        return population;
    }

    public int getGoods() {
        return goods;
    }

    public int getLifestyle() {
        return lifestyle;
    }

    public boolean hasSecurity() {
        return security;
    }

    public boolean hasHealth() {
        return health;
    }

    public boolean hasEducation() {
        return education;
    }

    public int getOutput() {
        return output;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setGoods(int goods) {
        this.goods = goods;
    }

    public void setLifestyle(int lifestyle) {
        this.lifestyle = lifestyle;
    }

    public void setSecurity(boolean security) {
        this.security = security;
    }

    public void setHealth(boolean health) {
        this.health = health;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public void setElectricity(int electricity) {
        this.electricity = electricity;
    }

    public abstract void updateLevel();
    public abstract int calculateOutput();

    public void resetTickData(){

        electricity=0;
        water=0;
        internet=0;
        population=0;
        goods=0;
        lifestyle=0;
        security=false;
        health=false;
        education=false;
    }


}
