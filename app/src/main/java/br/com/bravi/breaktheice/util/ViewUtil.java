package br.com.bravi.breaktheice.util;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

import android.content.Context;
import android.content.res.Configuration;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ViewUtil {

    public static boolean isOrientationPortrait(Context context) {
        return context
                .getResources()
                .getConfiguration()
                .orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static void createAdapter(
            Context context,
            RecyclerView recyclerView,
            RecyclerView.Adapter viewAdapter
    ) {
        createAdapter(context, recyclerView, viewAdapter, null, ORIENTATION_PORTRAIT);
    }

    public static void createAdapter(
            Context context,
            RecyclerView recyclerView,
            RecyclerView.Adapter viewAdapter,
            RecyclerView.ItemDecoration itemDecoration,
            int orientation
    ) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, orientation, false);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);

        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(isOrientationPortrait(context) ? linearLayoutManager : gridLayoutManager);
        recyclerView.setHasFixedSize(false);

        if (itemDecoration != null) {
            recyclerView.addItemDecoration(itemDecoration);
        }
    }
}
