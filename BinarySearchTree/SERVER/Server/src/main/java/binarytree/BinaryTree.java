package binarytree;

import java.util.ArrayList;

public class BinaryTree<T extends Comparable<T>> {
    private Node<T> root = null;
    private ArrayList <String> treeList = new ArrayList<String>();
    private String s;
    private Node<T> nodeToBeLinked = null;
    private Node<T> nodeToLink = null;
    private Node<T> parent = null;

    public String insert(T value) {
        if(this.root == null){
            this.root = new Node<T>(value);
            return getTree();
        }
        return insert(value, root);

    }
    private String insert(T value, Node<T> node) {
        
        if(value.compareTo(node.value) < 0){//value jest mniejsze od node.value
            if(node.left == null){
                node.left = new Node<T>(value);
                return getTree();
            } else {
                return insert(value, node.left);
            }
        } else if (value.compareTo(node.value) > 0) {//value jest większe od node.value
            if(node.right == null){
                node.right = new Node<T>(value);
                return getTree();
            } else {
                return insert(value, node.right);
            }
        } else if (value.compareTo(node.value) == 0) {//value jest równe node.value
            return getTree() + "\ntaka wartość juz istnieje w drzewie";
        }
        return getTree();
    } 


    public String search(T value){
        if(this.root == null){
            return"Brak elementów w drzewie";
        }
        return search( value, root);
    }
    public String search(T value, Node<T> node){
        if(node == null){
            return "Brak elementu " + value;
        } else if (value.compareTo(node.value) < 0){//value jest mniejsze od node.value
            return search(value, node.left);
        } else if (value.compareTo(node.value) > 0) {//value jest większe od node.value
            return search(value, node.right);
        } else if (value.compareTo(node.value) == 0) {
            return "Wartość " + value + " istnieje w drzewie";
        }
        return "Błąd";
    }

    public String delete(T value){
        if(this.root == null){
            return "Brak elementów w drzewie, nie można nic usunąć";
        }
        return delete( value, root);
    }
    private String delete(T value, Node<T> node){
        if(node == null){
            return "Brak elementu " + value;
        } else if (value.compareTo(node.value) < 0){//value jest mniejsze od node.value
            return delete(value, node.left);
        } else if (value.compareTo(node.value) > 0) {//value jest większe od node.value
            return delete(value, node.right);
        } else if (value.compareTo(node.value) == 0) {//znaleziona wartosc ktora chcemy usunac
            parent = getparent(root, node);
            System.out.println(parent.value + " \n");
            /*if(parent == null){
                root = node;
            }
            
            if(node.left == null && node.right == null){
                parent = getparent(value, node);

            }*/
       
        }
        return getTree();
    }
    
    private Node<T> searchmin (Node<T> node){
        if(node == null)
            return null;
        Node<T> current = node;
        while(current.left != null)
            current = current.left;
        return current;
    }

    private void draw(int height,Node<T> node) {
        if(node == null)
            return;

        this.draw(height+1,node.right);
        s = "";
        for(int i = 0; i < height ; i++)
            s += "  ";
        s += ">" + node.value;
        treeList.add(s);
        this.draw(height+1,node.left);
    }

    private Node<T> getparent(Node<T> currentRoot, Node<T> node){
        if(node == root || root == null){
            return null;
        } else {
                if(currentRoot.left==node || currentRoot.right==node)
                    return currentRoot;
                else {
                    if (currentRoot.value.compareTo(node.value) < 0) {
                        return getparent(currentRoot.right,node);
                    }
                    else {
                        return getparent(currentRoot.left,node);
                    }
                }
            }
    }

    public String getTree() {
        treeList = new ArrayList<String>();
        this.draw(0, this.root);
        s = "";
        for(String string : treeList){
            s = s + string + "\n";
        }
        if(s.compareTo("") == 0)
            s = "drzewo jest puste";
        return s;
    }
}

class Node <T extends Comparable<T>> {
    protected T value;
    protected Node<T> left = null;
    protected Node<T> right = null;

    public Node(){}
    public Node(T value){this.value = value;}
}