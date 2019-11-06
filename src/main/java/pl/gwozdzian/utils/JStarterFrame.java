/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.utils;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author user
 */
public class JStarterFrame extends JFrame{



    private Dimension defaultScreenDimension;
    private Dimension smallestScreenDimension;


    private Dimension largestScreenDimension;
    private List<Dimension> screenDimensions;
    private final JStarterFrame me;

    public JStarterFrame() throws HeadlessException {
            me = this;
        
        
            readScreenDimensions();
            
            SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                    
                
                init();
                me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                me.setVisible(true);
                me.pack();
                
            }

 
        });
    }
    
    
    protected void init(){
        
    }




    public static void setVerticalLayouting(Container container){
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    }

    public static void setHorizontalLayouting(Container container){
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
    }        
    
    public void setVerticalLayouting(){
        setVerticalLayouting(this.getContentPane());
    }

    public void setHorizontalLayouting(){
        setHorizontalLayouting(this.getContentPane());
    }    
    
    
    
    
    
    
    
    public Dimension getDefaultScreenDimension() {
        return defaultScreenDimension;
    }

    public Dimension getLargestScreenDimension() {
        return largestScreenDimension;
    }
    
    public Dimension getSmallestScreenDimension() {
        return smallestScreenDimension;
    }
    
    public List<Dimension> getScreenDimensions() {
        return screenDimensions;
    }    
    
    
    
    
    public static JScrollPane createJScrollPane(Component component, Dimension dimension){
        return createJScrollPane(component,  dimension.width,  dimension.height);
    }
    
    public static JScrollPane createJScrollPane(Component component, int w, int h){
        JScrollPane sp;
        if(component!=null){
            sp = new JScrollPane(component);
        }else{
            sp = new JScrollPane();
        }
        sp.setMinimumSize(new Dimension(w,h));
        sp.setPreferredSize(new Dimension(w,h));
        sp.setMaximumSize(new Dimension(w,h));
        sp.setSize(new Dimension(w,h));
        return sp;
    }
    
    
 
    
    
    
    
    
    
    
    private void readScreenDimensions() throws HeadlessException {
         GraphicsDevice defaultScreenDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
         GraphicsDevice[] screenDevices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
         
         int defaultScreenWidth = defaultScreenDevice.getDisplayMode().getWidth();
         int defaultScreenHeigh = defaultScreenDevice.getDisplayMode().getHeight();
         defaultScreenDimension = new Dimension(defaultScreenWidth, defaultScreenHeigh);
         smallestScreenDimension = null;
         largestScreenDimension = null;
         screenDimensions = new ArrayList();
         for(int i=0;i<screenDevices.length; i++){
             GraphicsDevice sD = screenDevices[i];
             int w = sD.getDisplayMode().getWidth();
             int h = sD.getDisplayMode().getHeight();
             
             screenDimensions.add(new Dimension(w,h));

             if(smallestScreenDimension==null){
                 smallestScreenDimension = new Dimension(w,h);
             }else{
                 smallestScreenDimension.width = Math.min(smallestScreenDimension.width, w);
                 smallestScreenDimension.height = Math.min(smallestScreenDimension.height, h);
             }


             if(largestScreenDimension==null){
                 largestScreenDimension = new Dimension(w,h);
             }else{
                 largestScreenDimension.width = Math.max(largestScreenDimension.width, w);
                 largestScreenDimension.height = Math.max(largestScreenDimension.height, h);
             }
             
//             for(Dimension dim : screenDimensions){
//                 Tools.trace(dim.toString());
//             }
             
             
//             for(Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()){
//                 Tools.trace("Font -> "+font.getFamily()+" - "+font.getFontName());
//             }

         }
     }    
    
    
}
