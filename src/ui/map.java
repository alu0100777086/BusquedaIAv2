/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author marcos
 */
public class map {
    private BufferedImage map[][];
    private BufferedImage robot;
    private BufferedImage suelo;
    private BufferedImage obstacle0;
    private BufferedImage obstacle10;
    private BufferedImage obstacle11;
    private BufferedImage obstacle12;
    private BufferedImage obstacle13;
    private int filas;
    private int columnas;
    private final int alto = 530;
    private final int ancho = 530;
    
    public map(int fil, int col){
        initMap(fil, col);
    }
    
    private void initMap(int fil, int col){
        //Initialize variables
        filas = fil;
        columnas = col;
        
        //Load all images in their variables
        try {
            try {
                suelo = ImageIO.read(new File(getClass().getResource("/ui/Suelo0.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                robot = ImageIO.read(new File(getClass().getResource("/ui/Robot0.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                obstacle10 = ImageIO.read(new File(getClass().getResource("/ui/Obstacle10.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                obstacle11 = ImageIO.read(new File(getClass().getResource("/ui/Obstacle11.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                obstacle12 = ImageIO.read(new File(getClass().getResource("/ui/Obstacle12.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                obstacle13 = ImageIO.read(new File(getClass().getResource("/ui/Obstacle13.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            try {
                obstacle0 = ImageIO.read(new File(getClass().getResource("/ui/Obstacle0.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //resize images
        suelo = resize(suelo);
        robot = resize(robot);
        obstacle0 = resize(obstacle0);
        obstacle10 = resize(obstacle10);
        obstacle11 = resize(obstacle11);
        obstacle12 = resize(obstacle12);
        obstacle13 = resize(obstacle13);
        
        //Draw the map
        map = new BufferedImage [filas+1][columnas+1];
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                map[i][j] = suelo;
        map[filas-1][0] = robot;
    }
    
    public BufferedImage resize(BufferedImage im){
        BufferedImage aux = new BufferedImage(ancho/columnas, alto/filas, BufferedImage.TYPE_INT_RGB);

        Graphics g = aux.createGraphics();
        g.drawImage(im, 0, 0, ancho/columnas, alto/filas, null);
        g.dispose();
        return aux;
    }
    
    public BufferedImage sprite(int i, int j){
        return map[i][j];
    }
    
    public int getWidth(){
        return suelo.getWidth();
    }
    
    public int getHeight(){
        return suelo.getHeight();
    }
    
    public void moveright(){
        int i = geti();
        int j = getj();
        map[i][j] = suelo;
        map[i][j+1] = robot;
    }
    
    public void moveleft(){
        int i = geti();
        int j = getj();
        map[i][j] = suelo;
        map[i][j-1] = robot;
    }
    
    public void moveup(){
        int i = geti();
        int j = getj();
        map[i][j] = suelo;
        map[i-1][j] = robot;
    }
    
    public void movedown(){
        int i = geti();
        int j = getj();
        map[i][j] = suelo;
        map[i+1][j] = robot;
    }
    
    public void move(){
        int i = geti();
        int j = getj();
        if(j+1<columnas && i==filas-1)
            moveright();
        else if(i>0 && j==columnas-1)
            moveup();
        else if(j>0)
            moveleft();
        else if(i+1<columnas)
            movedown();
    }
    
    public int geti(){
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                if (map[i][j] == robot)
                    return i;
        return 0;
    }
    
    public int getj(){
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                if (map[i][j] == robot)
                    return j;
        return 0;
    }
    
    public void generaternd(int n){
        int objects = Math.round(n*filas*columnas/100);
        Random r = new Random();
        int i, j, type, ori;
        for (int t=0;t<objects;t++){
            i = r.nextInt(filas);
            j = r.nextInt(columnas);
            type = r.nextInt(2);
            if (type==0){
                if (map[i][j] == suelo)
                    map[i][j] = obstacle0;
                else
                    t--;
            }else if (type==1 && t+1<=objects){
                ori = r.nextInt(2);
                if (ori == 0 && map[i][j] == suelo && map[i+1][j] == suelo){
                    map[i][j] = obstacle10;
                    map[i+1][j] = obstacle11;
                    t++;
                }else if(ori == 1 && map[i][j] == suelo && map[i][j+1] == suelo){
                    map[i][j] = obstacle12;
                    map[i][j+1] = obstacle13;
                    t++;
                }else{
                    t--;
                }
            }else{
                t--;
            }
        }
    }
    
    public void generate(int i, int j, int type, int ori){
        if (type==0){
            if (map[i][j] == suelo)
                map[i][j] = obstacle0;
        }else if (type==1){
            if (ori == 0 && map[i][j] == suelo && map[i+1][j] == suelo){
                map[i][j] = obstacle10;
                map[i+1][j] = obstacle11;
            }else if(ori == 1 && map[i][j] == suelo && map[i][j+1] == suelo){
                map[i][j] = obstacle12;
                map[i][j+1] = obstacle13;
            }
        }
    }
}
