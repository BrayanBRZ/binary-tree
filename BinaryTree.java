
import java.util.*; // Necessário para Queue, LinkedList e Stack

public class BinaryTree {

    private Node root;

    // INSERÇÃO NA ÁRVORE (BST)
    public void add(int value) {
        root = addRecursive(root, value);
    }

    private Node addRecursive(Node root, int value) {
        if (root == null) {
            return new Node(value);
        } else if (value < root.value) {
            root.left = addRecursive(root.left, value);
        } else {
            root.right = addRecursive(root.right, value);
        }
        return root;
    }

    // IMPRESSÃO EM ORDEM (RECURSIVA)
    public void printInOrder() {
        System.out.println("In-ordem:");
        printInOrderRecursive(this.root);
        System.out.println();
    }

    private void printInOrderRecursive(Node root) {
        if (root == null) {
            return;
        }

        printInOrderRecursive(root.left);
        System.out.print(root.value + " ");
        printInOrderRecursive(root.right);
    }

    // IMPRESSÃO EM ORDEM (ITERATIVA)
    public void printInOrderIterative() {
        System.out.println("In-ordem (iterativo):");
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.value + " ");
            current = current.right;
        }
        System.out.println();
    }

    // IMPRESSÃO NÍVEL POR NÍVEL (BFS)
    public void printLevelOrder() {
        System.out.println("Nível por nível:");
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.value + " ");

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }

    // IMPRESSÃO NÍVEL POR NÍVEL (EXPLÍCITA)
    public void printByLevel() {
        if (root == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Nível " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                System.out.print(current.value + " ");

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            System.out.println();
            level++;
        }
    }

    // IMPRESSÃO DAS FOLHAS
    public void printLeaves() {
        System.out.println("Folhas:");
        printLeavesRecursive(this.root);
        System.out.println();
    }

    private void printLeavesRecursive(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.print(root.value + " ");
        } else {
            printLeavesRecursive(root.left);
            printLeavesRecursive(root.right);
        }
    }

    // CONTAR FOLHAS
    public int countLeaves() {
        return countLeavesRecursive(this.root);
    }

    private int countLeavesRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return countLeavesRecursive(root.left) + countLeavesRecursive(root.right);
    }

    // CONTAR NÓS
    public int countNodes() {
        return countNodesRecursive(this.root);
    }

    private int countNodesRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        return 1 + countNodesRecursive(root.left) + countNodesRecursive(root.right);
    }

    // ALTURA DA ÁRVORE
    public int height() {
        return heightRecursive(this.root);
    }

    private int heightRecursive(Node root) {
        if (root == null) {
            return -1; // altura em arestas
        }
        return 1 + Math.max(heightRecursive(root.left), heightRecursive(root.right));
    }

    // OPERAÇÃO DE BUSCA
    public boolean contains(int value) {
        return containsRecursive(this.root, value);
    }

    private boolean containsRecursive(Node root, int value) {
        if (root == null) {
            return false;
        }

        if (root.value == value) {
            return true; 
        }else if (value < root.value) {
            return containsRecursive(root.left, value); 
        }else {
            return containsRecursive(root.right, value);
        }
    }

    // MENOR VALOR
    public int minValue() {
        return minValueRecursive(this.root);
    }

    private int minValueRecursive(Node root) {
        if (root == null) {
            return -1;
        }
        if (root.left != null) {
            return minValueRecursive(root.left);
        }
        return root.value;
    }

    // MAIOR VALOR
    public int maxValue() {
        return maxValueRecursive(this.root);
    }

    private int maxValueRecursive(Node root) {
        if (root == null) {
            return -1;
        }
        if (root.right != null) {
            return maxValueRecursive(root.right);
        }
        return root.value;
    }

    // VERIFICAR BALANCEAMENTO
    public boolean isBalanced() {
        return isBalancedRecursive(root) != -1;
    }

    private int isBalancedRecursive(Node node) {
        if (node == null) {
            return 0;
        }

        int left = isBalancedRecursive(node.left);
        int right = isBalancedRecursive(node.right);

        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;
    }

    // REMOÇÃO DE NÓ
    public void remove(int value) {
        this.root = removeRecursive(this.root, value);
    }

    private Node removeRecursive(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = removeRecursive(root.left, value);
        } else if (value > root.value) {
            root.right = removeRecursive(root.right, value);
        } else {
            // Nó encontrado
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }

            int minRightValue = minValueRecursive(root.right);
            root.value = minRightValue;
            root.right = removeRecursive(root.right, minRightValue);
        }
        return root;
    }

    // CLASSE INTERNA NODE
    private static class Node {

        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // TESTE
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Inserindo valores
        tree.add(5);
        tree.add(2);
        tree.add(3);
        tree.add(7);
        tree.add(8);

        System.out.println("TESTES DA ÁRVORE BINÁRIA");

        // Impressão em ordem (recursiva)
        tree.printInOrder();
        System.out.println();

        // Impressão em ordem (iterativa)
        tree.printInOrderIterative();
        System.out.println();

        // Impressão nível por nível (BFS simples)
        tree.printLevelOrder();
        System.out.println();

        // Impressão nível por nível (explícita com indicação de nível)
        tree.printByLevel();
        System.out.println();

        // Impressão das folhas
        tree.printLeaves();
        System.out.println();

        // Altura da árvore
        System.out.println(tree.height());
        System.out.println();

        // Quantidade de folhas
        System.out.println(tree.countLeaves());
        System.out.println();

        // Quantidade de nós
        System.out.println(tree.countNodes());
        System.out.println();

        // Busca de elemento
        int search = 5;
        System.out.println(tree.contains(search));
        System.out.println();

        // Menor e maior valor
        System.out.println(tree.minValue());
        System.out.println();

        System.out.println(tree.maxValue());
        System.out.println();

        // Verificação de balanceamento
        System.out.println(tree.isBalanced());
        System.out.println();

        // Remoção de um valor
        tree.remove(7);
        System.out.println("Árvore após remoção (in-ordem):");
        tree.printInOrder();
        System.out.println();
    }
}
