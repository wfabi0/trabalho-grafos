package menu;

import file.FileManager;
import graph.Graph;

import javax.swing.*;
import java.io.IOException;

public class MenuApp {

    private final Graph graph;

    public MenuApp() {
        this.graph = new Graph();
        new FileManager("entrada.txt", graph);
        this.start();
    }

    private void start() {
        String optionsMenu = "\n" +
                "1. Exibir grafo\n" +
                "2. Consultar se um vértice é adjacente a outro\n" +
                "3. Inserir nova aresta\n" +
                "4. Remover aresta\n" +
                "5. Editar coordenadas de um vértice\n" +
                "6. Exportar Grafo\n" +
                "0. Sair\n\n";

        int option;
        while (true) {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, optionsMenu, "Grafo - Menu", JOptionPane.PLAIN_MESSAGE));
                switch (option) {
                    case 1: {
                        try {
                            showGraph();
                        } catch (Exception ignored) {
                        }
                        break;
                    }
                    case 2: {
                        try {
                            checkVertex();
                        } catch (Exception ignored) {
                        }
                        break;
                    }
                    case 3: {
                        addEdge();
                        break;
                    }
                    case 4: {
                        removeEdge();
                        break;
                    }
                    case 5: {
                        editCoordenates();
                        break;
                    }
                    case 6: {
                        saveGraphToImage();
                        break;
                    }
                    case 0: {
                        exit();
                        return;
                    }
                    default: {
                        break;
                    }
                }
            } catch (Exception e) {
                System.err.println("Erro em tempo de execução.");
                System.err.println("Erro: " + System.lineSeparator() + e.getLocalizedMessage());
            }

        }
    }

    private void showGraph() {
        JOptionPane.showMessageDialog(null, graph.printAdjacencies(), "Grafo - Visualização 1", JOptionPane.PLAIN_MESSAGE);
    }

    private void checkVertex() {
        int v1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o primeiro vértice", "Grafo - Consultar Vértices", JOptionPane.QUESTION_MESSAGE));
        int v2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o segundo vértice", "Grafo - Consultar Vértices", JOptionPane.QUESTION_MESSAGE));
        JOptionPane.showMessageDialog(null, "Os vértices " + (graph.isAdjacant(v1, v2) ? "são" : "não são") + " adjacentes.", "Grafo - Consultar Vértices", JOptionPane.PLAIN_MESSAGE);
    }

    private void addEdge() {
        try {
            int source = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a origem da aresta", "Grafo - Adicionar Aresta", JOptionPane.QUESTION_MESSAGE));
            int destination = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o destino da aresta", "Grafo - Adicionar Aresta", JOptionPane.QUESTION_MESSAGE));
            double weight = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da aresta", "Grafo - Adicionar Aresta", JOptionPane.QUESTION_MESSAGE));
            graph.addEdge(source, destination, weight);
            JOptionPane.showMessageDialog(null, "Aresta criada com sucesso.", "Grafo - Adicionar Aresta", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ignore) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Adicionar Aresta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeEdge() {
        try {
            int source = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a origem da aresta", "Grafo - Remover Aresta", JOptionPane.QUESTION_MESSAGE));
            int destination = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o destino da aresta", "Grafo - Remover Aresta", JOptionPane.QUESTION_MESSAGE));
            graph.removeEdge(source, destination);
            JOptionPane.showMessageDialog(null, "Aresta removida com sucesso.", "Grafo - Remover Aresta", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ignore) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Remover Aresta", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCoordenates() {
        try {
            int vertex = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o vértice", "Grafo - Editar Coordenadas", JOptionPane.QUESTION_MESSAGE));
            int x = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a nova coodenada de X", "Grafo - Editar Coordenadas", JOptionPane.QUESTION_MESSAGE));
            int y = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a nova coodenada de Y", "Grafo - Editar Coordenadas", JOptionPane.QUESTION_MESSAGE));
            graph.setVertexCoordinates(vertex, x, y);
            JOptionPane.showMessageDialog(null, "Coordenada editada com sucesso.", "Grafo - Editar Coordenadas", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ignore) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Editar Coordenadas", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveGraphToImage() {
        try {
            String filePath = JOptionPane.showInputDialog("Digite o caminho para salvar a imagem (exemplo: grafico.png):");
            MenuGraphVisualizer menuGraphVisualizer = new MenuGraphVisualizer(graph);
            menuGraphVisualizer.setSize(800, 600);
            menuGraphVisualizer.saveGraphToImage(filePath);
            JOptionPane.showMessageDialog(null, "Imagem salva com sucesso!", "Salvar Imagem", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar a imagem.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exit() {
        JOptionPane.showMessageDialog(null, "Obrigado por usar o programa.", "Grafo - Saída", JOptionPane.PLAIN_MESSAGE);
    }
}
