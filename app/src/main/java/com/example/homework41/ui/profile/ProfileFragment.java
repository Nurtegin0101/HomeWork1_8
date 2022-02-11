package com.example.homework41.ui.profile;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.homework41.databinding.FragmentProfileBinding;
import com.example.homework41.ui.App;

public class ProfileFragment extends Fragment {

   // private static final int TAKE_PICTURE = 1;

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private ActivityResultLauncher<String> mGetContent;
    private Uri imageUri;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        binding.imgPhoto.setImageURI(App.prefs.getSave());
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.btnDelete.setOnClickListener(view -> {
            App.prefs.delete();
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Glide.with(getContext()).load(result).apply(RequestOptions.circleCropTransform()).into(binding.imgPhoto);
                binding.imgPhoto.setImageURI(result);
                App.prefs.save(result.toString());
            }
        });
    }

    public void initListener() {
        binding.imgPhoto.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
              /*  Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);*/
                mGetContent.launch("image/*");
            } else {
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        });

        binding.imgCamera.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
               /* File photo = new File(Environment.getExternalStorageDirectory(), "Pic.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photo));
                imageUri = Uri.fromFile(photo);*/
                startActivityForResult(intent, 1);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Glide.with(getContext()).load(bitmap).apply(RequestOptions.circleCropTransform()).into(binding.imgPhoto);
//            binding.imgPhoto.setImageBitmap(bitmap);
        }
    /*    if (requestCode == 1) {
            Uri selectedImage = imageUri;
            ContentResolver cr = getActivity().getContentResolver();
            Bitmap bitmap;
            try {
                bitmap = android.provider.MediaStore.Images.Media
                        .getBitmap(cr, selectedImage);
                Glide.with(getContext()).load(bitmap).apply(RequestOptions.circleCropTransform()).into(binding.imgPhoto);
                binding.imgPhoto.setImageBitmap(bitmap);
                App.prefs.save(selectedImage.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }

    /*  @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            ContentResolver contentResolver = getActivity().getContentResolver();
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = contentResolver.query(selectedImage,
                    filePathColumn, null, null, null);

            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            binding.imgPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            cursor.close();

        }
    }*/
    }

