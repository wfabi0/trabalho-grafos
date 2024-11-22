package menu;

import file.FileManager;
import graph.Edge;
import graph.Graph;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuApp {

    private final Graph graph;
    private final FileManager fileManager;

    public MenuApp() {
        this.graph = new Graph();
        this.fileManager = new FileManager(graph);
        this.start();
    }

    private void start() {
        String optionsMenu = "\n" +
                "1. Importar grafo\n" + //
                "2. Criar grafo\n" + //
                "3. Exibir adjacências\n" + //
                "4. Consultar se um vértice é adjacente a outro\n" + //
                "5. Inserir nova aresta\n" + //
                "6. Inserir arestas em conjunto\n" + //
                "7. Remover aresta\n" + //
                "8. Editar coordenadas de um vértice\n" + //
                "9. Consultar o primeiro adjacente de um vértice\n" +
                "10. Consultar o próximo adjacente de um vértice a partir de um adjacente\n" +
                "11. Consultar a lista completa de adjacentes de um vértice\n" +
                "12. Exportar grafo\n" +
                "13. Exibir grafo\n" +
                "0. Sair\n\n";

        int option;
        while (true) {
            try {
                option = Integer.parseInt(JOptionPane.showInputDialog(null, optionsMenu, "Grafo - Menu", JOptionPane.PLAIN_MESSAGE));
                switch (option) {
                    case 1: {
                        try {
                            importGraph();
                        } catch (Exception ignored) {
                            continue;
                        }
                        break;
                    }
                    case 2: {
                        emptyGraph();
                        break;
                    }
                    case 3: {
                        showGraph();
                        break;
                    }
                    case 4: {
                        checkVertex();
                        break;
                    }
                    case 5: {
                        addEdge();
                        break;
                    }
                    case 6: {
                        addEdges();
                        break;
                    }
                    case 7: {
                        removeEdge();
                        break;
                    }
                    case 8: {
                        editCoordenates();
                        break;
                    }
                    case 9: {
                        firstAdjacent();
                        break;
                    }
                    case 10: {
                        nextAdjacent();
                        break;
                    }
                    case 11: {
                        getAdjacents();
                        break;
                    }
                    case 12: {
                        exportGraph();
                        break;
                    }
                    case 13: {
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
                System.err.println("Erro: " + e.getLocalizedMessage());
            }

        }
    }

    private void importGraph() {
        try {
            String fileName = JOptionPane.showInputDialog(null, "Insira o nome do arquivo (Ex: entrada.txt)", "Grafo - Importar", JOptionPane.QUESTION_MESSAGE);
            graph.clear();
            fileManager.importFile(fileName);
            JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso.", "Grafo - Importar", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Importar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emptyGraph() {
        try {
            int vertexSize = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o número de vértices", "Grafo - Criar", JOptionPane.QUESTION_MESSAGE));
            graph.clear();
            graph.createEmptyGraph(vertexSize);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Criar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showGraph() {
        JOptionPane.showMessageDialog(null, graph.printAdjacencies(), "Grafo - Visualização 1", JOptionPane.PLAIN_MESSAGE);
    }

    private void checkVertex() {
        try {
            int v1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o primeiro vértice", "Grafo - Consultar Vértices", JOptionPane.QUESTION_MESSAGE));
            int v2 = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o segundo vértice", "Grafo - Consultar Vértices", JOptionPane.QUESTION_MESSAGE));
            JOptionPane.showMessageDialog(null, "Os vértices " + (graph.isAdjacant(v1, v2) ? "são" : "não são") + " adjacentes.", "Grafo - Consultar Vértices", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Consultar Vértices", JOptionPane.ERROR_MESSAGE);
        }
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

    private void addEdges() {
        try {
            int size = Integer.parseInt(JOptionPane.showInputDialog(null, "Número de arestas que deseja adicionar", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.QUESTION_MESSAGE));

            if (size <= 0) {
                JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.ERROR_MESSAGE);
            }

            List<Edge> edges = new ArrayList<>();
            int source = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira a origem da aresta", "Grafo - Múltiplas Adicionar Aresta", JOptionPane.QUESTION_MESSAGE));

            for (int i = 0; i < size; i++) {
                int destination = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o destino da " + (i + 1) + "° aresta", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.QUESTION_MESSAGE));
                double weight = Double.parseDouble(JOptionPane.showInputDialog(null, "Insira o peso da " + (i + 1) + "° aresta", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.QUESTION_MESSAGE));
                edges.add(new Edge(destination, weight));
            }

            graph.addEdges(source, edges);
            JOptionPane.showMessageDialog(null, "Todas as arestas foram adicionadas com sucesso.", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ignore) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Adicionar Múltiplas Aresta", JOptionPane.ERROR_MESSAGE);
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

    private void firstAdjacent() {
        try {
            int vertex = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o vértice", "Grafo - Primeiro Adjacente", JOptionPane.QUESTION_MESSAGE));
            Edge firstEdge = graph.getFirstAdjacent(vertex);
            JOptionPane.showMessageDialog(null, (firstEdge != null ? firstEdge.toString() : "Esse vértice não possui um adjacente."), "Grafo - Primeiro Adjacente de " + vertex, JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Primeiro Adjacente", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void nextAdjacent() {
        try {
            int vertex = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o vértice", "Grafo - Próximo Adjacente", JOptionPane.QUESTION_MESSAGE));
            int destination = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o adjacente da aresta", "Grafo - Próximo Adjacente", JOptionPane.QUESTION_MESSAGE));
            Edge nextEdge = graph.getNextAdjacent(vertex, destination);
            JOptionPane.showMessageDialog(null, nextEdge != null ? nextEdge.toString() : "Esse vértice não possui um próximo adjacente.", "Grafo - Próximo Adjacente de " + vertex + " -> " + destination, JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Próximo Adjacente", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void getAdjacents() {
        try {
            int vertex = Integer.parseInt(JOptionPane.showInputDialog(null, "Insira o vértice", "Grafo - Lista de Adjacentes", JOptionPane.QUESTION_MESSAGE));
            List<Edge> edges = graph.getAdjacents(vertex);
            if (edges.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Esse vértice não possui adjacentes.", "Grafo - Lista de Adjacentes de " + vertex, JOptionPane.PLAIN_MESSAGE);
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (Edge edge : edges) {
                    stringBuilder.append(edges.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(null, stringBuilder, "Grafo - Lista de Adjacentes de " + vertex, JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, provavelmente você não inseriu dados inválidos.", "Grafo - Lista de Adjacentes", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportGraph() {
        try {
            String fileName = JOptionPane.showInputDialog(null, "Insira o nome do arquivo (Ex: entrada.txt)", "Grafo - Exportar", JOptionPane.QUESTION_MESSAGE);
            fileManager.exportFile(fileName);
            JOptionPane.showMessageDialog(null, "Arquivo exportado com sucesso.", "Grafo - Exportar", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Um erro ocorreu, não foi possivel exportar o grafo.", "Grafo - Exportar", JOptionPane.ERROR_MESSAGE);
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
