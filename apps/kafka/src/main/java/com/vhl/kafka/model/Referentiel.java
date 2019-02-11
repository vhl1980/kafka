/*
 * Creation : 24 janv. 2019
 */
package com.vhl.kafka.model;

public class Referentiel {

    private Integer id;
    private String name;
    private char gender;

    public Referentiel() {
    }

    public Referentiel(Integer id, String name, char gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String toString() {
        String str = "Id : " + id + " name : " + name + " gender : " + gender;
        return str;
    }

}
