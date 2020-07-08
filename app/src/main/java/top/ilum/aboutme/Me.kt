package top.ilum.aboutme

sealed class Me {
    class HeaderMe(var name: String, var grade: String, var github: String) : Me()
    class HeaderItem(var header: String) : Me()
    class Project(var title: String, var info: String) : Me()
    class Skill(var name: String, var experience: Int) : Me()
}
