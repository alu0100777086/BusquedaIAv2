/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import ui.ListAStar;
import java.awt.image.BufferedImage;

/**
 *
 * @author eebritos
 */

public class Pathfinding 
{   
    private ListAStar Lista_abierta;
    private ListAStar Lista_cerrada;
    private ListAStar Lista_final;
    private map matriz;
    private int origen_x;
    private int origen_y;
    private int destino_x;
    private int destino_y;
    private boolean fin;
    private int current_g; //Valor g del nodo en el que estamos actualmente 
    public Pathfinding(map matriz_){

            Lista_abierta = new ListAStar();
            Lista_cerrada = new ListAStar();
            Lista_final = new ListAStar();
            matriz = matriz_;
            origen_x = matriz.geti();
            origen_y = matriz.getj();
            destino_x = matriz.getobsi();
            destino_y = matriz.getobsj();
            current_g=0;
            fin=false;		
    }

    public void nodeEval(int i, int j, boolean dir, Node padre){
            if(fin == false){
                    if(matriz.sprite(i,j) == matriz.polvo){
                            fin=true;
                            return;
                    }
                    if((matriz.obstacle0 == matriz.sprite(i,j)) || (matriz.obstacle10 == matriz.sprite(i,j)) || 
                        matriz.obstacle11 == matriz.sprite(i,j) || (matriz.obstacle12 == matriz.sprite(i,j) ||
                        matriz.obstacle13 == matriz.sprite(i,j))){
                            return;
                    }
                    Node node = new Node(i, j, false);
                    if(Lista_cerrada.find(node)){
                            return;
                    }
                    node.setParent(padre);
                    int i_manhattan;
                    int j_manhattan;
                    if(i>destino_x)
                            i_manhattan= i - destino_x;
                    else
                            i_manhattan= destino_x - i;
                    if(j>destino_y)
                            j_manhattan= j - destino_y;
                    else
                            j_manhattan= destino_y - j;
                    node.setH( (i_manhattan+j_manhattan)*10 );
                    if(!dir)
                            node.setG(current_g+10);
                    else
                            node.setG(current_g+50);
                    node.calcF();
                    if(Lista_abierta.find(node)){
                            //Buscamos el nodo
                            boolean encontrado = false;
                            NodeList nodoactual = Lista_abierta.first;

                            while (encontrado != true){
                                    if(nodoactual.getvalue().equalTo(node))
                                            encontrado = true;
                                    else
                                            nodoactual= nodoactual.getNext();
                            }

                            if(nodoactual.getvalue().getG() > node.getG()){
                                    nodoactual.getvalue().setG(node.getG());
                                    nodoactual.getvalue().setParent(padre);
                                    nodoactual.getvalue().calcF();
                            }
                    } 
                    else
                        Lista_abierta.insert(node);
            }
    }

    public ListAStar startAStar() {
            Node origen = new Node(origen_x, origen_y, false);
            Node destino = new Node(destino_x, destino_y, false);
            Node auxNode = null;
            Lista_abierta.insert(origen);
            while(fin != true){
                    if(Lista_abierta.isEmpty()){
                            fin=true;
                            return null;
                    }
                    auxNode = Lista_abierta.extract();
                    Lista_cerrada.insert(auxNode);
                    current_g = auxNode.getG();
                    int i = auxNode.getI();
                    int j = auxNode.getJ();
                    if(i!=0 && j!=0 && i!=matriz.filas-1 && j!=matriz.columnas-1){
                            nodeEval(i, j+1,false,auxNode);
                            nodeEval(i+1, j,false,auxNode);
                            nodeEval(i, j-1,false,auxNode);
                            nodeEval(i-1, j,false,auxNode);
                            nodeEval(i+1, j+1,true,auxNode);
                            nodeEval(i+1, j-1,true,auxNode);
                            nodeEval(i-1, j-1,true,auxNode);
                            nodeEval(i-1, j+1,true,auxNode);
                    }
                    else if(i==0){
                            if(j==0){
                                    //Estamos en la esquina superior izquierda
                                    nodeEval(i+1, j,false,auxNode);
                                    nodeEval(i, j+1,false,auxNode);
                                    nodeEval(i+1, j+1,true,auxNode);
                            }
                            else if(j==matriz.columnas-1){
                                    //EStamos es la esquina superior dcha
                                    nodeEval(i, j-1,false,auxNode);
                                    nodeEval(i+1, j,false,auxNode);
                                    nodeEval(i+1, j-1,true,auxNode);
                            }
                            else{
                                    //Estamos pegados a la parte superior (no esquina)
                                    nodeEval(i+1, j,false,auxNode);
                                    nodeEval(i, j-1,false,auxNode);
                                    nodeEval(i, j+1,false,auxNode);
                                    nodeEval(i+1, j-1,true,auxNode);
                                    nodeEval(i+1, j+1,true,auxNode);
                            }
                    }
                    else if(i==matriz.filas-1){
                            if(j==0){
                                    //Estamos en la esquina inferior izda
                                    nodeEval(i, j+1,false,auxNode);
                                    nodeEval(i-1, j,false,auxNode);
                                    nodeEval(i-1, j+1,true,auxNode);
                            }
                            else if(j==matriz.columnas-1){
                                    //Estamos en la esquina inferior dcha
                                    nodeEval(i, j-1,false,auxNode);
                                    nodeEval(i-1, j,true,auxNode);
                                    nodeEval(i-1, j-1,true,auxNode);
                            }
                            else{
                                    //Estamos pegados a la parte inferior (no esquina)
                                    nodeEval(i, j+1,false,auxNode);
                                    nodeEval(i, j-1,false,auxNode);
                                    nodeEval(i-1, j,false,auxNode);
                                    nodeEval(i-1, j+1,true,auxNode);
                                    nodeEval(i-1, j-1,true,auxNode);
                            }
                    }
                    else if(j==0){
                            //Estamos pegados a la parte izda
                            nodeEval(i-1, j,false,auxNode);
                            nodeEval(i+1, j,false,auxNode);
                            nodeEval(i, j+1,false,auxNode);
                            nodeEval(i-1, j+1,true,auxNode);
                            nodeEval(i+1, j+1,true,auxNode);
                    }
                    else if(j==matriz.columnas-1){
                            //Estamos pegados a la parte dcha
                            nodeEval(i, j-1,false,auxNode);
                            nodeEval(i+1, j,false,auxNode);
                            nodeEval(i-1, j,false,auxNode);
                            nodeEval(i-1, j-1,true,auxNode);
                            nodeEval(i+1, j-1,true,auxNode);
                    }
                    Lista_abierta.BubbleSort();
            }
            while(auxNode.getParent() != null){
                    Lista_final.insert(auxNode);
                    auxNode=auxNode.getParent();
            }
            return Lista_final;
    }
}
   
