package br.com.bravi.breaktheice.framework.source;

import java.util.List;
import java.util.Map;

import br.com.bravi.breaktheice.data.source.IActivityDataSource;
import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.framework.service.IActivityService;
import br.com.bravi.breaktheice.framework.BreakTheIceDatabase;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class ActivityDataSourceImpl implements IActivityDataSource {

    private final BreakTheIceDatabase _database;
    private final Retrofit _retrofit;

    public ActivityDataSourceImpl(BreakTheIceDatabase database, Retrofit retrofit) {
        _database = database;
        _retrofit = retrofit;
    }

    @Override
    public Observable<ActivityModel> doActivity() {
        return _retrofit.create(IActivityService.class)
                .doActivity();
    }

    @Override
    public Observable<ActivityModel> doActivityFiltered(Map<String, String> options) {
        return _retrofit.create(IActivityService.class)
                .doActivityFiltered(options);
    }

    @Override
    public Flowable<List<ActivityModel>> getActivities() {
        return _database.activityDao()
                .getAllActivities();
    }

    public void insertActivity(ActivityModel activityModel) {
        _database.activityDao()
                .insertActivity(activityModel);
    }

    public void deleteActivity(ActivityModel activityModel) {
        _database.activityDao()
                .deleteActivity(activityModel);
    }
}
