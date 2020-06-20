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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.Objects;

import static com.affinityapps.bakingapp.master.RecipeListActivity.EXTRA_DESCRIPTION;
import static com.affinityapps.bakingapp.master.RecipeListActivity.EXTRA_VIDEO_URL;

public class RecipeDetailFragment extends Fragment {

    private String description;
    private String videoUrl;
    private Button previousButton;
    private Button nextButton;
    private String finalDescription;
    private String finalVideoUrl;
    private Intent intent;
    private TextView fullDescriptions;
    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    public static final String ARG_ITEM_ID = "positionIdentifier";

    public RecipeDetailFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recipe_detail, container, false);

        intent = requireActivity().getIntent();
        finalDescription = intent.getStringExtra(EXTRA_DESCRIPTION);
        finalVideoUrl = intent.getStringExtra(EXTRA_VIDEO_URL);

        playerView = rootView.findViewById(R.id.baking_video_player);
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity());
        playerView.setPlayer(simpleExoPlayer);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(requireActivity(),
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
