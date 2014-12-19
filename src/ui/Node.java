/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author eebritos
 */
public class Node  
{
    public Node parent;
    public int i,j,g,h,f;
    public boolean obstacle;

    /**
     *
     * @param g_
     * @param h_
     * @param i_
     * @param j_
     */
    public Node(int i_,int j_,boolean obs)
    {
        g = 0;
        h = 0;
        f = 0;
        i = i_;
        j = j_;
        obstacle = obs;
        parent = null;
    }
    public Node()
    {
        g = 0;
        h = 0;
        f = 0;
        i = 0;
        j = 0;
        parent = null;
    }
    public int getF ()
    {
        return f;
    }
    public int getI()
    {
        return i;
    }
    public int getJ()
    {
        return j;
    }
    public Node getParent()
    {
        return parent;
    }
    public int getG()
    {
        return g;
    }

    /**
     *
     * @return
     */
    public boolean getObs() 
    {
        return obstacle;
    }

    /**
     *
     * @param pad
     */
    public void setParent(Node pad)
    {
        parent = pad;
    }

    /**
     *
     * @param obs
     */
    public void setObs(boolean obs) 
    {
        obstacle = obs;
    }

    /**
     *
     */
    public void calcF() 
    {
        f = g + h;
    }

    /**
     *
     * @param g_
     */
    public void setG(int g_)
    {
        g = g_;
    }

    /**
     *
     * @param h_
     */
    public void setH(int h_)
    {
        h = h_;
    }

    //Sobrecarga del operador ==

    /**
     *
     * @param a
     * @return
     */
    
    public boolean equalTo(Node a)
    {
       return a.getI() == i && a.getJ() == j;
    }
}
