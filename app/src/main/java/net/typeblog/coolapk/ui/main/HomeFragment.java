package net.typeblog.coolapk.ui.main;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.ui.apk.CheckUpdateFragment;
import net.typeblog.coolapk.ui.apk.NewAndUpdatedFragment;
import static net.typeblog.coolapk.util.Utility.*;

public class HomeFragment extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.home, container, false);
		return v;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getFragmentManager().beginTransaction().replace(R.id.updates_frame, new CheckUpdateFragment())
		.replace(R.id.new_frame, new NewAndUpdatedFragment()).commit();
		
	}

	@Override
	public void onStart() {
		super.onStart();
	}
}
