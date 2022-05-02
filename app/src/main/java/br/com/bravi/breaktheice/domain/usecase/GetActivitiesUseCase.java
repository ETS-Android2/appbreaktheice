package br.com.bravi.breaktheice.domain.usecase;

import java.util.List;

import br.com.bravi.breaktheice.data.repository.ActivityRepository;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class GetActivitiesUseCase {

    private final ActivityRepository _activityRepository;

    public GetActivitiesUseCase(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void invoke(
            CompositeDisposable compositeDisposable,
            ResourceSubscriber<List<ActivityModel>> resourceSubscriber
    ) {
        compositeDisposable.add(
                _activityRepository.getActivities()
                        .subscribeWith(resourceSubscriber)
        );
    }
}
