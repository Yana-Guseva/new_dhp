package org.eltech.ddm.associationrules;

import org.eltech.ddm.miningcore.MiningException;
import org.eltech.ddm.miningcore.miningmodel.MiningModelElement;

import java.util.List;

public class TransactionList extends HashMapMiningModelElement {
	private static final long serialVersionUID = 1L;

	public TransactionList(String id) {
		super(id);
	}

	public boolean containsTransaction(String tid) {
		for(MiningModelElement transaction : super.set) {
			if(transaction.getID().equals(tid)) {
				return true;
			}
		}
		return false;
	}

	public Transaction getTransaction(int index) {
		if(size() > index)
			return (Transaction) super.set.get(index);
		else
			return null;
	}

	public Transaction getTransaction(String tid) {
		for(MiningModelElement transaction : super.set) {
			if(transaction.getID().equals(tid)) {
				return (Transaction) transaction;
			}
		}
		return null;
	}

	@Override
	protected String propertiesToString() {
		return null;
	}

	@Override
	public void merge(List<MiningModelElement> elements) throws MiningException {

	}
}
