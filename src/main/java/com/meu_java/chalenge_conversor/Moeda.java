/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meu_java.chalenge_conversor;

import java.util.Map;

/**
 *
 * @author mathe
 */
public class Moeda {
    private String tipo;
    private double real;
    private double dolar;
    private double bolivia;
    private double chile;
    private double arge;
    private double colomb;
    private Map<String, Double> conversion_rates;

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }
   
    public double getDol(){
        return dolar;
}
    
    public double getRea(){
        return real;
    }
    
    public double getArg(){
        return arge;
    }
    
    public double getColo(){
        return colomb;
    }
    
    public double getBoli(){
        return bolivia;
    }
    
    public double getchi(){
        return chile;
    }
    
    public void sep(){
    Map<String, Double> taxas = getConversionRates();

        for (Map.Entry<String, Double> entry : taxas.entrySet()) {
            String moeda = entry.getKey();      // Código da moeda (ex: "EUR", "BRL")
            Double taxa = entry.getValue();     // Valor numérico da taxa de conversão

            switch(moeda){
                case "USD" -> this.dolar = taxa;
                case "BRL" -> this.real = taxa;
                case "ARS" -> this.arge = taxa;
                case "COP" -> this.colomb = taxa;
                case "BOB" -> this.bolivia = taxa;
                case "CLP" -> this.chile = taxa;
            }           
        }
}
    
    @Override
   public String toString(){
       return "Taxas de conversao baseado em" + ": " + real + ", "+ dolar + ", "+ bolivia + ", " + chile + ", " + arge + "," + colomb; 
    }
}
