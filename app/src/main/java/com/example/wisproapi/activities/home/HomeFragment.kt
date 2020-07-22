package com.example.wisproapi.activities.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import androidx.viewpager.widget.ViewPager
import com.example.wisproapi.R
import com.example.wisproapi.activities.MyRecyclerViewAdapter
import com.example.wisproapi.viewmodels.PaymentsViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View.OnTouchListener
import com.example.wisproapi.OnSwipeTouchListener

class HomeFragment : Fragment() {

    var adapter: MyRecyclerViewAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.statuscode)

        val sceneRoot: ViewGroup = root.findViewById(R.id.scene_root)
        val aScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_a, this.requireContext())
        val anotherScene: Scene = Scene.getSceneForLayout(sceneRoot, R.layout.scene_b, this.requireContext())

        var fadeTransition: Transition = Fade()

        var fadeTransition2: Transition =
            TransitionInflater.from(this.requireContext())
                .inflateTransition(R.transition.fade_transition)

//        val a:ViewGroup = root.findViewById(R.id.scene_root)
//        val b:ViewGroup = a.findViewById(R.id.test111)
//        val c:ViewGroup = a.findViewById(R.id.test222)
//        sceneRoot.setOnClickListener {
//            TransitionManager.go(anotherScene, fadeTransition)
//        }

        //Transition listeners
        sceneRoot.setOnTouchListener(object: OnSwipeTouchListener(activity) {
            override fun onSwipeLeft() {
                TransitionManager.go(anotherScene, fadeTransition2)
            }
            override fun onSwipeRight() {
                TransitionManager.go(aScene, fadeTransition2)
            }
        })

//        sceneRoot.setOnClickListener {
//            TransitionManager.go(anotherScene, fadeTransition)
//        }
//
//        anotherScene.setEnterAction{
//            sceneRoot.setOnClickListener{
//                TransitionManager.go(aScene, fadeTransition)
//            }
//        }
//        aScene.setEnterAction{
//            sceneRoot.setOnClickListener{
//                TransitionManager.go(anotherScene, fadeTransition)
//            }
//        }

        val view_model: PaymentsViewModel by viewModels()
        view_model.get_live_payment().observe(viewLifecycleOwner, androidx.lifecycle.Observer { new->
            //SetupReciclerView
            val recyclerView: RecyclerView = root.findViewById(R.id.reciclerview_widget)
            recyclerView.layoutManager =
                LinearLayoutManager(this.context)
            adapter = MyRecyclerViewAdapter(this.context, view_model.livePayment.value!!.payments)
            recyclerView.adapter = adapter

            textView.append("Total: " + view_model.livePayment.value!!.total.toString())
        })
        return root
    }
}
