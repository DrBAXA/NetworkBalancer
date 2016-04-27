package com.vdanyliuk.view;


import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.SimpleDirectedGraph;
import com.vdanyliuk.core.edges.Edge;
import com.vdanyliuk.core.edges.ElectricNetworkEdge;
import com.vdanyliuk.core.vertices.Counter;
import com.vdanyliuk.core.vertices.ElectricNetworkBus;
import com.vdanyliuk.core.vertices.Vertex;

import javax.swing.*;

import static com.vdanyliuk.core.edges.ElectricNetworkEdge.ZERO_LINE_DATA;

public class TestView {

    private final JFrame frame;
    private final JScrollPane pane;
    private final JGraph viewGraph;
    private final SimpleDirectedGraph<Vertex, Edge> graph;

    public static void main(String[] args) {
        TestView testView = new TestView();
        testView.init();
    }


    public TestView() {
        graph = new SimpleDirectedGraph<>(Edge.class);

        Vertex v1 = new Counter("C1", "1");
        Vertex v2 = new ElectricNetworkBus(graph, "B2");
        Vertex v3 = new Counter("C3", "3");
        Vertex v4 = new Counter("C4", "4");

        graph.addVertex(v1);
        graph.addVertex(v4);
        graph.addVertex(v3);
        graph.addVertex(v2);

        graph.addEdge(v1, v2, new ElectricNetworkEdge(v1, v2, ZERO_LINE_DATA));
        graph.addEdge(v2, v3, new ElectricNetworkEdge(v2, v3, ZERO_LINE_DATA));
        graph.addEdge(v2, v4, new ElectricNetworkEdge(v2, v4, ZERO_LINE_DATA));

        viewGraph = new JGraph(new JGraphModelAdapter<>(graph));

        pane = new JScrollPane(viewGraph);

        frame = new JFrame("GRAPH");
        frame.add(pane);


    }

    private void init() {
        frame.setVisible(true);
    }

}
