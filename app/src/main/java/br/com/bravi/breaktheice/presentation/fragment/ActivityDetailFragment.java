package br.com.bravi.breaktheice.presentation.fragment;

import static org.koin.android.compat.SharedViewModelCompat.getSharedViewModel;
import static br.com.bravi.breaktheice.databinding.FragmentActivityDetailBinding.inflate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import br.com.bravi.breaktheice.databinding.FragmentActivityDetailBinding;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityDetailFragment extends Fragment {

    public final static ActivityDetailFragment INSTANCE = new ActivityDetailFragment();

    private final MainViewModel viewModel = getSharedViewModel(ActivityDetailFragment.this, MainViewModel.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentActivityDetailBinding binding = inflate(getLayoutInflater());
        binding.setLifecycleOwner(requireActivity());
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
