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
        if(value.compareTo(node.value) < 0){//value jest mniejsze od value.root
            if(node.left == null){
                node.left = new Node<T>(value);
            } else {
                insert(value, node.left);
            }
        } else if (value.compareTo(node.value) > 0) {//value jest większe od value.root
            if(node.right == null){
                node.right = new Node<T>(value);
            } else {
                insert(value, node.right);
            }
        } else if (value.compareTo(node.value) == 0) {
            System.out.println("taka wartość juz istnieje w drzewie");
        }
    } 

    public void draw(){
        this.draw(this.root,0);
    }

    private void draw(Node<T> node, int height) {
        if(node == null)
            return;

        this.draw(node.right, height+1);

        for(int i = 0; i < height ; i++)
            System.out.print("  ");
        System.out.println(node.value);

        this.draw(node.left, height+1);
    }
}
    


class Node <T extends Comparable<T>> {
    protected T value;
    protected Node<T> left = null;
    protected Node<T> right = null;

    public Node(){}
    public Node(T value){this.value = value;}
}