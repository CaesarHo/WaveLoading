package com.caesar.waveloading.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.caesar.waveloading.R;
import com.caesar.waveloading.weight.MSeekBar;
import com.caesar.waveloading.weight.WaveLoadingView;
import com.larswerkman.lobsterpicker.OnColorListener;
import com.larswerkman.lobsterpicker.sliders.LobsterShadeSlider;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Blank2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Blank2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Blank2Fragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mTextView1, mTextView2;
    private MSeekBar mSeekBar1, mSeekBar2;

    private WaveLoadingView mWaveLoadingView;
    private int checkedItem = 0;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.equals(mSeekBar1)) {
            mTextView1.setText("滑块一当前值为" + progress);
        } else if(seekBar.equals(mSeekBar2)) {
            mTextView2.setText("滑块二当前值为" + progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public Blank2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Blank2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Blank2Fragment newInstance(String param1, String param2) {
        Blank2Fragment fragment = new Blank2Fragment();
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
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);
        mWaveLoadingView = (WaveLoadingView) view.findViewById(R.id.waveLoadingView);
        // Sets the length of the animation, default is 1000.
        mWaveLoadingView.setAnimDuration(3000);
        view.findViewById(R.id.btn_3d).setOnClickListener(this);

        // Shape Type
        view.findViewById(R.id.tv_shape).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context, R.style.dialog_normal).setTitle("Shape Type").setSingleChoiceItems(
                        new String[]{"CIRCLE", "TRIANGLE", "SQUARE", "RECTANGLE"}, checkedItem,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                checkedItem = which;
                                switch (which) {
                                    case 0:
                                        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
                                        dialog.dismiss();
                                        break;
                                    case 1:
                                        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.TRIANGLE);
                                        dialog.dismiss();
                                        break;
                                    case 2:
                                        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.SQUARE);
                                        dialog.dismiss();
                                        break;
                                    case 3:
                                        mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.RECTANGLE);
                                        dialog.dismiss();
                                        break;
                                    default:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        }).show();
            }
        });

        // Animator
        ((CheckBox) view.findViewById(R.id.cb_animator_cancel_and_start)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mWaveLoadingView.cancelAnimation();
                } else {
                    mWaveLoadingView.startAnimation();
                }
            }
        });

        ((CheckBox) view.findViewById(R.id.cb_animator_pause_and_resume)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mWaveLoadingView.pauseAnimation();
                } else {
                    mWaveLoadingView.resumeAnimation();
                }
            }
        });

        // Top Title
        ((CheckBox) view.findViewById(R.id.cb_title_top)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mWaveLoadingView.setTopTitle("Top Title");
                } else {
                    mWaveLoadingView.setTopTitle("");
                }
            }
        });
        // Center Title
        ((CheckBox) view.findViewById(R.id.cb_title_center)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mWaveLoadingView.setCenterTitle("Center Title");
                } else {
                    mWaveLoadingView.setCenterTitle("");
                }
            }
        });
        // Bottom Title
        ((CheckBox) view.findViewById(R.id.cb_title_bottom)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mWaveLoadingView.setBottomTitle("Bottom Title");
                } else {
                    mWaveLoadingView.setBottomTitle("");
                }
            }
        });

        // Progress
        ((DiscreteSeekBar) view.findViewById(R.id.seekbar_progress)).setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mWaveLoadingView.setProgressValue(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

            }
        });

        // Border
        ((DiscreteSeekBar) view.findViewById(R.id.seekbar_border_width)).setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mWaveLoadingView.setBorderWidth(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        // Amplitude
        ((DiscreteSeekBar) view.findViewById(R.id.seek_bar_amplitude)).setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                mWaveLoadingView.setAmplitudeRatio(value);
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
            }
        });

        // Wave Color
        ((LobsterShadeSlider) view.findViewById(R.id.shadeslider_wave_color)).addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                mWaveLoadingView.setWaveColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });
        //Wave Background Color
        ((LobsterShadeSlider) view.findViewById(R.id.shadeslider_wave_background_color)).addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                mWaveLoadingView.setWaveBgColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });

        // Border Color
        ((LobsterShadeSlider) view.findViewById(R.id.shadeslider_border_color)).addOnColorListener(new OnColorListener() {
            @Override
            public void onColorChanged(@ColorInt int color) {
                mWaveLoadingView.setBorderColor(color);
            }

            @Override
            public void onColorSelected(@ColorInt int color) {
            }
        });


        mTextView1 = (TextView) view.findViewById(R.id.tv_main1);
        mTextView2 = (TextView) view.findViewById(R.id.tv_main2);
        mSeekBar1 = (MSeekBar) view.findViewById(R.id.sb_main1);
        mSeekBar2 = (MSeekBar) view.findViewById(R.id.sb_main2);
        mSeekBar1.setOnSeekBarChangeListener(this);
        mSeekBar2.setOnSeekBarChangeListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_3d:

                break;
        }
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
}
