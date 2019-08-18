package com.oniktech.mixnote.fragments

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oniktech.mixnote.MainActivity

import com.oniktech.mixnote.R
import java.io.IOException
import java.lang.RuntimeException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val LOG_TAG = "AudioRecordTest"

class ButtonsFragment : Fragment() {


    private var isPause: Boolean = true
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null

    private var mListener: OnFragmentInteractionListener? = null

    private var fileName: String = ""
    
    private lateinit var stopButton: ImageButton
    private lateinit var startPauseButton: ImageButton

    var isRecording = false
    var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
        

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment1
        val view: View = inflater.inflate(R.layout.fragment_buttons, container, false)


        checkExternalStorage()

        //fileName = "${view.context.externalCacheDir.absolutePath}/audiorecordtest2.3gp"
        val dateFormat = SimpleDateFormat("yyyyMMddHHmmss")

        Toast.makeText(context, dateFormat.format(Date()) , Toast.LENGTH_SHORT).show()
        fileName = Environment.getExternalStorageDirectory().absolutePath + "/mixnote/${dateFormat.format(Date())}.3gp"
        startPauseButton = view.findViewById(R.id.start_pause_recording_button)
        stopButton = view.findViewById(R.id.stop_recording_button)

        onClicks()
        
        return view
    }

    private fun checkExternalStorage() {
        val externalStorageState = Environment.getExternalStorageState()
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            val storageDirectory = Environment.getExternalStorageDirectory().absoluteFile.toString()

        }
    }

    private fun onClicks() {
        startPauseButton.setOnClickListener{
            Log.i("mylog" , "nakon")

            isRecording = true
            isPause = !isPause
            onRecording(isRecording)
            //Toast.makeText(context , fileName , Toast.LENGTH_LONG).show()


        }
        
        stopButton.setOnClickListener(){
            isRecording = false
            isPause = true
            stopRecording()
        }
    }

    private fun onRecording(isRecording: Boolean) = if (isRecording) {
        startRecording()
        Toast.makeText(context , "بنال" , Toast.LENGTH_SHORT).show()
        
    } else {
        pauseRecording()
        Toast.makeText(context , "یه دیقه زر نزن" , Toast.LENGTH_SHORT).show()
    }

    private fun pauseRecording() {
        recorder?.apply { 
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                pause()
                
            }
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
                Toast.makeText(context , "Unable to prepare recorder" , Toast.LENGTH_SHORT).show()
            }

            //start()
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        if (mListener != null) {
            mListener!!.onFragmentInteraction()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            mListener = context
        } else{

        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance(): ButtonsFragment {
            val fragment = ButtonsFragment()
            return fragment
        }
    }
}// Required empty public constructor
