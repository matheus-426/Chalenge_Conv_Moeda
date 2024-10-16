/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meu_java.chalenge_conversor;

/**
 *
 * @author mathe
 */
public class Calculator {
    private double valor;
    private double taxaConv;
    private double valorFi;
    
    public void Calculator(double val, double taxa1){
        this.valor = val;
        this.taxaConv = taxa1;       
    }
    
    public double converteSel(){    
        return valor / taxaConv;       
    }
    
    public double converteCon(){
        return valor * taxaConv;
    }
}
