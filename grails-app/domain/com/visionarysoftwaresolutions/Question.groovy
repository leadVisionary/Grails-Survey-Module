package com.visionarysoftwaresolutions

class Question {
    String text
    
    static hasMany = [ answers : Answer ]
    static constraints = {
        text blank : false, unique : true
    }
    
    public String toString(){ "${text} - ${answers}" }
}
