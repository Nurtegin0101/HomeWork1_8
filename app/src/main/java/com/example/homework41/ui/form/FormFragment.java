package com.example.homework41.ui.form;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homework41.R;
import com.example.homework41.databinding.FragmentFormBinding;
import com.example.homework41.ui.App;

public class FormFragment extends Fragment {
    private FragmentFormBinding binding;

    public FormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        chekIsEdit();
        super.onViewCreated(view, savedInstanceState);
    }

    private void chekIsEdit() {
        if (getArguments() != null) {
            binding.etTask.setText(getArguments().getString("editText"));
            binding.btnSave.setOnClickListener(view -> {
                edit();
                close();
            });
        } else {
            binding.btnSave.setOnClickListener(view -> {
                save();
                close();
            });
        }
    }

    private void close() {
        NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        controller.navigateUp();
    }

    private void save() {
        Bundle bundle = new Bundle();
        String text = binding.etTask.getText().toString();
        FormModel model = new FormModel(text);
        App.db.noteDao().insertAllNote(model);
        bundle.putSerializable("text", model);
        getParentFragmentManager().setFragmentResult("key", bundle);
        getParentFragmentManager().popBackStack();
    }

    private void edit() {
        Bundle bundle = new Bundle();
        String text = binding.etTask.getText().toString();
        int id = getArguments().getInt("position");
        Log.e("----------", id + "");
        FormModel model = new FormModel(id, text);
        App.db.noteDao().upDateNote(model);
        bundle.putSerializable("editText", model);
        getParentFragmentManager().setFragmentResult("editNote", bundle);
        getParentFragmentManager().popBackStack();
    }
}
