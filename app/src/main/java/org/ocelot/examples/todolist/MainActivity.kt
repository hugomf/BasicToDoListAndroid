package org.ocelot.examples.todolist

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), TaskFormFragment.OnAddTaskListener{


    var todoItems = arrayListOf<String>(
            "Item 1"," Item 2", "Item 3"
    )

    var todoFragment:Fragment = ToDoFragment()

    var taskFormFragment: Fragment = TaskFormFragment()
    var selectedFragment: Fragment = todoFragment

    private val mOnNavigationItemSelectedListener =  BottomNavigationView.OnNavigationItemSelectedListener{ item ->

        when (item.itemId) {
            R.id.navigation_add -> {
                this.selectedFragment = taskFormFragment
            }
            R.id.navigation_delete -> {
                this.selectedFragment = todoFragment
            }
        }
        selectView()
        true
    }

    private fun selectView() {

        val bundle = Bundle()
        bundle.putStringArrayList("todoItems", todoItems)
        this.selectedFragment?.arguments = bundle

        switchView(this.selectedFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        selectView()

    }

    override fun onAddTask(taskName: String) {
        todoItems.add(taskName)
        switchView(this.todoFragment)
    }

    override fun onCancelTTask() {
        switchView(this.todoFragment)
    }

    fun switchView(fragment: Fragment){
        this.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }


}
