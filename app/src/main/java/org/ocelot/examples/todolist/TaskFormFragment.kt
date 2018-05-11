package org.ocelot.examples.todolist

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TaskFormFragment() : Fragment() {

    private var onAddTaskCallback : TaskFormFragment.OnAddTaskListener? = null

    interface OnAddTaskListener {
        fun onAddTask(taskName: String)
        fun onCancelTTask()
    }

    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View = inflater.inflate(R.layout.taskform_fragment, container, false)
        val txtTaskName  = view.findViewById<EditText>(R.id.txtTaskName)


        val btnAdd  = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener{
            val taskName = txtTaskName.text.toString()
            if(taskName.isBlank()) {
                showAlert("Task Name is Mandatory!", container)
            } else {
                //print("HOLAAAAAAAA")
                this.onAddTaskCallback?.onAddTask(taskName)
            }
        }

        val btnCancel = view.findViewById<Button>(R.id.btnCancel)
        btnCancel.setOnClickListener({
            this.onAddTaskCallback?.onCancelTTask()
        })


        return view
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            this.onAddTaskCallback = context as OnAddTaskListener
        } catch (e: ClassCastException){
            throw ClassCastException(context?.toString() + " must implement OnAddTaskListener")
        }
    }

    private fun showAlert(message: String, container: ViewGroup?) {

        var alertDialog = AlertDialog.Builder(container?.context!!)
        alertDialog.setTitle("Alert");
        alertDialog.setMessage(message);
        alertDialog.setNeutralButton("Ok"){_,_ ->
            Toast.makeText(container.context,"You cancelled the dialog.",Toast.LENGTH_SHORT).show()
        }
        alertDialog.show();

    }



}