
public class StudentNode {

    private int id, height; //key

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    private String name;
    private StudentNode left, right;

    public StudentNode(String name, int id) {
        this.height = 1;
        this.name = name;
        this.id = id;
        left = null;
        right = null;
    }

    @Override
    public String toString() {
        return name + ", " + id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentNode getLeft() {
        return left;
    }

    public void setLeft(StudentNode left) {
        this.left = left;
    }

    public StudentNode getRight() {
        return right;
    }

    public void setRight(StudentNode right) {
        this.right = right;
    }
}
