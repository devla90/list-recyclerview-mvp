package com.devla90.listmvp.activity.main;

import com.devla90.listmvp.api.ApiClient;
import com.devla90.listmvp.api.ApiInterface;
import com.devla90.listmvp.model.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;

    public MainPresenter(MainView mainView) {
        this.view = mainView;
    }

    void getData(){
        view.showLoading();
        //Request to server
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getCategorias();
        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getMessage());
            }
        });

    }
}
