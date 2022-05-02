package br.com.bravi.breaktheice.presentation.activity;

import static org.koin.android.compat.ViewModelCompat.getViewModel;
import static br.com.bravi.breaktheice.databinding.ActivityMainBinding.inflate;
import static br.com.bravi.breaktheice.util.ActivityUtil.addFragment;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.bravi.breaktheice.databinding.ActivityMainBinding;
import br.com.bravi.breaktheice.presentation.fragment.ActivityListFragment;
import br.com.bravi.breaktheice.presentation.viewmodel.MainViewModel;

/**
 * @author Raphael Santos
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class MainActivity extends AppCompatActivity {

    private final static String LOG = "MainActivity";

    private MainViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);

        viewModel = getViewModel(MainActivity.this, MainViewModel.class);

        final ActivityMainBinding binding = inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.setLifecycleOwner(MainActivity.this);
        binding.setViewModel(viewModel);

        addFragment(getSupportFragmentManager(), ActivityListFragment.INSTANCE, binding.container.getId());

        fetchError();
    }

    private void fetchError() {
        viewModel.error.observe(MainActivity.this, err -> Log.e(LOG, err.getMessage()));
    }
}
