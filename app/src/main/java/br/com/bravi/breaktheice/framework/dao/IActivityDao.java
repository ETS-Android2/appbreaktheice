package br.com.bravi.breaktheice.framework.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import io.reactivex.rxjava3.core.Flowable;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
@Dao
public interface IActivityDao {

    @Query("SELECT * FROM activity WHERE _id = :id")
    Flowable<ActivityModel> getActivity(int id);

    @Query("SELECT * FROM activity")
    Flowable<List<ActivityModel>> getActivities();

    @Insert
    void insertActivity(ActivityModel activityModel);

    @Delete
    void deleteActivity(ActivityModel activityModel);

    @Update
    void updateFinishTime(ActivityModel activityModel);

    @Update
    void updateStartTime(ActivityModel activityModel);
}
