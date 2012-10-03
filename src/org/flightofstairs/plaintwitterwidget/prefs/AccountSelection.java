package org.flightofstairs.plaintwitterwidget.prefs;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import org.flightofstairs.plaintwitterwidget.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AccountSelection extends PreferenceFragment {
	public final Map<String, Account> accountMap = new HashMap<String, Account>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.account_selection);

		AccountManager accountManager = AccountManager.get(getActivity());
		final Account[] accountArray = accountManager.getAccountsByType("com.twitter.android.auth.login");

		String[] accountNames = new String[accountArray.length];
		accountMap.clear();
		int i = 0;
		for(Account account : accountArray) {
			accountMap.put(account.name, account);
			accountNames[i++] = account.name;
		}

		Arrays.sort(accountNames);

		ListPreference listPreference = (ListPreference) findPreference("twitterAccount");
		listPreference.setEntries(accountNames);
		listPreference.setEntryValues(accountNames);
	}
}