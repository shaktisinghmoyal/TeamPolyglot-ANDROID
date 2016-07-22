package com.talentica.data.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;


public class SharedPreferencesStorage {
    private final String Tag = "PreferencesStorage";
    private final String BOOKSHELF_DATA = "BOOKSHELF_STORAGE";
    private final String RECENT_SEARCHES = "RECENT_SEARCHES";

    private final int totalSearches = 6;

    private SharedPreferences pref;
    private android.content.SharedPreferences.Editor editor;
    private String[] recentSearchArray = {"Fiction", "Sold", "Rudyard Kipling", "Charles Dickens", "Wing Of Fire", "Jungle Books"};

    @Inject
    public SharedPreferencesStorage(Context context) {
        pref = context.getSharedPreferences(BOOKSHELF_DATA, Context.MODE_PRIVATE);
        editor = pref.edit();

        if (pref.getInt(RECENT_SEARCHES + "_SIZE", 0) == 0) {
            editor.putInt(RECENT_SEARCHES + "_SIZE", totalSearches);
            for (int i = 0; i < totalSearches; i++) {
                editor.putString(RECENT_SEARCHES + "_" + i, recentSearchArray[i]);

            }
            editor.commit();
        }

    }

    public String[] getRecentSearchStrings() {
        String recentSearches[] = new String[totalSearches];
        for (int i = 0; i < totalSearches; i++) {
            recentSearches[i] = pref.getString(RECENT_SEARCHES + "_" + i, "UnKnown");
        }
        return recentSearches;
    }


    public void saveRecentSearchStrings(String recentSearch) {
        String currentString;
        String stringToReplace = recentSearch;
        for (int i = 0; i < totalSearches; i++) {

            currentString = pref.getString(RECENT_SEARCHES + "_" + i, "UnKnown");
            editor.putString(RECENT_SEARCHES + "_" + i, stringToReplace);
            stringToReplace = currentString;
            if (currentString.equals(recentSearch)) {
                break;
            }
            //  editor.putString(RECENT_SEARCHES + "_" + i, recentSearchArray[i]);
        }
        editor.commit();
        Log.e(Tag, "savedRecentSearchString ");
    }

}
