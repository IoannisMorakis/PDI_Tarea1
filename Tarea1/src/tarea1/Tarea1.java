/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea1;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author owner
 */
public class Tarea1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        File image = new File("bmp_24.bmp"); //bmp_24 //LAND3 
        //"C:\\Users\\owner\\OneDrive\\Escritorio\\"
        
        // Read Image Pixel by Pixel
        
        BufferedImage imgBuffer1 = ImageIO.read(image);
        BufferedImage imgBuffer2 = ImageIO.read(image);
        BufferedImage imgBuffer3 = ImageIO.read(image);
        
        
        
        File f1 = new File("Output1.bmp");
        File f2 = new File("Output2.bmp");
        File f3 = new File("Output3.bmp");
        
        
        
        
        
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
        
        ImageIO.write(imgBuffer1, "bmp", f1);
        ImageIO.write(imgBuffer2, "bmp", f2);
        ImageIO.write(imgBuffer3, "bmp", f3);
        
        
        System.out.println("Done");
        
        
        
        
        
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
    
    
    
    
    
    
    
    
}
