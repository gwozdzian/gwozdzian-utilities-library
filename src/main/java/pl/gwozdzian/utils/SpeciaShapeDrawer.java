/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.utils;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class SpeciaShapeDrawer {

    
    private static final int BASIC_CORNER = 0;
    private static final int ROUNDED_CORNER = 1;
    private static final int CHAMFERED_CORNER = 2;
    
    private final Graphics g;
    
    private List<Point> pointsList;
    private List<Integer> cornerTypesList;
    private List<Integer> cornerSizesList;
    

    
    public SpeciaShapeDrawer(Graphics g, int x, int y) {
        this.g = g;
        this.pointsList = new ArrayList();
        this.cornerTypesList = new ArrayList();
        this.cornerSizesList = new ArrayList();
        this.pointsList.add(new Point(x,y));
    }
    
    public SpeciaShapeDrawer addLine(int x, int y){
        this.pointsList.add(new Point(x,y));
        this.cornerTypesList.add(BASIC_CORNER);
        this.cornerSizesList.add(0);
        return this;
    }
    

    
    
    public SpeciaShapeDrawer addLineWithRoundedCorner(int x, int y, int radius){
        this.pointsList.add(new Point(x,y));
        this.cornerTypesList.add(ROUNDED_CORNER);
        this.cornerSizesList.add(radius);
        return this;
    }
    
    
    
    public SpeciaShapeDrawer addLineWithChamferedCorner(int x, int y, int chamferSize){
        this.pointsList.add(new Point(x,y));
        this.cornerTypesList.add(CHAMFERED_CORNER);
        this.cornerSizesList.add(chamferSize);        
        return this;
    }    
    
    
    public SpeciaShapeDrawer closeLine(){
        this.pointsList.add(pointsList.get(0));
        this.cornerTypesList.add(BASIC_CORNER);
        this.cornerSizesList.add(0);        
        return this;
    }    

    public SpeciaShapeDrawer closeLineWithRoundedCorner(int radius){
        this.pointsList.add(pointsList.get(0));
        this.cornerTypesList.add(ROUNDED_CORNER);
        this.cornerSizesList.add(radius);        
        return this;
    }  
    
    
    public SpeciaShapeDrawer closeLineWithChamferedCorner(int radius){
        this.pointsList.add(pointsList.get(0));
        this.cornerTypesList.add(CHAMFERED_CORNER);
        this.cornerSizesList.add(0);        
        return this;
    }      
    
    
    public void draw(){
        
    }
    
    
    
    
}
