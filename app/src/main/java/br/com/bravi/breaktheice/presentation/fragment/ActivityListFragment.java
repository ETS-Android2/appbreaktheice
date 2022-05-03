package br.com.bravi.breaktheice.presentation.fragment;

import static org.koin.android.compat.SharedViewModelCompat.getSharedViewModel;
import static br.com.bravi.breaktheice.databinding.FragmentActivityListBinding.inflate;
import static br.com.bravi.breaktheice.presentation.fragment.ActivityDetailFragment.ARGUMENT_ACTIVITY_MODEL_ID;
import static br.com.bravi.breaktheice.util.ActivityUtil.replaceFragment;
import static br.com.bravi.breaktheice.util.ViewUtil.createAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.bravi.breaktheice.R;
import br.com.bravi.breaktheice.databinding.FragmentActivityListBinding;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.presentation.activity.MainActivity;
import br.com.bravi.breaktheice.presentation.adapter.IItemClickListener;
import br.com.bravi.breaktheice.presentation.adapter.MainAdapter;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityListFragment extends Fragment implements IItemClickListener {

    public static ActivityListFragment getInstance(Bundle bundle) {
        final ActivityListFragment ActivityListFragment = new ActivityListFragment();

        ActivityListFragment.setArguments(bundle);

        return ActivityListFragment;
    }

    private MainViewModel viewModel;
    private MainAdapter mainAdapter;

    private final List<ActivityModel> activities = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        viewModel = getSharedViewModel(ActivityListFragment.this, MainViewModel.class);
        mainAdapter = new MainAdapter(getContext(), activities, ActivityListFragment.this);

        final FragmentActivityListBinding binding = inflate(getLayoutInflater());
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);
        binding.newActivityFab.setOnClickListener(view -> {
            final FragmentActivity fragmentActivity = requireActivity();
            final FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
            final ActivityFilterFragment activityFilterFragment = new ActivityFilterFragment();
            activityFilterFragment.show(fragmentManager);
        });

        createAdapter(getContext(), binding.recyclerView, mainAdapter);

        fetchResult();

        viewModel.getActivities();

        return binding.getRoot();
    }

    @Override
    public void onClick(View view, final int position) {
        final List<ActivityModel> activityModelList = Objects.requireNonNull(viewModel.activities.getValue());
        final ActivityModel activityModel = activityModelList.get(position);

        final Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_ACTIVITY_MODEL_ID, activityModel.getId());

        final FragmentActivity fragmentActivity = requireActivity();
        final FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
        replaceFragment(fragmentManager, ActivityDetailFragment.getInstance(bundle), R.id.container);
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
