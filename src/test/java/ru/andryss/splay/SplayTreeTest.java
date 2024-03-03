package ru.andryss.splay;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.andryss.splay.SplayTree.Node;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SplayTreeTest {

    @Test
    @DisplayName("Вставка вершины в пустое дерево прошла успешно")
    public void insert_nodeInserted_returnSameNode() {
        SplayTree tree = new SplayTree();

        Node node = tree.insert(0);

        assertEquals(getRoot(tree), node);
        assertEquals(0, node.getKey());
        assertNull(node.getParent());
        assertNull(node.getLeft());
        assertNull(node.getRight());
    }

    @Test
    @DisplayName("Вставка вершины в непустое дерево прошла успешно, вершина поднялась в корень")
    public void insert_treeNotEmpty_becomeRoot() {
        SplayTree tree = new SplayTree();
        tree.insert(1);

        tree.insert(0);

        Node root = getRoot(tree);
        assertEquals(0, root.getKey());
        assertNull(root.getParent());
        assertNull(root.getLeft());
        assertEquals(1, root.getRight().getKey());
        assertEquals(root, root.getRight().getParent());
        assertNull(root.getRight().getLeft());
        assertNull(root.getRight().getRight());
    }

    @Test
    @DisplayName("Вставка уже существующей вершины не прошла")
    public void insert_keyPresent_returnNull() {
        SplayTree tree = new SplayTree();
        tree.insert(10);

        Node node = tree.insert(10);

        assertNull(node);
    }

    @Test
    @DisplayName("Вставка многих вершин прошла успешно, дерево имеет корректную структуру")
    public void manyInsert_nodesInserted_correctTreeStructure() {
        SplayTree tree = new SplayTree();

        tree.insert(3);
        tree.insert(1);
        tree.insert(-2);
        tree.insert(-3);
        tree.insert(0);

        Node root = getRoot(tree);
        assertEquals(0, root.getKey());
        assertEquals(-3, root.getLeft().getKey());
        assertEquals(-2, root.getLeft().getRight().getKey());
        assertEquals(1, root.getRight().getKey());
        assertEquals(3, root.getRight().getRight().getKey());
    }

    @Test
    @DisplayName("Поиск вершины в пустом дереве вернул null")
    public void search_treeEmpty_returnNull() {
        SplayTree tree = new SplayTree();

        Node node = tree.search(10);

        assertNull(node);
    }

    @Test
    @DisplayName("Поиск отсутствующей вершины в дереве вернул null")
    public void search_keyAbsent_returnNull() {
        SplayTree tree = new SplayTree();
        tree.insert(-1);
        tree.insert(1);
        tree.insert(0);

        Node node = tree.search(5);

        assertNull(node);
    }

    @Test
    @DisplayName("Поиск вершины в дереве дереве вернул нужную вершину")
    public void search_keyPresent_returnNode() {
        SplayTree tree = new SplayTree();
        tree.insert(1);

        Node node = tree.search(1);

        assertEquals(1, node.getKey());
    }

    @Test
    @DisplayName("Поиск вершины в дереве прошел успешно, вершина в корне")
    public void search_keyPresent_splayAndBecomeRoot() {
        SplayTree tree = new SplayTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(0);

        Node root = tree.search(10);

        assertEquals(getRoot(tree), root);
        assertEquals(10, root.getKey());
        assertEquals(5, root.getLeft().getKey());
        assertEquals(0, root.getLeft().getLeft().getKey());
    }

    @Test
    @DisplayName("Поиск многих вершин прошел успешно, дерево имеет корректную структуру")
    public void manySearch_allPresent_correctTreeStructure() {
        SplayTree tree = new SplayTree();
        tree.insert(0);
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(5);

        tree.search(0);
        tree.search(5);
        tree.search(4);
        tree.search(1);
        tree.search(2);

        Node root = getRoot(tree);
        assertEquals(2, root.getKey());
        assertEquals(1, root.getLeft().getKey());
        assertEquals(0, root.getLeft().getLeft().getKey());
        assertEquals(3, root.getRight().getKey());
        assertEquals(5, root.getRight().getRight().getKey());
    }

    @Test
    @DisplayName("Удаление вершины из пустого дереве вернуло null")
    public void delete_emptyTree_returnNull() {
        SplayTree tree = new SplayTree();

        Node node = tree.delete(0);

        assertNull(node);
    }

    @Test
    @DisplayName("Удаление отсутствующей вершины из непустого дереве вернуло null")
    void delete_keyAbsent_returnNull() {
        SplayTree tree = new SplayTree();
        tree.insert(-100);
        tree.insert(100);
        tree.insert(0);

        Node node = tree.delete(15);

        assertNull(node);
    }

    @Test
    @DisplayName("Удаление вершины прошло успешно, вернулась удаленная вершина")
    void delete_keyPresent_returnSameNode() {
        SplayTree tree = new SplayTree();
        tree.insert(3);

        Node node = tree.delete(3);

        assertEquals(3, node.getKey());
        assertNull(node.getParent());
        assertNull(node.getLeft());
        assertNull(node.getRight());
    }

    @Test
    @DisplayName("Удаление вершины прошло успешно, вершины нет в дереве")
    void delete_keyPresent_deleteNode() {
        SplayTree tree = new SplayTree();
        tree.insert(3);

        tree.delete(3);

        assertNull(getRoot(tree));
    }

    @Test
    @DisplayName("Удаление вершины прошло успешно, дерево перестроилось правильно после удаления")
    void delete_keyPresent_childrenMerged() {
        SplayTree tree = new SplayTree();
        tree.insert(-1);
        tree.insert(1);
        tree.insert(0);

        tree.delete(1);

        Node root = getRoot(tree);
        assertEquals(0, root.getKey());
        assertEquals(-1, root.getLeft().getKey());
    }

    @Test
    @DisplayName("Удаление всех вершин прошло успешно, дерево пустое")
    void deleteAll_nodesDeleted_treeEmpty() {
        SplayTree tree = new SplayTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        tree.delete(6);
        tree.delete(5);
        tree.delete(7);

        assertNull(getRoot(tree));
    }

    @Test
    @DisplayName("Комбинация из вставок, поисков, удалений прошла успешно, дерево имеет корректную структуру")
    void complexOps_allCompleted_correctTreeStructure() {
        SplayTree tree = new SplayTree();

        tree.insert(5);
        tree.insert(1);
        tree.insert(10);
        tree.delete(5);
        tree.insert(15);
        tree.insert(3);
        tree.search(10);
        tree.delete(3);

        Node root = getRoot(tree);
        assertEquals(1, root.getKey());
        assertEquals(10, root.getRight().getKey());
        assertEquals(15, root.getRight().getRight().getKey());
    }

    @SneakyThrows
    private static Node getRoot(SplayTree tree) {
        Field field = tree.getClass().getDeclaredField("root");
        field.setAccessible(true);
        return (Node) field.get(tree);
    }

}