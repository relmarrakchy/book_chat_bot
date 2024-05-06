package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.booksConsultingApp.apis;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.booksConsultingApp.models.GoogleBooksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApi {
    @GET("v1/volumes")
    Call<GoogleBooksResponse> searchBooks(@Query("q") String query);
}
