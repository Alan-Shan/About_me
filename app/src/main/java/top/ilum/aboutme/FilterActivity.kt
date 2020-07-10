package top.ilum.aboutme

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.filter_layout.*
import java.util.ArrayList

class FilterActivity : AppCompatActivity() {

    companion object {
        private const val KEY_FILTERS = "filters"
    }
    var checked = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.filter_layout)
        val intent = intent
        val possibleChecks = (intent.getIntegerArrayListExtra("checks"))
            ?.distinct() as ArrayList<Int>
        if (!(savedInstanceState?.getIntegerArrayList(KEY_FILTERS).isNullOrEmpty())) {
            checked = savedInstanceState?.getIntegerArrayList(KEY_FILTERS) as ArrayList<Int>
        }
        val myIntent = Intent(this, MainActivity::class.java)
        recycle_filters.layoutManager = LinearLayoutManager(this)
        recycle_filters.itemAnimator = DefaultItemAnimator()
        recycle_filters.adapter = FilterAdapter {
            if (it.isNullOrEmpty()) {
                checkboxall.isChecked = false
            }
            checkboxall.isChecked = possibleChecks.size ==  it.size
            checked = it
        }.apply { items = possibleChecks }
        checkboxall.setOnClickListener {
            if (checkboxall.isChecked) {
                (recycle_filters.adapter as FilterAdapter).apply { isSelectedAll = true }
            } else {
                (recycle_filters.adapter as FilterAdapter).apply { isSelectedAll = false }
            }
            checked.clear()
            (recycle_filters.adapter as FilterAdapter).notifyDataSetChanged()
        }
        if (checked.isNullOrEmpty()) {
            checkboxall.performClick()
            checked.addAll(possibleChecks)
        }
        button.setOnClickListener {
            myIntent.putExtra("key", checked)
            myIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(myIntent)
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList(KEY_FILTERS, checked)
        super.onSaveInstanceState(outState)
    }
}
