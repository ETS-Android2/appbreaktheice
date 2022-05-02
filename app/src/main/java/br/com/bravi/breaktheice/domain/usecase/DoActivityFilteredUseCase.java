package br.com.bravi.breaktheice.domain.usecase;

import java.util.Map;

import br.com.bravi.breaktheice.data.repository.ActivityRepository;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class DoActivityFilteredUseCase {

    private final ActivityRepository _activityRepository;

    public DoActivityFilteredUseCase(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void invoke(
            CompositeDisposable compositeDisposable,
            DisposableObserver<ActivityModel> disposableObserver,
            Map<String, String> options
    ) {
        compositeDisposable.add(
                _activityRepository.doActivityFiltered(options)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(disposableObserver)
        );
    }
}
