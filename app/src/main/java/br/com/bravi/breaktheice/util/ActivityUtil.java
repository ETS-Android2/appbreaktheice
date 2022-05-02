package br.com.bravi.breaktheice.util;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.com.bravi.breaktheice.R;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityUtil {

    public static void addFragment(
            FragmentManager fragmentManager,
            Fragment fragment,
            @IdRes int viewId
    ) {
        fragmentManager
                .beginTransaction()
                .add(viewId, fragment)
                .commit();
    }

    public static void replaceFragment(
            FragmentManager fragmentManager,
            Fragment fragment,
            @IdRes int viewId
    ) {
        replaceFragment(
                fragmentManager,
                fragment,
                new int[]{
                        R.anim.slide_in_right, R.anim.slide_out_left,
                        R.anim.slide_in_left, R.anim.slide_out_right
                },
                viewId
        );
    }

    public static void replaceFragment(
            FragmentManager fragmentManager,
            Fragment fragment,
            int[] animationArr,
            @IdRes int viewId
    ) {
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (animationArr != null) {
            fragmentTransaction.setCustomAnimations(
                    animationArr[0], animationArr[1],
                    animationArr[2], animationArr[3]
            );
        }
        fragmentTransaction
                .replace(viewId, fragment)
                .commit();
    }

    public static void popBackStack(FragmentManager fragmentManager) {
        fragmentManager.popBackStack();
    }
}
