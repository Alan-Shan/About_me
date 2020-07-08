package top.ilum.aboutme

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.headerme_item.view.*
import kotlinx.android.synthetic.main.project_item.view.*
import kotlinx.android.synthetic.main.skill_item.view.*

class Adapter(private val infoList: List<Me>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        when (infoList[position]) {
            is Me.HeaderMe -> INFO_ME
            is Me.Project -> PROJECT
            is Me.HeaderItem -> HEADER
            is Me.Skill -> SKILL
        }
    override fun getItemCount(): Int =
        infoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == INFO_ME) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.headerme_item, parent, false)
            return ViewHolderMe(view)
        } else if (viewType == PROJECT) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.project_item, parent, false)
            return ViewHolderProject(view)
        } else if (viewType == HEADER) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.header_item, parent, false)
            return ViewHolderHeader(view)
        } else if (viewType == SKILL) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.skill_item, parent, false)
            return ViewHolderSkill(view)
        } else {
            throw RuntimeException("Incorrect type!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            INFO_ME -> initLayoutMe(holder as ViewHolderMe, position)
            PROJECT -> initLayoutProject(holder as ViewHolderProject, position)
            HEADER -> initLayoutHeader(holder as ViewHolderHeader, position)
            SKILL -> initLayoutSkill(holder as ViewHolderSkill, position)
            else -> {
            }
        }
    }

    internal class ViewHolderMe(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myName: TextView = itemView.myName
        var myGrade: TextView = itemView.myGrade
        var myGit: Button = itemView.myGitBtn
    }

    private fun initLayoutMe(holder: ViewHolderMe, pos: Int) {
        val item = infoList[pos] as Me.HeaderMe
        holder.myName.text = item.name
        holder.myGrade.text = item.grade
        holder.myGit.setOnClickListener {
            val url = item.github
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(it.context, intent, null)
        }
    }

    internal class ViewHolderProject(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header: TextView = itemView.header
        var description: TextView = itemView.description
    }

    private fun initLayoutProject(holder: ViewHolderProject, pos: Int) {
        val item = infoList[pos] as Me.Project
        holder.header.text = item.title
        holder.description.text = item.info
    }

    internal class ViewHolderHeader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var header: TextView = itemView.header
    }

    private fun initLayoutHeader(holder: ViewHolderHeader, pos: Int) {
        val item = infoList[pos] as Me.HeaderItem
        holder.header.text = item.header
    }

    internal class ViewHolderSkill(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.name
        var experience: TextView = itemView.experience
    }

    private fun initLayoutSkill(holder: ViewHolderSkill, pos: Int) {
        val item = infoList[pos] as Me.Skill
        val experience = when (item.experience) {
            0 -> "Меньше 1 года"
            1 -> "1 год"
            2 -> "2 года"
            3 -> "3 года"
            4 -> "Более 3 лет"
            else -> ""
        }
        holder.name.text = item.name
        holder.experience.text = experience
    }

    companion object {
        private const val INFO_ME = 1
        private const val PROJECT = 2
        private const val HEADER = 3
        private const val SKILL = 4
    }
}
