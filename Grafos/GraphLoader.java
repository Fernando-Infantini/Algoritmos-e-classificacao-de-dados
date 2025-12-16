import java.io.*;
import java.util.*;

public class GraphLoader {

    public static class Edge {
        public int from;
        public int to;
        public double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }


    public static List<Edge> load(String filePath) {

        List<Edge> edges = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;
                if (line.startsWith("c")) continue;
                if (line.startsWith("p")) continue;

                if (line.startsWith("a")) {
                    line = line.substring(1).trim();
                }

                String[] parts = line.split("\\s+");
                if (parts.length < 2) continue;

                int u = Integer.parseInt(parts[0]);
                int v = Integer.parseInt(parts[1]);

                double w = 1.0;
                if (parts.length >= 3)
                    w = Double.parseDouble(parts[2]);

                edges.add(new Edge(u, v, w));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return edges;
    }
}
