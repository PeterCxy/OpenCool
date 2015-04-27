package info.papdt.coolapk.model;

import java.util.ArrayList;
import java.util.List;

public class ApkListModel implements AbsListModel<ApkListModel, ApkModel>
{
	private List<ApkModel> data = new ArrayList<ApkModel>();

	@Override
	public void addAll(ApkListModel list) {
		for (ApkModel item : list.getList()) {
			if (!data.contains(item)) {
				data.add(item);
			}
		}
	}

	@Override
	public ApkModel get(int pos) {
		return data.get(pos);
	}

	@Override
	public List<ApkModel> getList() {
		return data;
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public void clear() {
		data.clear();
	}

}
