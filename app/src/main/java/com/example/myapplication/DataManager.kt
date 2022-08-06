package com.example.myapplication

object DataManager {
    val courses = HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        intalizeCourses()
        intalizeNotes()
    }

    private fun intalizeCourses(){
        //dummy data for testing the app functionality
        var course = CourseInfo("oop","object oriented" )
        courses.set(course.courseID,course)

        course = CourseInfo(courseID = "ds", title = "data structures")
        courses.set(course.courseID,course)

        course = CourseInfo(title = "algorithms", courseID = "algos")
        courses.set(course.courseID,course)

        course = CourseInfo("math", "math 3")
        courses.set(course.courseID,course)
    }

    private fun intalizeNotes(){
        var course = courses["oop"]!! //retrieve the course info
        var note = NoteInfo(course,"Dr. X","It was a very important course taught by Dr. X")
        notes.add(note)

         course = courses["ds"]!! //retrieve the course info
         note = NoteInfo(course,"Grade ","I got in A in such a hard and important course")
        notes.add(note)

        course = courses["math"]!! //retrieve the course info
        note = NoteInfo(course,"Overall","Overall the math courses was one of the most enjoyable courses for me throughout my university years")
        notes.add(note)

    }
}