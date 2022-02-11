package com.example.homework41.ui.onBoard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.homework41.R;
import com.example.homework41.databinding.FragmentBoardBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;
    private BoardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
        new TabLayoutMediator(binding.tabLayout, binding.boardPager, ((tab, position) -> tab.setIcon(R.drawable.tab_selector))).attach();
    }

    private void initViewPager() {
        adapter = new BoardAdapter();
        binding.boardPager.setAdapter(adapter);

        adapter.listener = () -> {
            NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            controller.navigateUp();
        };
    }
}