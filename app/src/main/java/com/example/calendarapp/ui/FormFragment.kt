package com.example.calendarapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.calendarapp.R
import com.example.calendarapp.databinding.FragmentFormBinding
import com.example.calendarapp.services.DataHandler
import com.example.calendarapp.viewmodels.FormViewModel
import java.lang.Error

class FormFragment : Fragment() {
    private lateinit var binding: FragmentFormBinding
    private val model: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFormBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewmodel = model
        setHasOptionsMenu(true)
        initializeForm()
    }

    private fun initializeForm() {
        model.dateHint.value = model.currentDate()
        val bundle = arguments ?: return
        val args = FormFragmentArgs.fromBundle(bundle)
        model.init(args.eventData)
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
        val id = eventId ?: (dh.lastId(context) + 1) // TODO
        model.eventData.value?.id = id
        model.setDate()
        val ed = model.eventData.value ?: throw Error("cannot save")
        if (eventId == null) dh.append(view.context, ed)
        else dh.editEvent(view.context, ed)
    }
}
