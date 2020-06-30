package com.affinityapps.bakingapp.master;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.affinityapps.bakingapp.R;
import com.affinityapps.bakingapp.databinding.FragmentRecipeDetailBinding;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class RecipeDetailFragment extends Fragment {

    private SimpleExoPlayer simpleExoPlayer;
    private FragmentRecipeDetailBinding binding;
    public static final String DESCRIPTION_ID = "descriptionIdentifier";
    public static final String VIDEO_URL_ID = "videoUrlIdentifier";


    public RecipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        assert getArguments() != null;
        String finalDescription = getArguments().getString("descriptionIdentifier");
        String finalVideoUrl = getArguments().getString("videoUrlIdentifier");

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity());
        binding.bakingVideoPlayer.setPlayer(simpleExoPlayer);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(requireActivity(),
                Util.getUserAgent(getActivity(), "appname"));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(finalVideoUrl));

        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);

        binding.bakingFullDescriptions.setText(finalDescription);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
