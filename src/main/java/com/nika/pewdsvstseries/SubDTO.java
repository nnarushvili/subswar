/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nika.pewdsvstseries;

/**
 *
 * @author Nika.Narushvili
 */
public class SubDTO {
    private String pewdsSubs;
    private String tSeriesSubs;
    
    private int subDifference;

    public String getPewdsSubs() {
        return pewdsSubs;
    }

    public void setPewdsSubs(String pewdsSubs) {
        this.pewdsSubs = pewdsSubs;
    }

    public String gettSeriesSubs() {
        return tSeriesSubs;
    }

    public void settSeriesSubs(String tSeriesSubs) {
        this.tSeriesSubs = tSeriesSubs;
    }

    public int getSubDifference() {
        return subDifference;
    }

    public void setSubDifference(int subDifference) {
        this.subDifference = subDifference;
    }
    
}
