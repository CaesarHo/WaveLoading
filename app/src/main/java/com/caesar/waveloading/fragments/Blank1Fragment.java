package com.caesar.waveloading.fragments;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.caesar.waveloading.R;
import com.caesar.waveloading.activitys.MainActivity;
import com.caesar.waveloading.weight.WaveDrawable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Blank1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Blank1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Blank1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private ImageView mImageView;
    private WaveDrawable mWaveDrawable;
    private SeekBar mLevelSeekBar;
    private SeekBar mAmplitudeSeekBar;
    private SeekBar mSpeedSeekBar;
    private SeekBar mLengthSeekBar;
    private RadioGroup mRadioGroup;

    private OnFragmentInteractionListener mListener;

    public Blank1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Blank1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Blank1Fragment newInstance(String param1, String param2) {
        Blank1Fragment fragment = new Blank1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank1, container, false);

        mImageView = (ImageView)view. findViewById(R.id.image);
        mWaveDrawable = new WaveDrawable(getActivity(), R.mipmap.ic_launcher);
        mImageView.setImageDrawable(mWaveDrawable);

        mLevelSeekBar = (SeekBar)view. findViewById(R.id.level_seek);
        mLevelSeekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveDrawable.setLevel(progress);
            }
        });

        mAmplitudeSeekBar = (SeekBar)view. findViewById(R.id.amplitude_seek);
        mAmplitudeSeekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveDrawable.setWaveAmplitude(progress);
            }
        });

        mLengthSeekBar = (SeekBar) view.findViewById(R.id.length_seek);
        mLengthSeekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveDrawable.setWaveLength(progress);
            }
        });

        mSpeedSeekBar = (SeekBar)view. findViewById(R.id.speed_seek);
        mSpeedSeekBar.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mWaveDrawable.setWaveSpeed(progress);
            }
        });

        mRadioGroup = (RadioGroup)view. findViewById(R.id.modes);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final boolean indeterminate = checkedId == R.id.rb_yes;
                setIndeterminateMode(indeterminate);
            }
        });
        setIndeterminateMode(mRadioGroup.getCheckedRadioButtonId() == R.id.rb_yes);

        ImageView imageView2 = (ImageView)view. findViewById(R.id.image2);
        WaveDrawable chromeWave = new WaveDrawable(getActivity(), R.mipmap.chrome_logo);
        imageView2.setImageDrawable(chromeWave);

        // Set customised animator here
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        chromeWave.setIndeterminateAnimator(animator);
        chromeWave.setIndeterminate(true);

        View view1 = view.findViewById(R.id.view);
        int color = getResources().getColor(R.color.colorAccent);
        WaveDrawable colorWave = new WaveDrawable(new ColorDrawable(color));
        view1.setBackground(colorWave);
        colorWave.setIndeterminate(true);

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void setIndeterminateMode(boolean indeterminate) {
        mWaveDrawable.setIndeterminate(indeterminate);
        mLevelSeekBar.setEnabled(!indeterminate);

        if (!indeterminate) {
            mWaveDrawable.setLevel(mLevelSeekBar.getProgress());
        }
        mWaveDrawable.setWaveAmplitude(mAmplitudeSeekBar.getProgress());
        mWaveDrawable.setWaveLength(mLengthSeekBar.getProgress());
        mWaveDrawable.setWaveSpeed(mSpeedSeekBar.getProgress());
    }

    private static class SimpleOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Nothing
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Nothing
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Nothing
        }
    }

    public static final String ACTION_ADD_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    private void addShortcut(String name) {
        Intent addShortcutIntent = new Intent(ACTION_ADD_SHORTCUT);

        // 不允许重复创建
        addShortcutIntent.putExtra("duplicate", false);// 经测试不是根据快捷方式的名字判断重复的
        // 应该是根据快链的Intent来判断是否重复的,即Intent.EXTRA_SHORTCUT_INTENT字段的value
        // 但是名称不同时，虽然有的手机系统会显示Toast提示重复，仍然会建立快链
        // 屏幕上没有空间时会提示
        // 注意：重复创建的行为MIUI和三星手机上不太一样，小米上似乎不能重复创建快捷方式

        // 名字
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 图标
        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(getActivity(), R.mipmap.ic_launcher));

        // 设置关联程序
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN);
        launcherIntent.setClass(getActivity(), MainActivity.class);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        addShortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launcherIntent);

        // 发送广播
        getActivity().sendBroadcast(addShortcutIntent);
    }
}
