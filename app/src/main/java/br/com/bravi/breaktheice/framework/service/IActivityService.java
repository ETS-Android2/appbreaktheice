package br.com.bravi.breaktheice.framework.service;

import java.util.Map;

import br.com.bravi.breaktheice.domain.entity.ActivityModel;
import br.com.bravi.breaktheice.util.constant.WebserviceConstant;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Daniel Rodrigues
 * "Sometimes I believe the compiler ignores all my comments."
 */
public interface IActivityService {

    @GET(WebserviceConstant.WEBSERVICE_ENDPOINT_ACTIVITY)
    Observable<ActivityModel> doActivity();

    @GET(WebserviceConstant.WEBSERVICE_ENDPOINT_ACTIVITY)
    Observable<ActivityModel> doActivityFiltered(@QueryMap Map<String, String> options);
}
