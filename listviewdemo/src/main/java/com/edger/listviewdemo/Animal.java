package com.edger.listviewdemo;

import java.util.Objects;

public class Animal {
    private int animalSerialNumber;
    private String animalName;
    private String animalDescription;
    private int animalIcon;

    public Animal() {

    }

    public Animal(int serialNumber, String Name, String Say, int Icon) {
        this.animalSerialNumber = serialNumber;
        this.animalName = Name;
        this.animalDescription = Say;
        this.animalIcon = Icon;
    }

    public int getAnimalSerialNumber() {
        return animalSerialNumber;
    }

    public void setAnimalSerialNumber(int animalSerialNumber) {
        this.animalSerialNumber = animalSerialNumber;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalDescription() {
        return animalDescription;
    }

    public void setAnimalDescription(String animalDescription) {
        this.animalDescription = animalDescription;
    }

    public int getAnimalIcon() {
        return animalIcon;
    }

    public void setAnimalIcon(int animalIcon) {
        this.animalIcon = animalIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return animalIcon == animal.animalIcon &&
                Objects.equals(animalName, animal.animalName) &&
                Objects.equals(animalDescription, animal.animalDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalSerialNumber, animalName, animalDescription, animalIcon);
    }
}
