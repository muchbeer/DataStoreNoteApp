package com.muchbeer.datastorenoteapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.muchbeer.datastorenoteapp.R
import com.muchbeer.datastorenoteapp.data.Task
import com.muchbeer.datastorenoteapp.data.TaskPriority
import com.muchbeer.datastorenoteapp.databinding.ListItemBinding

class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskVH>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskVH {

            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ListItemBinding.inflate(layoutInflater, parent, false)
            return TaskVH(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskVH, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bindData(repoItem)
        }
    }

    class TaskVH(val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
            fun bindData(task: Task) {
                binding.priority.text = itemView.context.resources.getString(
                    R.string.priority_value,
                    task.priority.name
                )
                // set the priority color based on the task priority
                val textColor = when (task.priority) {
                    TaskPriority.HIGH -> R.color.red
                    TaskPriority.MEDIUM -> R.color.yellow
                    TaskPriority.LOW -> R.color.green
                }
                binding.priority.setTextColor(ContextCompat.getColor(itemView.context, textColor))
            }
    }

  companion  object diffUtil : DiffUtil.ItemCallback<Task>()  {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.name ==newItem.name
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem ==newItem   }
    }

}

