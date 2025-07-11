package com.example.bai_tap_1_networking_apis.network;

import com.example.bai_tap_1_networking_apis.model.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}