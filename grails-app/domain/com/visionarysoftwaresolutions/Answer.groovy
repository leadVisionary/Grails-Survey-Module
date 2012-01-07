package com.visionarysoftwaresolutions

class Answer {
    String text
    boolean isCorrect
    
    static belongsTo = [ question : Question ]
    
    static constraints = {
        text blank : false, unique : true
        isCorrect() 
    }
    
    public String toString(){ "${text} - ${isCorrect}" }
}
