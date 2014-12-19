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
public class NodeList {

    private Node value;
    private NodeList next;

    public NodeList() {
      next = null;
    }

    public NodeList(Node o, NodeList n) {
      value = o;
      next = n;
    }

    public void setNext(NodeList n) {
      next = n;
    }

    public NodeList getNext() {
      return next;
    }

    public void setvalue(Node i){
      value = i;
    }

    public Node getvalue(){
      return value;
    }

}

