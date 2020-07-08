package top.ilum.aboutme

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val infoList = listOf(
            Me.HeaderMe("Михаил Лунев", "11 класс", "https://github.com/Alan-Shan"),
            Me.Project("Мой проект", "Информация обо мне"),
            Me.HeaderItem("Навыки"),
            Me.Skill("C#", 4),
            Me.Skill("PHP#", 3),
            Me.Skill("HTML/CSS", 3),
            Me.Skill("Python", 1),
            Me.Skill("Kotlin", 0)
        )
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.itemAnimator = DefaultItemAnimator()
        recycler.adapter = Adapter(infoList)
    }
}
