package br.com.bravi.breaktheice.util.binding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.view.View;

import androidx.databinding.BindingAdapter;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class AndroidBinding {

    @BindingAdapter("app:visibility")
    public static void setVisibility(View view, boolean value) {
        view.setVisibility(value ? VISIBLE : GONE);
    }
}
