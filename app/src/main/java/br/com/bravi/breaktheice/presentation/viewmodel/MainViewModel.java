package br.com.bravi.breaktheice.presentation.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.domain.usecase.DeleteActivityUseCase;
import br.com.bravi.breaktheice.domain.usecase.DoActivityFilteredUseCase;
import br.com.bravi.breaktheice.domain.usecase.DoActivityUseCase;
import br.com.bravi.breaktheice.domain.usecase.GetActivitiesUseCase;
import br.com.bravi.breaktheice.domain.usecase.InsertActivityUseCase;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableCompletableObserver;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.subscribers.ResourceSubscriber;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public class MainViewModel extends ViewModel {

    private final DeleteActivityUseCase _deleteActivityUseCase;
    private final DoActivityFilteredUseCase _doActivityFilteredUseCase;
    private final DoActivityUseCase _doActivityUseCase;
    private final GetActivitiesUseCase _getActivitiesUseCase;
    private final InsertActivityUseCase _insertActivityUseCase;

    public MainViewModel(
            DeleteActivityUseCase deleteActivityUseCase,
            DoActivityFilteredUseCase doActivityFilteredUseCase,
            DoActivityUseCase doActivityUseCase,
            GetActivitiesUseCase getActivitiesUseCase,
            InsertActivityUseCase insertActivityUseCase
    ) {
        _deleteActivityUseCase = deleteActivityUseCase;
        _doActivityFilteredUseCase = doActivityFilteredUseCase;
        _doActivityUseCase = doActivityUseCase;
        _getActivitiesUseCase = getActivitiesUseCase;
        _insertActivityUseCase = insertActivityUseCase;
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    /*
     * Live Data properties.
     */
    private final MutableLiveData<List<ActivityModel>> _activities = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<Throwable> _error = new MutableLiveData<>(null);

    public LiveData<List<ActivityModel>> activities = _activities;
    public LiveData<Throwable> error = _error;

    public void deleteActivity(ActivityModel activityModel) {
        _deleteActivityUseCase.invoke(compositeDisposable, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(@NonNull Throwable err) {
                _error.postValue(err);
            }
        }, activityModel);
    }

    public void doActivityFiltered(Map<String, String> options) {
        _doActivityFilteredUseCase.invoke(compositeDisposable, new DisposableObserver<ActivityModel>() {
            @Override
            public void onNext(ActivityModel res) {
                insertActivity(res);
            }

            @Override
            public void onError(@NonNull Throwable err) {
                _error.postValue(err);
            }

            @Override
            public void onComplete() {
            }
        }, options);
    }

    public void doActivity() {
        _doActivityUseCase.invoke(compositeDisposable, new DisposableObserver<ActivityModel>() {
            @Override
            public void onNext(ActivityModel res) {
                insertActivity(res);
            }

            @Override
            public void onError(@NonNull Throwable err) {
                _error.postValue(err);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void getActivities() {
        _getActivitiesUseCase.invoke(compositeDisposable, new ResourceSubscriber<List<ActivityModel>>() {
            @Override
            public void onNext(List<ActivityModel> res) {
                _activities.postValue(res);
            }

            @Override
            public void onError(@NonNull Throwable err) {
                _error.postValue(err);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    public void insertActivity(ActivityModel activityModel) {
        _insertActivityUseCase.invoke(compositeDisposable, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                getActivities();
            }

            @Override
            public void onError(@NonNull Throwable err) {
                _error.postValue(err);
            }
        }, activityModel);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        compositeDisposable.dispose();
    }
}
