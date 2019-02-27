package com.theory.graph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DG<T> {

    private Map<Integer, Node> lookupIndex = new HashMap<>();

    public Node getNode(Integer id) {
        return lookupIndex.get(id);
    }

    public void addNode(Node node) {
        if(lookupIndex.containsKey(node.id)) throw new IllegalArgumentException();
        lookupIndex.put(node.id, node);
    }

    public void addEdge(Integer inV, Integer outV) {
        if(!lookupIndex.containsKey(inV) && !lookupIndex.containsKey(outV)) throw new IllegalArgumentException();
        lookupIndex.get(inV).children.add(lookupIndex.get(outV));
    }

    public boolean hasBF(int source, int dest) {
        Node s = lookupIndex.get(source);
        Node d = lookupIndex.get(dest);
        Set<Integer> visited = new HashSet<>();
        LinkedList<Node> nextToVisit = new LinkedList<>();

        nextToVisit.add(s);

        while (!nextToVisit.isEmpty()) {
            s = nextToVisit.remove();
            if(!visited.contains(s.id)) {
                if(Objects.equals(s.id, d.id)) {
                    return true;
                }
                visited.add(s.id);
                nextToVisit.addAll(s.children);
            }
        }
        return false;
    }

    private static Map<Integer, Integer> computeDependencyCount(DG<?> directedGraph){
        Map<Integer, Integer> dependencyCount = new HashMap<>();
        for (Node<?> node : directedGraph.lookupIndex.values()) {
            for(Node<?> child : node.children) {
                dependencyCount.compute(child.getId(), (K, V) -> V == null ? 1 : V + 1);
            }
        }
        return  dependencyCount;
    }

    private static List<Integer> noDependentNodes(Map<Integer, Integer> dependencyCount) {
        List<Integer> noDependencyNode = new ArrayList<>();
        for (Map.Entry<Integer, Integer> IdCount : dependencyCount.entrySet()) {
            if(IdCount.getValue().equals(0)) {
                noDependencyNode.add(IdCount.getKey());
            }
        }
        return noDependencyNode;
    }

    public boolean isAciclic() {
        Map<Integer, Integer> dependencyCount = computeDependencyCount(this);
        Node[] toProcess = new Node[this.lookupIndex.values().size()];
        int next = 0;
        while(next < toProcess.length) {
        }
        return true;
    }

    public static class Node<T> {
        private T data;
        private Integer id;

        LinkedList<Node> children = new LinkedList<>();

        public  Node(Integer id, T data){
            this.data = data;
            this.id = id;
        }


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    public boolean hasDF(Integer source, Integer dest) {
        Set<Integer> visited = new HashSet<>();
        return hasDFInternal(lookupIndex.get(source), lookupIndex.get(dest), visited);
    }

    private boolean hasDFInternal(Node<T> source, Node<T> dest, Set<Integer> visited) {
        if(visited.contains(source.id)) return false;

        visited.add(source.id);

        if(source.id == dest.id) return true;

        for(Node child : source.children) {
            if(hasDFInternal(child, dest, visited)) {
                return true;
            }
        }
        return false;
    }

}

