package com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.R;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.MainActivity;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.model.UserModel;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.utils.AndroidUtil;
import com.errmakh.consultingbooksapp_chatbotapp_chatingapp_androidproject.chatingApp.utils.FirebaseUtil;


public class ProfileFragment extends Fragment {

    ImageView profilePic;
    EditText usernameInput;
    EditText phoneInput;
    Button updateProfileBtn;
    ProgressBar progressBar;
    TextView logoutBtn;

    UserModel currentUserModel;
    ActivityResultLauncher<Intent> imagePickLauncher;
    Uri selectedImageUri;

    public ProfileFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if(result.getResultCode() == Activity.RESULT_OK){
//                        Intent data = result.getData();
//                        if(data!=null && data.getData()!=null){
//                            selectedImageUri = data.getData();
//                            AndroidUtil.setProfilePic(getContext(),selectedImageUri,profilePic);
//                        }
//                    }
//                }
//        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        profilePic = view.findViewById(R.id.profile_image_view);
        usernameInput = view.findViewById(R.id.profile_username);
        phoneInput = view.findViewById(R.id.profile_phone);
        updateProfileBtn = view.findViewById(R.id.profle_update_btn);
        progressBar = view.findViewById(R.id.profile_progress_bar);
        logoutBtn = view.findViewById(R.id.logout_btn);

        getUserData();

        updateProfileBtn.setOnClickListener((v -> {
            updateBtnClick();
        }));

        logoutBtn.setOnClickListener((v)->{
//            FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        FirebaseUtil.logout();
//                        Intent intent = new Intent(getContext(), MainActivity.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(intent);
//                    }
//                }
//            });
            FirebaseUtil.logout();
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);




        });

//        profilePic.setOnClickListener((v)->{
//            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512,512)
//                    .createIntent(new Function1<Intent, Unit>() {
//                        @Override
//                        public Unit invoke(Intent intent) {
//                            imagePickLauncher.launch(intent);
//                            return null;
//                        }
//                    });
//        });

        return view;
    }

    void updateBtnClick(){
        String newUsername = usernameInput.getText().toString();
        if(newUsername.isEmpty() || newUsername.length()<3){
            usernameInput.setError("Username length should be at least 3 chars");
            return;
        }
        currentUserModel.setUsername(newUsername);
        setInProgress(true);


        if(selectedImageUri!=null){
//            FirebaseUtil.getCurrentProfilePicStorageRef().putFile(selectedImageUri)
//                    .addOnCompleteListener(task -> {
//                        updateToFirestore();
//                    });
        }else{
            updateToFirestore();
        }





    }

    void updateToFirestore(){
        FirebaseUtil.currentUserDetails().set(currentUserModel)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                    if(task.isSuccessful()){
                        AndroidUtil.showToast(getContext(),"Updated successfully");
                    }else{
                        AndroidUtil.showToast(getContext(),"Updated failed");
                    }
                });
    }



    void getUserData(){
        setInProgress(true);

//        FirebaseUtil.getCurrentProfilePicStorageRef().getDownloadUrl()
//                .addOnCompleteListener(task -> {
//                    if(task.isSuccessful()){
//                        Uri uri  = task.getResult();
//                        AndroidUtil.setProfilePic(getContext(),uri,profilePic);
//                    }
//                });

        FirebaseUtil.currentUserDetails().get().addOnCompleteListener(task -> {
            setInProgress(false);
            currentUserModel = task.getResult().toObject(UserModel.class);
            if(currentUserModel!=null){
                usernameInput.setText(currentUserModel.getUsername());
            }else {
                usernameInput.setText("set Username");

            }
            phoneInput.setText(currentUserModel.getPhone());
        });
    }


    void setInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        }else{
            progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}