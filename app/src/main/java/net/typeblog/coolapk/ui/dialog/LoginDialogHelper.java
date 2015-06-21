package net.typeblog.coolapk.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;

import net.typeblog.coolapk.R;
import net.typeblog.coolapk.ui.main.MainActivity;
import net.typeblog.coolapk.util.LoginManager;
import static net.typeblog.coolapk.util.Utility.*;

public class LoginDialogHelper
{
	public static void showLoginDialog(final MainActivity main) {
		LayoutInflater inflater = (LayoutInflater) main.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflater.inflate(R.layout.dialog_login, null);
		
		final EditText username = $(v, R.id.username);
		final EditText password = $(v, R.id.password);
		
		new AlertDialog.Builder(main)
			.setView(v)
			.setTitle(R.string.login)
			.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			})
			.setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					new LoginTask(main).execute(username.getText().toString().trim(), password.getText().toString().trim());
				}
			})
			.create().show();
		
	}
	
	private static class LoginTask extends AsyncTask<String, Void, Boolean> {
		MainActivity main;
		ProgressDialog prog;
		LoginManager manager;
		
		LoginTask(MainActivity main) {
			this.main = main;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			prog = new ProgressDialog(main);
			prog.setMessage(main.getString(R.string.plz_wait));
			prog.setCancelable(false);
			prog.show();
			
			manager = LoginManager.getInstance();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			return manager.doLogin(params[0], params[1]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			
			prog.dismiss();
			
			if (result) {
				main.reloadUserInfo();
			} else {
				Toast.makeText(main, R.string.login_fail, Toast.LENGTH_SHORT).show();
			}
		}
		
	}
}
