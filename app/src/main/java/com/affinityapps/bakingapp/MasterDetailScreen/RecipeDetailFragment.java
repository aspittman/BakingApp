package com.affinityapps.bakingapp.MasterDetailScreen;

import android.content.Context;
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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Objects;

public class RecipeDetailFragment extends Fragment {

    private TextView fullDescriptions;
    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static final String ARG_ITEM_ID = "fragment_id";

    public RecipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        assert getArguments() != null;
        String finalDescription = getArguments().getString("descriptionTransfer");
        String finalVideoUrl = getArguments().getString("videoUrlTransfer");

        playerView = rootView.findViewById(R.id.baking_video_player);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity());
        playerView.setPlayer(simpleExoPlayer);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(Objects.requireNonNull(getActivity()),
                Util.getUserAgent(getActivity(), "appname"));
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(finalVideoUrl));

        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);

        fullDescriptions = rootView.findViewById(R.id.baking_full_descriptions);
        fullDescriptions.setText(finalDescription);

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}
