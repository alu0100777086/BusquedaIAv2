/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author marcos
 */
public class map {
    public BufferedImage map[][];
    public BufferedImage robot;
    public BufferedImage suelo;
    public BufferedImage obstacle0;
    public BufferedImage obstacle10;
    public BufferedImage obstacle11;
    public BufferedImage obstacle12;
    public BufferedImage obstacle13;
    public BufferedImage polvo;
    public int filas;
    public int columnas;
    public int alto = 530;
    public int ancho = 530;
    public Pathfinding AStar;
    public ListAStar solucion = null;
    
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
                polvo = ImageIO.read(new File(getClass().getResource("/ui/polvo.png").toURI()));
            } catch (URISyntaxException ex) {
                Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(map.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        polvo = resize(polvo);
        
        //Draw the map
        map = new BufferedImage [filas+1][columnas+1];
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                map[i][j] = suelo;
        map[0][0] = robot;
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
    
    
    public void mostrarCaminoPorConsola(int x, int y){

            System.out.print("(");
            System.out.print(x);
            System.out.print(",");
            System.out.print(y);
            System.out.print(")");
            System.out.print("-> ");

    }
    
    public void move()
    {
//        int i = geti();
//        int j = getj();
//        if(j+1<columnas && i==filas-1)
//            moveright();
//        else if(i>0 && j==columnas-1)
//            moveup();
//        else if(j>0)
//            moveleft();
//        else if(i+1<columnas)
//            movedown();
        Pathfinding AStar = new Pathfinding(this);
        ListAStar solucion = null;
        solucion = AStar.startAStar();
        if(solucion == null){

            JOptionPane.showMessageDialog(null, "No existe una solución posible");
            System.exit(0);
        }
        else
        {
            if(!solucion.isEmpty())
            {

                    Node aux = solucion.extract();

                    int x = aux.getI();
                    int y = aux.getJ();

                    mostrarCaminoPorConsola(x,y);
                    
                    for(int j=0; j < filas; j++)
                    {
                            for(int k=0; k < columnas; k++)
                            {
                                if(sprite(j,k) == robot)
                                {
                                    map[j][k] = suelo;
                                }
                            }
                    }
                    map[x][y] = robot;
            }
        }
    }
    
    public int geti(){
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                if (map[i][j] == robot)
                    return i;
        return 0;
    }
    
    //SIN IMPLEMENTAR
    
    public int getobsi(){
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                if (map[i][j] == polvo)
                    return i;
        return 0;
    }
    
    public int getobsj(){
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++)
                if (map[i][j] == polvo)
                    return j;
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
    
    public void resizeimages(int tam){
        alto = ancho = tam;
        BufferedImage sueloaux = suelo;
        BufferedImage robotaux = robot;
        BufferedImage obstacle0aux = obstacle0;
        BufferedImage obstacle10aux = obstacle10;
        BufferedImage obstacle11aux = obstacle11;
        BufferedImage obstacle12aux = obstacle12;
        BufferedImage obstacle13aux = obstacle13;
        BufferedImage polvoaux = polvo;
        
        
        //Reload images for no quality lose
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
                polvo = ImageIO.read(new File(getClass().getResource("/ui/polvo.png").toURI()));
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
        
        
        //resize them to the new size
        suelo = resize(suelo);
        robot = resize(robot);
        obstacle0 = resize(obstacle0);
        obstacle10 = resize(obstacle10);
        obstacle11 = resize(obstacle11);
        obstacle12 = resize(obstacle12);
        obstacle13 = resize(obstacle13);
        polvo = resize(polvo);
        
        
        //Sustituye
        for (int i=0;i<filas;i++)
            for (int j=0;j<columnas;j++){
                if (map[i][j] == sueloaux)
                    map[i][j] = suelo;
                else if (map[i][j] == robotaux)
                    map[i][j] = robot;
                else if (map[i][j] == obstacle0aux)
                    map[i][j] = obstacle0;
                else if (map[i][j] == obstacle10aux)
                    map[i][j] = obstacle10;
                else if (map[i][j] == obstacle11aux)
                    map[i][j] = obstacle11;
                else if (map[i][j] == obstacle12aux)
                    map[i][j] = obstacle12;
                else if (map[i][j] == obstacle13aux)
                    map[i][j] = obstacle13;
                else if (map[i][j] == polvoaux)
                    map[i][j] = polvo;
            }
    }
    
    public void displaybot() {
        JTextField field1 = new JTextField("000");
        JTextField field2 = new JTextField("000");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("ROBOT X:"));
        panel.add(field1);
        panel.add(new JLabel("ROBOT Y:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Coordenadas del robot",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            displayobs(Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText()));
        } else {
            System.out.println("Cancelled");
        }
    }
    
    private void displayobs(int botx, int boty) {
        JTextField field1 = new JTextField("000");
        JTextField field2 = new JTextField("000");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Polvo X:"));
        panel.add(field1);
        panel.add(new JLabel("Polvo Y:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Coordenadas del obstaculo",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            changepos(botx, boty, Integer.parseInt(field1.getText()), Integer.parseInt(field2.getText()));
        } else {
            System.out.println("Cancelled");
        }
    }
    
    private void changepos(int botx, int boty, int objx, int objy){
        int i, j;
        i = geti();
        j = getj();
        map[i][j] = suelo;
        map[botx][boty] = robot;
        i = getobsi();
        j = getobsj();
        map[i][j] = suelo;
        map[objx][objy] = polvo;
    }
}


