package com.andhikaaw.chordly

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andhikaaw.chordly.adapter.LaguAdapter
import com.andhikaaw.chordly.database.Lagu
import com.andhikaaw.chordly.database.LaguApp
import com.andhikaaw.chordly.database.LaguDao
import com.andhikaaw.chordly.databinding.FragmentHomeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Home : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding : FragmentHomeBinding
    private lateinit var dao: LaguDao
    lateinit var laguAdapter: LaguAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            binding = FragmentHomeBinding.inflate(layoutInflater)
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        dao = LaguApp.invoke(requireContext()).getLaguDao()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_home, container, false)
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val btnTambah : FloatingActionButton = view.findViewById(R.id.button_tambah)

        btnTambah.setOnClickListener{
            val intent = Intent(activity, LirikLagu::class.java)
            activity?.startActivity(intent)
        }
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        CoroutineScope(Dispatchers.IO).launch {
            val lagu = dao.getAllLagu()
            Log.d("home", "dbResponse: $lagu")
            withContext(Dispatchers.IO) {
                laguAdapter.setData(lagu)
            }
        }
    }

    private fun setupRecyclerView(){
        val list_lagu: RecyclerView? = view?.findViewById(R.id.list_lagu)
        laguAdapter = LaguAdapter(arrayListOf(), object : LaguAdapter.OnAdapterListener{
            override fun onClick(lirik: Lagu) {
//                Toast.makeText(context, lirik.judul, Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, LirikLagu::class.java)
                activity?.startActivity(intent)
            }


        })
        list_lagu?.apply{
            layoutManager = LinearLayoutManager(requireContext().applicationContext)
            adapter = laguAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}