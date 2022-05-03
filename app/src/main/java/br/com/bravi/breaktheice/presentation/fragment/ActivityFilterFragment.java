package br.com.bravi.breaktheice.presentation.fragment;

import static org.koin.android.compat.SharedViewModelCompat.getSharedViewModel;
import static java.util.Locale.ROOT;
import static br.com.bravi.breaktheice.databinding.FragmentActivityFilterBinding.inflate;
import static br.com.bravi.breaktheice.util.constant.WebserviceConstant.WEBSERVICE_QUERY_TYPE;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.HashMap;
import java.util.Map;

import br.com.bravi.breaktheice.R;
import br.com.bravi.breaktheice.databinding.FragmentActivityFilterBinding;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityFilterFragment extends DialogFragment {

    private final static String TAG = "ActivityFilterFragment";

    public void show(FragmentManager fragmentManager) {
        if (fragmentManager.findFragmentByTag(TAG) == null) {
            show(fragmentManager, TAG);
        }
    }

    private MainViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        viewModel = getSharedViewModel(ActivityFilterFragment.this, MainViewModel.class);

        final FragmentActivityFilterBinding binding = inflate(getLayoutInflater());
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);
        binding.searchButton.setOnClickListener(view -> {
            final Map<String, String> queries = new HashMap<>();
            final String activityTypeStr = binding.typeAutoCompleteTextView.getText().toString();
            final String[] activityTypeArr = getResources().getStringArray(R.array.activity_type_array);
            if (activityTypeStr.equalsIgnoreCase(activityTypeArr[0])){
                viewModel.doActivity();
            } else {
                queries.put(WEBSERVICE_QUERY_TYPE, activityTypeStr.toLowerCase(ROOT));
                viewModel.doActivityFiltered(queries);
            }
            dismiss();
        });

        final Context context = requireContext();
        final String[] activityTypeArr = getResources().getStringArray(R.array.activity_type_array);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, R.layout.dropdown_item, activityTypeArr);
        binding.typeAutoCompleteTextView.setAdapter(arrayAdapter);

        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle bundle) {
        setStyle(STYLE_NORMAL, R.style.Theme_Dialog);

        return super.onCreateDialog(bundle);
    }
}