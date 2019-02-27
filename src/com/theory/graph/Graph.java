package com.theory.graph;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Graph<K, T> {
    private static class Node<T> {
        private Object data;
        private Integer id;

        LinkedList<Node> childs = new LinkedList<>();

        public  Node(Integer id, Object data){
            this.data = data;
            this.id = id;
        }


        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }

    private Map<Integer, Node> lookupIndex = new HashMap<>();

    @SuppressWarnings("unchecked")
    public T get(Integer id) {
        return (T)lookupIndex.get(id).getData();
    }

    public void add(Node node) {
        if(lookupIndex.containsKey(node.id)) throw new IllegalArgumentException();
        lookupIndex.put(node.id, node);
    }

    public void addEdge(Integer inV, Integer outV) {
        if(!lookupIndex.containsKey(inV) && !lookupIndex.containsKey(outV)) throw new IllegalArgumentException();
        lookupIndex.get(inV).childs.add(lookupIndex.get(outV));
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
                nextToVisit.addAll(s.childs);
            }
        }
        return false;
    }

    public boolean hasDF(Integer source, Integer dest) {
        Set<Integer> visited = new HashSet<>();
        return hasDFInternal(lookupIndex.get(source), lookupIndex.get(dest), visited);
    }

    private boolean hasDFInternal(Node<T> source, Node<T> dest, Set<Integer> visited) {
        if(visited.contains(source.id)) return false;

        visited.add(source.id);

        if(source.id == dest.id) return true;

        for(Node child : source.childs) {
            if(hasDFInternal(child, dest, visited)) {
                return true;
            }
        }
        return false;
    }

}

