package info.papdt.coolapk.model;

import java.util.List;

public interface AbsListModel<L extends AbsListModel<L, I>, I>
{
	void addAll(L list);
	List<I> getList();
	I get(int pos);
	int size();
	void clear();
}
