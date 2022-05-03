package br.com.bravi.breaktheice.presentation.fragment;

import static org.koin.android.compat.SharedViewModelCompat.getSharedViewModel;
import static br.com.bravi.breaktheice.databinding.FragmentActivityDetailBinding.inflate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalTime;

import br.com.bravi.breaktheice.databinding.FragmentActivityDetailBinding;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityDetailFragment extends Fragment {

    private final static String TAG = "ActivityDetailFragment";

    public final static String ARGUMENT_ACTIVITY_MODEL_ID = "activityId";

    private FragmentActivityDetailBinding binding;

    public ActivityModel startTime;

    public ActivityModel finishTime;

    public static ActivityDetailFragment getInstance(Bundle bundle) {
        final ActivityDetailFragment activityDetailFragment = new ActivityDetailFragment();

        activityDetailFragment.setArguments(bundle);

        return activityDetailFragment;
    }

    private MainViewModel viewModel;

    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle) {
        viewModel = getSharedViewModel(ActivityDetailFragment.this, MainViewModel.class);

        binding = inflate(getLayoutInflater());
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);

        /*
         * Button Actions Block
         */

        // Start Activity Button
        binding.startActivityFab.setOnClickListener(view -> {
            binding.cancelActivityFabDisabled.setVisibility(View.GONE);
            binding.cancelActivityFabEnabled.setEnabled(true);
            binding.startActivityFab.setVisibility(View.GONE);
            System.out.println("Start Time: ");
            getCurrentTime();
//            viewModel.updateStartTime(startTime);
        });

        //Finish Activity Button
        binding.finishActivityFab.setOnClickListener(view -> {
            finishActivityDialog();
        });

        // Cancel Activity Button
        binding.cancelActivityFabEnabled.setOnClickListener(view -> {
            cancelActivityDialog();
        });

        fetchResult();

        final int id = requireArguments().getInt(ARGUMENT_ACTIVITY_MODEL_ID);
        viewModel.getActivity(id);

        return binding.getRoot();
    }

    private void fetchResult() {
        viewModel.activity.observe(getViewLifecycleOwner(), res -> {
            if (res != null) {
                Log.d(TAG, res.toString());
            }
        });
    }

    private LocalTime getCurrentTime() {
        LocalTime time = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            time = LocalTime.now();
            System.out.println(time);
        }
        return time;
    }

    private void finishActivityDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Finish Activity?")
                .setMessage("Are you sure you wish to finish the activity?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        binding.cancelActivityFabDisabled.setVisibility(View.VISIBLE);
                        binding.cancelActivityFabEnabled.setEnabled(false);
                        binding.startActivityFab.setVisibility(View.VISIBLE);
                        System.out.println("Finish Time: ");
                        getCurrentTime();
                        // viewModel.updateFinishTime(finishTime);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    private void cancelActivityDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Cancel Activity?")
                .setMessage("Are you sure you wish to cancel the activity?")
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        binding.cancelActivityFabDisabled.setVisibility(View.VISIBLE);
                        binding.cancelActivityFabEnabled.setEnabled(false);
                        binding.startActivityFab.setVisibility(View.VISIBLE);
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}
