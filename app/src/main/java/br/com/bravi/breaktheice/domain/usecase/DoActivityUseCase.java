package br.com.bravi.breaktheice.domain.usecase;

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
public class DoActivityUseCase {

    private final ActivityRepository _activityRepository;

    public DoActivityUseCase(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void invoke(
            CompositeDisposable compositeDisposable,
            DisposableObserver<ActivityModel> disposableObserver
    ) {
        compositeDisposable.add(
                _activityRepository.doActivity()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(disposableObserver)
        );
    }
}
