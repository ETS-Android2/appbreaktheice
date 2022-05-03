package br.com.bravi.breaktheice.data.source;

import java.util.List;
import java.util.Map;

import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public interface IActivityDataSource {

    Observable<ActivityModel> doActivity();

    Observable<ActivityModel> doActivityFiltered(Map<String, String> options);

    Flowable<ActivityModel> getActivity(int id);

    Flowable<List<ActivityModel>> getActivities();

    void insertActivity(ActivityModel activityModel);

    void deleteActivity(ActivityModel activityModel);

//    void updateFinishTime(ActivityModel activityModel);
//
//    void updateStartTime(ActivityModel activityModel);
}
