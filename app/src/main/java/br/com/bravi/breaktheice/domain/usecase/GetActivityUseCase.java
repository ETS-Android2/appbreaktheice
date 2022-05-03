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
public class GetActivityUseCase {

    private final ActivityRepository _activityRepository;

    public GetActivityUseCase(ActivityRepository activityRepository) {
        _activityRepository = activityRepository;
    }

    public void invoke(
            CompositeDisposable compositeDisposable,
            ResourceSubscriber<ActivityModel> resourceSubscriber,
            int id
    ) {
        compositeDisposable.add(
                _activityRepository.getActivity(id)
                        .subscribeWith(resourceSubscriber)
        );
    }
}
