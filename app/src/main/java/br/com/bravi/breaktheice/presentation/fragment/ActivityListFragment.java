package br.com.bravi.breaktheice.presentation.fragment;

import static org.koin.android.compat.SharedViewModelCompat.getSharedViewModel;
import static br.com.bravi.breaktheice.databinding.FragmentActivityListBinding.inflate;
import static br.com.bravi.breaktheice.util.ViewUtil.createAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import br.com.bravi.breaktheice.databinding.FragmentActivityListBinding;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.presentation.adapter.MainAdapter;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityListFragment extends Fragment {

    public final static ActivityListFragment INSTANCE = new ActivityListFragment();

    private MainViewModel viewModel;
    private MainAdapter mainAdapter;

    private final List<ActivityModel> activities = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = getSharedViewModel(ActivityListFragment.this, MainViewModel.class);
        mainAdapter = new MainAdapter(activities, getContext());

        final FragmentActivityListBinding binding = inflate(getLayoutInflater());
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);
        binding.newActivityFab.setOnClickListener(view -> viewModel.doActivity());

        createAdapter(getContext(), binding.recyclerView, mainAdapter);

        fetchResult();

        viewModel.getActivities();

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchResult() {
        viewModel.activities.observe(getViewLifecycleOwner(), res -> {
            activities.clear();
            activities.addAll(res);
            mainAdapter.notifyDataSetChanged();
        });
    }
}
