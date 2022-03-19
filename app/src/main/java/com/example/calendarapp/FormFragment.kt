package com.example.calendarapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        setHasOptionsMenu(true)
    }

    private fun initializeEditText(view: View) {
        val bundle = arguments ?: return
        val args = FormFragmentArgs.fromBundle(bundle)
        val eventData = args.eventData

        view.findViewById<EditText>(R.id.form_et_name)?.setText(eventData?.name)
        view.findViewById<EditText>(R.id.form_et_desc)?.setText(eventData?.desc)
        view.findViewById<EditText>(R.id.form_et_startTime)?.setText(eventData?.startTime)
        view.findViewById<EditText>(R.id.form_et_endTime)?.setText(eventData?.endTime)
        view.findViewById<EditText>(R.id.form_et_date)?.setText(eventData?.date)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        val etDate = view.findViewById<EditText>(R.id.form_et_date)
        val today = Calendar.getInstance().time
        val sdf = SimpleDateFormat("yyyy.MM.dd")
        etDate.hint = sdf.format(today)
        initializeEditText(view)
        return view
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

        val name = view.findViewById<EditText>(R.id.form_et_name).text.toString()
        val desc = view.findViewById<EditText>(R.id.form_et_desc).text.toString()
        val startTime = view.findViewById<EditText>(R.id.form_et_startTime).text.toString()
        val endTime = view.findViewById<EditText>(R.id.form_et_endTime).text.toString()
        val date = view.findViewById<EditText>(R.id.form_et_date).text.toString()
        val dh = DataHandler()
        val id = eventId ?: dh.lastId(context) + 1
        val ed = EventData(id, name, desc, startTime, endTime, date)

        if (eventId == null) {
            dh.append(view.context, ed)
        } else {
            dh.editEvent(view.context, ed)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormFragment.
         */
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
