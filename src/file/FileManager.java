package file;

import graph.Graph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FileManager {

    public FileManager(String fileName, Graph graph) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Arquivo " + fileName + " criado automaticamente.");
                FileWriter writer = getFileWriter(file);
                writer.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

        try {
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split("=");
                graph.setDirected(Objects.equals(lineSplit[1], "sim"));

                if (scanner.hasNextLine()) {
                    int numVertices = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < numVertices; i++) {
                        String[] vertexInfo = scanner.nextLine().split(" ");
                        int vertex = Integer.parseInt(vertexInfo[0]);
                        int x = Integer.parseInt(vertexInfo[1]);
                        int y = Integer.parseInt(vertexInfo[2]);
                        graph.addVertex(vertex, x, y);
                    }

                    if (scanner.hasNextLine()) {
                        int numEdges = Integer.parseInt(scanner.nextLine());
                        for (int i = 0; i < numEdges; i++) {
                            String[] edgeInfo = scanner.nextLine().split(" ");
                            int source = Integer.parseInt(edgeInfo[0]);
                            int destination = Integer.parseInt(edgeInfo[1]);
                            double weight = Double.parseDouble(edgeInfo[2]);
                            graph.addEdge(source, destination, weight);
                        }
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private FileWriter getFileWriter(File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(writeLine("direcionado=nao"));
        writer.write(writeLine("10"));
        writer.write(writeLine("0 1 1"));
        writer.write(writeLine("1 50 20"));
        writer.write(writeLine("2 10 30"));
        writer.write(writeLine("3 12 50"));
        writer.write(writeLine("4 15 70"));
        writer.write(writeLine("5 20 90"));
        writer.write(writeLine("6 25 11"));
        writer.write(writeLine("7 30 12"));
        writer.write(writeLine("8 35 15"));
        writer.write(writeLine("9 40 20"));
        writer.write(writeLine("10"));
        writer.write(writeLine("0 1 50"));
        writer.write(writeLine("0 9 20"));
        writer.write(writeLine("1 2 35"));
        writer.write(writeLine("2 3 1"));
        writer.write(writeLine("3 4 10"));
        writer.write(writeLine("4 5 28"));
        writer.write(writeLine("5 6 13"));
        writer.write(writeLine("6 7 41"));
        writer.write(writeLine("7 8 8"));
        writer.write(writeLine("8 9 39"));
        return writer;
    }

    private String writeLine(String str) {
        return str + System.lineSeparator();
    }
}
