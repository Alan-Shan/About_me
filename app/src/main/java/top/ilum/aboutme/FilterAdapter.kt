package top.ilum.aboutme

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filter_item.view.*

class FilterAdapter(private val onClick: (position: ArrayList<Int>) -> Unit) : RecyclerView.Adapter<FilterAdapter.ViewHolder>() {
    var isSelectedAll = false
    var checked = ArrayList<Int>()
    var items: ArrayList<Int> = ArrayList<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.filter_item, parent, false))

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkc.text = when (items[position]) {
            0 -> "Менее года"
            1 -> "1 год"
            2 -> "2 года"
            3 -> "3 года"
            else -> "Более 3 лет"
        }
        if (isSelectedAll || checked.size == itemCount) {
            holder.checkc.isChecked = true
            checked.addAll(items)
            checked = checked.distinct() as ArrayList<Int>
        } else {
            holder.checkc.isChecked = false
            checked.remove(items[position])
        }
        holder.checkc.setOnClickListener {
            if (holder.checkc.isChecked) {
                checked.add(items[position])
            } else {
                checked.remove(items[position])
            }
            onClick(checked)
        }
    }

    inner class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        var checkc: CheckBox = root.checkbox
    }
}
