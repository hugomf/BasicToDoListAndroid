package org.ocelot.examples.todolist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

class ToDoFragment() : Fragment() {

    override  fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view:View = inflater.inflate(R.layout.todo_fragment, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.todoListView)
        val todoItems = this.arguments?.getStringArrayList("todoItems")


        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = TodoListRecyclerViewAdapter(todoItems!!)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL));


        return view


    }



}

