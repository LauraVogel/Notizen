package com.laura.notizen;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Laura on 21.04.2015.
 */
public class Paging {
    private Activity actualPage;


    Paging(Activity aP)
    {
        actualPage = aP;
    }

    private Intent paging(Activity fromPage, Class toPage)
    {
        Intent intent = new Intent(fromPage, toPage);
        return intent;
    }

    public Intent toPageMain()
    {
        return paging(actualPage, MainActivity.class);
    }

    public Intent toPageJsonFile()
    {
        return paging(actualPage, JsonFile.class);
    }

    public Intent toPageSafeNotes()
    {
        return paging(actualPage,SafeNotes.class);
    }

    public Intent toPageShowOverwiev()
    {
        return paging(actualPage,NotesOverview.class);
    }

    public Intent decidePage(int id)
    {
        switch (id)
        {
            case R.id.mainsite: return toPageMain();
            case R.id.jsonpage: return toPageJsonFile();
            case R.id.safenote: return toPageSafeNotes();
            case R.id.shownotes: return toPageShowOverwiev();
            default:
                return toPageMain();
        }
    }
}
