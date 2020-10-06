
public class BSTree implements BST {

    BSTNode root;
    BSTNode parent;

    public BSTree() {
    }

    public BSTree(BSTNode root) {
        this.root = root;
    }

    public int getSize() {
        return getSize(root);
    }

    public int getSize(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            int result = 1 + getSize(node.leftChild) + getSize(node.rigthChild);
            return result;
        }
    }

    public int getheight() {
        return getheight(root);
    }

    public int getheight(BSTNode node) {
        if (node == null) {
            return 0;
        } else {
            int result = 1 + Math.max(getheight(node.rigthChild), getheight(node.leftChild));
            return result;

        }

    }

    public int getDeph() {
        int deph = 0;
        BSTNode node = this.parent;
        while (node != null) {
            deph++;
            node = node.parent;
        }
        return deph;
    }

    public void ShowInorder() {
        ShowInorder(root);
    }

    public void ShowInorder(BSTNode node) {
        if (node == null) {
            return;
        }
        ShowInorder(node.leftChild);
        System.out.println(node.element);
        ShowInorder(node.rigthChild);
    }

    protected void ShowPostorder(BSTNode node) {
        if (node != null) {
            ShowPostorder(node.leftChild);
            ShowPostorder(node.rigthChild);
            System.out.println(node.element);
        }
    }

    public void ShowPostorder() {
        ShowPostorder(this);
        System.out.println();
    }

    protected void ShowPreorder(BSTNode node) {
        if (node != null) {
            System.out.println(node.element);
            ShowPostorder(node.leftChild);
            ShowPostorder(node.rigthChild);
        }
    }

    public void ShowPreorder() {
        ShowPreorder(this);
        System.out.println();
    }

    public String find(int key) {
        return find(root, key);
    }

    private String find(BSTNode current, int key) {
        String result = null;
        if (current == null) {
            System.out.println("It does not exist");
        } else {
            if (key == current.key) {
                result = current.element;

            } else if (key > current.key) {
                result = find(current.leftChild, key);
            } else {
                result = find(current.rigthChild, key);
            }

        }
        return result;
    }

    public String findIt(int key) {
        BSTNode aux = root;
        while (aux != null) {
            int keyAux = aux.key;
            if (key == keyAux) {
                return aux.element;
            } else if (key < keyAux) {
                aux = aux.leftChild;

            } else {
                aux = aux.rigthChild;
            }
        }
        return null;
    }

    public void insert(int key, String element) {
        BSTNode node = new BSTNode(key, element);
        if (root == null) {
            root = node;
        } else {
            insert(node, root);
        }
    }

    public void insert(BSTNode node, BSTNode nodo) {
        int key = node.key;
        if (key == nodo.key) {
            return;
        }
        if (key < nodo.key) {
            if (nodo.leftChild == null) {
                nodo.leftChild = node;
                node.parent = nodo;
            } else {
                insert(node, nodo.leftChild);
            }
        } else {
            if (nodo.rigthChild == null) {
                nodo.rigthChild = node;
                node.parent = nodo;
            } else {
                insert(node, node.rigthChild);
            }
        }
    }

    public void remove(int key) {
        if (root == null) {
            return;
        }
        if (key == root.key) {
            removeRoot();
        } else {
            remove(key, root);
        }
    }

    private BSTNode findMin(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode MInnode = node;
        while (MInnode.leftChild != null) {
            MInnode = MInnode.leftChild;
        }
        return MInnode;
    }

    private boolean remove(int key, BSTNode node) {
        if (node == null) {
            return false;
        }
        if (node.key < key) {
            return remove(key, node.rigthChild);
        }
        if (node.key > key) {
            return remove(key, node.leftChild);
        }
        if (node.leftChild == null && node.rigthChild == null) {
            parent = node.parent;
            if (parent.leftChild == parent) {
                parent.leftChild = null;
            } else {
                parent.rigthChild = null;
            }
            return true;
        }
        if (node.leftChild == null || node.rigthChild == null) {
            grandChild = null;
            if (node.leftChild == null) {
                grandChild = node.rigthChild;
            } else {
                grandChild = node.leftChild;
            }
            BSTNode grandParent = node.parent;
            if (grandParent.leftChild == node) {
                grandParent.leftChild = grandChild;
            } else {
                grandParent.rigthChild = grandChild;
            }
            grandChild.parent = grandParent;
            return true;
        }
        BSTNode sucesor = findMin(node.rigthChild);
        node.element = sucesor.element;
        node.key = sucesor.key;
        remove(sucesor.key, node.rigthChild);
        return true;
    }

    public void removeRoot() {
        if (root.rigthChild == null && root.leftChild == null) {
            root = null;
        } else if (root.leftChild == null || root.rigthChild == null) {
            if (root.leftChild == null) {
                root = root.rigthChild;
            } else {
                root = root.leftChild;
            }
            root.parent = null;
        } else {
            remove(root.key, root);
        }

    }
}
