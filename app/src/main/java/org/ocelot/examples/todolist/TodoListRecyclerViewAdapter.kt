package org.ocelot.examples.todolist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter
import kotlinx.android.synthetic.main.task_row.view.*

class TodoListRecyclerViewAdapter(val todoItems: MutableList<String>): RecyclerSwipeAdapter<CustomViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val taskRow = layoutInflater.inflate(R.layout.task_row, parent, false)
        return CustomViewHolder(taskRow)

    }

    override fun getItemCount(): Int {
        return todoItems.count()
    }


    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.title.text = todoItems.get(position)

        holder.buttonDelete.setOnClickListener(View.OnClickListener {

           // mItemManger.removeShownLayouts(holder.swipeLayout)
            todoItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,todoItems.count());
           // mItemManger.closeAllItems()
        })
    }


    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe;
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val swipeLayout: SwipeLayout =  view.findViewById(R.id.swipe)
    val buttonDelete: Button = view.findViewById(R.id.btnDelete)

}
