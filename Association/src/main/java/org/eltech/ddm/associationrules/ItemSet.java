package org.eltech.ddm.associationrules;

import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemSet extends MiningModelElement {
    private List<String> itemIDList = new ArrayList<String>();
    private Set<String> tidList = new HashSet<String>();
    private int supportCount = 0;

    public ItemSet(Item... items) {
        super(Stream.of(items).map(Item::getItemID)
                .collect(Collectors.joining(", ")));
        for (Item item : items) {
            addItem(item);
        }
        Collections.sort(this.itemIDList);
    }

//	public ItemSet(List<Item> items) {
//		for(Item item : items) {
//			addItem(item);
//		}
//		Collections.sort(this.itemIDList);
//	}

    public void addItem(Item item) {
        itemIDList.add(item.getItemID());
        if (itemIDList.size() == 1) {
            tidList.addAll(item.getTidList());
        } else {
            HashSet<String> newTids = new HashSet<String>();
            for (String id : item.getTidList()) {
                if (tidList.contains(id))
                    newTids.add(id);
            }
            tidList = newTids;
        }
        Collections.sort(itemIDList);
    }

    public ItemSet(String id, List<String> itemIDs) {
        super(id);
        StringBuilder sb = new StringBuilder();
        for (String itemID : itemIDs) {
            itemIDList.add(itemID);
        }
        Collections.sort(this.itemIDList);
    }

    public List<String> getItemIDList() {
        return itemIDList;
    }

    public void setItemIDList(List<String> itemIDList) {
        this.itemIDList = itemIDList;
    }

    public Set<String> getTIDList() {
        return tidList;
    }

    public void setTIDList(Set<String> tids) {
        tidList = tids;

    }

//	public void setTidList(List<Integer> tidList) {
//		this.	tidList = tidList;
//	}
//
//	@Override
//	public String toString() {
//		return "items " + itemIDList + ", 	tidList" + tidList ;
//	}

    @Override
    public void merge(List<MiningModelElement> elements) throws MiningException {

    }

    @Override
    public boolean equals(Object o) {

        if (itemIDList.size() != ((ItemSet) o).getItemIDList().size())
            return false;
        for (int i = 0; i < itemIDList.size(); i++) {
            if (!(itemIDList.get(i).equals(((ItemSet) o).getItemIDList().get(i))))
                return false;
        }
        return true;
    }

    public int getSupportCount() {
        if (tidList.isEmpty())
            return supportCount;
        else
            return tidList.size();
    }

    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }

    @Override
    protected String propertiesToString() {
        return null;
    }

    @Override
    public Object clone() {
        ItemSet o = (ItemSet) super.clone();

        if (itemIDList != null) {
            o.itemIDList = new ArrayList<>();
            for (String id : itemIDList) {
                o.itemIDList.add(id);
            }
        }

        if (tidList != null) {
            o.tidList = new HashSet<>();
            for (String id : tidList)
                o.tidList.add(id);
        }
        o.supportCount = supportCount;
        return o;
    }
}
