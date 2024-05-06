package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.R;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.adapter.RecentChatRecyclerAdapter;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.adapter.SearchUserRecyclerAdapter;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.model.ChatroomModel;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.model.UserModel;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserListFragment extends Fragment {

    RecyclerView recyclerView;
    SearchUserRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_user_list, container, false);
        recyclerView = view.findViewById(R.id.search_user_recycler_view);
        setupRecyclerView();

        return view;
    }

    void setupRecyclerView(){

        Query query = FirebaseUtil.allUserCollectionReference();


        FirestoreRecyclerOptions<UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query,UserModel.class).build();

        adapter = new SearchUserRecyclerAdapter(options,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onStart() {
        super.onStart();
        if(adapter!=null)
            adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter!=null)
            adapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter!=null)
            adapter.notifyDataSetChanged();
    }
}