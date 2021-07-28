package com.peerbitskuldeep.retrofitpl.adapter

//import androidx.recyclerview.widget.AsyncListDiffer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.peerbitskuldeep.retrofitpl.Todo
import com.peerbitskuldeep.retrofitpl.databinding.ItemTodoBinding

class RecAdapter: RecyclerView.Adapter<RecAdapter.RecViewHolder>() {

    inner class RecViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var todos : List<Todo>
    get() = differ.currentList
        set(value) { differ.submitList(value)}



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecViewHolder {
        return RecViewHolder(ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            textView.text = todo.title
            checkBox.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }



}