/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea1;

//import java.awt.List;
import java.util.Arrays;
import java.util.List;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 *
 * @author owner
 */
public class Tarea1 {
    
    static String path1;
    static List <BufferedImage> listArrImg=new ArrayList<BufferedImage>(); 
    
    public void setPath1(String path){
        path1= path;
        
    
    }
    

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
    */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        run("bmp_24.bmp"); // /bmp_24.bmp /LAND3.bmp
        //"C:\\Users\\owner\\OneDrive\\Escritorio\\"
    
    }
    
    public static void run(String path) throws IOException {
        // TODO code application logic here
        File image = new File(path); // /bmp_24.bmp /LAND3.bmp 
        //"C:\\Users\\owner\\OneDrive\\Escritorio\\"
        
        // Read Image Pixel by Pixel
        
 
        
        BufferedImage imgBuffer = ImageIO.read(image);
        BufferedImage imgBuffer1 = ImageIO.read(image);
        BufferedImage imgBuffer2 = ImageIO.read(image);
        BufferedImage imgBuffer3 = ImageIO.read(image);
        
        
        
        
        File f1 = new File("Output1.bmp");
        File f2 = new File("Output2.bmp");
        File f3 = new File("Output3.bmp");
        
        
        
        
        infoImg(path);
        
       
        
        for (int x=0; x< imgBuffer1.getWidth(); x++){
            for (int y=0; y< imgBuffer1.getHeight(); y++){
                int ipixel= imgBuffer1.getRGB(x, y);
                //System.out.println(ipixel);
                String p = Integer.toHexString(ipixel);
                //System.out.println(p);



                String alpha = p.subSequence(0, 2).toString();
                String r = p.subSequence(2,4).toString();
                String g = p.subSequence(4,6).toString();
                String b = p.subSequence(6,8).toString();


                /*
                //
                // String to Char
                // Creating array of string length
                    char[] ch = new char[p.length()];

                    // Copy character by character into array
                    for (int i = 0; i < p.length(); i++) {
                        ch[i] = p.charAt(i);
                    }
                //
                */

                //System.out.println(alpha);
                //System.out.println(r);
                //System.out.println(g);
                //System.out.println(b);

                //String SP = alpha + r + g + b;
                //System.out.println(SP);

                //int iz = (int)Long.parseLong(SP, 16);
                //System.out.println("z"+iz);



                int ir = (int)Long.parseLong(r, 16);
                int ig = (int)Long.parseLong(g, 16);
                int ib = (int)Long.parseLong(b, 16);

                //System.out.println("iresult "+ iresult); 
                
                int iresult1 = grayscale(alpha, ir,ig,ib);
                int iresult2 = negative(alpha, ir,ig,ib);
                
                String Gp = Integer.toHexString(iresult1);
                //System.out.println(p);



                String Galpha = Gp.subSequence(0, 2).toString();
                String Gr = Gp.subSequence(2,4).toString();
                String Gg = Gp.subSequence(4,6).toString();
                String Gb = Gp.subSequence(6,8).toString();
                
                
                int Gir = (int)Long.parseLong(Gr, 16);
                int Gig = (int)Long.parseLong(Gg, 16);
                int Gib = (int)Long.parseLong(Gb, 16);
                
                
                int iresult3 = blackwhite(alpha, Gir,Gig,Gib);
                
                
                imgBuffer1.setRGB(x, y, iresult1);
                imgBuffer2.setRGB(x, y, iresult2);
                imgBuffer3.setRGB(x, y, iresult3);
                
                
                //imgBuffer.setRGB(x, y, Gi);
                
                
        
            }
        
        
        }
        
        
        grayscaleFile(imgBuffer1);
        colorFile(imgBuffer2);
        binaryFile(imgBuffer3);
        
        listArrImg.add(imgBuffer);
        listArrImg.add(imgBuffer1);
        listArrImg.add(imgBuffer2);
        listArrImg.add(imgBuffer3);
        
        ImageIO.write(imgBuffer1, "bmp", f1);
        ImageIO.write(imgBuffer2, "bmp", f2);
        ImageIO.write(imgBuffer3, "bmp", f3);
        
        
        System.out.println("Done");
        
        
        
        
        
    }
    
    public static int grayscaleFile( BufferedImage imgBuf) throws IOException{
        // write file
        try {
          FileWriter myWriter = new FileWriter("fileGray.pgm");
          myWriter.write("P2\n");
          int W =(int)imgBuf.getWidth();
          myWriter.write(String.valueOf(W));
          myWriter.write('\n');
          int H =(int)imgBuf.getHeight();
          myWriter.write(String.valueOf(H));
          myWriter.write('\n');
          myWriter.write("255\n");
          for (int y=0; y< imgBuf.getHeight(); y++){
            for (int x=0; x< imgBuf.getWidth(); x++){
                int vec_pixel= imgBuf.getRGB(x, y); 
                int  gray = (vec_pixel & 0x000000ff);
                //short grayByte = (short) (gray);
                //byte grayByte = (byte) (gray);
                //myWriter.write(grayByte & 0xff);
                myWriter.write(String.valueOf(gray));
               
           
                myWriter.write(" ");

            }
             myWriter.write('\n');
          }
          
          
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        return 0;
    }
    
    
    public static int colorFile( BufferedImage imgBuf) throws IOException{
        // write file
        try {
          FileWriter myWriter = new FileWriter("fileColor.ppm");
          myWriter.write("P3\n");
          int W =(int)imgBuf.getWidth();
          myWriter.write(String.valueOf(W));
          myWriter.write('\n');
          int H =(int)imgBuf.getHeight();
          myWriter.write(String.valueOf(H));
          myWriter.write('\n');
          myWriter.write("255\n");
          for (int y=0; y< imgBuf.getHeight(); y++){
            for (int x=0; x< imgBuf.getWidth(); x++){
                int vec_pixel= imgBuf.getRGB(x, y); 
                int  red   = (vec_pixel & 0x00ff0000) >> 16;
                int  green = (vec_pixel & 0x0000ff00) >> 8;
                int  blue  =  (vec_pixel & 0x000000ff);
                //short grayByte = (short) (gray);
                //byte grayByte = (byte) (gray);
                //myWriter.write(grayByte & 0xff);
                myWriter.write(String.valueOf(red ));
                 myWriter.write(" ");
                myWriter.write(String.valueOf(green));
                 myWriter.write(" ");
                myWriter.write(String.valueOf(blue ));
               
                myWriter.write('\n');

            }
          }
          
          
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        return 0;
    }
    
    public static int binaryFile( BufferedImage imgBuf) throws IOException{
        // write file
        try {
          FileWriter myWriter = new FileWriter("fileBin.pbm");
          myWriter.write("P1\n");
          int W =(int)imgBuf.getWidth();
          myWriter.write(String.valueOf(W));
          myWriter.write('\n');
          int H =(int)imgBuf.getHeight();
          myWriter.write(String.valueOf(H));
          myWriter.write('\n');
          for (int y=0; y< imgBuf.getHeight(); y++){
            for (int x=0; x< imgBuf.getWidth(); x++){
                int vec_pixel= imgBuf.getRGB(x, y); 
                //System.out.println("B"+ String.valueOf(vec_pixel));
                int val= (vec_pixel & 0x000000ff);
                //System.out.println("B"+ String.valueOf(val));
                int bin;
                if(val==0){
                    bin =0;
                }
                else {bin =1;}
                
                myWriter.write(String.valueOf(bin));
               
           
                myWriter.write(" ");

            }
             myWriter.write('\n');
          }
          
          
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        return 0;
    }
    
    public static int grayscale(String alpha, int r, int g, int b) throws IOException{
         
        //Grayscale= 0.299R + 0.587G + 0.114B;
        
        //System.out.println(ir);
        //System.out.println(ib);
        //System.out.println(ib);


        double dr= r*0.299;
        double dg= g*0.587;
        double db= b*0.114;

        int ir= (int)dr;
        int ig= (int)dg;
        int ib= (int)db;

        int Gi= ir + ig +ib;



        String Gr= Integer.toHexString(Gi); //ir
        String Gg= Integer.toHexString(Gi); //ig
        String Gb= Integer.toHexString(Gi); //ib

        if(Gi< 16){
            Gr= "0" + Gr;
            Gg= "0" + Gg;
            Gb= "0" + Gb;

        }
        
        /*
                if(ir< 16){
                    Gr= "0" + Gr;

                }

                if(ig< 16){
                    Gg= "0" + Gg;

                }
                if(ib< 16){
                    Gb= "0" + Gb;

                }
        */

    
        //Get First Two Char from Every String
        Gr= String.format("%.02s", Gr);
        Gg= String.format("%.02s", Gg);
        Gb= String.format("%.02s", Gb);

        String result = alpha+ Gr+ Gg +Gb;



        int iresult = (int)Long.parseLong(result, 16);


        //System.out.println("G"+ Gr+ Gg +Gb);

        /*
        int[] arr = null;
        arr[0]=r;
        arr[1]=g;
        arr[2]=b;
        */
        
        
        

        return iresult;
        //ImageIO.write(imgBuffer, "bmp", image);
    
    }
    
    
    public static int negative(String alpha, int r, int g, int b) throws IOException{
         
        


        double dr= 255 - r;
        double dg= 255 - g;
        double db= 255 - b;

        int ir= (int)dr;
        int ig= (int)dg;
        int ib= (int)db;



        String Gr= Integer.toHexString(ir); //ir
        String Gg= Integer.toHexString(ig); //ig
        String Gb= Integer.toHexString(ib); //ib

        
        
        if(ir< 16){
            Gr= "0" + Gr;

        }

        if(ig< 16){
            Gg= "0" + Gg;

        }
        if(ib< 16){
            Gb= "0" + Gb;

        }
        

        
        
        //Get First Two Char from Every String
        Gr= String.format("%.02s", Gr);
        Gg= String.format("%.02s", Gg);
        Gb= String.format("%.02s", Gb);

        String result = alpha + Gr + Gg + Gb;



        int iresult = (int)Long.parseLong(result, 16);


        

        return iresult;
        
        //ImageIO.write(imgBuffer, "bmp", image);
    
    }
    
    public static int blackwhite(String alpha, int r, int g, int b) throws IOException{
        
        String bw ="00";
        if(r > 127){
            bw ="ff";
        }

        String result = alpha + bw + bw + bw;
        

        int iresult = (int)Long.parseLong(result, 16);


        return iresult;
        //ImageIO.write(imgBuffer, "bmp", image);
    
    }
    
    public static int uniqueColors(File img) throws IOException{
        BufferedImage imgBuf = ImageIO.read(img);
        int count =0;
        List <Integer> list=new ArrayList<Integer>();  
        for (int x=0; x< imgBuf.getWidth(); x++){
            for (int y=0; y< imgBuf.getHeight(); y++){
                int ipixel= imgBuf.getRGB(x, y);
                if(list.isEmpty()){list.add(ipixel); count++;}
                else{
                    if(!list.contains(ipixel)){
                       list.add(ipixel);
                       count++; 
                    }
                
                }
                
                
            }
        }
        
        
        return count;
    
    }
    
    
    
    public static String infoImg(String pathS)throws IOException{
        
        File image = new File(pathS); 
        String result= "";
 
        
        BufferedImage imgBuffer = ImageIO.read(image);
        
         //información sobre la imagen
        // dimensions
        int width= imgBuffer.getWidth();
        int height = imgBuffer.getWidth();
        //System.out.println("Width " + width);
        result= result+ "Width " + width + "\n";
        //System.out.println("Height " + height);
        result= result+ "Height " + height+ "\n";
        
        
        //
        
        // bits por pixel
        String str = imgBuffer.toString();
        int it = str.charAt(20);
        
        //System.out.println(str);
        
        String pB = "#pixelBits";
        
        it = str.indexOf("#pixelBits");
        //System.out.println(it);
        //result= result+ it+ "\n";
        
        
        
        char mychar1 = str.charAt(str.indexOf("#pixelBits = ") + 13);
        char mychar2 = str.charAt(str.indexOf("#pixelBits = ") + 14);
        
       //System.out.println("C"+ mychar1 );
        
        int z1 = Character.getNumericValue(mychar1);
        //System.out.println("#"+z1);
        String str1 = "";
        
        if (mychar2== ' '){
            str1 = "" + mychar1 ;
        
        } else {
            str1 = "" + mychar1 + mychar2;
        
        }
        
        //String str1 = ""+ mychar1 + mychar2;
        
        int ipb = Integer.valueOf(str1);
        //System.out.println("##"+ipb);
        result= result+ "pixelBits "+ipb+ "\n";
        
        
        //
        
        //DPI
        // 1 inch = 96 pixels
        // dpi = dot / inch
        int inch = width / 96;
        int dpi = width/inch;
        //System.out.println("Horizontal DPI " + dpi);
        result= result + "Horizontal DPI " + dpi+ "\n";
        
        inch = height / 96;
        dpi = height / inch;
        //System.out.println("Vertical DPI " + dpi);
        
        result= result + "Vertical DPI " + dpi+ "\n";
        
        
        //Colores unicos
        int uColors= uniqueColors(image);
        result= result + uColors+ "\n";
        
        //System.out.println( uColors);
        
        //System.out.println();
        //System.out.println( "Results:");
        //System.out.println( result);
        
        return result;
    
    }
    
    
    public static void transformGrayscale(String pathT)throws IOException{
        File image = new File(pathT); 
        
        File f = new File("Output1.bmp");
 
        
        BufferedImage imgBuffer = ImageIO.read(image);
        
        for (int x=0; x< imgBuffer.getWidth(); x++){
            for (int y=0; y< imgBuffer.getHeight(); y++){
                int ipixel= imgBuffer.getRGB(x, y);
                //System.out.println(ipixel);
                String p = Integer.toHexString(ipixel);
                //System.out.println(p);



                String alpha = p.subSequence(0, 2).toString();
                String r = p.subSequence(2,4).toString();
                String g = p.subSequence(4,6).toString();
                String b = p.subSequence(6,8).toString();



                int ir = (int)Long.parseLong(r, 16);
                int ig = (int)Long.parseLong(g, 16);
                int ib = (int)Long.parseLong(b, 16);

                //System.out.println("iresult "+ iresult); 
                
                int iresult1 = grayscale(alpha, ir,ig,ib);
                
                
                
                imgBuffer.setRGB(x, y, iresult1);
                
                
                //imgBuffer.setRGB(x, y, Gi);
                
            }
        
        
        }
        
        grayscaleFile(imgBuffer);

        ImageIO.write(imgBuffer, "bmp", f);
        
        listArrImg.add(imgBuffer);
                
        
        
    }
    
    public static void transformBin(String pathT)throws IOException{
        File image = new File(pathT); 
        
        
        File f = new File("Output3.bmp");
 
        
        BufferedImage imgBuffer = ImageIO.read(image);
        
        for (int x=0; x< imgBuffer.getWidth(); x++){
            for (int y=0; y< imgBuffer.getHeight(); y++){
                int ipixel= imgBuffer.getRGB(x, y);
                //System.out.println(ipixel);
                String p = Integer.toHexString(ipixel);
                //System.out.println(p);



                String alpha = p.subSequence(0, 2).toString();
                String r = p.subSequence(2,4).toString();
                String g = p.subSequence(4,6).toString();
                String b = p.subSequence(6,8).toString();



                int ir = (int)Long.parseLong(r, 16);
                int ig = (int)Long.parseLong(g, 16);
                int ib = (int)Long.parseLong(b, 16);

                //System.out.println("iresult "+ iresult); 
                
                int iresult1 = grayscale(alpha, ir,ig,ib);
                
                String Gp = Integer.toHexString(iresult1);
                //System.out.println(p);



                String Galpha = Gp.subSequence(0, 2).toString();
                String Gr = Gp.subSequence(2,4).toString();
                String Gg = Gp.subSequence(4,6).toString();
                String Gb = Gp.subSequence(6,8).toString();
                
                
                int Gir = (int)Long.parseLong(Gr, 16);
                int Gig = (int)Long.parseLong(Gg, 16);
                int Gib = (int)Long.parseLong(Gb, 16);
                
                
                int iresult3 = blackwhite(alpha, Gir,Gig,Gib);
                
                
                imgBuffer.setRGB(x, y, iresult3);
                
                
            }
        }
        
        binaryFile(imgBuffer);
      
        ImageIO.write(imgBuffer, "bmp", f);
        
        listArrImg.add(imgBuffer);
        
    }
    
    public static void transformNeg(String pathT)throws IOException{
        File image = new File(pathT); 
        
        
        File f = new File("Output3.bmp");
 
        
        BufferedImage imgBuffer = ImageIO.read(image);
        
        for (int x=0; x< imgBuffer.getWidth(); x++){
            for (int y=0; y< imgBuffer.getHeight(); y++){
                int ipixel= imgBuffer.getRGB(x, y);
                //System.out.println(ipixel);
                String p = Integer.toHexString(ipixel);
                //System.out.println(p);



                String alpha = p.subSequence(0, 2).toString();
                String r = p.subSequence(2,4).toString();
                String g = p.subSequence(4,6).toString();
                String b = p.subSequence(6,8).toString();



                int ir = (int)Long.parseLong(r, 16);
                int ig = (int)Long.parseLong(g, 16);
                int ib = (int)Long.parseLong(b, 16);

                
                int iresult2 = negative(alpha, ir,ig,ib);
                
                
                imgBuffer.setRGB(x, y, iresult2);
                
             
            }
        
        }
        
        colorFile(imgBuffer);

        ImageIO.write(imgBuffer, "bmp", f);
        
        listArrImg.add(imgBuffer);
    
    
    }
    
    
    
}
