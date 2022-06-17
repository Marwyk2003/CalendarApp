package com.example.calendarapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calendarapp.DataHandler
import com.example.calendarapp.R
import com.example.calendarapp.databinding.FragmentFormBinding
import com.example.calendarapp.models.EventData
import com.example.calendarapp.viewmodels.FormViewModel

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private val model: FormViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = model

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
        initializeForm()
    }

    private fun initializeForm() {
        model.dateHint.value = model.currentDate()
        val bundle = arguments ?: return
        val args = FormFragmentArgs.fromBundle(bundle)
        val eventData = args.eventData

        model.name.value = eventData?.name
        model.desc.value = eventData?.desc
        model.timeStart.value = eventData?.startTime
        model.timeEnd.value = eventData?.endTime
        model.date.value = eventData?.date
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                val curView = view
                if (curView != null) {
                    save(curView)
                    findNavController().navigateUp()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun save(view: View) {
        val bundle = arguments ?: return
        val args = FormFragmentArgs.fromBundle(bundle)
        val eventId = args.eventData?.id

        val dh = DataHandler()
        val id = eventId ?: (dh.lastId(context) + 1)
        val ed = EventData(
            id,
            model.name.value ?: "",
            model.desc.value ?: "",
            model.timeStart.value ?: "",
            model.timeEnd.value ?: "",
            model.date.value ?: ""
        )
        if (eventId == null) {
            dh.append(view.context, ed)
        } else {
            dh.editEvent(view.context, ed)
        }
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
