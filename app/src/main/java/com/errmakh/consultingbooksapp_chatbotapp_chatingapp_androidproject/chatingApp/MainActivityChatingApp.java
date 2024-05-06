package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.R;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.adapters.ChatBotAdapter;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.apis.BrainShopApi;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.models.BrainShopResponse;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatBotApp.models.MessageModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityChatingApp extends AppCompatActivity {
    // Declare variables
    private List<MessageModel> messages = new ArrayList<>();
    private EditText editTextUser;
    private ImageButton buttonSend;
    private RecyclerView recyclerView;

    // Override the onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatbot);

        // Initialize UI elements
        editTextUser = findViewById(R.id.edit_text);
        buttonSend = findViewById(R.id.send_button);
        recyclerView = findViewById(R.id.recycler_view);


        // Create and set up the adapter for the RecyclerView
        ChatBotAdapter chatBotAdapter = new ChatBotAdapter(messages, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setAdapter(chatBotAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        // Set OnClickListener for the send button
        buttonSend.setOnClickListener(view -> {
            // Get user input
            String msg = editTextUser.getText().toString();

            // Add user message to the list
            messages.add(new MessageModel(msg, "user"));
            chatBotAdapter.notifyDataSetChanged();

            // Clear the input field
            editTextUser.setText("");

            messages.add(new MessageModel("user other text", "bot"));
            chatBotAdapter.notifyDataSetChanged();

            // Asynchronously handle API response
//            response.enqueue(new Callback<BrainShopResponse>() {
//                @Override
//                public void onResponse(Call<BrainShopResponse> call, Response<BrainShopResponse> response) {
//                    // Add bot response to the list
//                    messages.add(new MessageModel(response.body().getCnt(), "bot"));
//                    chatBotAdapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onFailure(Call<BrainShopResponse> call, Throwable t) {
//                    // Log error message in case of failure
//                    Log.i("Error", "Error: " + t.getMessage());
//                }
//            });
        });
    }
}