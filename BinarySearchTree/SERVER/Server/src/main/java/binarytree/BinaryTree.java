package binarytree;

public class BinaryTree<T extends Comparable<T>> {
    public Node<T> root = null;


    public void insert(T value) {
        if(this.root == null){
            this.root = new Node<T>(value);
        } else {
            insert(value, root);
        }
    }
    private void insert(T value, Node<T> node) {
        if(value.compareTo(node.value) < 0){//value jest mniejsze od node.value
            if(node.left == null){
                node.left = new Node<T>(value);
            } else {
                insert(value, node.left);
            }
        } else if (value.compareTo(node.value) > 0) {//value jest większe od node.value
            if(node.right == null){
                node.right = new Node<T>(value);
            } else {
                insert(value, node.right);
            }
        } else if (value.compareTo(node.value) == 0) {
            System.out.println("taka wartość juz istnieje w drzewie");
        }
    } 


    public void search(T value){
        if(this.root == null){
            System.out.println("Brak elementów w drzewie");
            return;
        }
        search( value, root);
    }
    public void search(T value, Node<T> node){
        if(node == null){
            System.out.println("Brak elementu " + value);
            return;
        } else if (value.compareTo(node.value) < 0){//value jest mniejsze od node.value
            search(value, node.left);
        } else if (value.compareTo(node.value) > 0) {//value jest większe od node.value
            search(value, node.right);
        } else if (value.compareTo(node.value) == 0) {
            System.out.println("Wartość " + value + " istnieje w drzewie");
        }
    }


    public void draw(){
        this.draw(0, this.root);
    }
    private void draw(int height,Node<T> node) {
        if(node == null)
            return;

        this.draw(height+1,node.right);

        for(int i = 0; i < height ; i++)
            System.out.print("  ");
        System.out.println(node.value);

        this.draw(height+1,node.left);
    }
}
    
class Node <T extends Comparable<T>> {
    protected T value;
    protected Node<T> left = null;
    protected Node<T> right = null;

    public Node(){}
    public Node(T value){this.value = value;}
}