package info.papdt.coolapk.ui.main;

import android.os.Bundle;

import info.papdt.coolapk.R;
import info.papdt.coolapk.api.BaseApi;
import info.papdt.coolapk.api.apk.ApkApi;
import info.papdt.coolapk.ui.common.ToolbarActivity;
import info.papdt.coolapk.util.GlobalContext;
import static info.papdt.coolapk.util.Utility.*;

public class MainActivity extends ToolbarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getFragmentManager().beginTransaction().replace(R.id.frame, new CheckUpdateFragment()).commit();
	}

	@Override
	protected int getLayoutId() {
		return R.layout.main;
	}
}
