package br.com.bravi.breaktheice.data.repository;

import java.util.List;
import java.util.Map;

import br.com.bravi.breaktheice.data.source.IActivityDataSource;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityRepository {

    private final IActivityDataSource _activityDataSource;

    public ActivityRepository(IActivityDataSource activityDataSource) {
        _activityDataSource = activityDataSource;
    }

    public Observable<ActivityModel> doActivity() {
        return _activityDataSource.doActivity();
    }

    public Observable<ActivityModel> doActivityFiltered(Map<String, String> options) {
        return _activityDataSource.doActivityFiltered(options);
    }

    public Flowable<ActivityModel> getActivity(int id) {
        return _activityDataSource.getActivity(id);
    }

    public Flowable<List<ActivityModel>> getActivities() {
        return _activityDataSource.getActivities();
    }

    public void insertActivity(ActivityModel activityModel) {
        _activityDataSource.insertActivity(activityModel);
    }

    public void deleteActivity(ActivityModel activityModel) {
        _activityDataSource.deleteActivity(activityModel);
    }

//    public void updateFinishTime(ActivityModel activityModel) {
//        _activityDataSource.updateFinishTime(activityModel);
//    }
//
//    public void updateStartTime(ActivityModel activityModel) {
//        _activityDataSource.updateStartTime(activityModel);
//    }
}
