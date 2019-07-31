package com.devla90.listmvp.api;

import com.devla90.listmvp.model.Note;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @GET("Api/Categorias")
    Call<List<Note>> getCategorias();

    @GET("Api/Categorias/Categoria")
    Call<List<Note>> getCategoriasTipoDona(@QueryMap Map<String,Object> options);
}
