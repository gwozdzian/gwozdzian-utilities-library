/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gwozdzian.utils;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;




    
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

 
//import org.json.JSONArray;
//import org.json.JSONObject;    

//import org.apache

/**
 *
 * @author user
 */
public class Tools {
    
    public static final String DATA_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATA_FORMAT_IN_FILE_NAMES = "yyyy-MM-dd_HH-mm-ss";
    public static final String DATA_FORMAT_IN_EXIF = "yyyy:MM:dd HH:mm:ss";
    public static String newLine = "\n";
    
    

    public static String getFormatedDate(Long time, String dataFormatDefinition) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTimeInMillis(time);
        return getFormatedDate(calendar, dataFormatDefinition);
    }
    

    public static String getFormatedDate(Calendar calendar, String dataFormatDefinition){
        return getFormatedDate(calendar.getTime(), dataFormatDefinition);
    }
    
    public static String getFormatedDate(Date date, String dataFormatDefinition) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormatDefinition);
        
        return simpleDateFormat.format(date);       
    }

    //    public static boolean hasReadableMetadata(File file){
    //        return createPhotoFile(file.getAbsolutePath())!=null;
    //    }

    /**
     *
     * @param dataString the value of dataString
     * @param dataFormatDefinition the value of dataFormatDefinition
     * @return the java.util.Date
     */
    public static Calendar getDateFromFormattedString(String dataString, String dataFormatDefinition) {

        try {
            SimpleDateFormat simpleDataFormat = new SimpleDateFormat(dataFormatDefinition);
            //simpleDataFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            //String inputString = "00:01:30.500";
            Date date = simpleDataFormat.parse(dataString);
            Calendar calendar = Calendar.getInstance();// new GregorianCalendar(); //Calendar.getInstance();// simpleDataFormat.getCalendar();//parse(dataString);
            calendar.setTime(date);
            return calendar;
        } catch (ParseException ex) {
            //System.out.println(dataString +" can't be parsed");
            return null;
        }
        

    }

    public static void traceFile(File file, int space) {
        if(file!=null && file.exists() && file.canRead() ){
            if(file.isFile()){
                System.out.println(getSpaces(space)+file.getName());
            }
            if(file.isDirectory()){
                for(File currFile : file.listFiles()){
                    System.out.println(getSpaces(space)+currFile.getName());
                    if(currFile.isDirectory()){
                        traceFile(currFile, space+1);
                    }
                }
            }
        }
    }

    /**
     * 
     * @param space Quantity of spaces to be returned
     * @return String contain passed quantity of spaces
     */
    public static String getSpaces(int space) {
        StringBuilder sb = new StringBuilder("");
        for(int i=0; i<space; i++){
            sb.append(" ");
        }
            
        return sb.toString();
    }
    
    
    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
    Set<T> keys = new HashSet<>();
    for (Entry<T, E> entry : map.entrySet()) {
        if (Objects.equals(value, entry.getValue())) {
            keys.add(entry.getKey());
        }
    }
    return keys;
}

    /**
     * Return array with collection elements. 
     * @param <T> the type of the collection and array content
     * @param inputCollection Collection to be converted/copied to array
     * @return array copied from collection
     * @see #collectionToArray(java.util.Collection, T[])
     */
    public static <T> T[] collectionToArray(Collection<T> inputCollection) {
        
        
        Class<T> componentsType = (Class<T>) Object.class;
        Iterator<T> inputCollectionIterator = inputCollection.iterator();
        if(inputCollectionIterator.hasNext()){
            componentsType = (Class<T>) inputCollectionIterator.next().getClass();
        }
        T[] outputArray = (T[])Array.newInstance(componentsType, inputCollection.size());

        inputCollectionIterator = inputCollection.iterator();
        int i=0;
        while(inputCollectionIterator.hasNext()){
            outputArray[i] = inputCollectionIterator.next();
            i++;
        }
        //collectionToArray( inputCollection, outputArray);
        return outputArray;
        
        
    }
    
    
    /*
    public static <T> void collectionToArray(Collection<T> inputCollection, T[] outputArray) {
        Class<T> componentsType = (Class<T>) Object.class;
        Iterator<T> inputCollectionIterator = inputCollection.iterator();
        if(inputCollectionIterator.hasNext()){
            componentsType = (Class<T>) inputCollectionIterator.next().getClass();
        }
        outputArray = (T[])Array.newInstance(componentsType, inputCollection.size());
        

        //T[] outputArray = new T[calCol.size()];
//        outputArray = (T[]) new Object[inputCollection.size()];
//        [] colArr = inputCollection.toArray();
//        for (int i = 0; i < inputCollection.size(); i++) {
//            outputArray[i] = (T) colArr[i];
//        }
    }
*/

    /**
     *
     * @param fileTimes the value of fileTimes
     */
    public static <S, T> T[] extractValuesArrayFromMap(Map<S, T> fileTimes) {
        return Tools.collectionToArray(fileTimes.values());
    }

    public static boolean isContainFile(File folder, String fileName) {
        for(String currFile : folder.list()){
            if(currFile.equals(fileName)) return true;
        }
        return false;
    }
    
    
    
    
    
    
    
    public static BufferedImage readImageFromFile(File file) throws IOException
    {
        return ImageIO.read(file);
    }
 
    public static boolean writeImageToJPG(File file,BufferedImage bufferedImage) 
    {
        if(bufferedImage!=null){
            try {
                ImageIO.write(bufferedImage,"jpg",file);
                System.out.println("writeImageToJPG() -> Zapisałem obraz do pliku "+file.getName());
                return true;
            } catch (IOException ex) {
                //Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("writeImageToJPG() -> NIE zapisałem obraz do pliku "+file.getName()+ ex.getMessage());
                return false;
            }finally{
                bufferedImage.flush();
                bufferedImage = null;
                System.out.println("writeImageToJPG() -> Wyflushowałem bufferedImage po próbie zapisu do pliku "+file.getName());
            }
            

        }else{
            System.out.println("writeImageToJPG() -> bufferedImage==null dla "+file.getName());
        }
        return false;

    }
    
    
    
    
    /**
 * Converts a given Image into a BufferedImage
 *
 * @param img The Image to be converted
 * @return The converted BufferedImage
 */
public static BufferedImage toBufferedImage(Image img)
{
    if(img==null) return null;
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB); //TYPE_INT_ARGB

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();
    //bimage.flush();

    // Return the buffered image
    return bimage;
}










    public static BufferedImage rescaleTo(BufferedImage before, Double scale){


        int w = before.getWidth();
        int h = before.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp = 
           new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);

        return after;

    }
    
    
    
    
    
    
    
    
    
    public static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
        
        dest.setLastModified(source.lastModified());
    }
    

    
    
    
    
    
    public static <S> String getObjectStructure(S obj){
        StringBuilder sb = new StringBuilder("");
        
        sb.append("Object Class: "+obj.getClass().getName()+newLine);
        
        sb.append("Declared Fields"+newLine);
        for(Field declaredField:obj.getClass().getDeclaredFields()){
            sb.append(" ["+declaredField.getType().getName()+" "+declaredField.getName()+"] ");
        }
        sb.append(newLine+newLine);
        
        
        
        sb.append("Fields"+newLine);
        for(Field field:obj.getClass().getFields()){
            sb.append(" ["+field.getType().getName()+" "+field.getName()+"] ");
        }
        sb.append(newLine+newLine);
        
        
        
        sb.append("Declared Methods"+newLine);
        for(Method declaredMethod:obj.getClass().getDeclaredMethods()){
            sb.append(" ["+declaredMethod.getReturnType().getSimpleName()+" "+declaredMethod.getName()+"(");
            for(Parameter param:declaredMethod.getParameters()){
                sb.append(param.getType().getSimpleName()+" "+param.getName()+", ");
            }
            sb.append(")] ");
                    
                    
                    //+")"+] ");
        }
        sb.append(newLine+newLine);
        
        //sb.append();
        
        return sb.toString();
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    

 
    
    

        
        






   public static List<Class> getClassesForPackage(String pckgname) throws ClassNotFoundException {
      // This will hold a list of directories matching the pckgname. There may be more than one if a package is split over multiple jars/paths
      ArrayList<File> directories = new ArrayList<File>();
      String packageToPath = pckgname.replace('.', '\\');
      try {
          ClassLoader cld = Thread.currentThread().getContextClassLoader();
          if (cld == null) {
              throw new ClassNotFoundException("Can't get class loader.");
          }

          // Ask for all resources for the packageToPath
          Enumeration<URL> resources = cld.getResources(packageToPath);
          
          while (resources.hasMoreElements()) {
              directories.add(new File(URLDecoder.decode(resources.nextElement().getPath(), "UTF-8")));
          }
          Tools.trace(directories);
      } catch (NullPointerException x) {
          throw new ClassNotFoundException(pckgname + " does not appear to be a valid package (Null pointer exception)");
      } catch (UnsupportedEncodingException encex) {
          throw new ClassNotFoundException(pckgname + " does not appear to be a valid package (Unsupported encoding)");
      } catch (IOException ioex) {
          throw new ClassNotFoundException("IOException was thrown when trying to get all resources for " + pckgname);
      }

      ArrayList<Class> classes = new ArrayList<Class>();
      // For every directoryFile identified capture all the .class files
      while (!directories.isEmpty()){
          File directoryFile  = directories.remove(0);             
          if (directoryFile.exists()) {
              // Get the list of the files contained in the package
              File[] files = directoryFile.listFiles();

              for (File file : files) {
                  // we are only interested in .class files
                  if ((file.getName().endsWith(".class")) && (!file.getName().contains("$"))) {
                      System.out.println("Plik z rozszeżeniem .class ale bez $ "+ file.getAbsolutePath());
                      // removes the .class extension
                      int index = directoryFile.getPath().indexOf(packageToPath);
                      
                      //System.out.println("directoryFile = "+directoryFile);
                      //System.out.println("packageToPath = "+packageToPath);
                      //System.out.println("index = "+index);
                      
                      String packagePrefix = directoryFile.getPath().substring(index).replace('\\', '.');

                    try {                  
                      String className = packagePrefix + '.' + file.getName().substring(0, file.getName().length() - 6);                            
                      classes.add(Class.forName(className));                                
                    } catch (NoClassDefFoundError e)
                    {
                      // do nothing. this class hasn't been found by the loader, and we don't care.
                    }
                  } else if (file.isDirectory()){ // If we got to a subdirectory
                      directories.add(new File(file.getPath()));                          
                  }
              }
          } else {
              throw new ClassNotFoundException(pckgname + " (" + directoryFile.getPath() + ") does not appear to be a valid package");
          }
      }
      System.out.println("Klasy w "+pckgname);
      Tools.trace(classes);
      return classes;
  }  







        
        

        

    public static void trace(String str){
        System.out.println(str);
    }
 

    public static <S> void trace(List<S> list) {  
            trace(list, 0);
    }  
        
    public static <S> void trace(List<S> list, int deep) {
        for(S elem:list){
            
            if(elem instanceof List){
                trace((List) elem, deep+1);
            }else if(elem instanceof Class){
                System.out.println(Tools.getSpaces(deep)+((Class) elem).getSimpleName() + ".class | "+((Class) elem).getCanonicalName());
            }else if(elem instanceof File){
                System.out.println(Tools.getSpaces(deep)+((File) elem).getName()+  " | "+((File) elem).getAbsolutePath());
            }else{
                System.out.println(Tools.getSpaces(deep)+elem);
            }
            

            
        }
        

    }

    public static String newLine(int newLineCount) {
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<=newLineCount; i++){
            sb.append(newLine);
        }
        return sb.toString();
    }

    public static String getChars(String str, int repeatCount) {
        StringBuilder sb = new StringBuilder("");
        for(int i=0;i<=repeatCount; i++){
            sb.append(str);
        }
        return sb.toString();        
    }
    
    
    public static String traceGetMethods(Object obj){
        Method[] methodsList = obj.getClass().getMethods();
        StringBuilder sb = new StringBuilder("");
        for(Method method:methodsList){
            if(method.getName().startsWith("get") && method.getParameterCount()==0){
                try {
                    Object retObj = method.invoke(obj);
                    if(retObj!=null){
                        String retStr = retObj.toString();
                        if(!retStr.equals("") && !retStr.equals("null") && !retStr.equals("0"))
                            sb.append(method.getName().replaceFirst("get", "")+" = "+method.invoke(obj)+Tools.newLine);    
                    }
                    
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Tools.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return sb.toString();
    }

    public static void traceMethodName(Object obj) {
        System.out.println(getMethodName() + "() -> " + obj.toString());
    }

    public static String bytesToStringName(long longLength) {
        double length = (double) longLength; //.doubleValue();
        DecimalFormat df = new DecimalFormat("###.#");
        //StringBuilder stringBuilder = new StringBuilder("");
        if (length < 800) {
            return length + " b";
            //stringBuilder.append(500);
        } else if (length < 800000) {
            return df.format(length / 1000) + " kb";
        } else if (length < 800000000) {
            return df.format(length / 1000000) + " mb";
        } else {
            return df.format(length / 1000000000) + " gb";
        }
    }

    public static String getAllStockTraces() {
        Map<Thread, StackTraceElement[]> allStockMap = Thread.getAllStackTraces();
        Object[] keys = allStockMap.keySet().toArray(); //Set<Thread>
        Object[] values = allStockMap.values().toArray(); //Collection<StackTraceElement[]>
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < keys.length; i++) {
            sb.append(((Thread) keys[i]).getName() + newLine);
            for (StackTraceElement currMethN : ((Thread) keys[i]).getStackTrace()) {
                sb.append("   " + currMethN.getLineNumber() + "] " + currMethN.getClassName() + ", " + currMethN.getFileName() + ", " + currMethN.getMethodName() + newLine);
            }
            //sb.append(((Thread)keys[i]).getStackTrace().toString());
        }
        return sb.toString();
    }

    public static String listMethodsResult(Object... methodsResult) {
        StringBuilder sb = new StringBuilder("");
        for (Object currMethodResult : methodsResult) {
            sb.append("[0]" + getMethodName(0) + " , ");
            sb.append("[1]" + getMethodName(1) + " , ");
            sb.append("[2]" + getMethodName(2) + " , ");
            sb.append("[3]" + getMethodName(3) + " , ");
            sb.append("[4]" + getMethodName(4) + " , ");
            sb.append("[5]" + getMethodName(5) + " " + newLine);
            sb.append(Tools.getAllStockTraces());
            sb.append("() -> " + currMethodResult + newLine + newLine);
        }
        return sb.toString();
    }

    public static String getMethodName() {
        return getMethodName(3);
    }

    public static String getMethodName(final int depth) {
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        if (depth < ste.length) {
            StackTraceElement currSte = ste[depth];
            String currClass = currSte.getClassName();   
            currClass = currClass.substring(currClass.lastIndexOf(".")+1);
            return currClass+"."+ste[depth].getMethodName()+"()";
        } else {
            return "There is no method for "+depth+" level";
        }
    }

    public static void trace(Object obj, String text) {
        if (obj != null) {
            System.out.println(obj.getClass().getSimpleName() + "." + Tools.getMethodName(3) + "() -> " + text);
        } else {
            System.out.println(Tools.getMethodName(3) + "()" + text);
        }
    }


    public static void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2){

            //creates a copy of the Graphics instance
            Graphics2D g2d = (Graphics2D) g.create();
            
            //set the stroke of the copy, not the original 
            Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
            g2d.setStroke(dashed);
            g2d.drawLine(x1, y1, x2, y2);

            //gets rid of the copy
            g2d.dispose();
    }




    public static boolean hasCommonPartOfRanges(double beginOfFirstRange, double endOfFirstRange, double beginOfSecondRange, double endOfSecondRange){
        if(endOfFirstRange>=beginOfSecondRange && beginOfFirstRange<=endOfSecondRange){
            return true;
        }else{

            return false;
        }

    }  
    

    public static Color randomColor(int alpha){
        Random random = new Random();
        Color color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));// = Color.argb(alpha, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        return color;
    }


    

    
}
