package org.eltech.ddm.associationrules;

import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.ArrayList;
import java.util.List;

import static org.eltech.ddm.miningcore.miningmodel.EMiningModel.index;


public class Transaction extends MiningModelElement {
	protected String tid;
	protected List<String> itemIDList = new ArrayList<String>();

	public Transaction(String tid)   {
		super(tid);
		this.tid = tid;
	}

	@Override
	protected String propertiesToString() {
		return null;
	}

	public String getTID() {
		return tid;
	}

	public void setTID(String tid) {
		this.tid = tid;
	}

	public List<String> getItemIDList() {
		return itemIDList;
	}

	public void setItemIDList(List<String> itemList) {
		this.itemIDList = itemList;
	}

	@Override
	public String toString() {
		return "tid = " + tid + ", " + itemIDList;
	}

	@Override
	public void merge(List<MiningModelElement> elements) throws MiningException {

	}

	public void addItem(Item element) {
		super.add(element);
	}

	@Override
	public Object clone() {
		Transaction o = new Transaction(getID());

//		if(set != null){
//			o.set = new ArrayList<>();
//			for(MiningModelElement element : set)
//				o.set.add(getElement(element.getID()));
//		}
		if (itemIDList != null) {
			o.itemIDList = new ArrayList<>();
			for (String id : itemIDList) {
				o.itemIDList.add(id);
			}
		}
		o.tid = tid;
		return o;
	}
}
