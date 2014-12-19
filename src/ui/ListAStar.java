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
public class ListAStar 
{
  public NodeList first;
  public ListAStar() 
  {
    first = null;
  }
  public void insert(Node o)
  {
    NodeList nuevo = new NodeList(o, first);
    first = nuevo;
  }


  public Node extract() 
  {
    Node out = null;
    if (!isEmpty()) 
    {
      out = first.getvalue();
      first = first.getNext();
    }
    return out;
  }
  public boolean isEmpty() 
  {
      return first == null;
  }

  public int length()
  {
    int cnt = 0;
    NodeList nodoaux = first;
    while(nodoaux != null)
    {
      cnt++;
      nodoaux = nodoaux.getNext();
    }
    return cnt;
  }
  public void BubbleSort() 
  {
  	NodeList actual;
  	NodeList siguiente;
  	Node aux;
  	actual = first;
  	while( actual != null )
        {
            siguiente = actual.getNext();
            while(siguiente!= null)
            {
                if(actual.getvalue().getF() > siguiente.getvalue().getF()){
                        aux = actual.getvalue();
                        actual.setvalue(siguiente.getvalue());
                        siguiente.setvalue(aux);

                }
                siguiente = siguiente.getNext();
            }
            actual = actual.getNext();
  	}
  }
  public boolean find(Node nodo)
  {
  	NodeList nodoactual = first;
  	while (nodoactual != null){
  		if(nodoactual.getvalue().equalTo(nodo))
  			return true;
  		else
  			nodoactual= nodoactual.getNext();
  	}
  	return false;
  }
}