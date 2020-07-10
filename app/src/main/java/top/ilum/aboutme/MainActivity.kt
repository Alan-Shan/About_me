package top.ilum.aboutme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var possiblechecks = ArrayList<Int>()
    private var infolist = arrayListOf(
        Me.HeaderMe("Михаил Лунев", "11 класс", "https://github.com/Alan-Shan"),
        Me.Project("Мой проект", "Информация обо мне"),
        Me.HeaderItem("Навыки"),
        Me.Skill("C#", 4),
        Me.Skill("PHP#", 3),
        Me.Skill("HTML/CSS", 3),
        Me.Skill("Python", 1),
        Me.Skill("Kotlin", 0)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = Adapter().apply { infoList = infolist }
            .apply { possibleChecks = possiblechecks }
        if (possiblechecks.isNullOrEmpty()) {
            infolist.forEach {
                if (it is Me.Skill) {
                    possiblechecks.add(it.experience)
                }
            }
        }
        possiblechecks = possiblechecks.distinct() as ArrayList<Int>
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        val value = intent.getIntegerArrayListExtra("key")
        if (value != null) {
            infolist = checker(infolist, value) as ArrayList<Me>
            (recycler.adapter as Adapter).apply { filterActive = true }
                .updateData(infolist)
            if (value.size == possiblechecks.size) {
                (recycler.adapter as Adapter).apply { filterActive = false }.notifyDataSetChanged()
            }
        }
    }

    private fun checker(checkList: ArrayList<Me>, condition: ArrayList<Int>): List<Me> =
        checkList.filter {
            (it is Me.Skill && it.experience in condition) || it !is Me.Skill
        }
}
