package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.apis;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.models.BrainShopResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BrainShopApi {
    @GET
    Call<BrainShopResponse> getResponse(@Url String url);
}
