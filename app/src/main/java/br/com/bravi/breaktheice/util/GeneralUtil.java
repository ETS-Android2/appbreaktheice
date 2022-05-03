package br.com.bravi.breaktheice.util;

import android.content.Context;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.com.bravi.breaktheice.R;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class GeneralUtil {

    public static String getAccessibilityText(Context context, float accessibility) {

        if (accessibility >= 0 && accessibility <= 3.3) {
            return context.getString(R.string.label_accessibility_easy);
        } else if (accessibility >= 3.3 && accessibility <= 6.6) {
            return context.getString(R.string.label_accessibility_medium);
        } else {
            return context.getString(R.string.label_accessibility_hard);
        }
    }
}
