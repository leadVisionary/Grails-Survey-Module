package com.visionarysoftwaresolutions

import grails.test.*

class AnswerTests extends GrailsUnitTestCase {
    Answer toTest
    
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidAnswerWithOnlyText() {
        toTest= new Answer(text:"You", isCorrect:true)
        Question question = new Question(text:"Who's awesome?")
        toTest.question = question
        mockForConstraintsTests(Answer, [toTest])
        assertTrue toTest.validate()
    }
    
    void testInvalidNonUniqueText(){
        Question question = new Question(text:"Who's awesome?")
        toTest = new Answer(text:"You", isCorrect:true, question: question)
        Answer another = new Answer(text:"You", isCorrect:true, question: question)
        mockForConstraintsTests(Answer, [toTest, another])
        assertFalse toTest.validate()
        assertFalse another.validate()
        assertEquals "unique", toTest.errors['text']
        assertEquals "unique", another.errors['text']
    }
    
    void testAnswerStringRepresentation(){
        Question question = new Question(text:"Who's awesome?")
        toTest = new Answer(text:"You", isCorrect:true, question: question)
        assertEquals "You - true", "${toTest}"
    }
}
