package org.eltech.ddm.associationrules;

import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapMiningModelElement extends MiningModelElement {

    private HashMap<String, Integer> map;
    private List<String> getAllKeyElements;

    public HashMapMiningModelElement(String id) {
        super(id);
        map = new HashMap<>();
    }

    @Override
    protected String propertiesToString() {
        return "";
    }

    @Override
    public void merge(List<MiningModelElement> elements) throws MiningException {
        System.out.println("merge");
    }

    public MiningModelElement getElement(String key) {
        if (map.containsKey(key)) {
            return super.getElement(map.get(key));
        }
        return null;
    }

    @Override
    protected synchronized void add(MiningModelElement element) {
        super.add(element);
        map.put(element.getID(), super.size() - 1);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public synchronized void put(String key, MiningModelElement value) {
        super.add(value);
        map.put(key, super.size() - 1);
    }

    public HashMap<String, Integer> getEntry() {
        return map;
    }

    public List<MiningModelElement> getSet() {
        return super.set;
    }

    public void setSet(List<MiningModelElement> newSet) {
        map = new HashMap<>();
        for (int i = 0; i < newSet.size(); i++) {
            map.put(newSet.get(i).getID(), i);
        }
        super.set = newSet;
    }

    public List<String> getAllKeyElements() {
        if (getAllKeyElements == null) {
            getAllKeyElements = new ArrayList<>();
            map.keySet().stream().map(s -> s.split(";")).collect(Collectors.toList()).forEach(s -> getAllKeyElements.addAll(Arrays.asList(s)));
        }
        return getAllKeyElements;
    }

//    @Override
//    public Object clone() {
//        Object clone = super.clone();
//        System.out.println("cloned");
//        return clone;
//    }
}
