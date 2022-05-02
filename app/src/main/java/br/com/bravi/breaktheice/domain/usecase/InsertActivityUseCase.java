package br.com.bravi.breaktheice.domain.usecase;

import br.com.bravi.breaktheice.data.repository.ActivityRepository;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class InsertActivityUseCase {

    private final ActivityRepository _activityRepository;

    public InsertActivityUseCase(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void invoke(
            CompositeDisposable compositeDisposable,
            DisposableCompletableObserver disposableCompletableObserver,
            ActivityModel activityModel
    ) {

        compositeDisposable.add(
                Completable
                        .fromRunnable(() -> _activityRepository.insertActivity(activityModel))
                        .subscribeWith(disposableCompletableObserver)
        );
    }
}
