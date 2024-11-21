package menu;

import graph.Edge;
import graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class MenuGraphVisualizer extends JPanel {

    private final Graph graph;

    // Posição central da tela
    private final int centerX = 400;
    private final int centerY = 300;

    // Distância de espaçamento entre os vértices
    private final int vertexSpacing = 100;

    // Número máximo de vértices
    private final int maxVerticesInCircle = 12;

    // Contador para controlar a posição dos vértices
    private int vertexCounter = 0;

    public MenuGraphVisualizer(Graph graph) {
        this.graph = graph;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.GRAY);

        // Desenha as arestas entre os vértices
        for (Map.Entry<Integer, List<Edge>> entry : graph.getAdjVertices().entrySet()) {
            int source = entry.getKey();
            int[] sourceCoordenates = graph.getCoordinates().get(source);

            // Se as coordenadas não existirem, ajusta para o centro da tela
            if (sourceCoordenates == null) {
                sourceCoordenates = getVertexCoordinates(source);
                graph.getCoordinates().put(source, sourceCoordenates);  // Armazenar a posição
            }

            for (Edge edge : entry.getValue()) {
                int destination = edge.destination;
                int[] destinationCoordenates = graph.getCoordinates().get(destination);

                // Se as coordenadas de destino não existirem, ajusta para o centro da tela
                if (destinationCoordenates == null) {
                    destinationCoordenates = getVertexCoordinates(destination);
                    graph.getCoordinates().put(destination, destinationCoordenates);  // Armazenar a posição
                }

                // Desenha a linha entre o vértice de origem e o vértice de destino
                graphics2D.drawLine(sourceCoordenates[0], sourceCoordenates[1], destinationCoordenates[0], destinationCoordenates[1]);
            }
        }

        // Desenha os vértices
        graphics2D.setColor(Color.BLUE);
        for (Map.Entry<Integer, int[]> vertex : graph.getCoordinates().entrySet()) {
            int vertexId = vertex.getKey();
            int[] coordenates = vertex.getValue();

            // Se as coordenadas não existirem, desenha no centro
            if (coordenates == null) {
                coordenates = getVertexCoordinates(vertexId);
                graph.getCoordinates().put(vertexId, coordenates);  // Armazenar a posição
            }

            graphics2D.fillOval(coordenates[0] - 5, coordenates[1] - 5, 10, 10);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(String.valueOf(vertexId), coordenates[0] - 10, coordenates[1] - 10);
            graphics2D.setColor(Color.BLUE);
        }
    }

    // Método para gerar as coordenadas dos vértices
    private int[] getVertexCoordinates(int vertexId) {
        // Calcula o ângulo para a posição circular, considerando o contador de vértices
        double angle = 2 * Math.PI * vertexCounter / maxVerticesInCircle;

        // Calcula a nova posição com base no centro e no espaçamento entre vértices
        int x = (int) (centerX + centerX + vertexSpacing * Math.cos(angle));
        int y = (int) (centerY + centerY + vertexSpacing * Math.sin(angle));

        // Incrementa o contador de vértices para a próxima posição
        vertexCounter++;

        // Retorna as coordenadas calculadas
        return new int[] {x, y};
    }

    // Novo método para salvar a imagem em um arquivo PNG
    public void saveGraphToImage(String filePath) throws IOException {
        int width = getWidth();
        int height = getHeight();

        // Cria a imagem em memória
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = image.createGraphics();

        // Renderiza o conteúdo do painel no BufferedImage
        paintComponent(graphics2D);

        // Salva a imagem no caminho especificado
        File file = new File(filePath);
        ImageIO.write(image, "PNG", file);
    }
}
